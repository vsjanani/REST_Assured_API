package library.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {
	static RequestSpecification objReqSpec;
	FileOutputStream objFOS;

	HashMap<String, Object> objHashMap = new HashMap<String, Object>();

	public RequestSpecification reqSpecs() throws IOException {
		if (objReqSpec == null) {
			objFOS = new FileOutputStream("TestLogs.txt", false);
		} else {
			objFOS = new FileOutputStream("TestLogs.txt", true);
		}
		PrintStream objPrtStrm = new PrintStream(objFOS);
		objReqSpec = new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri(getGlobalData("baseURI"))
				.setContentType("application/json").addFilter(RequestLoggingFilter.logRequestTo(objPrtStrm))
				.addFilter(ResponseLoggingFilter.logResponseTo(objPrtStrm)).build();
		return objReqSpec;

	}
	
	public String resToJsonPathString(Response objWhenRes, String strKey) {
		String strValue = objWhenRes.then().extract().response().jsonPath().getString(strKey);
		return strValue;
		
	}

	public String getGlobalData(String strBaseURI) throws IOException {
		FileInputStream objFIS = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/main/java/library/resources/global.properties"));
		Properties objProp = new Properties();
		objProp.load(objFIS);
		return objProp.getProperty(strBaseURI);

	}

	public HashMap excelToHashMap(String strExcelRowName) throws IOException {

		FileInputStream objFIS = new FileInputStream(new File("C://Users//jansr//Downloads//DemoDataSetUp.xlsx"));
		XSSFWorkbook objWorkBook = new XSSFWorkbook(objFIS);
		XSSFSheet objWorkSheet = objWorkBook.getSheet("RestAssured");
		int intTotalRows = objWorkSheet.getPhysicalNumberOfRows();
		DataFormatter objDataFormatter = new DataFormatter();
		for (int i = 0; i < intTotalRows; i++) {
			if (objWorkSheet.getRow(i).getCell(0).getStringCellValue().equals(strExcelRowName)) {
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
}
