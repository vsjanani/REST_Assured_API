package bddFramework.cucumber.featureFiles;

import org.junit.runner.RunWith;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class StepDefinitionsDemo {

	@Given("^Stick should be in hand$")
    public void Stick_should_be_in_hand() throws Throwable {
        System.out.println("given step execution by janani");
    }


	@When("{string} on head")
	public void on_head(String dummy) {
	   System.out.println("chumma when" + dummy);
	}

	
	@Then("He faints {string}")
	public void he_faints(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("yes, he did" + string);
	}


    
}