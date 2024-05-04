
package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "",
        features = {"src/test/resources/login.feature"},
        glue = {"stepdef"},
        plugin = {"pretty", "html:target/cucumber.html"},
        dryRun = false)
public class Runner extends AbstractTestNGCucumberTests {

}