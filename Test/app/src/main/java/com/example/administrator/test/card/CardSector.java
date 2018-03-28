package com.example.administrator.test.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;

/**
 * Created by hantao on 2017/11/10.
 */

public class CardSector extends CardSectorBase {
    public CardSector(int m_sector, long cardguid, Tag tag, byte[] myKeyA, Intent intent, MifareClassic mfc) {
        super(m_sector, cardguid, tag, myKeyA, intent, mfc);
    }
    public CardSector(CardSector cardSector) {
        super(cardSector.getM_sector() ,cardSector.getCardguid() ,cardSector.getTag(),cardSector.getMyKeyA(),cardSector.getIntent(),cardSector.getMfc() );
    }
    public long lng1of256=(long)256;
    public long lng2of256=lng1of256*lng1of256;
    public long lng7of256=(long)65536 * (long)65536*(long)65536* (long)256;
    public long lng6of256=(long)65536 * (long)65536*(long)65536;
    public long lng5of256=(long)65536 * (long)65536*(long)256;
    public long lng4of256=(long)65536 * (long)65536;
    public long lng3of256=(long)65536 * (long)256;
    @Override
    public boolean pushData() {
        return false;
    }

    @Override
    public boolean retrieveData() {
        return false;
    }
    protected long getLongFromFirs8Bytes(byte[] Block1){
        long lng=0;
        lng = (HsopCardData.getUnsignedByte(Block1[3]) * lng3of256 + HsopCardData.getUnsignedByte(Block1[2]) * lng2of256 + HsopCardData.getUnsignedByte(Block1[1]) * lng1of256 + HsopCardData.getUnsignedByte(Block1[0]));

        lng=lng+HsopCardData.getUnsignedByte(Block1[4]) * lng4of256+HsopCardData.getUnsignedByte(Block1[5]) * lng5of256+HsopCardData.getUnsignedByte(Block1[6]) * lng6of256+HsopCardData.getUnsignedByte(Block1[7]) * lng7of256;
        return lng;
    }
    protected  void  pushLongToFirst8Bytes(long lng,byte[] tmpBlock1)
    {
        tmpBlock1[7] = ((byte) (lng/ lng7of256));
        tmpBlock1[6] = ((byte) ((lng - tmpBlock1[7] * lng7of256)/ (lng6of256)));
        tmpBlock1[5] = ((byte) ((lng - tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256)/ (lng5of256)));
        tmpBlock1[4] = ((byte) ((lng - tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256- tmpBlock1[5] * lng5of256)/ (lng4of256)));
        tmpBlock1[3] = ((byte) ((lng - tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256 - tmpBlock1[5] * lng5of256- tmpBlock1[4] * lng4of256)/ (lng3of256)));
        tmpBlock1[2] = (byte) ((lng- tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256 - tmpBlock1[5] * lng5of256 - tmpBlock1[4] * lng4of256 - tmpBlock1[3] *lng3of256) / lng2of256);
        tmpBlock1[1] = (byte) ((lng- tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256 - tmpBlock1[5] * lng5of256- tmpBlock1[4] * lng4of256- tmpBlock1[3] * lng3of256 - tmpBlock1[2] * lng2of256) / 256);
        tmpBlock1[0] = (byte) (lng % 256);
    }
}
