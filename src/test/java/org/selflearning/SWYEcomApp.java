package org.selflearning;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class SWYEcomApp extends BrowserFactory {
    @Test
    public void logIn() {
        driver.getCurrentPackage();
        System.out.println("Current Page : " + driver.getCurrentPackage());
        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
