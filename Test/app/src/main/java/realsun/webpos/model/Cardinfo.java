package realsun.webpos.model;

import com.alibaba.fastjson.annotation.JSONField;

import org.litepal.annotation.Column;

import java.util.Date;

/**
 * Created by hantao on 2017/10/15.
 */

public class Cardinfo extends BaseRecord {
    @JSONField(name="C3_530464189717")

    private String holderCode;

    @JSONField(name="C3_549575617088")
    private String holderHrCode;

    @Column(unique = true )
    @JSONField(name="C3_529881862316")
    private String cardNo;
    @JSONField(name="C3_529882158973")
    private String holderName;
    @JSONField(name="C3_529881838223")
    private String badgeno="";
    @JSONField(name="C3_529882914973")
    private String holderType;
    @JSONField(name="C3_529882914973")
    private String cardType;
    @JSONField(name="C3_529881869488")
    private String cardStatus;
    @JSONField(name="C3_562257038936")
    private Date startDate;
    @JSONField(name="C3_562257042280")
    private Date endDate;
    @Column(unique = true )
    @JSONField(name="C3_549830088060")
    private String sysCardno;
    @JSONField(name="C3_529881809035")
    private String enterpriseCode;
    @JSONField(name="C3_529181816435")
    private Date datetime;





    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getHolderHrCode() {
        return holderHrCode;
    }

    public void setHolderHrCode(String holderHrCode) {
        this.holderHrCode = holderHrCode;
    }

    public String getHolderCode() {
        return holderCode;
    }

    public void setHolderCode(String holderCode) {
        this.holderCode = holderCode;
    }
    public String getSysCardno() {
        return sysCardno;
    }

    public void setSysCardno(String sysCardno) {
        this.sysCardno = sysCardno;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getBadgeno() {
        return badgeno;
    }

    public void setBadgeno(String badgeno) {
        this.badgeno = badgeno;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public  void  copyNewCard(Cardinfo card){
        this.setBadgeno(card.getBadgeno());
        this.setCardStatus(card.getCardStatus());
        this.setCardType(card.getCardType());
        this.setEndDate(card.getEndDate());
        this.setStartDate(card.getStartDate());
        this.setCardStatus(card.getCardStatus());
        this.setEnterpriseCode(card.getEnterpriseCode());
        this.setHolderCode(card.getHolderCode());
        this.setHolderName(card.getHolderName());
        this.setHolderType(card.getHolderType());
        this.setHolderHrCode(card.getHolderHrCode());
        this.setSysCardno(card.getSysCardno());
        this.setCardNo(card.getCardNo());
        this.REC_ID=card.REC_ID;
        this.REC_RESID=card.REC_RESID;
        this.datetime=card.datetime;
    }
    public  void  updateCard(Cardinfo card){
        this.setBadgeno(card.getBadgeno());
        this.setCardStatus(card.getCardStatus());
        this.setCardType(card.getCardType());
        this.setEndDate(card.getEndDate());
        this.setStartDate(card.getStartDate());
        this.setCardStatus(card.getCardStatus());
        this.setEnterpriseCode(card.getEnterpriseCode());
        this.setHolderCode(card.getHolderCode());
        this.setHolderName(card.getHolderName());
        this.setHolderType(card.getHolderType());
        this.setHolderHrCode(card.getHolderHrCode());
        this.setDatetime(card.datetime);



    }
}
