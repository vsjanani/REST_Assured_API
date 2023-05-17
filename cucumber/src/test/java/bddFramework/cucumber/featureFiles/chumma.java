package bddFramework.cucumber.featureFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class chumma {
	static FileOutputStream myObj;
	
	public static void main(String[] args) throws IOException {
		chumma objChumma = new chumma();
		objChumma.createFile();
//		chumma objChumma1 = new chumma();
//		objChumma1.createFile();

}

	public void createFile() throws IOException {
		
		myObj = new FileOutputStream("filename.txt", false);
		myObj.write(20);
		System.out.println("written");
}}
