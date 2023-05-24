package bddFramework.cucumber.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.commons.io.output.WriterOutputStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {

	static RequestSpecification objReqSpec;
	FileOutputStream objFOS;

	public RequestSpecification requestSpec() throws IOException {

		if (objReqSpec == null) {
			objFOS = new FileOutputStream("TestLogs.txt", false);
		}
		objFOS = new FileOutputStream("TestLogs.txt", true);
		PrintStream objPrintStream = new PrintStream(objFOS);
		objReqSpec = new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri(globalData("baseURI"))
				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(objPrintStream))
				.addFilter(ResponseLoggingFilter.logResponseTo(objPrintStream))
				.addFilter(ErrorLoggingFilter.logErrorsTo(objPrintStream)).build();
		return objReqSpec;
//		}
//		return objReqSpec;

	}

	public String globalData(String strGlobalData) throws IOException {
		Properties objProperties = new Properties();
		FileInputStream objFIS = new FileInputStream(new File(
				System.getProperty("user.dir") + "/src/main/java/bddFramework/cucumber/resources/global.properties"));
		objProperties.load(objFIS);
		return objProperties.getProperty(strGlobalData);
	}

	public String respToJsonPath(Response objWhenResponse, String strExptdKey) {
		String strActualValue = objWhenResponse.then().extract().response().jsonPath().getString(strExptdKey);
		return strActualValue;
	}

}
