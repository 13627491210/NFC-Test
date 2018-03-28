package com.example.administrator.test.Model;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/23.
 */

public class Teacher extends DataSupport{
    private String name;
    private String photo;
    private String tid;
    private int recid;
    private String cardno;
    private Date datetime;
    private Date enddate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getRecid() {
        return recid;
    }

    public void setRecid(int recid) {
        this.recid = recid;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
