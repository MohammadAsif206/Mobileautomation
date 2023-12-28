package org.selflearning.factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.selflearning.pagesteps.BaseBrowser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserFactory {
    private static WebDriver driver;

    public static AppiumDriverLocalService service;
    private static String PATH = "C:\\Users\\moham\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    private String APP_API = "C://Users//moham//IdeaProjects//Mobileautomation//src//test//java//resources//ApiDemos-debug.apk";
    // private String APP_UI = "C://Users//moham//IdeaProjects//Mobileautomation//src//test//java//resources//General-Store.apk"; C:\WebDrivers\\scr-safeway-debug.apk
    private String APP_UI = "C:\\WebDrivers\\src-safeway-debug.apk";
    private static String IP_ADDRESS = "127.0.0.1";
    private static String URL = "http://127.0.0.1:4723";
    private String DEVICE_NAME = "Pixel2API29";
    private static int PORT_NUMBER = 4723;
    public static final WebDriver getBrowser(String browserName) throws MalformedURLException {
        if(browserName.equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("Android")){
            service = new AppiumServiceBuilder().withAppiumJS(new File(PATH)).withTimeout(Duration.ofMinutes(2))
                    .withIPAddress(IP_ADDRESS).usingPort(PORT_NUMBER).build();
            service.start();
            UiAutomator2Options cap = new UiAutomator2Options();
            cap.setCapability("platformName", "Android");
            cap.setCapability("deviceName", "LGE LM-Q850");
            cap.setCapability("appPackage", "com.safeway.client.android.safeway.debug");
            cap.setCapability("appActivity", "com.safeway.mcommerce.android.SplashScreen");
            driver = new AndroidDriver(new URL(URL), cap);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        }
        return driver;
    }
}
