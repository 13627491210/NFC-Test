package com.example.administrator.test.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;

import com.example.administrator.test.smartdevice.utils.StringUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hantao on 2017/11/10.
 */

public class AppSector extends CardSector {
    public AppSector(int m_sector, long cardguid, Tag tag, byte[] myKeyA, Intent intent, MifareClassic mfc) {
        super(m_sector, cardguid, tag, myKeyA, intent, mfc);
    }

    public AppSector(CardSector cardSector) {
        super(cardSector.getM_sector(), cardSector.getCardguid(), cardSector.getTag(), cardSector.getMyKeyA(), cardSector.getIntent(), cardSector.getMfc());
    }

    private String m_datetime;

    public String getM_datetime() {
        return m_datetime;
    }

    public void setM_datetime(String m_datetime) {
        this.m_datetime = m_datetime;
    }

    @Override
    public boolean pushData() {
           // String dt1 = StringUtility.timeToString(m_datetime);
         SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt2 = sf.parse(m_datetime.toString());
            int Year = 0;
            byte Month = 0, Day = 0,Hour=0,Minute=0,Second=0;
            byte[] tmpBlock1 = new byte[16];
            byte[] tmpBlock2 = new byte[16];
            byte[] tmpBlock3 = new byte[16];
            this.clear();


            Year = StringUtility.getYear(dt2);
            Month = (byte) StringUtility.getMonth(dt2);
            Day = (byte) StringUtility.getDay(dt2);
            Hour = (byte)StringUtility.getHour(dt2);
            Minute = (byte)StringUtility.getMinute(dt2);
            Second = (byte)StringUtility.getSecond(dt2);

            tmpBlock1[0] = (byte) (Year - 2000);
            tmpBlock1[1] = (byte) Month;
            tmpBlock1[2] = (byte) Day;
            tmpBlock1[3] = (byte) Hour;
            tmpBlock1[4] = (byte) Minute;
            tmpBlock1[5] = (byte) Second;
           // tmpBlock1=this.CopyTo(tmp1, tmpBlock1, 12);
            setM_block1(tmpBlock1.clone());
        } catch (ParseException e) {
            this.setM_error(e.getMessage());
            return false;
        }
            return true;

        }


    @Override
    public boolean retrieveData() {

       // String dt1 = StringUtility.timeToString(m_datetime);
        byte[] Block1 = getM_block1();
        byte[] Block2 = getM_block2();
        byte[] Block3 = getM_block3();
        try{
       // if (HsopCardData.CheckDataValid(Block1) && HsopCardData.CheckDataValid(Block2) && HsopCardData.CheckDataValid(Block3)) {
        m_datetime = String.valueOf(2000 + (int) (Block1[0])) + '-' + String.valueOf(Block1[1]) + '-' + String.valueOf(Block1[2])
                    + ' ' + String.valueOf(Block1[3]) + ':' + String.valueOf(Block1[4]) + ':' + String.valueOf(Block1[5]);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        } catch (Exception e) {
            this.setM_error(e.getMessage());
            return false;
        }
            return true;
        }// else {
           // return false;
       // }
    //}
}
