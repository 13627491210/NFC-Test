package com.example.administrator.test.util;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;


import com.example.administrator.test.Interfaces.CardGuidNotify;
import com.example.administrator.test.Interfaces.KeyChangedNotify;
import com.example.administrator.test.card.CardSectorBase;

import java.io.IOException;

/**
 * Created by hantao on 2017/8/28.
 */

public class MifareCard {
    public static  boolean ReadBlock(MifareClassic mfc, CardSectorBase cardSector, int dataIndex, int blockIndex, StringBuffer strError )
    {
        try
        {

            byte[] result= mfc.readBlock(blockIndex);
            if (dataIndex==1) {  cardSector.setM_block1(result.clone()); }
            if (dataIndex==2) {  cardSector.setM_block2(result.clone()); }
            if (dataIndex==3) {  cardSector.setM_block3(result.clone()); }

        }
        catch (Exception ex)
        {
            strError=strError.append(ex.getMessage());
            return false;
        }

        return true;
    }
    public static  boolean WriteBlock(MifareClassic mfc, int blockIndex, byte[] data, StringBuffer strError )
    {
        try
        {

            mfc.writeBlock(blockIndex, data);
        }
        catch (Exception ex)
        {
            strError=strError.append(ex.getMessage());
            return false;
        }
        return true;
    }

