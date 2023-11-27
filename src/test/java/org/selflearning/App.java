package org.selflearning;

import io.appium.java_client.AppiumBy;
<<<<<<< HEAD
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Alert;
=======
>>>>>>> a661bcb9ba291fdb7c50c451b8b164b43e44b6a7
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
<<<<<<< HEAD
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.event.MouseEvent;
=======
import org.testng.annotations.Test;

>>>>>>> a661bcb9ba291fdb7c50c451b8b164b43e44b6a7
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App extends CommonClass{

    @Test
<<<<<<< HEAD
    public void appOptionTest() throws InterruptedException {
=======
    public void appOptionTest(){
>>>>>>> a661bcb9ba291fdb7c50c451b8b164b43e44b6a7
//        WebElement app = fluentWaitForElement(driver,By.xpath("//android.widget.TextView[@content-desc=\"App\"]"),
//                Duration.ofSeconds(30), Duration.ofSeconds(5));
        WebElement app = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"App\"]"));
        app.click();
        driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
        List<WebElement> listOfAllerts = driver.findElements(By.className("android.widget.Button"));
        int size = listOfAllerts.size();
<<<<<<< HEAD
        for(int i = 0; i<size; i++){
            System.out.println("Value of the list: "+listOfAllerts.get(i).getText());
        }
        //Set<String> elements = new HashSet<>();
       // String text;
        for(int i = 0; i<size; i++){

            Thread.sleep(10000);
           String text = listOfAllerts.get(i).getText();
            System.out.println("I VALUE, ITERATED: "+i);
           String actualVal;
            String expectedVal;
            Alert alert;
           if(text.equals("OK Cancel dialog with a message")){
               expectedVal = "Lorem ipsum dolor sit aie consectetur adipiscing\n" +
                       "Plloaso mako nuto siwuf cakso dodtos anr koop.";
               listOfAllerts.get(i).click();
               alert = driver.switchTo().alert();
              actualVal = driver.findElement(By.id("android:id/alertTitle")).getText();
               Assert.assertEquals(actualVal,expectedVal);
               driver.findElement(By.id("android:id/button1")).click();

           }else if(text.equals("OK Cancel dialog with a long message")){
               expectedVal = "hus";
               listOfAllerts.get(i).click();
               alert = driver.switchTo().alert();
               actualVal = driver.findElement(By.id("android:id/message")).getText();
               Assert.assertTrue(actualVal.contains(expectedVal));
               driver.findElement(By.id("android:id/button2")).click();

           }else if(text.equals("OK Cancel dialog with ultra long message")){
               expectedVal = "Whag schengos";
               listOfAllerts.get(i).click();
               alert = driver.switchTo().alert();
               actualVal = driver.findElement(By.id("android:id/message")).getText();
               Assert.assertTrue(actualVal.contains(expectedVal));
               driver.findElement(By.id("android:id/button3")).click();

           }else if(text.equals("List dialog")){
               expectedVal = "Command three";
               listOfAllerts.get(i).click();
               alert = driver.switchTo().alert();

               List<WebElement> list = driver.findElements(By.className("android.widget.TextView"));
               Assert.assertEquals(list.get(3).getText(),expectedVal);
               list.get(3).click();
              // driver.findElement(By.id("android:id/button3")).click();
               String element = driver.findElement(By.id("android:id/message")).getText();
               Assert.assertEquals("You selected: 2 , Command three",element);
               driver.pressKey(new KeyEvent(AndroidKey.BACK));

           }else if(text.equals("Progress dialog")){
               Thread.sleep(1000);
               listOfAllerts.get(i).click();
              // alert = driver.switchTo().alert();
              // Thread.sleep(1000);
             //  driver.pressKey(new KeyEvent(AndroidKey.TAB));
             //  driver.pressKey(new KeyEvent(AndroidKey.TAB));
            //   Thread.sleep(1000);
            //   driver.pressKey(new KeyEvent(AndroidKey.ENTER));
               Thread.sleep(10000);
           }else if(text.equals("Single choice list")){
               listOfAllerts.get(i).click();
                actualVal = "Single choice list";
                expectedVal = driver.findElement(By.id("android:id/alertTitle")).getText();
                Assert.assertEquals(actualVal,expectedVal);
                List<WebElement> optionList = driver.findElements(By.className("android.widget.CheckedTextView"));
                int listSize = optionList.size();
               System.out.print("Single List :");
               for(int j = 0; j<listSize; j++){
                    System.out.print(" "+optionList.get(j).getText());
                   System.out.println("  "+optionList.get(j).getAttribute("selected"));
                   if(optionList.get(j).getAttribute("selected").equals(false)){
                       System.out.println("Before selection: "+optionList.get(j).getAttribute("selected"));
                       optionList.get(j).click();
                       System.out.println(" After selection: "+optionList.get(j).getAttribute("selected"));

                   }
                }
               driver.findElement(By.id("android:id/button1")).click();
           }else if(text.equals("Repeat alarm")){
               actualVal = "Repeat alarm";
               listOfAllerts.get(i).click();
               driver.switchTo().alert();
               WebElement webElement = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/alertTitle\"]"));
               Assert.assertEquals(actualVal,webElement.getText());
               webElement.click();
               List<WebElement> selectionOptions = driver.findElements(By.className("android.widget.CheckedTextView"));
               int size2=0;
               for(int k=0;k<size2;k++){
                   String optionsText = selectionOptions.get(k).getText();
                   String [] splitText = optionsText.split(" ");
                   if(splitText[1].charAt(0) != 'S'){
                       if(selectionOptions.get(k).getAttribute("checked").equals(false)){
                           selectionOptions.get(k).click();
                       }
                   }
               }


           }else if(text.equals("Send Call to VoiceMail")){

           }else if(text.equals("Text Entry dialog")){

           }else if(text.equals("OK Cancel dialog with traditional theme")){

           }else if(text.equals("OK Cancel dialog with Holo Light theme")){

           }else {
               System.out.println("Index "+i+" has this "+listOfAllerts.get(i).getText()+" Unwanted value");

           }

        }

=======
        //Set<String> elements = new HashSet<>();
        for(int i = 0; i<size; i++)
            System.out.println(listOfAllerts.get(i).getText());
>>>>>>> a661bcb9ba291fdb7c50c451b8b164b43e44b6a7

    }
}
