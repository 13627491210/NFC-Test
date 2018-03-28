package realsun.webpos.model;

import com.alibaba.fastjson.annotation.JSONField;

import org.litepal.annotation.Column;

import java.util.ArrayList;
import java.util.List;

public class PosOrder extends BaseRecord{



    @JSONField(name ="C3_529485594613")
    private String name;
    @JSONField(name ="C3_529889657644")
    private String badgeno;
    @JSONField(name ="C3_529489922410")
    private String cardno;
    @Column(unique = true,nullable = false)
    @JSONField(name ="C3_512261452989")
	private String orderno;
    @JSONField(name ="C3_512140206692")
    private int  dinnertypeno;
    @JSONField(name ="C3_512140206161")
    private String dates;
    @JSONField(name ="C3_512262270614")
    private String ispaid;
    @JSONField(name ="C3_529085248876")
    private String iscancle;

	@JSONField(name ="C3_530474055358")
	private int winno;
	@JSONField(name ="C3_512140208817")
    private float price;

	@JSONField(name ="C3_512262253052")
    private String isfetch;
	@JSONField(name="C3_512140205598")
	private long memberid=0;
	public int getDinnertypeno() {
		return dinnertypeno;
	}

	public void setDinnertypeno(int dinnertypeno) {
		this.dinnertypeno = dinnertypeno;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	@JSONField(name ="512140171786")
	//@Column (ignore = true)
	private List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
	public String getIsfetch() {
		return isfetch;
	}

	public void setIsfetch(String isfetch) {
		this.isfetch = isfetch;
	}


	public int getWinno() {
		return winno;
	}

	public void setWinno(int winno) {
		this.winno = winno;
	}


   public void setName(String name) {
		this.name = name;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getBadgeno() {
		return badgeno;
	}

	public void setBadgeno(String badgeno) {
		this.badgeno = badgeno;
	}

	public String getName() {
		return name;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getIspaid() {
		return ispaid;
	}

	public void setIspaid(String ispaid) {
		this.ispaid = ispaid;
	}

	public String getIscancle() {
		return iscancle;
	}

	public void setIscancle(String iscancle) {
		this.iscancle = iscancle;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@JSONField(name ="C3_513890663124")
	private String dinnertypename;
	@JSONField(name ="C3_512261273552")
	//C3_512261273552 餐盘类型
	private String dishtype;

	public String getDinnertypename() {
		return dinnertypename;
	}

	public void setDinnertypename(String dinnertypename) {
		this.dinnertypename = dinnertypename;
	}

	public String getDishtype() {
		return dishtype;
	}

	public void setDishtype(String dishtype) {
		this.dishtype = dishtype;
	}
}
