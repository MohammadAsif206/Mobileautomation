package org.selflearning;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/resources/ui/feature/SignIn.feature",
        glue = {"org.selflearning.pagesteps","org.selflearning.Hook"},
        tags = "@yourTag",
        plugin = {"pretty", "html:target/cucumber-reports"}


)

public class TestRunner extends AbstractTestNGCucumberTests {
}
