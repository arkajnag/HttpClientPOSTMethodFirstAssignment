package com.HttpClientPost.qa.TestBase;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {

	public static Properties prop;
	public static int Response_Code_Success=200;
	public static int Response_Code_Create=201;
	public static int Response_Code_Auth_Failure=401;
	public static int Response_Code_PageNotFound_Failure=404;
	public static int Response_Code_ServerNotFound_Failure=500;
	
	public TestBase()
	{
		prop=new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream("/Users/arka/Documents/workspace/HttpClientPOSTMethod/src/main/java/com/HttpClientPost/qa/Config/config.properties");
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
