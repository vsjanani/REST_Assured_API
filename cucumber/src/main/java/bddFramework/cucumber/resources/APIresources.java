package bddFramework.cucumber.resources;

public enum APIresources {

	ADDAPI("maps/api/place/add/json"),
	GETAPI("maps/api/place/get/json"),
	DELETEAPI("maps/api/place/delete/json");

	private String strAPIResource;

	APIresources(String strAPIResource) {
		// TODO Auto-generated constructor stub
		this.strAPIResource = strAPIResource;
	}

	public String getAPIResource() {
		return strAPIResource;
	}

}
