package com.example.administrator.test.util;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.entity.StringEntity;


public class NetRestClient {
	public static final String BASE_URL = "http://kingofdinner.realsun.me:9091/";
	public static AsyncHttpClient client = new AsyncHttpClient();
	public static SyncHttpClient  synclient = new SyncHttpClient();
	public static String loginMethod ="api/Account/Login";
	public static String login_error_code = "11201";
	static {
		client.setTimeout(1000 * 10);
	}
	public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
		if (ConnectionChangeReceiver.connectionFla) {
			client.get(getAbsoluteUrl(url), params, responseHandler);
		}

	}
	 
	public static void get(String url, WebClientConnection wcc,
                           JsonHttpResponseHandler responseHandler) {
		if (ConnectionChangeReceiver.connectionFla) {
			Context context = null;

		   RequestParams params=new RequestParams();
		   client.get(  context,url,wcc.getHeaders(),params,responseHandler);

		}

	}
	public static void Post(String url, StringEntity parms, WebClientConnection wcc,
							AsyncHttpResponseHandler responseHandler) {
		
	
		
		if (ConnectionChangeReceiver.connectionFla) {
			 
			Context context = null;
		 
			client.post(context, url, wcc.getHeaders(), parms, "application/json", responseHandler);
		}
	}
	public static void postBySyn(String url, StringEntity parms, JsonHttpResponseHandler responseHandler) {
		
	
		
		if (ConnectionChangeReceiver.connectionFla) {

			Context context = null;
			synclient.post(context, url, parms, "application/json", (ResponseHandlerInterface) responseHandler);
		}
	}

	public static void post(String url, StringEntity parms,
                            AsyncHttpResponseHandler responseHandler) {
		
	
		
		if (ConnectionChangeReceiver.connectionFla) {

			Context context = null;
			client.post(context, getAbsoluteUrl(url), parms, "application/json", responseHandler);
		}
	}
	public static void AsynPost(String url, StringEntity parms,
                                AsyncHttpResponseHandler responseHandler) {
		
	
		
		if (ConnectionChangeReceiver.connectionFla) {
			 
			Context context = null;
			client.post(context, url, parms, "application/json", responseHandler);
		}
	}
	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}


}
