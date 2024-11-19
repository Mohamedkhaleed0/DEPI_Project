package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import utils.WebDriverManager;

//import java.awt.*;
@CucumberOptions(

        features = "src/test/resources/Features",
        glue = {"StepDefinitions"},
        plugin = {"pretty",
                "html:target/TestReport.html",
        }
        //tags = "@Smoke"
)
public class testRunner extends AbstractTestNGCucumberTests {
        @AfterClass
        public static void tearDownAfterClass() {
                WebDriverManager.quitDriver(); // Call quitDriver method after all scenarios
        }

}
