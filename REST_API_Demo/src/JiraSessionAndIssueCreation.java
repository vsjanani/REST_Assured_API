import org.testng.annotations.Test;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JiraSessionAndIssueCreation {

	public JiraSessionAndIssueCreation() {
		baseURI = "http://localhost:8080";
	}

	public SessionFilter objSession;

	@Test
	public void sessionCreation() {
		objSession = new SessionFilter();
		given().header("Content-Type", "application/json")
				.body("{ \"username\": \"janani\", \"password\": \"SanjeevKrish@01\" }").filter(objSession).when()
				.post("rest/auth/1/session").then().log().body().extract().jsonPath();
	}

	@Test(priority = 1)
	public void issueCreation() {
		given().header("Content-Type", "application/json").body("{\r\n" + "    \"fields\": {\r\n"
				+ "       \"project\":\r\n" + "       {\r\n" + "          \"key\": \"RESTAPI\"\r\n" + "       },\r\n"
				+ "       \"summary\": \"automation of creating ISSUE-6\",\r\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
				+ "       \"issuetype\": {\r\n" + "          \"name\": \"Bug\"\r\n" + "       }\r\n" + "   }\r\n" + "}")
				.filter(objSession).when().post("rest/api/2/issue").then().log().body().assertThat().statusCode(201);
	}

	@Test(priority = 2)
	public void getIssue() {
		given().filter(objSession).when().get("rest/api/2/issue/10206").then().log().body();
	}
}
