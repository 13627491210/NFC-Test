package realsun.webpos.model;

import com.alibaba.fastjson.annotation.JSONField;

import org.litepal.annotation.Column;

import java.util.Date;

/**
 * Created by hantao on 2017/9/2.
 */

public class WebPosDefine extends BaseRecord {
    //    C3_530389677320 登入编码 文字 50  显示   计算      联合唯一
    @JSONField(name ="C3_530389677320")
    private String logincode;
    //2 编辑 C3_530122591910 站点号 整数 8  显示          联合唯一
    @Column(unique = true)
    @JSONField(name ="C3_530122591910")
    private int winno=-1;
//3 编辑 C3_530122607223 站点名称 文字 80  显示
    @JSONField(name ="C3_530122607223")
    private String winnname;
//4 编辑 C3_530122630863 loginUrl 文字 800  显示
    @JSONField(name ="C3_530122630863")
    private String loginUrl;
//5 编辑 C3_530122737504 localbaseUrl 文字 800  显示
    @JSONField(name ="C3_530122737504")
    private String localbaseUrl;
//6 编辑 C3_530122651019 user 文字 20  显示
    @JSONField(name ="C3_530122651019")
    private String user;
//7 编辑 C3_530122663363 upass 文字 20  显示
    @JSONField(name ="C3_530122663363")
    private String upass;


    //8 编辑 C3_530122684379 本机Ip 文字 20  显示
    @JSONField(name ="C3_530122684379")
    private String devicemac;
//9 编辑 C3_530124612816 餐厅编号 文字 15  显示 高级字典
    @JSONField(name ="C3_530124612816")
    private String cateencode;
    //10 编辑 C3_530124613082 餐厅名称 文字 20  显示
    @JSONField(name ="C3_530124613082")
    private String cateenname;

    //11 编辑 C3_530389849974 绑定微信号 文字 50  显示
    @JSONField(name ="C3_530389849974")
    private String wxunionid;
    //12 编辑 C3_530399460189 uploadFileUrl 文字 800  显示
    @JSONField(name ="C3_530399460189")
    private String uploadFileUrl;
    //13 编辑 C3_530399471235 httppath 文字 800  显示
    @JSONField(name ="C3_530399471235")
    private String httppath;
    //14 编辑 C3_530458675389 当前餐别编号 整数 8  显示   计算
    @JSONField(name ="C3_530458675389")
    private int dinnertypeno;
    //15 编辑 C3_530458701967 当前餐别 文字 8  显示   计算
    @JSONField(name ="C3_530458701967")
    private String dinnertype;
    //16 编辑 C3_530458931701 当前小时 整数 8  显示   计算
    @JSONField(name ="C3_530458931701")
    private int curhour;
    //17 编辑 C3_530459359201 是否启用 文字 8  显示
    @JSONField(name ="C3_530459359201")
    private String isenabled;
    //18 编辑 C3_530459427248 操作员 文字 20  显示
    @JSONField(name ="C3_530459427248")
    private String operator;
    //19 编辑 C3_530124918144 本地订单更新时间 时间 8  显示
    @JSONField(name ="C3_530124918144")
    private Date orderupdatedate;
    //20 编辑 C3_530459977639 开始时间 时间 8  显示
    @JSONField(name ="C3_530459977639")
    private Date starttime;
    //21 编辑 C3_530459988405 结束时间 时间 8  显示
    @JSONField(name ="C3_530459988405")
    private Date endtime;
    //22 编辑 C3_530553501045 查询日期 时间 8  显示
    @JSONField(name ="C3_530553501045")
    private String dates;
    //23 编辑 C3_530553501264 夜宵领取数 整数 8  显示
    @JSONField(name ="C3_530553501264")
    private int amount4;
    //24 编辑 C3_530553501482 晚餐领取数 整数 8  显示
    @JSONField(name ="C3_530553501482")
    private int amount3;
    //25 编辑 C3_530553501701 中餐领取数 整数 8  显示   计算
    @JSONField(name ="C3_530553501701")
    private int amount2;
    @JSONField(name="C3_558196133837")
    //窗口机类型
    private String postype="unkown";
    @JSONField(name="C3_558466109031")
    private String enterprisecode="";
//    C3_558467039178 版本更新请求地址
@JSONField(name="C3_558467039178")
private String updateurl="";

