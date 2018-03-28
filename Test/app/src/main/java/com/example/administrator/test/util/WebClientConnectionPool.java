package com.example.administrator.test.util;

import android.preference.PreferenceActivity;
import android.util.Log;

import com.example.administrator.test.Interfaces.WebConnectionNotifier;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.Header;

import java.util.Date;


public class WebClientConnectionPool {
	
  private static   WebDbConfig  m_wdbc=null;

public static WebDbConfig getM_wdbc() {
	return m_wdbc;
}

public static void setM_wdbc(WebDbConfig m_wdbc) {
	WebClientConnectionPool.m_wdbc = m_wdbc;
}

public  static void TryCreateWebClientConnection(WebDbConfig m_wdbc, final WebConnectionNotifier Notifier ){
	  String loginMethod="default";
	  StringEntity stringEntity=null ;
	 
	  try { 
	      JSONObject jsonObject=new JSONObject() ;
		  jsonObject.put("Code", m_wdbc.sysUserID); 
		  jsonObject.put("Password", m_wdbc.sysUserPass); 
		  jsonObject.put("loginMethod",loginMethod); 
		
		    stringEntity = new StringEntity(jsonObject.toString()); 
		  } catch (Exception e1) {
		     e1.printStackTrace(); 
		  } 
	  String url=m_wdbc.BASE_URL+m_wdbc.loginMethod;
	  NetRestClient.AsynPost(url, stringEntity, new JsonHttpResponseHandler() {
		  @Override
			public void onFailure(int statusCode, Header[] headers,
                                  String responseBody, Throwable e) {
				super.onFailure(statusCode, headers, responseBody, e);
				 
				WebClientConnection wcc=new WebClientConnection();
				 
				  wcc.OpResult="N";
					wcc.ErrorMsg= String.valueOf(statusCode);
				Notifier.result(wcc);
			}
		  @Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				WebClientConnection wcc=new WebClientConnection();
				if (statusCode == 200) {

					 
					final String OpResult,SessionId;
					try {
						OpResult = response.getString("OpResult");
						 
						
						String jsonString = response.toString();
					
						wcc=(WebClientConnection) JsonUtil.json2obj(jsonString, WebClientConnection.class);
				         wcc.conTime=new Date();
						 
						Notifier.result(wcc);
					} catch (JSONException e) {
					 
					 
						
					    wcc.OpResult="Y";
						wcc.ErrorMsg=e.getMessage();
						Notifier.result(wcc);
					}

				} else {
				}
			}
});
 
	 
}

  
  
  
}
