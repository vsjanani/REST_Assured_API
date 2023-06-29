import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ExcelToHashMapToJson {

	HashMap<String, Object> objHashMap = new HashMap<String, Object>();

	@Test
	public HashMap excelToHashMap() throws IOException {

		FileInputStream objFIS = new FileInputStream(new File("C://Users//jansr//Downloads//DemoDataSetUp.xlsx"));
		XSSFWorkbook objWorkBook = new XSSFWorkbook(objFIS);
		XSSFSheet objWorkSheet = objWorkBook.getSheet("RestAssured");
		Iterator<Row> objRowIterator = objWorkSheet.rowIterator();
		int intTotalRows = objWorkSheet.getPhysicalNumberOfRows();
		DataFormatter objDataFormatter = new DataFormatter();
		for (int i = 0; i < intTotalRows; i++) {
			if (objWorkSheet.getRow(i).getCell(0).getStringCellValue().equals("AddBook")) {
				for (int j = 1; j < objWorkSheet.getRow(i).getLastCellNum(); j++) {
					String strKey = objWorkSheet.getRow(0).getCell(j).getStringCellValue();
					System.out.println(objWorkSheet.getRow(i).getCell(j));
					Object objValue = objDataFormatter.formatCellValue(objWorkSheet.getRow(i).getCell(j));
					objHashMap.put(strKey, objValue);

				}
			}

		}

		System.out.println(objHashMap);
		return objHashMap;

	}

	@Test
	public void addBook() throws IOException {
		baseURI = "http://216.10.245.166";
		RestAssured.basePath = "Library";
		given().header("Content-Type", "application/json").body(excelToHashMap()).when().post("Addbook.php").then().log().body();
	}

}
