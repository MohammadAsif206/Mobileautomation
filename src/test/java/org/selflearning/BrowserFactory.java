package org.selflearning;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;

public class BrowserFactory {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public String PATH = "C:\\Users\\moham\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    public String IP_ADDRESS = "127.0.0.1";
    public String URL = "http://127.0.0.1:4723";
    public String DEVICE_NAME = "Pixel2API29";
    public int PORT_NUMBER = 4723;

    @BeforeClass
    public void startUp() {
        try {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File(PATH))
                    .withTimeout(Duration.ofMinutes(2))
                    .withIPAddress(IP_ADDRESS)
                    .usingPort(PORT_NUMBER)
                    .build();

            service.start();

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(DEVICE_NAME);
            options.setChromedriverExecutable("C:\\WebDrivers\\chromedriver_win32\\chromedriver.exe");
            options.setCapability("browserName", "Chrome");
            options.setCapability("mode","--incognito");

            driver = new AndroidDriver(new URL(URL), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(160));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }


    public WebElement fluentWaitForElement(WebDriver driver, By locator, Duration timeout, Duration pollingInterval) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        return wait.until((ExpectedCondition<WebElement>) webDriver -> webDriver.findElement(locator));
    }
    public WebElement waitForElement(By locator, Duration timeout, Duration pollingInterval) {
        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval)
                .ignoring(NoSuchElementException.class);

        return wait.until((Function<WebDriver, WebElement>) webDriver ->
                ExpectedConditions.presenceOfElementLocated(locator).apply(webDriver));
    }
}
