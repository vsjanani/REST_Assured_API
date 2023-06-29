package rest.excelDataDrive.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/rest/excelDataDriven/feature/AddBook.feature",
glue= {"rest.excelDataDrive.stepDefinitions"},dryRun= false, 
plugin= {"pretty", "summary",
		"json:target/jsonReports/cucumber-report.json"}, tags=("@Regression"))
public class TestRunner {
	
	
}
