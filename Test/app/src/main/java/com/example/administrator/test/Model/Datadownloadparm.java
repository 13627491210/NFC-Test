package com.example.administrator.test.Model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

import realsun.webpos.model.BaseRecord;

/**
 * Created by hantao on 2017/10/15.
 */

public class Datadownloadparm  extends BaseRecord {


    @JSONField(name ="C3_558553893149")
    private String strResid = "";
    @JSONField(name ="C3_558553908066")
    private String strCmswhere = "";
    @JSONField(name ="C3_558553855110")
    private String strTaskid = "";
    @JSONField(name ="C3_558727183700")
    private String strUpdateMethod ="";
    @JSONField(name ="C3_558731070140")
    private String strIsRun = "";
    @JSONField(name ="C3_559337826773")
    private String strPagesize="";

    @JSONField(name ="C3_559337858436")
    private String strColumns="";
    @JSONField(name ="C3_559518162881")
    private String strSubResid = "";
    @JSONField(name ="C3_559518839849")
    private String strSubColumns="";
    @JSONField(name ="C3_562172420756")
    private String strBaseUrl="";

    public String getStrBaseUrl() {
        return strBaseUrl;
    }

    public void setStrBaseUrl(String strBaseUrl) {
        this.strBaseUrl = strBaseUrl;
    }

    public String getStrResid() {
        return strResid;
    }

    public void setStrResid(String strResid) {
        this.strResid = strResid;
    }

    public String getStrCmswhere() {
        return strCmswhere;
    }

    public void setStrCmswhere(String strCmswhere) {
        this.strCmswhere = strCmswhere;
    }

    public String getStrTaskid() {
        return strTaskid;
    }

    public void setStrTaskid(String strTaskid) {
        this.strTaskid = strTaskid;
    }

    public String getStrUpdateMethod() {
        return strUpdateMethod;
    }

    public void setStrUpdateMethod(String strUpdateMethod) {
        this.strUpdateMethod = strUpdateMethod;
    }

    public String getStrIsRun() {
        return strIsRun;
    }

    public void setStrIsRun(String strIsRun) {
        this.strIsRun = strIsRun;
    }

    public String getStrPagesize() {
        return strPagesize;
    }

    public void setStrPagesize(String strPagesize) {
        this.strPagesize = strPagesize;
    }

    public String getStrColumns() {
        return strColumns;
    }

    public void setStrColumns(String strColumns) {
        this.strColumns = strColumns;
    }

    public String getStrSubResid() {
        return strSubResid;
    }

    public void setStrSubResid(String strSubResid) {
        this.strSubResid = strSubResid;
    }

    public String getStrSubColumns() {
        return strSubColumns;
    }

    public void setStrSubColumns(String strSubColumns) {
        this.strSubColumns = strSubColumns;
    }
    public static Datadownloadparm parseObject(String strDatadownloadparm)
    {
        List<Datadownloadparm> listofDatadownloadparm= JSON.parseArray(strDatadownloadparm,Datadownloadparm.class);
        Datadownloadparm datadownloadparm=listofDatadownloadparm.get(0);
        return datadownloadparm;
       // Datadownloadparm datadownloadparm= JSON.parseObject(strDatadownloadparm,Datadownloadparm.class);

    }

}
