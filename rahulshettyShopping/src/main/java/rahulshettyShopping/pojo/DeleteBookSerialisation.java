package rahulshettyShopping.pojo;

public class DeleteBookSerialisation {
	public DeleteBook deletePayload(String strID) {
		DeleteBook objDel = new DeleteBook();
		objDel.setID(strID);
		return objDel;
	}
}
