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
	public void dynamicJson() {
		RestAssured.baseURI = "http://216.10.245.166";
		try {
			given()
				.header("Content-Type","application/json")
				.body(FileUtils.readFileToString(new File("C:/Users/jansr/Downloads/REST_Assured_API/BodyContent.json"), StandardCharsets.UTF_8))
			.when().post("Library/Addbook.php")
			.then().log().body().assertThat().statusCode(200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	}
