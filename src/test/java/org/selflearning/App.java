package org.selflearning;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App extends CommonClass{

    @Test
    public void appOptionTest(){
//        WebElement app = fluentWaitForElement(driver,By.xpath("//android.widget.TextView[@content-desc=\"App\"]"),
//                Duration.ofSeconds(30), Duration.ofSeconds(5));
        WebElement app = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"App\"]"));
        app.click();
        driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
        List<WebElement> listOfAllerts = driver.findElements(By.className("android.widget.Button"));
        int size = listOfAllerts.size();
        //Set<String> elements = new HashSet<>();
        for(int i = 0; i<size; i++)
            System.out.println(listOfAllerts.get(i).getText());

    }
}
