import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojoFiles.Pojo_Json_Deserialisation;

public class OAuth_Pojo_Deserialisation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		baseURI = "https://rahulshettyacademy.com";
		// it is not possible to get <code> through automation since gmail is not
		// accepting to get hit with automation scripts.
		String strURLGetCode = "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&auth_url=https://accounts.google.com/o/oauth2/v2/auth&state=hello";
//		WebDriver objWebDriver = new ChromeDriver();
//		objWebDriver.get(strURLGetCode);
//		objWebDriver.findElement(By.cssSelector("input[type='email']")).sendKeys("jansree90@gmail.com");
//		objWebDriver.findElement(By.cssSelector(".VfPpkd-RLmnJb")).click();
//		System.out.println("url is" + objWebDriver.getCurrentUrl());
		// hence strURLGetCode has to be given manually in browser to get code. Note
		// code is then sent to get access_token. using access_token we will get course
		// details from rahulshettyacademy.com
		String strURLwithCode = "https://rahulshettyacademy.com/getCourse.php?state=hello&code=4%2F0AbUR2VML4EMJsBpSf9I1Nnz8H4zb1hSzzIHLUkn1hGHsd8dCBfSIgLxRFvdVoZY1rEKLkA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String strCode = StringUtils.substringBetween(strURLwithCode, "code=", "&scope");
		System.out.println(strCode);

		JsonPath objJsonPath = given().urlEncodingEnabled(false).relaxedHTTPSValidation().queryParams("code", strCode)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").jsonPath();		
		String strAccessToken = objJsonPath.getString("access_token");
		
		Pojo_Json_Deserialisation objPojoClass = given().relaxedHTTPSValidation().queryParam("access_token", strAccessToken).expect().defaultParser(Parser.JSON)
		.when().get("getCourse.php")
		.then().log().all().extract().response().as(Pojo_Json_Deserialisation.class);
		System.out.println("expertise is" + objPojoClass.getExpertise());
		System.out.println("course details are "+objPojoClass.getCourses().getWebAutomation().get(1));
		//to get price of "courseTitle": "SoapUI Webservices testing","price": "40" follow below steps.
		//doing via jsonpath is very easy, refer jiraAddComment.java class where we just get list of strings, find index of course and get the price.
		int intAPICourses = objPojoClass.getCourses().getApi().size();//return type is by default. so dont think too much about it.
		for(int i=0; i<intAPICourses; i++) {
			if(objPojoClass.getCourses().getApi().get(i).getCourseTitle().contains("SoapUI Webservices testing")){
				System.out.println("price of soapUI course is" + objPojoClass.getCourses().getApi().get(i).getPrice());
			}
		}

	}

}
