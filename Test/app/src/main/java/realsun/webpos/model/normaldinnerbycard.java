package realsun.webpos.model;

/**
 * Created by hantao on 2017/9/16.
 */

public class normaldinnerbycard extends BaseRecord {
//    posno 站点号
//    cardno 卡号
//    dates 供应日期
    private int posno=0;

    public int getPosno() {
        return posno;
    }

    public void setPosno(int posno) {
        this.posno = posno;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    private String cardno;
    private String dates;
}
