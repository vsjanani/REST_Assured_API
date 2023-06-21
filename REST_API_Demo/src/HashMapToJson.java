import java.util.HashMap;

import org.testng.annotations.Test;

public class HashMapToJson {
	@Test
	public void dum() {
		HashMap<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("name", "janani");
		myMap.put("class", "2");
		System.out.println(myMap);
		//note that, it is still hasmap but its rest assured library job to convert into json when passed inside body() method.
	}

}
