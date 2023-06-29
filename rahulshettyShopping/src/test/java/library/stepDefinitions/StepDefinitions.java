package library.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import library.resources.APIResources;
import library.resources.Utilities;
import rahulshettyShopping.pojo.DeleteBookSerialisation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;



public class StepDefinitions extends Utilities {
	RequestSpecification objGiven;
	Response objRes;
	JsonPath objJson;
	HashMap<String, Object> myMap;
	static String strActualID;
	
//	in below given method, input payload for addbook api is excel(converted tojson) and for delete bookapi, it is pojo (converted to json via serialisation)
	@Given("{string} and {string} Json payload")
	public void and_json_payload(String strBaseURI, String strPayloadFor) throws IOException {
		if(strPayloadFor.equals("AddBook")) {
		myMap = excelToHashMap(strPayloadFor);
		objGiven = given().spec(reqSpecs()).body(myMap);}
		else if(strPayloadFor.equals("DeleteBook")) {
			DeleteBookSerialisation objDelPayload = new DeleteBookSerialisation();			
//			objGiven = given().spec(reqSpecs()).body(objDelPayload.deletePayload(strActualID));
			objGiven = given().spec(reqSpecs()).body("{\r\n"
					+ "    \"ID\" : \""+strActualID+"\"\r\n"
					+ "}");
		}
	}

	@When("user hits {string} API using HTTP Method {string}")
	public Response user_hits_api_using_http_method(String strAPIResource, String strHTTPMethod) {
		APIResources objAPI = APIResources.valueOf(strAPIResource);
		String strAPI = objAPI.getAPIResource();
		if (strHTTPMethod.equals("POST")) {
			objRes = objGiven.when().post(strAPI);
		}
		else if(strHTTPMethod.equals("DELETE"))
		{
			objRes = objGiven.when().delete(strAPI);
		}
		return objRes;
	}

	@Then("{string} should be {string}")
	public void should_be(String strKey, String strExptdValue) {
		String strActualValue = resToJsonPathString(objRes,strKey);
		Assert.assertEquals(strExptdValue, strActualValue);
		
	}
	
	

	@Then("Response Status code should be {string}")
	public void response_status_code_should_be(String strExptdStatusCode){
		objRes.then().assertThat().statusCode(200);
	}

	@Then("{string} should be isbn+aisle")
	public void should_be_isbn_aisle(String strID) {
		Object str = myMap.get("isbn");
		Object str1 = myMap.get("aisle");
		String strExptdID = str.toString()+str1.toString();
		strActualID = resToJsonPathString(objRes,strID);
//		strActualID = objJson.getString(strID);
		Assert.assertEquals(strExptdID, strActualID);
		
	}



	
	




}
