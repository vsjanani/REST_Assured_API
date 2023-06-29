import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import pojoFiles.Del;
public class DeleteBook {
	@Test
	public void chumma() {
		baseURI = "http://216.10.245.166";
		Del objDel = new Del();
		objDel.setID("dddd111");
		given().body(objDel).log().all().when().post("Library/DeleteBook.php").then().log().body();
		
	}
	
}
