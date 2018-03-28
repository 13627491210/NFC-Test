package com.example.administrator.test.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by hantao on 2017/9/4.
 */

public class KeyGenerate {
    public String m_strKey1="上海酬勤";
    public String m_strKey2="红房子医";
    public String m_strKey3="院卡系统";
    public static int byte2Int(byte b){
        int r = (int) b;
        return r;
    }
    public static byte int2Byte(int i){
        byte r = (byte) i;
        return r;
    }
    /*
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        String r = "";

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }

        return r;
    }
    /*
     * 字符转换为字节
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /*
     * 16进制字符串转字节数组
     */
    public static byte[] hexString2Bytes(String hex) {

        if ((hex == null) || (hex.equals(""))){
            return null;
        }
        else if (hex.length()%2 != 0){
            return null;
        }
        else{
            hex = hex.toUpperCase();
            int len = hex.length()/2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i=0; i<len; i++){
                int p=2*i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p+1]));
            }
            return b;
        }

    }
    /*
    * 字节数组转字符串
    */
    public static String bytes2String(byte[] b) throws Exception {
        String r = new String(b,"UTF-8");
        return r;
    }
    /*
    * 字符串转字节数组
    */
    public static byte[] string2Bytes(String s){
        byte[] r = s.getBytes();
        return r;
    }
    /*
     * 16进制字符串转字符串
     */
    public static String hex2String(String hex) throws Exception {
        String r = bytes2String(hexString2Bytes(hex));
        return r;
    }
    /*
    * 字符串转16进制字符串
    */
    public static String string2HexString(String s) throws Exception {
        String r = bytes2HexString(string2Bytes(s));
        return r;
    }
    public static String leftPaddingZero(String str, int len)
    {

        //判断str字符串是否为空或者null  
        if (str != null && !"".equals(str))
        {
            if (str.length() < len)
            {//字符串长度小于指定长度，需要左填充  
                //1.使用字符串的格式化，先左填充空格  
                String format = "%" + len + "s";
                String tempResult = String.format(format, str);

                //2.使用String的replace函数将空格转换为指定字符即可  
                String finalResult = tempResult.replace(" ", "0");

                return finalResult;
            }
            else
            {
                return str;
            }
        }
        else
        {
            return "左填充的字符串不能为空！";
        }
    }
    public static String ParseByte2str(byte[] bData)
    {
        String strTemp="";
        for (int i = 0; i < bData.length; i++)
        {
            String tmp= Long.toHexString(bData[i]);
            strTemp=leftPaddingZero(tmp,2);

        }
        return strTemp;
    }
    public static byte[] ParseStr2Byte(String str, byte len)
    {
      //  byte[] bData=new byte[len];
//        String strTemp = "";
//        for (int i = 0; i < len; i++)
//        {
//            strTemp = str.substring(i * 2, (i * 2)+1);
//
//            //bData[i] = Convert.ToByte(strTemp, 16);
//
//        }

        byte[] bData=bData=hexString2Bytes(str);
        return bData;

    }
    public static boolean ParseKeyStr2Byte(String strkey, byte[] byteKey)
    {
        String strTemp="";

        if (strkey.length() == 12)
        {
//            for (int i = 0; i < 6; i++)
//            {
//               // strTemp=strkey.Substring(i*2,2);
//
//               // byteKey[i] = Convert.ToByte(strTemp, 16);
//
//
//
//            }
            byte[] bData=bData=hexString2Bytes(strkey);
            byteKey=bData;
            return true;
        }
        return false;

    }

    private boolean AbstractionGuid(long MifareGUID,   byte[] BufferData)
    {
        byte[] byteBuffer ={0,0,0,0};
//        byteBuffer[3] = Convert.ToByte((MifareGUID / (65536 * 256)) % 256);
        byteBuffer[3] = (byte)((MifareGUID / (65536 * 256)) % 256);
//        byteBuffer[2] = Convert.ToByte(((MifareGUID - byteBuffer[3] * 65536 * 256) / 65536) % 256);
        byteBuffer[2] = (byte)(((MifareGUID - byteBuffer[3] * 65536 * 256) / 65536) % 256);
//        byteBuffer[1] = Convert.ToByte(((MifareGUID - byteBuffer[3] * 65536 * 256 - byteBuffer[2] * 65536) / 256) % 256);
        byteBuffer[1] = (byte)(((MifareGUID - byteBuffer[3] * 65536 * 256 - byteBuffer[2] * 65536) / 256) % 256);
        byteBuffer[0] =(byte)( MifareGUID % 256);
        BufferData[0] = (byte)((115 ^ byteBuffer[0] ^ byteBuffer[1] ^ byteBuffer[2] ^ byteBuffer[3]) % 256);
        BufferData[1] = (byte)((134 + byteBuffer[0] + byteBuffer[1] + byteBuffer[2] + byteBuffer[3]) % 256);
        BufferData[2] =(byte)(((((85 + byteBuffer[0]) ^ byteBuffer[1]) + byteBuffer[2]) ^ byteBuffer[3]) % 256);
        BufferData[3] = (byte)(((((170 ^ byteBuffer[0]) + byteBuffer[1]) ^ byteBuffer[2]) + byteBuffer[3]) % 256);
        BufferData[4] = (byte)((17 ^ BufferData[0] ^ BufferData[1] ^ BufferData[2] ^ BufferData[3]) % 256);
        BufferData[5] = (byte)((68 + BufferData[0] + BufferData[1] + BufferData[2] + BufferData[3]) % 256);
        return true;

    }
    private static int getUnsignedByteInt (byte data){      //将data字节型数据转换为0~255 (0xFF 即BYTE)。
        return (int)(data&0x0FF);
    }
    private boolean AbstractionSector(int sector,  byte[] BufferData)
    {
        int iSector = 0;
        iSector = sector % 16;

        BufferData[0] = (byte)((105 + iSector) % 256);
        BufferData[1] = (byte)((83 ^ (iSector * 3)) % 256);
        BufferData[2] = (byte)((164 + BufferData[1]) % 256);
        BufferData[3] = (byte)((BufferData[0] ^ BufferData[1]) % 256);
        BufferData[4] = (byte)((113 ^ BufferData[iSector % 4]) % 256);
     BufferData[5] = (byte)((90 ^ BufferData[getUnsignedByteInt(BufferData[4])  % 5]) % 256);

      //  BufferData[5] = (byte)((90 ^ BufferData[ (BufferData[4])  % 5]) % 256);
        return true;
    }
    public static byte[] StringToGb2312Bytes(String strX)
    {
        byte[] newtemp;
        try
        {
            byte[] temp = strX.getBytes("utf-8");
            newtemp = new String(temp, "utf-8").getBytes("gbk");//这里写转换后的编码方式
            return newtemp;

        }
        catch(UnsupportedEncodingException e){
 // TODO Auto-generated catch block
            e.printStackTrace();
            return new byte[0];
        }

    }
    private boolean AbstractionStr(String StrSource, byte[] BufferData)
    {
        byte i=0;
        byte ucX = 0;
        byte tmp = 0;
        byte[] byteStr = StringToGb2312Bytes(StrSource);
        BufferData[0] = (byte)(byteStr.length);
        BufferData[1] =(byte)0xa5;
        BufferData[2]=(byte)0x77;
        BufferData[3] =(byte)0x93;
        BufferData[4] =(byte)0xcc;
        BufferData[5] = (byte)0xf0;
        for (i = 0; i < (byteStr.length ); i++)
        {
            tmp = (byte)( i & 0x01);
            if (tmp == 1)
            {
                ucX = (byte)(((int)byteStr[i ]) % 256);
            }
            else
            {
                ucX = (byte)((((int)byteStr[i]) << 1) % 256);
            }
            BufferData[1] =(byte)((BufferData[1] ^ ucX) % 256);
            BufferData[2] = (byte)((BufferData[2] + ucX) % 256);
            BufferData[3] = (byte)(((BufferData[3] ^ BufferData[2])) % 256);
            BufferData[4] = (byte)(((BufferData[4] + BufferData[1])) % 256);
            BufferData[5] =(byte)((byte)(BufferData[5] - BufferData[i % 5]) % 256);
        }
        return true;
    }
    public Boolean GenKey(long MifareGUID, int bSector, byte[] NKey)
    {
        if (m_strKey1 == "")
        {

            return false;
        }
        int i = 0;
        byte[] Buff_Guid ={ 0, 0, 0, 0, 0, 0 }, Buff_Key1 ={ 0, 0, 0, 0, 0, 0 }, Buff_Key2 ={ 0, 0, 0, 0, 0, 0 }, Buff_Key3 ={ 0, 0, 0, 0, 0, 0 }, Buff_Sector ={ 0, 0, 0, 0, 0, 0 };
        byte[] aKeyA={0,0,0,0,0,0};
        byte[] aKeyB={0,0,0,0,0,0};
        AbstractionGuid(MifareGUID,   Buff_Guid);
        AbstractionStr(m_strKey1,   Buff_Key1);
        AbstractionStr(m_strKey2,   Buff_Key2);
        AbstractionStr(m_strKey3,   Buff_Key3);
        AbstractionSector(bSector,   Buff_Sector);
        for (i = 0; i < 6; i++)
        {
            aKeyA[i] =(byte)((Buff_Guid[i] ^ Buff_Key1[i] ^ Buff_Key2[i] ^ Buff_Key3[i] ^ Buff_Sector[i]) % 256);
            aKeyB[i] = (byte)(((byte)(Buff_Guid[i] + Buff_Key1[i] - Buff_Key2[i] + Buff_Key3[i] - Buff_Sector[i])) % 256);
            NKey[i] = (byte)((aKeyA[i]) % 256);

        }


        return true;

    }
}
