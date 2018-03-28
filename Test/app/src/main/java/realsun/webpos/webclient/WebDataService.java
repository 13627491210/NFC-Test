package realsun.webpos.webclient;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.util.NetRestClient;
import com.example.administrator.test.util.WebClientConnection;
import com.example.administrator.test.util.WebDbConfig;
import com.loopj.android.http.RequestParams;

import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import realsun.webpos.model.Custormerfunctiondata;


public class WebDataService {
	private String m_baseUrl= WebDbConfig.m_BASE_URL;
	public WebDataService(String baseUrl){
		m_baseUrl=baseUrl;

	}
	public WebDataService(){
		m_baseUrl=WebDbConfig.m_BASE_URL;

	}
	public void save(SaveDataTableParm parms,WebClientConnection wcc,final WebDataNotifier wdn) { 
		 StringEntity stringEntity = null;
		 try { 

			  parms.setData();
			  String jsonStr= JSON.toJSONString(parms);
			  
			  stringEntity = new StringEntity(jsonStr, HTTP.UTF_8);
			  
			  } catch (Exception e1) {
			     e1.printStackTrace(); 
			  } 
		 String url=m_baseUrl+WebDbConfig.m_saveMethod;
		 NetRestClient.Post(url, stringEntity,wcc, new DataResultResponseHandler(wdn));
		
		
	}
	public void ProcessCustormerFunction(Custormerfunctiondata parms, WebClientConnection wcc, final WebDataNotifier wdn) {
		StringEntity stringEntity = null;
		try {

			//parms.setData();
			String jsonStr= JSON.toJSONString(parms);

			stringEntity = new StringEntity(jsonStr, HTTP.UTF_8);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String url=m_baseUrl+WebDbConfig.m_ProcessCustormerFunction;
		NetRestClient.Post(url, stringEntity,wcc, new DataResultResponseHandler(wdn));


	}
	public   void retrieve(GetDatatableParm parms,WebClientConnection wcc,final WebDataNotifier wdn){
		 
	 
		if (parms.resid.equalsIgnoreCase(""))
		{
			DataResult result=new DataResult();
			result.message="resid is null";
			wdn.result(result);
			return;
		}
		RequestParams params= new RequestParams();
		String url=m_baseUrl+WebDbConfig.m_retrieveMethod+parms.getUrlParm();
 
		NetRestClient.get(url,wcc, new DataResultResponseHandler(wdn));
	 
		
		 
		
		 
	}
	public   void retrieve2(GetDatatableParm parms,WebClientConnection wcc,final WebDataNotifier wdn){


		if (parms.resid.equalsIgnoreCase(""))
		{
			DataResult result=new DataResult();
			result.message="resid is null";
			wdn.result(result);
			return;
		}
		RequestParams params= new RequestParams();
		String url=m_baseUrl+WebDbConfig.m_retrieveMethod2+parms.getUrlParm();

		NetRestClient.get(url,wcc, new DataResultResponseHandler(wdn));





	}

}