    @JSONField(name="C3_559303092211")
    private String orderupdateurl="";
    @JSONField(name="C3_563289397583")
    private String runMode="offline";
    @JSONField(name="C3_563299080452")
    private String wsurl="";

    public String getRunMode() {
        return runMode;
    }

    public void setRunMode(String runMode) {
        this.runMode = runMode;
    }

    public String getWsurl() {
        return wsurl;
    }

    public void setWsurl(String wsurl) {
        this.wsurl = wsurl;
    }

    public String getOrderupdateurl() {
        return orderupdateurl;
    }

    public void setOrderupdateurl(String orderupdateurl) {
        this.orderupdateurl = orderupdateurl;
    }


    public String getUpdateurl() {
        return updateurl;
    }

    public void setUpdateurl(String updateurl) {
        this.updateurl = updateurl;
    }

    public String getEnterprisecode() {
        return enterprisecode;
    }

    public void setEnterprisecode(String enterprisecode) {
        this.enterprisecode = enterprisecode;
    }

    public String getPostype() {
        return postype;
    }

    public void setPostype(String postype) {
        this.postype = postype;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @JSONField(name="C3_558202774441")
    //窗口机类型
    private String classname="unkown";

    public String getLogincode() {
        return logincode;
    }

    public void setLogincode(String logincode) {
        this.logincode = logincode;
    }

    public int getWinno() {
        return winno;
    }

    public void setWinno(int winno) {
        this.winno = winno;
    }

    public String getWinnname() {
        return winnname;
    }

    public void setWinnname(String winnname) {
        this.winnname = winnname;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLocalbaseUrl() {
        return localbaseUrl;
    }

    public void setLocalbaseUrl(String localbaseUrl) {
        this.localbaseUrl = localbaseUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }




    public String getCateencode() {
        return cateencode;
    }

    public void setCateencode(String cateencode) {
        this.cateencode = cateencode;
    }

    public String getCateenname() {
        return cateenname;
    }

    public void setCateenname(String cateenname) {
        this.cateenname = cateenname;
    }

    public String getWxunionid() {
        return wxunionid;
    }

    public void setWxunionid(String wxunionid) {
        this.wxunionid = wxunionid;
    }

    public String getUploadFileUrl() {
        return uploadFileUrl;
    }

    public void setUploadFileUrl(String uploadFileUrl) {
        this.uploadFileUrl = uploadFileUrl;
    }

    public String getHttppath() {
        return httppath;
    }

    public void setHttppath(String httppath) {
        this.httppath = httppath;
    }

    public int getDinnertypeno() {
        return dinnertypeno;
    }

    public void setDinnertypeno(int dinnertypeno) {
        this.dinnertypeno = dinnertypeno;
    }

    public String getDinnertype() {
        return dinnertype;
    }

    public void setDinnertype(String dinnertype) {
        this.dinnertype = dinnertype;
    }

    public int getCurhour() {
        return curhour;
    }

    public void setCurhour(int curhour) {
        this.curhour = curhour;
    }

    public String getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(String isenabled) {
        this.isenabled = isenabled;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOrderupdatedate() {
        return orderupdatedate;
    }

    public void setOrderupdatedate(Date orderupdatedate) {
        this.orderupdatedate = orderupdatedate;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public int getAmount4() {
        return amount4;
    }

    public void setAmount4(int amount4) {
        this.amount4 = amount4;
    }

    public int getAmount3() {
        return amount3;
    }

    public void setAmount3(int amount3) {
        this.amount3 = amount3;
    }

    public int getAmount2() {
        return amount2;
    }

    public void setAmount2(int amount2) {
        this.amount2 = amount2;
    }
    public String getDevicemac() {

        return devicemac;
    }

    public void setDevicemac(String devicemac) {
        this.devicemac = devicemac;
    }


//    //
}
