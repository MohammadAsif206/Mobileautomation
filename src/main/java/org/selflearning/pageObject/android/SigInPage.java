package org.selflearning.pageObject.android;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selflearning.utils.CommonUtils;

import java.time.Duration;

public class SigInPage extends CommonUtils {

    private AndroidDriver driver;

    public SigInPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement generalStoreTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement formPageField;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement genderFemale;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement genderMale;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryList;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement productPage;

    //for toast message there is a mandatory standard tag name. android.widget.Toast;
    @AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
    private WebElement toastMessage;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private WebElement productNames;

    public WebElement waitForPageToLoad(WebElement element, Duration duration) {

        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(element));

        System.out.println("Page loaded: the title of the page is: " + element.getText());
        return element;

    }


    public void setNameField(String name) {
        // WebElement ele = waitForElement(formPageField, SHORT_TIME_OUT, POLING_TIME);
        formPageField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setActivity() {
        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
        // driver.startActivity(activity);
    }

    public WebElement getProductPage() {
        return productPage;
    }

    public WebElement getGeneralStoreTitle() {
        return generalStoreTitle;
    }

    public void selectGender(String gender) {
        if (gender.contains("Female")) {
            genderFemale.click();
        } else
            genderMale.click();


    }

    public void findCountry(String countryName) {
        countryList.click();
        scrollToAnElement(countryName);
        WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']"));
        System.out.println(" THE COUNTRY SELECTED IS : " + ele.getText());
        ele.click();
    }

    public WebElement submitForm() {
        WebElement ele = waitForElement(shopButton, SHORT_TIME_OUT, POLING_TIME);
        ele.click();
        return ele;
    }

    public void navigateToProductPage() {
        reTry(productPage, "getText");
//        WebElement element = waitForElement(productPage, Duration.ofSeconds(30), Duration.ofSeconds(5));
//        System.out.println("THE LOADED PAGE TITLE IS : " + element.getText());

    }

    public void launchTheAppMainPage() {
        reTry(generalStoreTitle, "getText");
//        WebElement element = waitForElement(generalStoreTitle, Duration.ofSeconds(30), Duration.ofSeconds(5));
//        System.out.println("THE LOADED PAGE IS THE APP WITH THE APP TITLE IS : " + element.getText());

    }

    public void navBackAndroidNative() {
        navigateBackInAndroidNative();
        launchTheAppMainPage();

    }

    public void clearNameField() {
        waitForElement(formPageField, Duration.ofSeconds(20), Duration.ofSeconds(3)).clear();
    }

    public void handleToastMessage() {
        reTry(toastMessage, "doClick");
    }
    public WebElement addProductToCart(String productName){
        WebElement element =  scrollToAnElement(productName);
        return element;
    }
}
