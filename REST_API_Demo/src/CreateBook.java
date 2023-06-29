import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import pojoFiles.Create;
import pojoFiles.Del;
public class CreateBook {
	@Test
	public void chumma() {
		baseURI = "http://216.10.245.166";
		Create objCreate = new Create();
		objCreate.setAisle("123");
		objCreate.setAuthor("krish");
		objCreate.setIsbn("come");
		objCreate.setName("dinesh");
		given().body(objCreate).log().all().when().post("Library/Addbook.php").then().log().body();
		
	}
	
}
