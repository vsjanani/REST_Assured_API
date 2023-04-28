import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.testng.Assert;

import files.BodyContent;
import io.restassured.path.json.JsonPath;

public class NestedJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BodyContent objBodyContent = new BodyContent();
		JsonPath objNestedJson = new JsonPath(objBodyContent.nestedJson());
		System.out.println("No. of couses are " + objNestedJson.getInt("courses.size()"));
		int intCourseSize = objNestedJson.getInt("courses.size()");
		System.out.println("Purchase amount is " + objNestedJson.getInt("dashboard.purchaseAmount"));
		System.out.println("Course Titles are "+ objNestedJson.getList("courses.title"));
		System.out.println("Individual course" + objNestedJson.getString("courses[1].title"));
		for(int i=0; i<intCourseSize; i++) {
			System.out.println("Individual titles" + objNestedJson.get("courses["+i+"].title"));
			System.out.println("Individual titles" + objNestedJson.get("courses["+i+"].price"));
		}
		List<Integer> lstTotal = new ArrayList<Integer>();
		System.out.println(objNestedJson.getList("courses"));
		//to print price of one title RPA
		for(int i=0; i<intCourseSize; i++) {
			System.out.println(objNestedJson.getString("courses["+i+"].title"));
			if(objNestedJson.getString("courses["+i+"].title").equals("RPA")) {
				System.out.println("true");
				System.out.println(objNestedJson.getInt("courses["+i+"].price"));
				}
		}
		//to verify if total is equal to price*copies of each course
		for(int i=0; i<intCourseSize; i++) {
			lstTotal.add(objNestedJson.getInt("courses["+i+"].price") * objNestedJson.getInt("courses["+i+"].copies"));
		}
		System.out.println(lstTotal);
		int intTotal = lstTotal.stream().mapToInt(s->s).sum();
		System.out.println(intTotal);
		Assert.assertEquals(intTotal, 910);
		
		JsonPath hi = new JsonPath(objBodyContent.hey());
		System.out.println(hi.getString("fields.comment.comments.body"));
	}
}
