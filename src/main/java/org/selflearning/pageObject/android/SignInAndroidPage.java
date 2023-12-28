package org.selflearning.pageObject.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.selflearning.utils.CommonUtils;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SignInAndroidPage extends CommonUtils {
    AndroidDriver driver;
    Duration WAITDURATION = Duration.ofSeconds(120);
    Duration SHORT_WAIT_10S = Duration.ofSeconds(10);
    Duration POOLING = Duration.ofSeconds(5);

    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/tv_continue_as_guest")
    private WebElement LOGIN_PAGE;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/switchCompat")
    private WebElement mobileOption;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/uma_edit_text")
    private WebElement EMAIL_SIGNIN_PAGE;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/uma_btn_next")
    private WebElement EMAIL_NEXT_BTN;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/editTextOtp")
    // @AndroidFindBy(className = "android.widget.EditText")
    private WebElement OTP_CODE_FIELD;
    @AndroidFindBy(xpath = "(//android.widget.EditText[@text=\"-\"])[1]")
    private WebElement OTP_DIGITS;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/btn_next")
    private WebElement OTP_NEXT_BTN;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/aerr_wait\"]")
    private WebElement ERROR_ALERT_WAIT;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
    private WebElement LOCATION_ALERT;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/frm_container")
    private WebElement POPUP_FRAME;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/tv_tooltip_message")
    private WebElement NEXT_CHOSE_FULFILMENT_TYPE;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/btn_tooltip")
    private WebElement NEXT_BUTTON1;

    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/navigation_bar_item_large_label_view")
    private WebElement HOMEPAGE;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/btn_ur_two")
    private WebElement FOR_U;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.safeway.client.android.safeway.debug:id/sign_up' and @text='Clip coupon']")
    private List<WebElement> COUPONS_BUTTONS;
    @AndroidFindBy(id = "com.safeway.client.android.safeway.debug:id/tv_deal_name")
    private List<WebElement> COUPONS_NAMES;


    public SignInAndroidPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public void appInstalledAndLaunched(){
        WebElement element = waitForElement(LOGIN_PAGE, WAITDURATION, POOLING);
        String expected = "Continue as Guest";
        Assert.assertEquals(expected, element.getText());
        System.out.println("The app is launched and ready for sign in");
    }

    public void selectEmailLogIn() throws InterruptedException, IOException {
        long start = System.currentTimeMillis();

        WebElement e = waitForElement(LOGIN_PAGE, WAITDURATION, POOLING);
        long end = System.currentTimeMillis();
        System.out.println("Time the threed sleep: " + (end - start));
        start = end = 0;

        System.out.println("HOME PAGE IS LOADED: " + LOGIN_PAGE.getText());
        WebElement ele = waitForElement(mobileOption, WAITDURATION, POOLING);
        while (ele == null) {
            ele = waitForElement(mobileOption, WAITDURATION, POOLING);
            System.out.println("Element mobile option found: " + ele.toString());
            System.out.println("Element mobile option found: " + ele.getText());
        }
        ele.click();
        Thread.sleep(1000);
        ele.click();

        WebElement ele1 = waitForElement(EMAIL_SIGNIN_PAGE, WAITDURATION, POOLING);

        while (ele1 == null) {
            ele1 = waitForElement(EMAIL_SIGNIN_PAGE, WAITDURATION, POOLING);
            System.out.println("EMAIL FIELD: " + ele1.toString());
            System.out.println("EMAIL FIELD: " + ele1.getText());

        }
        ele1.sendKeys("dec7_opt@mail7.io");

        WebElement ele2 = waitForElement(EMAIL_NEXT_BTN, WAITDURATION, POOLING);
        while (ele2 == null) {
            ele2 = waitForElement(EMAIL_NEXT_BTN, WAITDURATION, POOLING);
            System.out.println("EMAIL BUTTON: " + ele2.getText());
        }
        ele2.click();
        Thread.sleep(1000);
        String otp = retrieveEmailOTP("dec7_opt");
        while (otp == null) {
            otp = retrieveEmailOTP("dec7_opt");
        }
        String otp1 = otp.trim();
        System.out.println("THE OTP CODE: " + otp);
        // Thread.sleep(10000);
        List<WebElement> dig = driver.findElements(By.className("android.widget.EditText"));
        int otpLen = otp1.length();
        int otpElen = dig.size();
        for (int i = 0, j = 0; i < otpLen && j < otpElen; i++, j++) {
            if ((i == j)) {
                dig.get(j).sendKeys(String.valueOf(otp1.charAt(i)));
                Thread.sleep(1000);
            }

            // waitForElement(driver.findElement(By.xpath(str)), WAITDURATION, POOLING).sendKeys(String.valueOf(otp1.charAt(i)));
        }

        waitForElement(OTP_NEXT_BTN, WAITDURATION, POOLING).click();
        ele = waitForElement(LOCATION_ALERT, WAITDURATION, POOLING);
        System.out.println("SIGN IN SUCCESSFULLY: \nCLICK ON CONTINUE TO CONSENT SHARING YOUR LOCATION: " + ele.getText());
        ele.click();

        int count = 1;
        while (count < 4) {
          //  WebElement e1 = waitForElement(NEXT_CHOSE_FULFILMENT_TYPE, WAITDURATION, POOLING);
        //    System.out.println("NEXT " + count + ": " + e1.getText());
      //      WebElement e2 = waitForElement(NEXT_BUTTON1, WAITDURATION, POOLING);
     //       e2.click();

            count++;
        }
        waitForElement(FOR_U,WAITDURATION,POOLING).click();
        List<WebElement> coupon_button = waitForElements(COUPONS_BUTTONS, WAITDURATION, POOLING);
        List<WebElement> coupon_names = waitForElements(COUPONS_NAMES, WAITDURATION, POOLING);
        int cb_size = coupon_button.size();
        int cn_size = coupon_names.size();
        for (int i = 0, j = 0; i < cb_size && j < cn_size; i++, j++) {
            System.out.println("Coupon " + i + 1 + " Button: " + coupon_button.get(i).getText());
            System.out.println("Coupon " + j + 1 + " Name: " + coupon_names.get(j).getText());

        }
        Thread.sleep(30000);


    }

    public void handleErrorAlert() {
        try {
            WebElement ele = waitForElement(ERROR_ALERT_WAIT, SHORT_TIME_OUT, POOLING);
            // Alert alert = driver.switchTo().alert();
            System.out.println("ERROR ALERT OCCURRED " + ele.getText());
            ele.click();
        } catch (NoSuchElementException e) {
            e.getStackTrace();
            System.out.println("Error did not happened, the rest of scrip will continue executing.");
        }

    }

}
