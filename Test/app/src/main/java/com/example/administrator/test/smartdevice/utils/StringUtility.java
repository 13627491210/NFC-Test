package com.example.administrator.test.smartdevice.utils;

import android.text.TextUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtility 
{
	/**@author xuxl
	 * @param strInput 传入String
	 * @return boolean 传入的String是否为空
	 * */
	static public boolean isEmpty(String strInput)
	{
	    return TextUtils.isEmpty(strInput);
	}
	
	public static String getStringFormat(byte[] bytes){
		String str = "";
		for(byte b : bytes){
			str += String.format("%02X ", b);
		}
		return str;
	}
	
	static protected boolean CheckByte(byte byteIn)
	{
		//'0' - '9'
		if(byteIn <= 0x39 && byteIn >= 0x30)
			return true;
		//'A' - 'F'
		if(byteIn <= 0x46 && byteIn >= 0x41)
			return true;
		//'a' - 'f'
		if(byteIn <= 0x66 && byteIn >= 0x61)
			return true;
		return false;
	}
	static protected boolean CheckString(String strInput)
	{
		strInput = strInput.trim();
		if(strInput.length() != 2)
			return false;
		byte[] byteArry = strInput.getBytes();
		for(int i = 0; i < 2; i++)
		{
			if(!CheckByte(byteArry[i]))
				return false;
		}
		return true;
	}
	
	static protected byte StringToByte(String strInput)
	{
		byte[] byteArry = strInput.getBytes();
		for(int i = 0; i < 2; i++)
		{
			
			if(byteArry[i] <= 0x39 && byteArry[i] >= 0x30)
			{
				byteArry[i] -= 0x30; 
			}
			else if(byteArry[i] <= 0x46 && byteArry[i] >= 0x41)
			{
				byteArry[i] -= 0x37;
			}
			else if(byteArry[i] <= 0x66 && byteArry[i] >= 0x61)
			{
				byteArry[i] -= 0x57;
			}
		}
		return (byte)((byteArry[0] << 4) | (byteArry[1] & 0x0F));
	}
	/** @author xuxl
	 *  功能：字符串转字节数�?
	 *  @param strInput 
	 *  @param
	 *  @return int
	 * */
	static public byte[] StringToByteArray(String strInput)
	{
		int l = strInput.length() / 2;  
        byte[] ret = new byte[l];  
        for (int i = 0; i < l; i++) {  
            ret[i] = (byte) Integer
                    .valueOf(strInput.substring(i * 2, i * 2 + 2), 16).byteValue();  
        }  
        return ret;  

	}
	static public String ByteArrayToString(byte[] arryByte, int nDataLength)
	{
		String strOut = new String();
		for(int i = 0; i < nDataLength; i++)
			strOut += String.format("%02X ", arryByte[i]);
		return strOut;
	}
	/** @author john.li
	 *  @param str 传入字符�?
	 *  @param reg 按照哪种方式或哪个字段拆�?
	 *  @return arrayStr 返回拆分后的数组�?
	 * */
	static public String[] spiltStrings(String str, String reg){
		String[] arrayStr = str.split(reg);
		return arrayStr;
	}
	
	// 字符序列转换为16进制字符串  
    static public String bytesToHexString(byte[] src) {
        return bytesToHexString(src, true);  
    }  
  
    static public String bytesToHexString(byte[] src, boolean isPrefix) {
        StringBuilder stringBuilder = new StringBuilder();
        if (isPrefix == true) {  
            stringBuilder.append("0x");  
        }  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        char[] buffer = new char[2];  
        for (int i = 0; i < src.length; i++) {  
            buffer[0] = Character.toUpperCase(Character.forDigit(
                    (src[i] >>> 4) & 0x0F, 16));  
            buffer[1] = Character.toUpperCase(Character.forDigit(src[i] & 0x0F,
                    16));  
            System.out.println(buffer);
            stringBuilder.append(buffer);
            stringBuilder.append("  ");
        }  
        return stringBuilder.toString();  
    }
	public static String dateToString(Date time){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ctime = formatter.format(time);

		return ctime;
	}
	public static String dateToString(Date time, String format){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(format);
		if (time==null)
		{
			return "19000101";
		}
		String ctime = formatter.format(time);

		return ctime;
	}
	public static String timeToString(Date time){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = formatter.format(time);

		return ctime;
	}
	public static Date stringToDate(String time, String format){
		SimpleDateFormat formatter;

		time=time.trim() ;
		formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		java.util.Date ctime = formatter.parse(time, pos);

		return ctime;
	}
	public static Date stringToDate(String time){
		SimpleDateFormat formatter;
		int tempPos=time.indexOf("AD") ;
		time=time.trim() ;
		formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
		if(tempPos>-1){
			time=time.substring(0,tempPos)+
					"公元"+time.substring(tempPos+"AD".length());//china
			formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
		}
		tempPos=time.indexOf("-");
		if(tempPos>-1&&(time.indexOf(" ")<0)){
			formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
		}
		else if((time.indexOf("/")>-1) &&(time.indexOf(" ")>-1)){
			formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}
		else if((time.indexOf("-")>-1) &&(time.indexOf(" ")>-1)){
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		else if((time.indexOf("/")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){
			formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
		}
		else if((time.indexOf("-")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){
			formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
		}
		ParsePosition pos = new ParsePosition(0);
		java.util.Date ctime = formatter.parse(time, pos);

		return ctime;
	}

	/**
	 * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss a'(12小时制)<br>
	 * 如Sat May 11 17:23:22 CST 2002 to '2002-05-11 05:23:22 下午'<br>
	 * @param time Date 日期<br>
	 * @param x int 任意整数如：1<br>
	 * @return String 字符串<br>
	 */
	public static String dateToString(Date time, int x){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
		String ctime = formatter.format(time);

		return ctime;
	}


	/**
	 *取系统当前时间:返回只值为如下形式
	 *2002-10-30 20:24:39
	 * @return String
	 */
	public static String Now(){
		return dateToString(new Date());
	}

	/**
	 *取系统当前时间:返回只值为如下形式
	 *2002-10-30 08:28:56 下午
	 *@param hour 为任意整数
	 *@return String
	 */
	public static String Now(int hour){
		return dateToString(new Date(),hour);
	}


	/**
	 *取系统当前时间:返回值为如下形式
	 *2002-10-30
	 *@return String
	 */
	public static String getYYYY_MM_DD(){
		return dateToString(new Date()).substring(0,10);

	}


	/**
	 *取系统给定时间:返回值为如下形式
	 *2002-10-30
	 *@return String
	 */
	public static String getYYYY_MM_DD(String date){
		return date.substring(0,10);

	}

	public static String getHour(){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("H");
		String ctime = formatter.format(new Date());
		return ctime;
	}
	public static String getMinute(){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("m");
		String ctime = formatter.format(new Date());
		return ctime;
	}

	public static String getDay(){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("d");
		String ctime = formatter.format(new Date());
		return ctime;
	}

	public static String getMonth(){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("M");
		String ctime = formatter.format(new Date());
		return ctime;
	}

	public static String getYear(){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy");
		String ctime = formatter.format(new Date());
		return ctime;
	}
	public static int getHour(Date date){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("H");
		String ctime = formatter.format(date);
		return Integer.valueOf(ctime);
	}
	public static int getMinute(Date date){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("m");
		String ctime = formatter.format(date);
		return Integer.valueOf(ctime);
	}
	public static int getSecond(Date date){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("s");
		String ctime = formatter.format(date);
		return Integer.valueOf(ctime);
	}
	public static int getYear(Date date){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy");
		String ctime = formatter.format(date);
		return Integer.valueOf(ctime);
	}
	public static int getMonth(Date date){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("M");
		String ctime = formatter.format(date);
		return Integer.valueOf(ctime);
	}
	public static int getDay(Date date){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("d");
		String ctime = formatter.format(date);
		return Integer.valueOf(ctime);
	}
	public static String getWeek(){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("E");
		String ctime = formatter.format(new Date());
		return ctime;
	}
}
