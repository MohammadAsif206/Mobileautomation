package org.selflearning.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    private AndroidDriver driver;
    public final Duration SHORT_TIME_OUT = Duration.ofSeconds(5);
    public final Duration MEDIUM_TIME_OUT = Duration.ofSeconds(10);
    public final Duration LONG_TIME_OUT = Duration.ofSeconds(15);
    public final Duration VERY_LONG_TIME_OUT = Duration.ofSeconds(30);
    public final Duration POLING_TIME = Duration.ofSeconds(3);

    private static final String YOUR_API_KEY = "99303054-da68-40f2-a7e3-45a4a36983eb";
    private static final String YOUR_API_SECRET = "c1c9b6f1-450d-4fee-8d25-88b46aab31ce";
     public CommonUtils(){}
    public CommonUtils(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElement(WebElement webElement, Duration timeout, Duration pollingInterval) {
        try {
            FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                    .withTimeout(timeout)
                    .pollingEvery(pollingInterval)
                    .ignoring(NoSuchElementException.class);


            return wait.until((Function<WebDriver, WebElement>) webDriver ->
                    ExpectedConditions.visibilityOf(webElement).apply(webDriver));
        } catch (NoSuchElementException e) {
            e.getStackTrace();
            System.out.println("ELEMENT NOT LOCATED DURING THE SET WAIT");
            return null;
        }
    }

    public List<WebElement> waitForElements(List<WebElement> webElements, Duration timeout, Duration pollingInterval) {
        int i = 0;
        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval)
                .ignoring(NoSuchElementException.class);
        wait.until((Function<WebDriver, List<WebElement>>) webDriver -> {
            for (WebElement element : webElements) {
                ExpectedConditions.visibilityOf(element).apply(webDriver);
            }
            return null;
        });
        return webElements;

    }
    //New strategy for wait for element
    public static By waitForELement(WebDriver driver, String locatorStrategy, String identifier){
        By by = getBy(locatorStrategy,identifier);
        waitForElementi(driver,Duration.ofSeconds(120),Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(by));
        return by;

    }
    private static By getBy(String locatorStrategy,String identifier){
        switch (locatorStrategy.toLowerCase()){
            case "id":
                return By.id(identifier);
            case "xpath":
                return By.xpath(identifier);
            case "className":
                return By.className(identifier);
            default:
                throw new IllegalArgumentException("Unsupported locator "+locatorStrategy);
        }
    }
    private static Wait<WebDriver> waitForElementi(WebDriver driver, Duration duration, Duration pooling){
        return new FluentWait<>(driver)
                .withTimeout(duration)
                .pollingEvery(pooling)
                .ignoring(NoSuchElementException.class);
    }



    //end of the new

    public List<WebElement> waitForElements1(List<WebElement> webElements, Duration timeout, Duration pollingInterval) {
        List<WebElement> list = new ArrayList<>();
        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval)
                .ignoring(NoSuchElementException.class);

        for (int i = 0; i < webElements.size(); i++) {
            wait.until(ExpectedConditions.visibilityOf(webElements.get(i)));
            list.add(webElements.get(i));
        }

        return list;
    }

    public WebElement scrollToAnElement(String specificCountry) {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + specificCountry + "\"));"));
    }

    public void navigateBackInAndroidNative() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void reTry(WebElement element, String actionOnElement) {
        String getText = "getText";
        String doClick = "doClick";
        WebElement ele = null;
        int attempt = 0;
        while (attempt < 3) {

            try {
                if (actionOnElement.equals(getText) && ele == null) {
                    ele = element;

                    System.out.println(" THE TEXT RETRIEVED IS : " + ele.getText());
                }
                if (actionOnElement.equals(doClick)) {
                    ele = element;
                    System.out.println(" THE ELEMENT CLICKED IS : " + ele.getText());
                    ele.click();
                }
            } catch (StaleElementReferenceException e) {
                e.getStackTrace();
                System.out.println(" THE ELEMENT FAILED TO GET ACTED UPON ON " + attempt + " ");
            }
            attempt++;
        }
    }

    public static String retrieveEmailOTP(String UserName) throws IOException, InterruptedException {
        Thread.sleep(20000);
        URL url = new URL("https://api.mail7.io/inbox?apikey=" + YOUR_API_KEY + "&apisecret=" + YOUR_API_SECRET + "&to=" + UserName);
        HttpURLConnection con;
        con = (HttpURLConnection) url.openConnection();
        // handle error response code it occurs
        int responseCode = con.getResponseCode();
        System.out.println("Raw Response: " + con.getInputStream().toString());
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = con.getInputStream();
        } else {
            inputStream = con.getErrorStream();
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        inputStream));
        StringBuilder response = new StringBuilder();
        String currentLine;
        int l = 0;
        while ((currentLine = in.readLine()) != null && l < 2) {
            String patternString = "Test[\\s\\S]*?(\\d{6})";
            Pattern sixdigitcode = Pattern.compile(patternString);
            Matcher matcher = sixdigitcode.matcher(currentLine);
            Set<String> set = new TreeSet<>();
            String code;
            Map<String, String> map = new HashMap<>();

            while (matcher.find() && l < 2) {
                if (l == 1) {
                    System.out.println("MATCHER INISED WHEILE LOOP: " + matcher.toString());
                    response.append(matcher.toString());
                }
                l++;
            }
        }
        in.close();
        int size1 = response.toString().length() - 1;
        int size2 = size1 - 6;
        System.out.println("Response is : " + response.toString());
        String forReturn = response.toString().substring(size2, size1);
        //  System.out.println("FINAL TEXT RETURNED IS: "+response.toString());
        System.out.println("THE CODE IS: " + forReturn);
        return forReturn;
    }


}
