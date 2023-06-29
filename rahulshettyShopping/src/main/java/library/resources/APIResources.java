package library.resources;

public enum APIResources {
	
	AddBook("Library/Addbook.php"),
	DeleteBook("Library/DeleteBook.php");
	
	private String strAPIResource;	

	APIResources(String strAPIResource) {
		 this.strAPIResource = strAPIResource;
	}
	
	public String getAPIResource() {
		return strAPIResource;
	}
}
