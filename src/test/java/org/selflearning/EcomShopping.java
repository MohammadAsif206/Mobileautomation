package org.selflearning;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.awt.*;
import java.security.Key;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EcomShopping extends CommonClass {

    @Test
    @Ignore
    public void fillTheForm() throws InterruptedException {
        WebElement nameField = driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
        nameField.sendKeys("Mohammad Asif");
        WebElement gender = driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale"));
        Assert.assertEquals("Female", gender.getText());
        gender.click();
        // Assert.assertEquals(gender.getAttribute("Selected").equals("true"),"true");
        WebElement countryList = driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
        countryList.click();
        WebElement specificCountry =
                driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Zimbabwe\"));"));


        scrollOperations(specificCountry, "Down");
        specificCountry.click();
        Assert.assertEquals("Zimbabwe", specificCountry.getText());
        WebElement shop = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
        shop.click();
        Thread.sleep(5000);
        String shoppingListTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
        Assert.assertEquals("Products", shoppingListTitle);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        // print the list of all countries
        countryList = driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
        countryList.click();
        List<WebElement> nameOfCountries;
        boolean isScrollable = false;
        boolean check;
        Set<String> countreis = new HashSet<>();
        System.out.println("LIST OF COUNTRIES: ");
        do {

            Thread.sleep(5000);
            nameOfCountries = driver.findElements(By.className("android.widget.TextView"));
            int size = nameOfCountries.size();
            for (int i = 0; i < size; i++) {
                String country = nameOfCountries.get(i).getText();
                System.out.print(" " + country);
                countreis.add(country);
            }
            check = isScrollable(isScrollable);
            scrollOperations(driver.findElement(By.className("android.widget.TextView")), "down");
            Thread.sleep(7000);
        } while (check == true);
        nameOfCountries = driver.findElements(By.className("android.widget.TextView"));
        int size = nameOfCountries.size();
        for (int i = 0; i < size; i++) {
            String country = nameOfCountries.get(i).getText();
            System.out.print(" " + country);
            countreis.add(country);
        }

        System.out.println("ALL LIST: ");
        for (String set : countreis) {
            System.out.print(" " + set);
        }


        System.out.println("App launched: ");
        Thread.sleep(20000);
    }

    @Test
    public void fillTheFormWithoutNameProvided() throws InterruptedException {
        WebElement nameField = driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
        WebElement shop = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
        shop.click();
        //for toast message there is a mandatory standard tag name. android.widget.Toast;
        String msg = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getText();
        System.out.println("THE TOAST MESSAGE IS: " + msg);
        // Handle Native App and Web APP
        nameField = driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
        nameField.sendKeys("Khadija Asif");
        shop = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
        shop.click();
        List<WebElement> prod = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart"));
        prod.get(0).click();
        prod.get(1).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        WebElement box = driver.findElement(By.className("android.widget.CheckBox"));
        System.out.println("CHECKED VALUE BEFORE SELECTION: " + box.getAttribute("checked"));
        String actual = box.getText();
        box.click();
        Assert.assertEquals(actual, "Send me e-mails on discounts related to selected products in future");
        System.out.println("CHECKED VALUE AFTER SELECTION: " + box.getAttribute("checked"));
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("hplogo"))));
        // Here on word context switch is needed as we navigate from Native mode to Web mode
        Thread.sleep(20000);
        Set<String> context = driver.getContextHandles();
        for (String handale : context) {
            System.out.println(handale);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");
        WebElement googleSearch = driver.findElement(By.name("q"));
        googleSearch.sendKeys("Google Map");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        // driver.get("www-qa2.safeway.com");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(6000);
        driver.context("NATIVE_APP");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"))));
        System.out.println("GENERAL STORE LOGiN PAGE: " + driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText());


    }

    @Test
    @Ignore
    public void doShop() throws InterruptedException {
        WebElement nameField = driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
        nameField.sendKeys("Khadija Asif");
        WebElement gender = driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale"));
        Assert.assertEquals("Female", gender.getText());
        gender.click();
        WebElement shop = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
        shop.click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
        int size = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        String productName;
        float total = 0;
        for (int i = 0; i < size; i++) {
            productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

            if (productName.equals("Jordan 6 Rings")) {
                String val = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
                String str;
                str = val.substring(1);
                total += Float.parseFloat(str);

                WebElement addToCart = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i);
                System.out.println("TEXT ON PRODUCT BUTTON BEFORE ADDING: " + addToCart.getText());
                Assert.assertEquals(addToCart.getText(), "ADD TO CART");
                addToCart.click();
                WebElement addedToCart = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i);
                System.out.println("TEXT ON PRODUCT BUTTON AFTER ADDING: " + addedToCart.getText());
                Assert.assertEquals(addedToCart.getText(), "ADDED TO CART");
            }
            if (productName.equals("Jordan Lift Off")) {
                String val = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                String str;
                str = val.substring(1);
                total += Float.parseFloat(str);
            }
        }
        System.out.println("Price of the added product from product page: $ " + total);
        // String actual =

        System.out.println("THE PRICE OF THE INDIVIDUAL PRODUCTS: " + total);
        WebElement cartCount = driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart"));
        String count = cartCount.getText();
        System.out.println("CART COUNT IS: " + count);
        cartCount.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // WebElement expectedElement = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        productName = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(productName, "Jordan 6 Rings");
        String totalAmounTitle = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Total Purchase Amount:  \"]")).getText();
        String totalPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        System.out.println(" Expected Value: " + totalAmounTitle + total + " Actual: " + totalAmounTitle + totalPrice);
        Assert.assertEquals((totalAmounTitle + "$ " + total), (totalAmounTitle + totalPrice));


    }
}
