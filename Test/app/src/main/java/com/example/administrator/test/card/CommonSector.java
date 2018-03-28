package com.example.administrator.test.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;


import com.example.administrator.test.smartdevice.utils.StringUtility;
import com.example.administrator.test.util.KeyGenerate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;

import static com.example.administrator.test.card.HsopCardData.CheckData;


/**
 * Created by hantao on 2017/11/7.
 */

public class CommonSector extends CardSector  {
    public CommonSector(int m_sector, long cardguid, Tag tag, byte[] myKeyA, Intent intent, MifareClassic mfc) {
        super(m_sector, cardguid, tag, myKeyA, intent, mfc);
    }
    public CommonSector(CardSector cardSector) {
        super(cardSector.getM_sector() ,cardSector.getCardguid() ,cardSector.getTag(),cardSector.getMyKeyA(),cardSector.getIntent(),cardSector.getMfc() );
    }
//这里的cardno 对应的是后台人员编号（会员编号） cardinfo.getHolderCode()
    private long m_cardno;
    private int m_cardgrade;
    private int m_cardgroup;
    private int m_cardtype;
    private int m_cardsubtype;
    private int m_timelimit;
    private int m_daylimit;

    private String m_cardholderid;
    private String m_cardholdername;
    private String m_startdate;
    private String m_enddate;
    private int m_deptcode;
    private int m_carddeposit;
    private String m_carddatetime;

    public long getM_cardno() {
        return m_cardno;
    }

    public void setM_cardno(long m_cardno) {
        this.m_cardno = m_cardno;
    }

    public int getM_cardgrade() {
        return m_cardgrade;
    }

    public void setM_cardgrade(int m_cardgrade) {
        this.m_cardgrade = m_cardgrade;
    }

    public int getM_cardgroup() {
        return m_cardgroup;
    }

    public void setM_cardgroup(int m_cardgroup) {
        this.m_cardgroup = m_cardgroup;
    }

    public int getM_cardtype() {
        return m_cardtype;
    }

    public void setM_cardtype(int m_cardtype) {
        this.m_cardtype = m_cardtype;
    }

    public int getM_cardsubtype() {
        return m_cardsubtype;
    }

    public void setM_cardsubtype(int m_cardsubtype) {
        this.m_cardsubtype = m_cardsubtype;
    }

    public int getM_timelimit() {
        return m_timelimit;
    }

    public void setM_timelimit(int m_timelimit) {
        this.m_timelimit = m_timelimit;
    }

    public int getM_daylimit() {
        return m_daylimit;
    }

    public void setM_daylimit(int m_daylimit) {
        this.m_daylimit = m_daylimit;
    }

    public String getM_cardholderid() {
        return m_cardholderid;
    }

    public void setM_cardholderid(String m_cardholderid) {
        this.m_cardholderid = m_cardholderid;
    }

    public String getM_cardholdername() {
        return m_cardholdername;
    }

    public void setM_cardholdername(String m_cardholdername) {
        this.m_cardholdername = m_cardholdername;
    }

    public String getM_startdate() {
        return m_startdate;
    }

    public void setM_startdate(String m_startdate) {
        this.m_startdate = m_startdate;
    }

    public String getM_enddate() {
        return m_enddate;
    }

    public void setM_enddate(String m_enddate) {
        this.m_enddate = m_enddate;
    }

    public int getM_deptcode() {
        return m_deptcode;
    }

    public void setM_deptcode(int m_deptcode) {
        this.m_deptcode = m_deptcode;
    }

    public int getM_carddeposit() {
        return m_carddeposit;
    }

    public void setM_carddeposit(int m_carddeposit) {
        this.m_carddeposit = m_carddeposit;
    }

    public String getM_carddatetime() {
        return m_carddatetime;
    }

    public void setM_carddatetime(String m_carddatetime) {
        this.m_carddatetime = m_carddatetime;
    }

