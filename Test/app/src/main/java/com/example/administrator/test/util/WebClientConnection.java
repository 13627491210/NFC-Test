package com.example.administrator.test.util;


import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.Date;


import java.util.Date;

public class WebClientConnection {
	
	public String OpResult="N";
	public String ErrorMsg="" ;
	public String AccessToken="" ;
	public String UserCode="";
	public String Data="";
	public String badgeno="";
    public String unionid="";
    public String enterprisecode="";
    public String loginmethod="default";
    public Header[] getHeaders(){
		 Header[] headers =
    		    {new BasicHeader("userCode",this.UserCode ),new BasicHeader("accessToken",this.AccessToken ),new BasicHeader("badgeno",this.badgeno),
    			 new BasicHeader("unionid",this.unionid ),new BasicHeader("enterprisecode",this.enterprisecode ),new BasicHeader("loginmethod",this.loginmethod),};
    	
    	 return headers;
    }
    public Date conTime;
   
}
