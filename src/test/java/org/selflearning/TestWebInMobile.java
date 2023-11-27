package org.selflearning;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.Set;

public class TestWebInMobile extends BrowserFactory {
    public Duration timeOut = Duration.ofSeconds(20);
    public Duration polling = Duration.ofSeconds(5);

    @Test
    public void signInToSWY() throws InterruptedException {
        Set<String> context = driver.getContextHandles();
        for (String str : context) {
            System.out.println("FEAT THE " + str);
        }
        navigateToHomePage();
       // waitForElement(By.xpath("//*[@data-qa='hdr-accnt-icn']"), timeOut, polling).click();
        waitForElement(By.className("menu-nav__profile-button"), timeOut, polling).click();
        int check = 0;
        try {
            driver.findElement(By.id("sign-in-modal-link")).click();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found. Continuing with the test.");
            Thread.sleep(7000);
            //driver.pressKey(new KeyEvent(Keys.));
            driver.navigate().back();
            waitForElement(By.className("menu-nav__profile-button"), timeOut, polling).click();
            waitForElement(By.id("sign-in-modal-link"), timeOut, polling).click();
        }
        waitForElement(By.id("label-email"), timeOut, polling).sendKeys("dallas.city@yopmail.com");
        waitForElement(By.id("label-password"), timeOut, polling).sendKeys("12344321");
        waitForElement(By.id("btnSignIn"), timeOut, polling).click();
        String homePage = waitForElement(By.className("menu-nav__center-logo"), timeOut, polling).getAttribute("alt");
        System.out.println("Check the alt val if it serves as text: "+ homePage);
        System.out.println("* is given to get any attributes val which matches the text ");
        Assert.assertEquals(homePage, "Safeway");
        waitForElement(By.className("menu-nav__hamburger-button"), timeOut, polling).click();
        waitForElement(By.xpath("//*[@id=\"shopFlyOutModal\"]/div/div/div/nav/div/ul/span[2]/li[2]/a"), timeOut, polling).click();
        //Quick Links
//        WebElement elementToScrollTo = waitForElement(By.id("Quick Links"), timeOut, polling);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToScrollTo);
        System.out.println("THE LINK IS: " + scrollToElement(By.id("Quick Links")).getText());
//        WebElement scrollToTop = driver.findElement(By.className("left-navigation-level__main-text"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0]." +
//                "scrollIntoView(true);", scrollToTop);
        System.out.println("Top element is: " + scrollToElement(By.className("left-navigation-level__main-text")).getText());

    }

    public WebElement scrollToElement(By element) {
        WebElement elementToScrollTo = waitForElement(element, timeOut, polling);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToScrollTo);
        return elementToScrollTo;
    }

    public void navigateToHomePage() {
        driver.get("https://www-qa2.safeway.com");
        waitForPageToLoad(timeOut);
        String location = "//*[@id=\"apps-flyer-wrapper\"]/div[2]/div[1]/div/div";

        waitForElement(By.xpath(location), timeOut, polling).click();
        location = "//*[@id='onetrust-reject-all-handler']";
        waitForElement(By.xpath(location), timeOut, polling).click();

    }

    public void waitForPageToLoad(Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }
}