    @Override
    public boolean retrieveData()
    {
        try{

        byte[] tmp = new byte[12];
        byte[] Block1=getM_block1();
        byte[] Block2=getM_block2();
        byte[] Block3=getM_block3();
        long uint256 = 256;
        long uint65536 = 65536;
        long uint16777216 = 16777216;
        if (HsopCardData.CheckDataValid(Block1) && HsopCardData.CheckDataValid(Block2) && HsopCardData.CheckDataValid(Block3))
        {

//            m_cardno = (HsopCardData.getUnsignedByte(Block1[3]) * lng3of256 + HsopCardData.getUnsignedByte(Block1[2]) * lng2of256 + HsopCardData.getUnsignedByte(Block1[1]) * lng1of256 + HsopCardData.getUnsignedByte(Block1[0]));
//
//            m_cardno=m_cardno+HsopCardData.getUnsignedByte(Block1[4]) * lng4of256+HsopCardData.getUnsignedByte(Block1[5]) * lng5of256+HsopCardData.getUnsignedByte(Block1[6]) * lng6of256+HsopCardData.getUnsignedByte(Block1[7]) * lng7of256;
            m_cardno=getLongFromFirs8Bytes(Block1);
            m_cardtype = Block1[8];
            m_deptcode = Block1[9];
            m_timelimit = Block1[11] * 256 + Block1[10];
            m_daylimit = Block1[13] * 256 + Block1[12];
            m_carddeposit = Block1[14];
            m_cardholderid = HsopCardData.AsciiByte2UnicodeStr(HsopCardData.getbytes(0, 11, Block2));
            m_cardholdername = HsopCardData.AsciiByte2UnicodeStr(HsopCardData.getbytes(0, 11, Block3));

            m_startdate = String.valueOf(2000 + (int)(Block2[12])) + '-' + String.valueOf(Block2[13]) + '-' + String.valueOf(Block2[14]);
            m_enddate =   String.valueOf(2000 + (int)(Block3[12])) + '-' + String.valueOf(Block3[13]) + '-' + String.valueOf(Block3[14]);
            m_carddatetime = String.valueOf(2000 + (int)(Block3[12])) + '-' + String.valueOf(Block3[13]) + '-' + String.valueOf(Block3[14]);
           // m_cardholdername = HsopCardData.AsciiByte2UnicodeStr(HsopCardData.getbytes(0, 11, Block3));

        }
        else
        {
            this.setM_error("卡未发行!");
            return false;
        }}
        catch (Exception ex)
        {
            this.setM_error(ex.getMessage());
            return false;
        }
        return true;
    }
    @Override
    public boolean pushData()
    {
        try {

            Date dt1 = StringUtility.stringToDate(m_startdate,"yyyy-MM-dd");
            Date dt2 =  StringUtility.stringToDate(m_enddate,"yyyy-MM-dd");



            int Year = 0;
            byte Month = 0, Day = 0;
            byte[] tmp1 = new byte[12], tmp11 = new byte[12];
            byte[] tmp2 = new byte[4], tmp22 = new byte[4];
            byte[] tmpBlock1 = new byte[16];
            byte[] tmpBlock2 = new byte[16];
            byte[] tmpBlock3 = new byte[16];
            this.clear();
            long m_cardnoHigh=0;
            long m_cardnoLow=0;
            long int4bytes=(long)(65536*65536);


//            tmpBlock1[3] = ((byte) (m_cardnoLow / (65536 * 256)));
//            tmpBlock1[2] = (byte) ((m_cardnoLow - tmpBlock1[3] * 65536 * 256) / 65536);
//            tmpBlock1[1] = (byte) ((m_cardnoLow - tmpBlock1[3] * 65536 * 256 - tmpBlock1[2] * 65536) / 256);
//            tmpBlock1[0] = (byte) (m_cardnoLow % 256);


//            tmpBlock1[7] = ((byte) (m_cardno/ lng7of256));
//            tmpBlock1[6] = ((byte) ((m_cardno - tmpBlock1[7] * lng7of256)/ (lng6of256)));
//            tmpBlock1[5] = ((byte) ((m_cardno - tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256)/ (lng5of256)));
//            tmpBlock1[4] = ((byte) ((m_cardno - tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256- tmpBlock1[5] * lng5of256)/ (lng4of256)));
//            tmpBlock1[3] = ((byte) ((m_cardno - tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256 - tmpBlock1[5] * lng5of256- tmpBlock1[4] * lng4of256)/ (lng3of256)));
//            tmpBlock1[2] = (byte) ((m_cardno- tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256 - tmpBlock1[5] * lng5of256 - tmpBlock1[4] * lng4of256 - tmpBlock1[3] *lng3of256) / lng2of256);
//            tmpBlock1[1] = (byte) ((m_cardno- tmpBlock1[7] * lng7of256- tmpBlock1[6] * lng6of256 - tmpBlock1[5] * lng5of256- tmpBlock1[4] * lng4of256- tmpBlock1[3] * lng3of256 - tmpBlock1[2] * lng2of256) / 256);
//            tmpBlock1[0] = (byte) (m_cardno % 256);
            pushLongToFirst8Bytes(m_cardno,tmpBlock1);


            tmpBlock1[8] = (byte) (m_cardtype);
            tmpBlock1[9] = (byte) m_deptcode;
            tmpBlock1[10] = (byte) (m_timelimit % 256);
            tmpBlock1[11] = (byte) ((m_timelimit / 256) % 256);
            tmpBlock1[12] = (byte) (m_daylimit % 256);
            tmpBlock1[13] = (byte) ((m_daylimit / 256) % 256);
            tmpBlock1[14] = (byte) m_carddeposit;
            tmpBlock1[15] = CheckData(tmpBlock1);
            //

            tmp1 = KeyGenerate.StringToGb2312Bytes(m_cardholderid);
            Year = StringUtility.getYear(dt1);
            Month = (byte) StringUtility.getMonth(dt1);
            Day = (byte) StringUtility.getDay(dt1);
            tmp2[0] = (byte) (Year - 2000);
            tmp2[1] = (byte) Month;
            tmp2[2] = (byte) Day;
            tmp2[3] = 0;
//        tmp1.CopyTo(tmpBlock2, 0);
//        tmp2.CopyTo(tmpBlock2, 12);
            tmpBlock2=this.CopyTo(tmp1, tmpBlock2, 0);
            tmpBlock2=this.CopyTo(tmp2, tmpBlock2, 12);
            tmpBlock2[15] = CheckData(tmpBlock2);
            //
            tmp1 = tmp11;//clear
            tmp2 = tmp22;
            tmp1 = KeyGenerate.StringToGb2312Bytes(m_cardholdername);
            Year = StringUtility.getYear(dt2);
            Month = (byte) StringUtility.getMonth(dt2);
            Day = (byte)StringUtility.getDay(dt2);
            tmp2[0] = (byte) (Year - 2000);
            tmp2[1] = (byte) Month;
            tmp2[2] = (byte) Day;
            tmp2[3] = 0;
            tmpBlock3=this.CopyTo(tmp1, tmpBlock3, 0);

            tmpBlock3=this.CopyTo(tmp2, tmpBlock3, 12);
            tmpBlock3[15] = CheckData(tmpBlock3);

            setM_block1(tmpBlock1.clone());
            setM_block2(tmpBlock2.clone());
            setM_block3(tmpBlock3.clone());
        }
        catch (Exception ex)
        {
            this.setM_error(ex.getMessage());
            return false;
        }
        return true;
    }


}
