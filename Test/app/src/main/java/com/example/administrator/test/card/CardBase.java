package com.example.administrator.test.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;


import com.example.administrator.test.util.KeyGenerate;

import org.litepal.util.Const;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hantao on 2017/11/9.
 */

public class CardBase {

    private List<CardSector> cardSectorList=new ArrayList<CardSector>();
    private int intSectors;
    private Tag tag;
    private Intent intent;
    private MifareClassic mfc;
    private long cardguid=0;
    private String error="";
    public static    int CardStatusOfLost=55;


    public List<CardSector> getCardSectorList() {
        return cardSectorList;
    }

    public void setCardSectorList(List<CardSector> cardSectorList) {
        this.cardSectorList = cardSectorList;
    }

    public int getIntSectors() {
        return intSectors;
    }

    public void setIntSectors(int intSectors) {
        this.intSectors = intSectors;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public MifareClassic getMfc() {
        return mfc;
    }

    public void setMfc(MifareClassic mfc) {
        this.mfc = mfc;
    }

    public long getCardguid() {
        return cardguid;
    }

    public void setCardguid(long cardguid) {
        this.cardguid = cardguid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private KeyGenerate keyGenerate=new KeyGenerate();
    public CardBase(  int intSectors, Tag tag, Intent intent, MifareClassic mfc, long cardguid) {

        this.intSectors = intSectors;
        this.tag = tag;
        this.intent = intent;
        this.mfc = mfc;
        this.cardguid = cardguid;
        createSectors();
    }

    private void  createSectors()
    {
        cardSectorList.clear();
        for (int i=0;i<intSectors;i++)
        {
            byte[] myKeyA={(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF};
            keyGenerate.GenKey(this.cardguid,i,myKeyA);
            CardSector cardSector= new CardSector(i, this.cardguid, this.tag, myKeyA, this.intent, this.mfc);
            cardSectorList.add(cardSector);
        }
    }

    public boolean VerifyCardKey()
    {
        for (CardSector cardSector:cardSectorList) {

            if (!cardSector.VerifyKey()){
                int i=cardSectorList.indexOf(cardSector);
                this.error="Sector key error:"+String.valueOf(i);
                return false;
            };

        }
        return  true;
    }


}
