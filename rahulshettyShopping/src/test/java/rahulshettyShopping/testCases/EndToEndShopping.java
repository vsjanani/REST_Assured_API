package rahulshettyShopping.testCases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import rahulshettyShopping.pojo.Login;

public class EndToEndShopping {
	public JsonPath objJsonPath;

	@Test(priority = 1)
	public void login() {

		RequestSpecification objReqSpec = new RequestSpecBuilder().setRelaxedHTTPSValidation()
				.setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

		Login loginRequest = new Login();
		loginRequest.setUserEmail("jansree90@gmail.com");
		loginRequest.setUserPassword("SanjeevKrish@01");

		RequestSpecification objGivenSpec = given().spec(objReqSpec).body(loginRequest);
		objJsonPath = objGivenSpec.when().post("api/ecom/auth/login").then().log().all().extract().response()
				.jsonPath();

	}

	@Test(priority=2)
	public void createProduct() {
//		System.out.println("token is"+objJsonPath.getString("token"));
		RequestSpecification objReqSpec = new RequestSpecBuilder().setRelaxedHTTPSValidation()
				.setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addHeader("authorization", objJsonPath.getString("token")).build();

		HashMap formParamData = new HashMap();
		formParamData.put("productName", "Super Tshirt");
		formParamData.put("productAddedBy", objJsonPath.getString("userId"));
		formParamData.put("productCategory", "fashion");
		formParamData.put("productSubCategory", "shirt");
		formParamData.put("productPrice", "10000");
		formParamData.put("productDescription", "hike");
		formParamData.put("productFor", "women");
		formParamData.put("productImage", new File("C://Users//jansr//Downloads//jananiTshirt.png"));
		
		RequestSpecification objGivenSpec = given().spec(objReqSpec).formParams(formParamData);
		objGivenSpec.when().post("api/ecom/product/add-product").then().log().all().extract().response().jsonPath();
	}

}
