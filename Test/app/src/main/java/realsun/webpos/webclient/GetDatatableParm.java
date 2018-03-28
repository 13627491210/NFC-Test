package realsun.webpos.webclient;

import com.example.administrator.test.util.WebDbConfig;
import com.loopj.android.http.RequestParams;

public class GetDatatableParm {
	public String DataBaseUrl= WebDbConfig.m_BASE_URL;
	public String resid;
	public String subresid="";
	public String cmswhere="";
	public String hostrecid="";
	public String sortorder="";
	public String sortfield="";
	public String key="";
	public String cmscolumns="";
	public String getcolumninfo="0";
	public int pageindex=0;
	public  int pagesize=0;
	public String subcmscolumns="";

	public String getUrlParm(){
		 StringBuffer sb = new StringBuffer();
		 sb.append("resid="+this.resid+"&");
		 sb.append("subresid="+this.subresid+"&");
		 sb.append("cmswhere="+this.cmswhere+"&");
		 sb.append("hostrecid="+this.hostrecid+"&");
		 sb.append("sortorder="+this.sortorder+"&");
		 sb.append("sortfield="+this.sortfield+"&");
		 sb.append("cmscolumns="+this.cmscolumns+"&");
		sb.append("subcmscolumns="+this.subcmscolumns+"&");
		 sb.append("key="+this.key+"&");
		 sb.append("getcolumninfo="+this.getcolumninfo+"&");
		 sb.append("pageindex="+ String.valueOf(this.pageindex)+"&");
		 sb.append("pagesize="+ String.valueOf(this.pagesize)+"&");
		 return sb.toString();
		 
	}
	public RequestParams getRequestParams()
	{
		RequestParams Params=new RequestParams();
		Params.put("resid", this.resid);
		Params.put("subresid", this.subresid);
		Params.put("cmswhere", this.cmswhere);
		Params.put("hostrecid", this.hostrecid);
		Params.put("sortorder", this.sortorder);
		Params.put("sortfield", this.sortfield);
		Params.put("cmscolumns", this.cmscolumns);
		Params.put("key", this.key);
		Params.put("getcolumninfo", this.getcolumninfo);
		Params.put("pageindex", this.pageindex);
		Params.put("pagesize", this.pagesize);
		Params.put("subcmscolumns",this.subcmscolumns);
		return Params;
	}
	
}
