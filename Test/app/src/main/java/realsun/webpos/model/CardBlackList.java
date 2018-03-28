package realsun.webpos.model;

import com.alibaba.fastjson.annotation.JSONField;

import org.litepal.annotation.Column;

/**
 * Created by hantao on 2017/10/25.
 */

public class CardBlackList extends BaseRecord {

    @Column(unique = true )
    @JSONField(name="C3_549831071955")
    private String cardNo;
    @JSONField(name="C3_549831072944")

    private String sysCardno;


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

    public void copyNewCardBlack(CardBlackList cardBlack)
    {
        this.setCardNo(cardBlack.getCardNo());
        this.setSysCardno(cardBlack.getSysCardno());
        this.REC_ID=cardBlack.REC_ID;
        this.REC_RESID=cardBlack.REC_RESID;

    }
    public void updateCardBlack(CardBlackList cardBlack)
    {
        this.setCardNo(cardBlack.getCardNo());
        this.setSysCardno(cardBlack.getSysCardno());

    }

}
