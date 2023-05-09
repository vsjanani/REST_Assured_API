import org.testng.annotations.Test;

import pojoFiles.Pojo_Json_Serialisation;
import pojoFiles.Location;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pojo_Serialisation {

	@Test
	public void pojoSerialisation() {
		baseURI = "https://rahulshettyacademy.com";
		Pojo_Json_Serialisation objConvertToJson = new Pojo_Json_Serialisation();
		objConvertToJson.setAccuracy(20);
		objConvertToJson.setAddress("300, Mission Parkway");
		objConvertToJson.setLanguage("Tamil");
		objConvertToJson.setName("Darshini dinesh");
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
		given().relaxedHTTPSValidation().header("Content-Type", "application/json").queryParam("key", "qaclick123")
				.body(objConvertToJson).log().all().when().post("/maps/api/place/add/json").then().assertThat()
				.statusCode(200).log().all().extract().response().jsonPath();

	}
}
