package bddFramework.cucumber.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/bddFramework/cucumber/featureFiles/GoogleAPIs.feature", glue = "bddFramework.cucumber.stepDefinitions")
public class TestRunner {
	
}
