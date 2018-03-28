package realsun.webpos.webclient;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataResultResponseHandler extends JsonHttpResponseHandler {
	private WebDataNotifier m_wdn;
	public DataResultResponseHandler(WebDataNotifier wdn) {
		// TODO Auto-generated constructor stub
		this.m_wdn=wdn;
	}

	public DataResultResponseHandler() {
		super();
	}

	@Override
	public void onFinish() {
		super.onFinish();
	}

	public DataResultResponseHandler(String encoding) {
		super(encoding);
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
		super.onSuccess(statusCode, headers, response);
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, String responseString) {
		super.onSuccess(statusCode, headers, responseString);
	}

	@Override
	protected Object parseResponse(byte[] responseBody) throws JSONException {
		return super.parseResponse(responseBody);
	}

	@Override
	public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
		super.onFailure(statusCode, headers, throwable, errorResponse);
		DataResult result=new DataResult();
		result.error=-1;
		result.message="statusCode="+ String.valueOf(statusCode)+"throwable:"+throwable.getMessage();
		m_wdn.result(result);
	}

	@Override
	public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
		super.onFailure(statusCode, headers, throwable, errorResponse);
		DataResult result=new DataResult();
		result.error=-1;
		result.message="statusCode="+ String.valueOf(statusCode)+"throwable:"+throwable.getMessage();
		m_wdn.result(result);

	}

	@Override
		public void onFailure(int statusCode, Header[] headers,
                              String responseBody, Throwable e) {
			super.onFailure(statusCode, headers, responseBody, e);
			DataResult result=new DataResult();
			result.error=-1;
			result.message=responseBody;
			m_wdn.result(result);
		}
	  @Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			super.onSuccess(statusCode, headers, response);
			DataResult result=new DataResult();
			if (statusCode == 200) {

			
			 
				try {
					 
					 
					
					String jsonString = response.toString();

					result= com.alibaba.fastjson.JSON.parseObject(jsonString, DataResult.class);

					 
					m_wdn.result(result);
					 
					 
				} catch ( Exception e) {
				 
					result.error=-2;
					result.message=e.getMessage();
					m_wdn.result(result);
				}

			} else {
		
				result.error=statusCode;
				result.message="server error";
				m_wdn.result(result);
			 
			}
		}

}
