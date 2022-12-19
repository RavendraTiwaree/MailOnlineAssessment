package cucumberTestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features ={"src/test/resources/cucumberFeatureFile/"},
        glue = {"cucumberStepDefinition"},

        tags = "@UI_TC01",
        dryRun = false


)

public class TestRunner {

}
