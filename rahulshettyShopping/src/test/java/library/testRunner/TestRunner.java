package library.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/library/featureFile/LibraryAddBook.feature",
glue= {"library.stepDefinitions"},dryRun= false, 
plugin= {"pretty", "summary",
		"json:target/jsonReports/cucumber-report.json"}, tags=("@Regression"))
public class TestRunner {
	
	
}
