package realsun.webpos.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by hantao on 2017/9/9.
 */

public class DinnerTimesOfCatteen extends BaseRecord{
    public String getDinnertimecode() {
        return dinnertimecode;
    }

    public void setDinnertimecode(String dinnertimecode) {
        this.dinnertimecode = dinnertimecode;
    }

    public String getCateencode() {
        return cateencode;
    }

    public void setCateencode(String cateencode) {
        this.cateencode = cateencode;
    }

    public int getDinnertypeno() {
        return dinnertypeno;
    }

    public void setDinnertypeno(int dinnertypeno) {
        this.dinnertypeno = dinnertypeno;
    }

    public String getDinnertypename() {
        return dinnertypename;
    }

    public void setDinnertypename(String dinnertypename) {
        this.dinnertypename = dinnertypename;
    }

    public int getStarthour() {
        return starthour;
    }

    public void setStarthour(int starthour) {
        this.starthour = starthour;
    }

    public int getStartminute() {
        return startminute;
    }

    public void setStartminute(int startminute) {
        this.startminute = startminute;
    }

    public int getEndhour() {
        return endhour;
    }

    public void setEndhour(int endhour) {
        this.endhour = endhour;
    }

    public int getEndminute() {
        return endminute;
    }

    public void setEndminute(int endminute) {
        this.endminute = endminute;
    }

    public String getIsovernight() {
        return isovernight;
    }

    public void setIsovernight(String isovernight) {
        this.isovernight = isovernight;
    }

    public String getTimezonestr() {
        return timezonestr;
    }

    public void setTimezonestr(String timezonestr) {
        this.timezonestr = timezonestr;
    }

    /*1 编辑 C3_511308011817 餐别时段编号 文字 15  显示   计算     唯一值
    2 编辑 C3_511300647317 餐厅编号 文字 15  显示 下拉字典
    3 编辑 C3_511308724723 餐别编号 整数 8  显示 下拉字典
    4 编辑 C3_511300714145 餐别时段名称 文字 20  显示     必填项
    5 编辑 C3_512264012739 开始小时 文字 8  显示
    6 编辑 C3_512264016442 开始分钟 文字 8  显示
    7 编辑 C3_512264037911 结束小时 文字 8  显示
    8 编辑 C3_512264038130 结束分钟 文字 8  显示
    9 编辑 C3_512266262005 是否跨夜 文字 1  显示
    10 编辑 C3_513617980805 时段 文字 50  显示   计算
    11 编辑 C3_547741895316 开始s

    */
    @JSONField(name="C3_511308011817")
    private String dinnertimecode ;
    @JSONField(name="C3_511300647317")
    private String cateencode;
    @JSONField(name="C3_511308724723")
    private int dinnertypeno=0;
    @JSONField(name="C3_511300714145")
    private String dinnertypename="暂停营业";
    @JSONField(name="C3_512264012739")
    private int starthour;
    @JSONField(name="C3_512264016442")
    private int startminute;
    @JSONField(name="C3_512264037911")
    private int endhour;
    @JSONField(name="C3_512264038130")
    private int endminute;
    @JSONField(name="C3_512266262005")
    private String isovernight;
    @JSONField(name="C3_513617980805")
    private String timezonestr;


}
