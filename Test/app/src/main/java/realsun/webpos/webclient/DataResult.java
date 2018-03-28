package realsun.webpos.webclient;


import java.util.Hashtable;
import java.util.List;

import realsun.webpos.model.Subdata;

public class DataResult {
	public int total=0;
	public String token="";
	public int error=0;
	public String message="";
	public List cmscolumninfo;
	public List data;
	public Hashtable customerdata;
	public Subdata subdata;


}