    public static  boolean WriteBlock(MifareClassic mfc, int sector, int sectorBlock, byte[] data, StringBuffer strError )
    {
         return WriteBlock(mfc,(sector*4+sectorBlock),data,strError);
    }
    public static  boolean WriteSector(MifareClassic mfc, int sector, byte[] data1, byte[] data2, byte[] data3, StringBuffer strError )
    {
        int sectorBlock=sector*4;
        if (WriteBlock(mfc,sectorBlock,data1,strError))
        {
            if (WriteBlock(mfc,sectorBlock+1,data2,strError)){
                if (WriteBlock(mfc,sectorBlock+2,data3,strError)){
                    return true;
                }
                else
                {
                    return false;
                }

            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

    }
    public static  boolean ReadSector(MifareClassic mfc, CardSectorBase cardSector, int sector, StringBuffer strError)
    {
        int sectorBlock=sector*4;
        if (ReadBlock(mfc,cardSector,1,sectorBlock,strError))
        {
            if (ReadBlock(mfc,cardSector,2,sectorBlock+1,strError)){
                if (ReadBlock(mfc,cardSector,3,sectorBlock+2,strError)){
                    return true;
                }
                else
                {
                    return false;
                }

            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

    }
    public static void WriteKeyA(Intent intent, MifareClassic mfc, Tag tag , int sectorIndex, byte[] myKeyA, boolean mfcclose, KeyChangedNotify Kcn){
        try {

            mfc.connect();
            if (isKeyMifareClassicEnable(  intent,mfc,sectorIndex,myKeyA) && myKeyA != null){


                byte[]  KeyValue = mfc.readBlock(4*sectorIndex+3);
                mfc.close();

                for(int i = 0;i<6;i++){
                    KeyValue[i] = myKeyA[i];
                }
                mfc.connect();
                if (isKeyMifareClassicEnable(  intent,mfc,sectorIndex,myKeyA))
                {
                    mfc.writeBlock(4*sectorIndex+3, KeyValue);
                }

                mfc.close();

                Kcn.result(true,"");
                return;
            }
            else
            {
                mfc.close();
                Kcn.result(false,"密码验证失败");
            }


        } catch (Exception e) {
            e.printStackTrace();
            Kcn.result(false,e.getMessage());
        }
        finally {
            try {
                if (mfc.isConnected())
                {
                    mfc.close();
                }



            } catch (Exception e) {
                Kcn.result(false,e.getMessage());

            }
        }


        Kcn.result(false,"");
        return;

    }
    public static boolean WriteKeyA(Intent intent, MifareClassic mfc, int sectorIndex, byte[] myKeyA, StringBuffer strError){
        try {

            mfc.connect();
            if (isKeyMifareClassicEnable(  intent,mfc,sectorIndex,myKeyA) && myKeyA != null){


                byte[]  KeyValue = mfc.readBlock(4*sectorIndex+3);
                mfc.close();

                for(int i = 0;i<6;i++){
                    KeyValue[i] = myKeyA[i];
                }
                mfc.connect();
                if (isKeyMifareClassicEnable(  intent,mfc,sectorIndex,myKeyA))
                {
                    mfc.writeBlock(4*sectorIndex+3, KeyValue);
                }

                mfc.close();


                return true;
            }
            else
            {
                mfc.close();
                strError=new StringBuffer("密码验证失败1");
               return false;
            }


        } catch (Exception e) {
            e.printStackTrace();

            strError=new StringBuffer(e.getMessage());
            return false;
        }
        finally {
            try {
                if (mfc.isConnected())
                {
                    mfc.close();
                }



            } catch (Exception e) {
                strError=new StringBuffer(e.getMessage());
                return false;

            }
        }




    }
    public static boolean isKeyMifareClassicEnable(Intent intent, MifareClassic mfc, int sectorIndex, byte[] myKeyA ){
        boolean auth = false;

        try {
            auth = mfc.authenticateSectorWithKeyA(sectorIndex,
                    MifareClassic.KEY_DEFAULT);
            if(auth==false){
                auth = mfc.authenticateSectorWithKeyA(sectorIndex,myKeyA);
            }

        } catch (IOException e) {

            return false;
        }
        return auth;
    }
    public static boolean isDefaultKeyMifareClassicEnable(Intent intent, MifareClassic mfc, int sectorIndex){
        boolean auth = false;

        try {
            auth = mfc.authenticateSectorWithKeyA(sectorIndex,
                    MifareClassic.KEY_DEFAULT);
            if(auth==false){
                return  false;
            }

        } catch (IOException e) {

            return false;
        }
        return auth;
    }
    public static boolean isKeyAMifareClassicEnable(Intent intent, MifareClassic mfc, int sectorIndex, byte[] myKeyA){
        boolean auth = false;

        try {
            auth = mfc.authenticateSectorWithKeyA(sectorIndex,
              myKeyA);
            if(auth==false){
                return  false;
            }

        } catch (IOException e) {

            return false;
        }
        return auth;
    }
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);

            stringBuilder.append(buffer);

        }
        return stringBuilder.toString();
    }

    private static String bytesToHexString2(byte[] src) {

        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);

            stringBuilder.append(buffer);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
    public  static  void getCardGuid(Intent intent, MifareClassic mfc, CardGuidNotify cgn, boolean closemfc)
    {

        try {

            byte[] myNFCID = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);

            int before = (int) Long.parseLong(bytesToHexString(myNFCID), 16);

            int r24 = before >> 24 & 0x000000FF;
            int r8 = before >> 8 & 0x0000FF00;
            int l8 = before << 8 & 0x00FF0000;
            int l24 = before << 24 & 0xFF000000;
            long cardno= Long.parseLong(
                    Integer.toHexString((r24 | r8 | l8 | l24)), 16);
            cgn.getCardGuid(cardno);
        } catch (Exception e) {
            e.printStackTrace();
         //   cgn.getCardGuid(0);

        }

    }
    public  static void cardIncoming(Intent intent){
        boolean auth = false;
        String cardStr="";
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        cardStr="ID："+bytesToHexString(tag.getId());

        String[] techList = tag.getTechList();
        boolean haveMifareUltralight = false;
        cardStr+="\r\nTECH：";
        for (String tech : techList) {
            cardStr+=tech+",";
            if (tech.indexOf("MifareClassic") >= 0) {
                haveMifareUltralight = true;
                break;
            }
        }

        MifareClassic mfc = MifareClassic.get(tag);

        try {
            String metaInfo = "";
            // Enable I/O operations to the tag from this TagTechnology object.
            mfc.connect();
            int type = mfc.getType();// 获取TAG的类型
            int sectorCount = mfc.getSectorCount();// 获取TAG中包含的扇区数
            String typeS = "";
            switch (type) {
                case MifareClassic.TYPE_CLASSIC:
                    typeS = "TYPE_CLASSIC";
                    break;
                case MifareClassic.TYPE_PLUS:
                    typeS = "TYPE_PLUS";
                    break;
                case MifareClassic.TYPE_PRO:
                    typeS = "TYPE_PRO";
                    break;
                case MifareClassic.TYPE_UNKNOWN:
                    typeS = "TYPE_UNKNOWN";
                    break;
            }
            byte[] myNFCID = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);

            int before = (int) Long.parseLong(bytesToHexString(myNFCID), 16);

            int r24 = before >> 24 & 0x000000FF;
            int r8 = before >> 8 & 0x0000FF00;
            int l8 = before << 8 & 0x00FF0000;
            int l24 = before << 24 & 0xFF000000;

            metaInfo += "ID(dec):"
                    + Long.parseLong(
                    Integer.toHexString((r24 | r8 | l8 | l24)), 16)
                    + "\nID(hex):" + bytesToHexString2(myNFCID) + "\nType："
                    + typeS + "\nSector：" + sectorCount + "\n Block："
                    + mfc.getBlockCount() + "\nSize： " + mfc.getSize() + "B";





        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (mfc != null) {
                    mfc.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static boolean WriteKey(Intent intent, MifareClassic mfc, int sectorIndex, byte[] myKeyA, StringBuffer strError){
        try {


            if (isKeyMifareClassicEnable(  intent,mfc,sectorIndex,myKeyA) && myKeyA != null){


                byte[]  KeyValue = mfc.readBlock(4*sectorIndex+3);

                for(int i = 0;i<6;i++){
                    KeyValue[i] = myKeyA[i];
                }

                if (isKeyMifareClassicEnable(  intent,mfc,sectorIndex,myKeyA))
                {
                    mfc.writeBlock(4*sectorIndex+3, KeyValue);
                }



                return true;
            }
            else
            {

                strError=new StringBuffer("WriteKey 密码验证失败");
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();

            strError=new StringBuffer(e.getMessage());
            return false;
        }
        finally {
            try {
                if (mfc.isConnected())
                {

                }



            } catch (Exception e) {
                strError=new StringBuffer(e.getMessage());
                return false;

            }
        }




    }
}
