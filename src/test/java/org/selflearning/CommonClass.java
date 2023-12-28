package org.selflearning;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
//import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.selflearning.utils.CommonUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;

public class CommonClass   {
    private AndroidDriver driver;

    public AppiumDriverLocalService service;
    private String PATH = "C:\\Users\\moham\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    private String APP_API = "C://Users//moham//IdeaProjects//Mobileautomation//src//test//java//resources//ApiDemos-debug.apk";
   // private String APP_UI = "C://Users//moham//IdeaProjects//Mobileautomation//src//test//java//resources//General-Store.apk"; C:\WebDrivers\\scr-safeway-debug.apk
     private String APP_UI = "C:\\WebDrivers\\src-safeway-debug.apk";
    private String IP_ADDRESS = "127.0.0.1";
    private String URL = "http://127.0.0.1:4723";
    private String DEVICE_NAME = "Pixel2API29";
    private int PORT_NUMBER = 4723;

    @BeforeClass
    public void startUp() throws MalformedURLException {
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability("platformName", "Android");
//        cap.setCapability("deviceName", "LGE LM-Q850");
//        cap.setCapability("appPackage", "com.safeway.client.android.safeway.debug");
//        cap.setCapability("appActivity", "com.safeway.mcommerce.android.SplashScreen");
//        System.out.println("Please Enter the name of AUT");
//        Scanner input = new Scanner(System.in);
//        String app = input.nextLine();
//        if(input.equals(app.equals("api"))){
//            service = new AppiumServiceBuilder().withAppiumJS( new File(PATH)).withTimeout(Duration.ofMinutes(2))
//                    .withIPAddress(IP_ADDRESS).usingPort(PORT_NUMBER).build();
//            service.start();
//            UiAutomator2Options options = new UiAutomator2Options();
//            options.setDeviceName(DEVICE_NAME);
//            options.setApp(APP_API);
//            driver = new AndroidDriver(new URL(URL),options);
//        }
//        if(input.equals(app.equals("ui"))){
        // new method
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
//        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,11);
//        cap.setCapability(CapabilityType.);
//        cap.

        // end of new method
        service = new AppiumServiceBuilder().withAppiumJS(new File(PATH)).withTimeout(Duration.ofMinutes(2))
                .withIPAddress(IP_ADDRESS).usingPort(PORT_NUMBER).build();
        service.start();
        UiAutomator2Options cap = new UiAutomator2Options();
        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "LGE LM-Q850");
        cap.setCapability("appPackage", "com.safeway.client.android.safeway.debug");
        cap.setCapability("appActivity", "com.safeway.mcommerce.android.SplashScreen");
      //  options.setDeviceName(DEVICE_NAME);
      //  options.setCapability();
     //   options.setApp(APP_UI);
        //   options.setChromedriverExecutable("C:\\WebDrivers\\chromedriver_win32\\chromedriver.exe"); // this loc is for mobile web browser launch
        driver = new AndroidDriver(new URL(URL), cap);
//        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
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

    /**
     * The method is used to do a long press; meaning user needs to press the mouse on an element
     * and keep it pressing until the desired view is displayed.
     *
     * @param longClick
     */

    public void longPressOperation(WebElement longClick) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) longClick).getId(), "duration", 2000));
    }

    /**
     * The util method used for scrolling the page; It is used to scroll the entire page (pages)
     *
     * @param canScrollMore
     * @return
     */

    public boolean isScrollable(boolean canScrollMore) {
        canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", 100, "top", 100, "width", 200, "height", 200,
                "direction", "down",
                "percent", 3.0, "duration", 2000
        ));
        if (canScrollMore == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The util method scrolls to a specific element
     *
     * @param element
     * @param direction
     */
    public void scrollOperations(WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(), "left", 100, "top", 100, "width", 200, "height", 200,
                "direction", direction,
                "percent", 3.0, "duration", 2000));
    }

    /**
     * The util method swipes to a specific element
     *
     * @param element
     * @param direction
     */
    public void swipeOperation(WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

    public void dragDropOperation(WebElement from, int[] to) {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) from).getId(),
                "endX", to[0],
                "endY", to[1]
        ));
    }

    public WebElement fluentWaitForElement(WebDriver driver, By locator, Duration timeout, Duration pollingInterval) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        return wait.until((ExpectedCondition<WebElement>) webDriver -> webDriver.findElement(locator));
    }

    public WebElement waitForElement(WebElement locator, Duration timeout, Duration pollingInterval) {
        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval)
                .ignoring(NoSuchElementException.class);

        return wait.until((Function<WebDriver, WebElement>) webDriver ->
                ExpectedConditions.visibilityOf(locator).apply(webDriver));
    }


}
