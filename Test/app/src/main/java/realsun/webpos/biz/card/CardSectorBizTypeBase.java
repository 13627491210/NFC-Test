package realsun.webpos.biz.card;

/**
 * Created by hantao on 2017/11/9.
 */

public class CardSectorBizTypeBase {
    private String biztype;
    private byte sector=0;

    public CardSectorBizTypeBase(String biztype, byte sector) {
        this.biztype = biztype;
        this.sector = sector;
    }

    public String getBiztype() {
        return biztype;
    }

    public byte getSector() {
        return sector;
    }
}
