package org.selflearning;

import io.appium.java_client.android.AndroidDriver;
import org.selflearning.pageObject.android.SignInAndroidPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class AndroidAppSignin extends CommonClass{
    private AndroidDriver driver;

    @Test
    public void selectEmailOption() throws InterruptedException, IOException {
        SignInAndroidPage signInAndroid = new SignInAndroidPage(driver);
        signInAndroid.selectEmailLogIn();
    }

}
