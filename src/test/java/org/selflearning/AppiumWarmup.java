package org.selflearning;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.app.ActivityOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppiumWarmup extends CommonClass{

    @Test @Ignore
    public void kickstartTest() throws MalformedURLException, InterruptedException {
        WebElement el = driver.findElement(AppiumBy.accessibilityId("Preference"));
        System.out.println("The value is: "+el.getText());
        System.out.println("Is value clicked? "+el.isDisplayed());
        el.click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        // For rotating the screen
        DeviceRotation landScapeMode = new DeviceRotation(0,0,90);
        driver.rotate(landScapeMode);
        WebElement wifiSetting = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"WiFi settings\"]"));
        Assert.assertEquals("WiFi settings", wifiSetting.getText());
        wifiSetting.click();
        //paste from clipboard
        driver.setClipboardText("Mohammad WiFi");
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        DeviceRotation portraitMode = new DeviceRotation(0,0,0);
        driver.rotate(portraitMode);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        String perf3 = driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).getText();
        Assert.assertTrue(perf3.contains("3"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        String pref = driver.findElement(AppiumBy.accessibilityId("Preference")).getText();
        Assert.assertEquals("Preference",pref);


    }
    @Test @Ignore
    public void longPress() throws InterruptedException {
        boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", 100, "top", 100, "width", 200, "height", 200,
                "direction", "down",
                "percent", 3.0,"duration",2000
        ));
        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement longClick = driver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]"));
        longClick.click();
        Thread.sleep(5000);
        //longClick.clear();


        List<WebElement> elements = driver.findElements(By.className("android.widget.TextView"));
        ArrayList<String> arrayOfElement = new ArrayList<>();
        int size = elements.size();
        System.out.println("Value of the list is: ");
        for(int i=0; i<size;i++){
            arrayOfElement.add(elements.get(i).getText());
            System.out.println(elements.get(i).getText());
        }
        System.out.println("THIS IS SOME VALUE HERE: "+arrayOfElement.size());
        for(int j=0; j<size;j++){
            System.out.print(" "+arrayOfElement.get(j).toString());
        }

        WebElement longClick1 = driver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]"));
        longPressOperation(longClick1);
        WebElement sampleMenu = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\"" +
                "and @text=\"Sample menu\"]"));
        Assert.assertEquals("Sample menu",sampleMenu.getText());
        WebElement sampleAction = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\"" +
                "and @text=\"Sample action\"]"));
        Assert.assertEquals("Sample action",sampleAction.getText());

    }
    @Test @Ignore
    public void scrollOperation() throws InterruptedException {
        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        boolean isScrollable = false;
        boolean check;
        List<WebElement> elements;
        Set<String> arrayOfElement = new HashSet<>();
//        List<WebElement> elements = driver.findElements(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())." +
//                "scrollIntoView(text(\"WebView\"));"));
        do{
            elements = driver.findElements(AppiumBy.className("android.widget.TextView"));
            Thread.sleep(10000);
            int size = elements.size();
            System.out.println("Value of the list is: "+size);
            for(int i=0; i<size;i++){
                arrayOfElement.add(elements.get(i).getText());
                System.out.println(elements.get(i).getText());
            }
           check =isScrollable(isScrollable);
           Thread.sleep(7000);
        } while (check);
        elements = driver.findElements(AppiumBy.className("android.widget.TextView"));
        int size = elements.size();
        for(int i=0; i<size;i++){
            arrayOfElement.add(elements.get(i).getText());
            System.out.println(elements.get(i).getText());
        }


     //   List<WebElement> elements = driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.TextView\");"));


       int size1 = arrayOfElement.size();
        System.out.println("THIS IS SOME VALUE HERE: "+arrayOfElement.size());
        for(int j=0; j<size1;j++){
            System.out.print(" "+arrayOfElement.size());
        }
    }
    @Test @Ignore
    public void swipeOperation() throws InterruptedException {
        Thread.sleep(20000);
        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        WebElement gallery = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Gallery\"]"));
        Assert.assertEquals("Gallery",gallery.getText());
        Assert.assertTrue(gallery.isDisplayed());
        gallery.click();
        WebElement photo = driver.findElement(AppiumBy.accessibilityId("1. Photos"));
        Assert.assertEquals("1. Photos",photo.getText());
        photo.click();
        String el = driver.findElement(By.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[1]")).getAttribute("focusable");
        Assert.assertEquals("true",el);
        // Java
        WebElement firstImage = driver.findElement(By.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[2]"));

        Thread.sleep(20000);
        swipeOperation(firstImage,"left");
        String el1 = driver.findElement(By.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[1]")).getAttribute("focusable");

        Assert.assertEquals(el1,"false");
        Thread.sleep(20000);
    }
    @Test @Ignore
    public void dragAndDropOperation() throws InterruptedException {
        Thread.sleep(10000);
        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement from = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        int [] coordinate = {611,616};
        dragDropOperation(from,coordinate);
        Thread.sleep(6000);
        String actual = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(actual,"Dropped!");

    }
    @Test
    public void navigateDirectlyToPage() throws InterruptedException {
        //Run the command in cmd: adb shell dumpsys window | find "mCurrentFocus"
        String packageName = "io.appium.android.apis";
        String activityName = "io.appium.android.apis.os.SmsMessagingDemo";
        Activity activity = new Activity(packageName,activityName);


        Thread.sleep(20000);

    }
}
