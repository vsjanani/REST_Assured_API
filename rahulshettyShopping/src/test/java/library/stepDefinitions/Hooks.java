package library.stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;
import io.restassured.response.Response;
import library.resources.Utilities;

public class Hooks extends Utilities{
	StepDefinitions objStepDef = new StepDefinitions();
	@Before("@DeleteBook")
	public void deleteBook() throws IOException {
		
		if(StepDefinitions.strActualID == null) {
			objStepDef.and_json_payload("baseURI", "AddBook");
			Response objWhenRes = objStepDef.user_hits_api_using_http_method("AddBook", "POST");
			StepDefinitions.strActualID = resToJsonPathString(objWhenRes, "ID");
		}
	}
}
