package com.example.administrator.test.card;

import android.util.Xml;

import java.io.UnsupportedEncodingException;

/**
 * Created by hantao on 2017/9/6.
 */

public class HsopCardData {
    private long m_cardguid;
    private  int m_LockFlag = 1;
    public static byte CheckData(byte[] bData)
    {

        byte tmp = bData[0];
        for (int i = 1; i < 15; i++)
        {
            tmp = (byte)(tmp ^ bData[i]);
        }
        tmp = (byte)(~tmp);

        return tmp;
    }
    public static boolean CheckDataValid(byte[] bData)
    {
        if (CheckData(bData) == bData[15])
        {
            return true;
        }

        return false;
    }
    public static String AsciiByte2UnicodeStr2(byte[] bData)
    {

        String s=new String(bData);
        try {
            byte[] unicodeBytes=s.getBytes("gbk");
            String s1=new String(unicodeBytes);
            return s1;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }


//
//        // Encoding.ASCII.GetString(
//

//        Encoding unicode = Encoding.Unicode;
//        byte[] tmp = Encoding.Convert(gb, unicode, bData);
//
//        return Encoding.Unicode.GetString(tmp,0,tmp.Length );

    }
    public static String AsciiByte2UnicodeStr(byte[] bData)
    {

        try {
            String s=new String(bData,"gbk");
            byte[] unicodeBytes=s.getBytes("gbk");
            String s1=new String(unicodeBytes);
            return s;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }


//
//        // Encoding.ASCII.GetString(
//
//        Encoding gb = Encoding.GetEncoding(936);
//        Encoding unicode = Encoding.Unicode;
//        byte[] tmp = Encoding.Convert(gb, unicode, bData);
//
//        return Encoding.Unicode.GetString(tmp,0,tmp.Length );

    }
    public static byte[] getbytes(int sIndex, int eIndex,byte[] bData)
    {
        byte[] tmp=new byte[(eIndex -sIndex)+1];
        int ii=0;
        for (int i = sIndex; i < (eIndex + 1); i++)
        {
            tmp[ii]=bData[i];
            ii++;
        }
        return tmp;
    }
    public static boolean CheckWalletData(byte[] bData, int sindex, int eindex)
    {
        for (int i = sindex; i < (eindex + 1); i++)
        {
            if (!(bData [i] == (byte)(~bData [i +eindex -sindex +1])))
            {
                return false ;
            }
        }
        return true;
    }
    public static byte[] strToidentityBytes(String identitynumber)
    {
        byte[] tmp = new byte[identitynumber.length() ];
        for (int i = 0; i < identitynumber.length(); i++)
        {
            tmp[i] = Byte.valueOf(identitynumber.substring(i,i));
        }
        return tmp;

    }
    public static long getUnsignedByte (byte data){      //将data字节型数据转换为0~255 (0xFF 即BYTE)。
        return (long)(data&0x0FF);
    }
    public static int getUnsignedByteInt (byte data){      //将data字节型数据转换为0~255 (0xFF 即BYTE)。
        return (int)(data&0x0FF);
    }
    public static String identityBytesTostr(byte [] tmp)
    {
        String num="";
        for (int i = 0; i < tmp.length; i++)
        {
            num = num + String.valueOf(tmp[i]);
        }
        return num;
    }
}
