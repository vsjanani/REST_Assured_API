import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.BodyContent;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public class ExternalJson {
	BodyContent objBodyContent = new BodyContent();
	@Test
	public void dynamicJson() throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		given()
			.header("Content-Type","application/json")
			.body(new File("C:/Users/jansr/Downloads/REST_Assured_API/BodyContent.json"))
		.when().post("Library/Addbook.php")
		.then().log().body().assertThat().statusCode(200);
		
	}
	
	}
