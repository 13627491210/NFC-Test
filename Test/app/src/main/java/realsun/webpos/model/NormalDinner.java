package realsun.webpos.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by hantao on 2017/9/19.
 */

public class NormalDinner extends BaseRecord {

    @JSONField(name ="C3_547741366226")
    private String ispaid="N";
    @JSONField(name ="C3_547742645012")
    private String isfetch="N";
    @JSONField(name ="C3_548176667882")
    private String enterprisename="";
    @JSONField(name ="C3_548176693655")
    private String cateenname="";
    @JSONField(name ="C3_548176711707")
    private String personid="";
    @JSONField(name ="C3_548270100837")
    private String badgeno="";
    @JSONField(name ="C3_548176854517")
    private float price=0;
    @JSONField(name ="C3_548176773961")
    private String personname="";
    @JSONField(name ="C3_548176785859")
    private String dates="";
    @JSONField(name ="C3_548176798567")
    private String dinnertype="";
    @JSONField(name ="C3_548176839937")
    private String dishtype="";

    public String getIspaid() {
        return ispaid;
    }

    public void setIspaid(String ispaid) {
        this.ispaid = ispaid;
    }

    public String getIsfetch() {
        return isfetch;
    }

    public void setIsfetch(String isfetch) {
        this.isfetch = isfetch;
    }

    public String getEnterprisename() {
        return enterprisename;
    }

    public void setEnterprisename(String enterprisename) {
        this.enterprisename = enterprisename;
    }

    public String getCateenname() {
        return cateenname;
    }

    public void setCateenname(String cateenname) {
        this.cateenname = cateenname;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getBadgeno() {
        return badgeno;
    }

    public void setBadgeno(String badgeno) {
        this.badgeno = badgeno;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getDinnertype() {
        return dinnertype;
    }

    public void setDinnertype(String dinnertype) {
        this.dinnertype = dinnertype;
    }

    public String getDishtype() {
        return dishtype;
    }

    public void setDishtype(String dishtype) {
        this.dishtype = dishtype;
    }
}
    /*
    requestid requestid 文字 15  显示
2 编辑 C3_547741366226 支付状态 文字 800  显示   计算
3 编辑 C3_547742645012 是否已领取 文字 800  显示   计算
4 编辑 paramName paramName 文字 20  显示
5 编辑 C3_548176667882 企业名称 文字 20  显示
6 编辑 C3_548176693655 餐厅 文字 20  显示
7 编辑 C3_548176711707 员工编号 文字 20  显示
8 编辑 C3_548176773961 员工姓名 文字 20  显示
9 编辑 C3_548176785859 日期 文字 8  显示
10 编辑 C3_548176798567 餐别 文字 8  显示
11 编辑 C3_548176839937 餐盘类型 文字 8  显示
12 编辑 C3_548176854517 价格 小数 8  显示       2
13 编辑 C3_548270100837 员工工号 文字

    * */
