package bddFramework.cucumber.stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import bddFramework.cucumber.PayLoad.BodyForAddAPI;
import bddFramework.cucumber.resources.Utilities;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinitions extends Utilities {

	RequestSpecification objGivenSpec;
	JsonPath objJsonPath;
	Response objWhenResp;

	@Given("JSON Payload and Header Key")
	public void json_payload_and_header_key() throws IOException {
		System.out.println(globalData("baseURI"));
		BodyForAddAPI objPayLoad = new BodyForAddAPI();
		objGivenSpec = given().spec(requestSpec()).body(objPayLoad.bodyForAddAPI());
		

	}

	@Given("JSON Payload and Header Key with {string},{string},{string}")
	public void json_payload_and_header_key_with(String strName, String strAddress, String strLanguage)
			throws IOException {

		BodyForAddAPI objPayLoadwithParams = new BodyForAddAPI();
		objGivenSpec = given().spec(requestSpec()).body(objPayLoadwithParams.bodyForAddAPIwithParams(strName, strAddress, strLanguage));
	}

	@When("User POST {string} resource")
	public void user_post_resource(String strResource) {
		objWhenResp = objGivenSpec.when().post(strResource);

	}

	@Then("Response Status code should be {int}")
	public void Response_Status_code_should_be(int intExptdStatusCode) {
		Assert.assertEquals(intExptdStatusCode, objWhenResp.getStatusCode());

	}

	@Then("{string} in response body should be {string}")
	public void in_response_body_should_be(String strExptdKey, String strExptdValue) {
		objJsonPath = respToJsonPath(objWhenResp);
		System.out.println(objJsonPath.getString("place_id"));
		Assert.assertEquals(strExptdValue, objJsonPath.getString(strExptdKey));
	}

}
