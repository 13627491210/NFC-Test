package realsun.webpos.model;

import com.alibaba.fastjson.annotation.JSONField;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.Date;

public class BaseRecord extends DataSupport {
	@Column(ignore = true)
	public int _id=0;
	@Column(ignore = true)
	public String _state="added";
	@Column(ignore = true)
	public String _dberror="";
	@Column(ignore = true)
	public String _error="";
	@JSONField(name="REC_RESID")
	public String REC_RESID;
	@JSONField(name="REC_ID")
	@Column(unique = true )
	public String REC_ID;
	@Column(ignore = true)
	private Date REC_CRTTIME=new Date();
	@Column(ignore = true)
	private Date REC_EDTTIME=new Date();








}
