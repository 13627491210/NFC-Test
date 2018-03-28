package realsun.webpos.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/15.
 */

public class Teacher extends BaseRecord {

    @JSONField(name ="C3_574185926549")
    private String name;

    @JSONField(name ="C3_574185941597")
    private String photo;

    @JSONField(name ="C3_574859259975")
    private String tid;

    private String recid;
    @JSONField(name="C3_574962968584")

    private String cardno;
    @JSONField(name = "C3_575202226275")

    private Date datetime;
    @JSONField(name="C3_575202262777")

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

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
