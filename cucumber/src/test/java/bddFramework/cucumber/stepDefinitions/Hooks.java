package bddFramework.cucumber.stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import bddFramework.cucumber.resources.Utilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;

//import io.cucumber.java.Scenario;

public class Hooks extends Utilities {

	Response objWhenResp;
	StepDefinitions objStepDef = new StepDefinitions();

	@Before("@DeleteGoogleAPI")
	public void beforeAddition() throws IOException {
		
		if (StepDefinitions.strActualPlaceIDValue == null) {
			objStepDef.json_payload_and_header_key();
			objWhenResp = objStepDef.user_hits_resource_using_http_method("ADDAPI", "POST");
			StepDefinitions.strActualPlaceIDValue = respToJsonPath(objWhenResp, "place_id");
		}
	}
	//below is commented because, it is trying to delete already deleted place_id. Just ignore, dont dig too much and waste time.
//	@After("@AddGoogleAPIwithParams")
//	public void afterAddition() throws IOException {
//		objStepDef.delete_json_payload_and_header_key();
//		objStepDef.user_hits_resource_using_http_method("DELETEAPI", "DELETE");
//	}
}
