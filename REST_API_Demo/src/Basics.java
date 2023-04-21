import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.BodyContent;
import io.restassured.path.json.JsonPath;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BodyContent objBodyContent = new BodyContent();
		baseURI = "https://rahulshettyacademy.com";
		//POST request
		JsonPath objPostJsonPath = given().header("Content-Type", "application/json").queryParam("key", "qaclick123")
				.body(objBodyContent.inputBodyContent()).when().post("maps/api/place/add/json").then().log().body()
				.assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
				.extract().response().jsonPath();
		// instead of log().body(), log().all() can be used if we want all the details
		// to be displayed.

		System.out.println("place_id of posted request is" + objPostJsonPath.getString("place_id"));
		String place_id = objPostJsonPath.getString("place_id");
		String strUpdatedAddress = "jananiHighway, USA";
		
		//PUT request
		given().header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + place_id + "\",\r\n" + "\"address\":\""+strUpdatedAddress+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().log().body().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
		//GET request
		JsonPath objGetJsonPath = given().queryParam("key", "qaclick123").queryParam("place_id", place_id)
				.when().put("maps/api/place/get/json")
				.then().log().body().assertThat().statusCode(200).extract().jsonPath();
		System.out.println(objGetJsonPath.getString("address"));
		Assert.assertEquals(objGetJsonPath.getString("address"), strUpdatedAddress);
		

	}

}
