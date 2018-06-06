package com.HttpClientPost.qa.TestCase;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.HttpClientPost.qa.RestClient.HttpClientPostMethod;
import com.HttpClientPost.qa.TestBase.TestBase;
import com.HttpClientPost.qa.UserData.HttpPostUserData;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HttpClientPostMethodTest extends TestBase{

	public HttpClientPostMethodTest()
	{
		super();
	}
	
	String HostingURL,ServiceURL, BaseURI;
	HttpClientPostMethod PostMethodRequest;
	CloseableHttpResponse HttpPostResponse;
	ObjectMapper mapper;
	HttpPostUserData HttpPostExpectedUserData;
	HttpPostUserData HttpPostActualUserData;
	
	@BeforeMethod
	public void SetUp() throws ClientProtocolException, IOException
	{
		HostingURL=prop.getProperty("hosturl");
		ServiceURL=prop.getProperty("serviceurl");
		/***Step1: Setting up the Base URI***/
		BaseURI=HostingURL+ServiceURL;
		
		/***Step2: Initialising the RestClient Java Class Object***/
		PostMethodRequest=new HttpClientPostMethod();
		
		/***Step3: Setting up the Header Entry into the HttpRequest***/
		HashMap<String,String> PostMethodHeader=new HashMap<String,String>();
		PostMethodHeader.put("content-type", "application/json");
		
		/***Step4: Creating the POJO (Plain Old Java Object) and Setting up for Marshaling***/
		/************************************************************************************ 
		 * This is the HttpPostUserData class - POJO; which will be used for Marshaling.	*
		 * Jackson API will be used to convert POJO into JSON Object						*
		 ************************************************************************************/
		
		mapper= new ObjectMapper();
		HttpPostExpectedUserData= new HttpPostUserData("arkajyoti","leader");
		
		String JSONObjectToString= mapper.writeValueAsString(HttpPostExpectedUserData);
		System.out.println("JSON String being Posted::"+JSONObjectToString);
		HttpPostResponse= PostMethodRequest.PostMethodWithHeaders(BaseURI, PostMethodHeader, JSONObjectToString);
		System.out.println("Http Put Response::"+HttpPostResponse);
	}
	
	@Test
	public void PostMethodWithHeadersTest() throws ParseException, IOException
	{
		/** Verifying the Status Code and Status Line of the Response. Expected Value = Actual value **/
		int StatusCode=HttpPostResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code::"+StatusCode);
		Assert.assertEquals(StatusCode, Response_Code_Create, "Status Code is as Expected.");
		
		
		/** Verifying that Response Body with Expected and Actual being Created **/
		/** Fetching the JSONObject from the Response using EntityUtils & JSONObject **/
		String httpresponseStringEntity= EntityUtils.toString(HttpPostResponse.getEntity(), "UTF-8");
		JSONObject JSONhttpresponseStringEntity= new JSONObject(httpresponseStringEntity);
		System.out.println("API Response::"+JSONhttpresponseStringEntity);
		
		/** Converting JSON Object to Java Object **/
		HttpPostActualUserData=mapper.readValue(httpresponseStringEntity, HttpPostUserData.class);
		System.out.println("Expected Name Response::"+HttpPostExpectedUserData.getName()+" & Actual Name Response::"+HttpPostActualUserData.getName());
	}
}
