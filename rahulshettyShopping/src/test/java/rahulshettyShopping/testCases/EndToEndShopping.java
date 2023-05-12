package rahulshettyShopping.testCases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import rahulshettyShopping.pojo.ListPlaceOrders;
import rahulshettyShopping.pojo.Login;
import rahulshettyShopping.pojo.PlaceOrder;

public class EndToEndShopping {
	public JsonPath objJsonPath;
	public String strProductId;
	public String strOrderId;

	@Test(priority = 1)
	public void login() {

		RequestSpecification objReqSpec = new RequestSpecBuilder().setRelaxedHTTPSValidation()
				.setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

		Login loginRequest = new Login();
		loginRequest.setUserEmail("jansree90@gmail.com");
		loginRequest.setUserPassword("SanjeevKrish@01");

		RequestSpecification objGivenSpec = given().spec(objReqSpec).log().all().body(loginRequest);
		objJsonPath = objGivenSpec.when().post("api/ecom/auth/login").then().log().body().extract().response()
				.jsonPath();

	}

	@Test(priority = 2)
	public void createProduct() {
		// ContentType is given as multipart only then output works. Even if nothing is
		// given it works. but ContentType.JSON will throw error coz, we are sending
		// form params with multipart of file as input and it is not json form input.
		RequestSpecification objReqSpec = new RequestSpecBuilder().setRelaxedHTTPSValidation()
				.setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.MULTIPART)
				.addHeader("authorization", objJsonPath.getString("token")).build();

		HashMap formParamData = new HashMap();
		formParamData.put("productName", "sanju");
		formParamData.put("productAddedBy", objJsonPath.getString("userId"));
		formParamData.put("productCategory", "fashion");
		formParamData.put("productSubCategory", "shirt");
		formParamData.put("productPrice", "100");
		formParamData.put("productDescription", "hike");
		formParamData.put("productFor", "women");

		RequestSpecification objGivenSpec = given().spec(objReqSpec).formParams(formParamData).multiPart("productImage",
				new File("C://Users//jansr//Downloads//jananiTshirt.png"));
		JsonPath objJsonPath1 = objGivenSpec.when().post("api/ecom/product/add-product").then().log().body().extract()
				.response().jsonPath();
		strProductId = objJsonPath1.getString("productId");
	}

	@Test(priority = 3)
	public void placeOrder() {
		RequestSpecification objReqSpec = new RequestSpecBuilder().setRelaxedHTTPSValidation()
				.setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addHeader("authorization", objJsonPath.getString("token")).build();

		PlaceOrder objPlaceOrder = new PlaceOrder();
		objPlaceOrder.setCountry("india");
		objPlaceOrder.setProductOrderedId(strProductId);

		List<PlaceOrder> lstPlaceOrder = new ArrayList<PlaceOrder>();
		lstPlaceOrder.add(objPlaceOrder);

		ListPlaceOrders objLstPlaceOrders = new ListPlaceOrders();
		objLstPlaceOrders.setOrders(lstPlaceOrder);

		RequestSpecification objGivenSpec = given().spec(objReqSpec).body(objLstPlaceOrders);
		JsonPath objJsonPath2 = objGivenSpec.when().post("api/ecom/order/create-order").then().log().body().extract()
				.response().jsonPath();

		strOrderId = objJsonPath2.getString("orders");	

	}

	@Test(priority=4)
	public void deleteProduct() {
		RequestSpecification objReqSpec = new RequestSpecBuilder().setRelaxedHTTPSValidation()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", objJsonPath.getString("token")).build();
		
		RequestSpecification objGivenSpec = given().spec(objReqSpec).log().all().pathParam("productID", strProductId);
		JsonPath objJsonPath = objGivenSpec.when().delete("api/ecom/product/delete-product/{productID}").then().extract().body().jsonPath();
		
		Assert.assertEquals("Product Deleted Successfully", objJsonPath.getString("message"));
	}

}
