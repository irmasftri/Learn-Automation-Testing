package cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/cucumber/features",
        glue = {"cucumber.stepDef"},
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner {
}
