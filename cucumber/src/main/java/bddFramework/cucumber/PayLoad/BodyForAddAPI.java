package bddFramework.cucumber.PayLoad;

import java.util.ArrayList;
import java.util.List;

import bddFramework.cucumber.POJO.AddSerialisation;
import bddFramework.cucumber.POJO.Location;

public class BodyForAddAPI {

	public AddSerialisation bodyForAddAPI() {
		AddSerialisation objAddSerialisation = new AddSerialisation();
		Location objLocation = new Location();
		List<String> myList = new ArrayList<String>();
		myList.add("glouse");
		myList.add("jacket");
		objLocation.setLat(-38.383497);
		objLocation.setLng(26.89857);

		objAddSerialisation.setLocation(objLocation);
		objAddSerialisation.setTypes(myList);
		objAddSerialisation.setAccuracy(60);
		objAddSerialisation.setName("jingliii");
		objAddSerialisation.setLanguage("malayalam");
		objAddSerialisation.setAddress("50, side layout, cohen 09");
		objAddSerialisation.setPhone_number("8220734564");
		objAddSerialisation.setWebsite("https://sanjeevKrish.com");

		return objAddSerialisation;
	}

	public AddSerialisation bodyForAddAPIwithParams(String strName, String strAddress, String strLanguage) {
		AddSerialisation objAddSerialisationwithParams = new AddSerialisation();
		Location objLocation = new Location();
		List<String> myList = new ArrayList<String>();
		myList.add("glouse");
		myList.add("jacket");
		objLocation.setLat(-38.383497);
		objLocation.setLng(26.89857);

		objAddSerialisationwithParams.setLocation(objLocation);
		objAddSerialisationwithParams.setTypes(myList);
		objAddSerialisationwithParams.setAccuracy(60);
		objAddSerialisationwithParams.setName(strName);
		objAddSerialisationwithParams.setLanguage(strLanguage);
		objAddSerialisationwithParams.setAddress(strAddress);
		objAddSerialisationwithParams.setPhone_number("8220734564");
		objAddSerialisationwithParams.setWebsite("https://sanjeevKrish.com");

		return objAddSerialisationwithParams;
	}

}
