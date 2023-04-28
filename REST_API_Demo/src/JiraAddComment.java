import org.testng.annotations.Test;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JiraAddComment {
	public JiraAddComment() {
		baseURI = "http://localhost:8080";
	}

	public SessionFilter objSessionFilter;
	public String strInputComment = "my second input";
	public String strCommentID;

	@Test
	public void createSession() {
		objSessionFilter = new SessionFilter();
		given().header("Content-Type", "application/json")
				.body("{ \"username\": \"janani\", \"password\": \"SanjeevKrish@01\" }").filter(objSessionFilter).when()
				.post("rest/auth/1/session").then().log().body();

	}

//	note in below test case, key value can directly given in post() or via pathParam as well.
//adding comment and retrieving comment ID to check in next case if comment added successfully.
	@Test(priority = 1)
	public void addComment() {

		JsonPath objJsonPath = given().pathParam("id", "10206").log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"" + strInputComment + "\",\r\n" + "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
						+ "}")
				.filter(objSessionFilter).when().post("rest/api/2/issue/{id}/comment").then().assertThat()
				.statusCode(201).extract().response().jsonPath();
		strCommentID = objJsonPath.get("id");
	}

	@Test(priority = 2)
	public void getIssue() {
		
		//below is the simpler form of doing it. But just to explain pathparam and queryparam, it is extended a bit for knowledge purpose.
//		JsonPath objJsonPath = given().filter(objSessionFilter).when().get("rest/api/2/issue/10206").then().extract()
//				.response().jsonPath();
		//note in below queryParam is used to limit the display of json only to comment instead of whole body. this can be done without using query as well, by directly collecting ouput in jsonpath and filtering what we need.
		JsonPath objJsonPath = given().filter(objSessionFilter).pathParam("id", "10206").queryParam("fields", "comment").when().get("rest/api/2/issue/{id}").then().extract().response().jsonPath();
		String[] strID = objJsonPath.get("fields.comment.comments.id");
		List<String> mylist = Arrays.asList(strID);
		List check = mylist.stream().filter(s->s.equals(strCommentID)).collect(Collectors.toList(
//		System.out.println(objJsonPath.getString("fields.comment.comments.body"));
	}
}
