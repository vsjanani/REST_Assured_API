import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.BodyContent;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {
	BodyContent objBodyContent = new BodyContent();
	@Test(dataProvider="sendData")
	public void dynamicJson(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		given()
			.header("Content-Type","application/json")
			.body(objBodyContent.bodyDynamicJson(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().body().assertThat().statusCode(200);
		
	}
	
	@DataProvider
	public Object[][] sendData() {
		return new Object[][] {{"vsj","004"},{"vsj","005"}};
	}
}
