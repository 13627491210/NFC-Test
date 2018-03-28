package com.example.administrator.test.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;


import com.example.administrator.test.util.MifareCard;

import java.io.IOException;

/**
 * Created by hantao on 2017/9/6.
 */

public abstract class CardSectorBase implements  ICardSector{
    public final static String CommonSectorflag="common";
    public  final static String SpecialSectorflag="special";
    public  final static String ActiveSectorflag="active";
    public  final static String AppSectorflag="app";
    public  final static String WalletSectorflag="wallet";
    public CardSectorBase(int m_sector, long cardguid, Tag tag, byte[] myKeyA, Intent intent, MifareClassic mfc) {
        this.m_sector = m_sector;
        this.cardguid = cardguid;
        this.tag = tag;
        this.myKeyA = myKeyA;
        this.intent = intent;
        this.mfc = mfc;
        this.clear();
    }


    private  boolean isUsed=false;
    private int m_sector;
    private byte[] m_emptyblock = {(byte) 255,(byte) (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)
            255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255};
    private byte[] m_block1 = new byte[16];
    private byte[] m_block2 = new byte[16];
    private byte[] m_block3 = new byte[16];
    private StringBuffer m_error=new StringBuffer("");
    private long cardguid=0;
    private Tag tag;
    private byte[] myKeyA={(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF};
    private Intent intent;
    private  MifareClassic mfc;

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public long getCardguid() {
        return cardguid;
    }

    public void setCardguid(long cardguid) {
        this.cardguid = cardguid;
    }

    public StringBuffer getM_error() {
        return m_error;
    }

    public void setM_error(String strError) {
        this.m_error=new StringBuffer(strError);
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

    public byte[] getMyKeyA() {
        return myKeyA;
    }

    public void setMyKeyA(byte[] myKeyA) {
        this.myKeyA = myKeyA;
    }

    public int getM_sector() {
        return m_sector;
    }

    public void setM_sector(int m_sector) {
        this.m_sector = m_sector;
    }

    public byte[] getM_emptyblock() {
        return m_emptyblock;
    }

    public void setM_emptyblock(byte[] m_emptyblock) {
        this.m_emptyblock = m_emptyblock;
    }

    public byte[] getM_block1() {
        return m_block1;
    }

    public void setM_block1(byte[] m_block1) {
        this.m_block1 = m_block1;
    }

    public byte[] getM_block2() {
        return m_block2;
    }

    public void setM_block2(byte[] m_block2) {
        this.m_block2 = m_block2;
    }

    public byte[] getM_block3() {
        return m_block3;
    }

    public void setM_block3(byte[] m_block3) {
        this.m_block3 = m_block3;
    }

    public byte[] getM_block4() {
        return m_block4;
    }

    public void setM_block4(byte[] m_block4) {
        this.m_block4 = m_block4;
    }

    private byte[] m_block4 = new byte[16];
@Override
   public abstract boolean pushData();
    @Override
    public abstract boolean retrieveData();
    private  boolean verifyKey()
    {
        if  (MifareCard.isKeyAMifareClassicEnable(intent,mfc,m_sector,myKeyA))
        {
            return true;
        }
        else
        {
            if (MifareCard.WriteKey(intent,mfc,m_sector,myKeyA,m_error))
            {
               return true;
            }
        }
        setM_error("密码验证失败3");
        return false;
    }
    protected  boolean writeSector(){

        if (verifyKey())
        {
           return MifareCard.WriteSector(mfc,m_sector,m_block1,m_block2,m_block3,m_error);
        }

        return false;
    }
    protected  boolean readSectorRawData(){
        if (verifyKey())
        {
            return MifareCard.ReadSector(mfc,this,m_sector,this.m_error);
        }
        return false;
    }
    public  void clear()
    {
        m_block1=m_emptyblock.clone();
        m_block2 = m_emptyblock.clone();
        m_block3=m_emptyblock.clone();
        m_block4=m_emptyblock.clone();
    }
    public byte[] CopyTo(byte[] source,byte[] data,int startIndex)
    {
        for (int i=0;i<source.length;i++)
        {
            data[startIndex]=source[i];
            startIndex++;
        }

        return  data;
    }
    public boolean WriteBizDataToSector()
    {
        if (  this.pushData())
        {
            try {
                this.getMfc().connect();
            } catch (IOException e)
            {
                e.printStackTrace();
                this.setM_error(e.getMessage());
                return false;
            }
            boolean result=  this.writeSector();
            try {
                this.getMfc().close();
            } catch (IOException e) {
                e.printStackTrace();
                this.setM_error(e.getMessage());
                return false;
            }
            return result;
        }
        return false;

    }
    public boolean RestoreCard()
    {

        try {
            this.getMfc().connect();
        } catch (IOException e)
        {
            e.printStackTrace();
            this.setM_error(e.getMessage());
            return false;
        }
        this.clear();
        boolean result=  this.writeSector();
        try {
            this.getMfc().close();
        } catch (IOException e) {
            e.printStackTrace();
            this.setM_error(e.getMessage());
            return false;
        }
        return result;
    }
    public boolean ReadBizDataFromSector()
    {
        try {
            this.getMfc().connect();
        } catch (IOException e) {
            e.printStackTrace();
            this.setM_error(e.getMessage());
            return false;
        }
        boolean result =   this.readSectorRawData();
        if (result)
        {
            result=retrieveData();
            if (!result)
            {
                this.setM_error(this.m_error.toString());
            }
        }
        else
        {
            this.setM_error(this.m_error.toString());
        }

        try {
            this.getMfc().close();
        } catch (IOException e) {
            e.printStackTrace();
            this.setM_error(e.getMessage());
            return false;
        }
        return result;

    }

    @Override
    public boolean VerifyKey() {
        try {
            this.getMfc().connect();
        } catch (IOException e) {
            e.printStackTrace();
            this.setM_error(e.getMessage());
            return false;
        }
        boolean result=   this.verifyKey();

        try {
            this.getMfc().close();
        } catch (IOException e) {
            e.printStackTrace();
            this.setM_error(e.getMessage());
            return false;
        }
        return result;
    }
}
