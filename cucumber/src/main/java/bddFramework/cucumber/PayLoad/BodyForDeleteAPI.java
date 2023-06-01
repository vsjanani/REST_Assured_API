package bddFramework.cucumber.PayLoad;

import bddFramework.cucumber.POJO.DeleteSerialisation;

public class BodyForDeleteAPI {
	public DeleteSerialisation bodyForDeleteAPI(String strPlaceIDValue) {
	DeleteSerialisation objDeleteSerialisation = new DeleteSerialisation();
	objDeleteSerialisation.setPlace_id(strPlaceIDValue);
	return objDeleteSerialisation;
	}
}
