package org.selflearning.pagesteps;

import io.cucumber.java.Before;
import org.selflearning.factory.BrowserFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

import static org.selflearning.factory.BrowserFactory.service;

public class Hook extends BaseBrowser{

    private BaseBrowser baseBrowser;
    public Hook(BaseBrowser baseBrowser) {
        this.baseBrowser = baseBrowser;
    }



    @BeforeClass
    public void startUP() throws InterruptedException, MalformedURLException {
        baseBrowser.driver = BrowserFactory.getBrowser(System.getProperty("browser"));
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

}
