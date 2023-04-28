import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.filter.session.SessionFilter;

public class JiraAddAttachment {
	public static void main(String[] args) {
		SessionFilter objSessionFilter = new SessionFilter();
		baseURI = "http://localhost:8080";
		given().header("Content-Type","application/json").body("{ \"username\": \"janani\", \"password\": \"SanjeevKrish@01\" }")
		.filter(objSessionFilter)
		.when().post("rest/auth/1/session")
		.then().log().all();
		
		given().pathParam("id", "10206").filter(objSessionFilter)
		.when().get("rest/api/2/issue/{id}")
		.then().log().all();
		
		
//		int intStatusCode = given().pathParam("id","10206").header("X-Atlassian-Token","no-check").header("content-type","multipart/form-data")
//		.multiPart("file",new File("infoAttach.txt"))
//		.filter(objSessionFilter)
//		.when().post("rest/api/2/issue/{id}/attachments")
//		.then().log().all().extract().response().statusCode();
//		System.out.println(intStatusCode);
	}
}
