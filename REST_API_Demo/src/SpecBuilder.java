import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoFiles.Location;
import pojoFiles.Pojo_Json_Serialisation;

public class SpecBuilder {

	@Test
	public void specBuilder() {
		RequestSpecification objReqSpecs = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addHeader("key", "qaclick123").build();
		ResponseSpecification objResSpecs = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).build();

		Pojo_Json_Serialisation objConvertToJson = new Pojo_Json_Serialisation();
		objConvertToJson.setAccuracy(20);
		objConvertToJson.setAddress("500, Mission Parkway");
		objConvertToJson.setLanguage("English");
		objConvertToJson.setName("Janani dinesh");
		objConvertToJson.setPhone_number("5092345895");
		objConvertToJson.setWebsite("https://krishnacademy.com");
		List<String> types = new ArrayList<String>();
		types.add("shoe");
		types.add("socks");
		objConvertToJson.setTypes(types);
		Location objLocation = new Location();
		objLocation.setLat(-34.35436);
		objLocation.setLng(56.09874);
		objConvertToJson.setLocation(objLocation);

		RequestSpecification objGiven = given().spec(objReqSpecs).body(objConvertToJson);
		JsonPath objJsonPath = objGiven.when().post("/maps/api/place/add/json").then().spec(objResSpecs).extract()
				.response().jsonPath();
	}
}
