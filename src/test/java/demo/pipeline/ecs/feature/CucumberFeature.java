package demo.pipeline.ecs.feature;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "demo.pipeline.ecs",
        strict = true,
        plugin = {"pretty", "html:demo-pipeline-test"}
)
public class CucumberFeature {
}
