package realsun.webpos.webclient;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class SaveDataTableParm {
	
	 	public String uniquecolumns="";
	 	public String withoutdata;
	    public String formulalayer="1";
	    public String synchronizedat="1";
	    public String resid="" ;
	    public String data="" ;
	    public List dataoflist=new ArrayList();
	    public void setData()
	    {
	    	this.data= JSON.toJSONString(dataoflist);
	    }
}
