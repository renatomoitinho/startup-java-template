package demo.pipeline.ecs.suite;

import demo.pipeline.ecs.feature.CucumberFeature;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CucumberFeature.class})
public class TestSuite {
}
