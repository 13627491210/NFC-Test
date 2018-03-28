package com.example.administrator.test.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;


import java.io.IOException;

/**
 * Created by hantao on 2017/11/8.
 */

public interface ICardSector {
    public  boolean pushData();
    public boolean retrieveData();
    public void clear();
    public boolean WriteBizDataToSector();
    public boolean ReadBizDataFromSector();
    public boolean VerifyKey();

}
