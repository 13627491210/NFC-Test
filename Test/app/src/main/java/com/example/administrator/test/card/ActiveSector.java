package com.example.administrator.test.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;

/**
 * Created by hantao on 2017/11/10.
 */

public class ActiveSector extends CardSector {
    public ActiveSector(int m_sector, long cardguid, Tag tag, byte[] myKeyA, Intent intent, MifareClassic mfc) {
        super(m_sector, cardguid, tag, myKeyA, intent, mfc);
    }
    public ActiveSector(CardSector cardSector) {
        super(cardSector.getM_sector() ,cardSector.getCardguid() ,cardSector.getTag(),cardSector.getMyKeyA(),cardSector.getIntent(),cardSector.getMfc() );
    }

    @Override
    public boolean pushData() {
        return true;
    }

    @Override
    public boolean retrieveData() {
        return true;
    }
}
