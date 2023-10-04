package bddFramework.cucumber.stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import bddFramework.cucumber.PayLoad.BodyForAddAPI;
import bddFramework.cucumber.PayLoad.BodyForDeleteAPI;
import bddFramework.cucumber.resources.APIresources;
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
	static String strActualPlaceIDValue;
	BodyForAddAPI objPayLoad = new BodyForAddAPI();
	BodyForDeleteAPI objDelPayLoad = new BodyForDeleteAPI();

	@Given("JSON Payload and Header Key")
	public void json_payload_and_header_key() throws IOException {
		System.out.println(globalData("baseURI"));		
		objGivenSpec = given().spec(requestSpec()).body(objPayLoad.bodyForAddAPI());

	}

	@Given("JSON Payload and Header Key with {string},{string},{string}")
	public void json_payload_and_header_key_with(String strName, String strAddress, String strLanguage)
			throws IOException {

		objGivenSpec = given().spec(requestSpec())
				.body(objPayLoad.bodyForAddAPIwithParams(strName, strAddress, strLanguage));
	}

	@When("User hits {string} resource using http method {string}")
	public Response user_hits_resource_using_http_method(String strAPIResource, String strHTTPMethod) {
		
		APIresources mystr = APIresources.valueOf(strAPIResource);
		String strAPIResourceToSend = mystr.getAPIResource();
		if (strHTTPMethod.equalsIgnoreCase("POST")) {
			objWhenResp = objGivenSpec.when().post(strAPIResourceToSend);
		} else if (strHTTPMethod.equalsIgnoreCase("GET")) {
			objWhenResp = objGivenSpec.when().get(strAPIResourceToSend);
		} else if (strHTTPMethod.equalsIgnoreCase("DELETE")) {
			objWhenResp = objGivenSpec.when().delete(strAPIResourceToSend);
		}
		return objWhenResp;

	}
	@Then("Response Status code should be {int}")
	public void Response_Status_code_should_be(int intExptdStatusCode) {
		Assert.assertEquals(intExptdStatusCode, objWhenResp.getStatusCode());

	}

	@Then("{string} in response body should be {string}")
	public void in_response_body_should_be(String strExptdKey, String strExptdValue) {
		String strActualValue = respToJsonPath(objWhenResp, strExptdKey);
		Assert.assertEquals(strExptdValue, strActualValue);
	}

	@Then("on hitting {string} using http method {string}, already created {string} should carry {string} in {string} field")
	public void on_hitting_using_http_method_already_created_should_carry_in_field(String strAPIResource, String strHTTPMethod,
			String strPlaceIDKey, String strExptdValue, String strKey) throws IOException {
		strActualPlaceIDValue = respToJsonPath(objWhenResp, strPlaceIDKey);
		objGivenSpec = given().spec(requestSpec()).queryParam(strPlaceIDKey, strActualPlaceIDValue);
		user_hits_resource_using_http_method(strAPIResource, strHTTPMethod);
		String strActualValue = respToJsonPath(objWhenResp, strKey);
		Assert.assertEquals(strExptdValue, strActualValue);

	}
	
	@Given("Delete JSON Payload and Header Key")
	public void delete_json_payload_and_header_key() throws IOException {
		System.out.println(strActualPlaceIDValue);
		objGivenSpec = given().spec(requestSpec()).body(objDelPayLoad.bodyForDeleteAPI(strActualPlaceIDValue));
	
	}

	
	

}
