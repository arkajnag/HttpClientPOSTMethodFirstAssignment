package com.HttpClientPost.qa.RestClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.HttpClientPost.qa.TestBase.TestBase;

public class HttpClientPostMethod extends TestBase{

	public CloseableHttpResponse PostMethodWithHeaders(String URI,HashMap<String, String> HashMapHeader,String EntityString) throws ClientProtocolException, IOException
	{
		/***Step:1 Setting up the default Http Client Connection between two Systems ***/
		CloseableHttpClient httpClient= HttpClients.createDefault();
		
		/***Step:2 Setting up the Post Class Object with URI to process the data ***/
		HttpPost httpPostRequest= new HttpPost(URI);
		
		/***Step:3 Setting up the Header Entry into the Post Request by HashMap and Map Entry Loop & Iterating with EntrySet() ***/
		for(Map.Entry<String, String> entry: HashMapHeader.entrySet())
		{
			/***Step:4 Adding the Header Entry with Key and Value Pair ***/
			httpPostRequest.addHeader(entry.getKey(), entry.getValue());
		}
		
		/***Step:5 Setting up the PayLoad/Body for HttpPost request ***/
		httpPostRequest.setEntity(new StringEntity(EntityString));
		
		/***Step:6 Executing the HttpPost request ***/
		CloseableHttpResponse httpResponse= httpClient.execute(httpPostRequest);
		
		/***Step:7 Returning the Response ***/
		return httpResponse;
	}
}
