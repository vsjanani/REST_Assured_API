package bddFramework.cucumber.testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/bddFramework/cucumber/featureFiles/GoogleAPIs.feature", 
glue = {"bddFramework.cucumber.stepDefinitions"}, 
tags = ("@AddGoogleAPI"),dryRun=false,monochrome = true,
plugin = {"pretty", "summary", "json:target/jsonReports/cucumber-report.json"})

//if you want to tell Cucumber to print code snippets in console for missing step definitions use the summary plugin, you can specify it like above
//note that in junit also missing code snippets gets printed but that comes up with two extra lines which isn't user friendly to copy.
// if you want to check whether all feature file steps have corresponding step definitions, you can specify dryRun as true. By default it is false
public class TestRunner {

}
