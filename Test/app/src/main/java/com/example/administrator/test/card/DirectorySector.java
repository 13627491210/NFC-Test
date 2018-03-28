package com.example.administrator.test.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;

import java.util.ArrayList;
import java.util.List;

import realsun.webpos.biz.card.CardSectorBizTypeBase;


/**
 * Created by hantao on 2017/11/8.
 */

public class DirectorySector extends CardSector {
    public DirectorySector(int m_sector, long cardguid, Tag tag, byte[] myKeyA, Intent intent, MifareClassic mfc) {
        super(m_sector, cardguid, tag, myKeyA, intent, mfc);
    }
    public DirectorySector(CardSector cardSector) {
        super(cardSector.getM_sector() ,cardSector.getCardguid() ,cardSector.getTag(),cardSector.getMyKeyA(),cardSector.getIntent(),cardSector.getMfc() );
    }


    private byte m_commonsector = 1;
    private byte m_specialsector = 2;
    private byte m_activesector = 3;
    private byte m_wallet1 = 4;
    private byte m_wallet2 = 5;
    private byte m_wallet3 = 6;
    private byte m_wallet4 = 7;
    private byte m_wallet5 = 8;
    private byte m_app1 = 9;
    private byte m_app2 = 10;
    private byte m_app3 = 11;
    private byte m_app4 = 12;
    private byte m_app5 = 13;
    private byte m_app6 = 14;
    private byte m_app7 = 15;

    public byte getM_app1() {
        return m_app1;
    }

    public void setM_app1(byte m_app1) {
        this.m_app1 = m_app1;
    }

    public byte getM_app2() {
        return m_app2;
    }

    public void setM_app2(byte m_app2) {
        this.m_app2 = m_app2;
    }

    public byte getM_app3() {
        return m_app3;
    }

    public void setM_app3(byte m_app3) {
        this.m_app3 = m_app3;
    }

    public byte getM_app4() {
        return m_app4;
    }

    public void setM_app4(byte m_app4) {
        this.m_app4 = m_app4;
    }

    public byte getM_app5() {
        return m_app5;
    }

    public void setM_app5(byte m_app5) {
        this.m_app5 = m_app5;
    }

    public byte getM_app6() {
        return m_app6;
    }

    public void setM_app6(byte m_app6) {
        this.m_app6 = m_app6;
    }

    public byte getM_app7() {
        return m_app7;
    }

    public void setM_app7(byte m_app7) {
        this.m_app7 = m_app7;
    }

    private List<CardSectorBizTypeBase> cardSectorBizBaseList =new ArrayList<CardSectorBizTypeBase>();

    public List<CardSectorBizTypeBase> getCardSectorBizBaseList() {

        CardSectorBizTypeBase cardSectorBizBase1=new CardSectorBizTypeBase(CardSectorBase.CommonSectorflag,m_commonsector);

        CardSectorBizTypeBase cardSectorBizBase2=new CardSectorBizTypeBase(CardSectorBase.SpecialSectorflag,m_specialsector);
        CardSectorBizTypeBase cardSectorBizBase3=new CardSectorBizTypeBase(CardSectorBase.ActiveSectorflag,m_activesector);
        CardSectorBizTypeBase cardSectorBizBase4=new CardSectorBizTypeBase(CardSectorBase.WalletSectorflag,m_wallet1);
        CardSectorBizTypeBase cardSectorBizBase5=new CardSectorBizTypeBase(CardSectorBase.WalletSectorflag,m_wallet2);
        CardSectorBizTypeBase cardSectorBizBase6=new CardSectorBizTypeBase(CardSectorBase.WalletSectorflag,m_wallet3);
        CardSectorBizTypeBase cardSectorBizBase7=new CardSectorBizTypeBase(CardSectorBase.WalletSectorflag,m_wallet4);
        CardSectorBizTypeBase cardSectorBizBase8=new CardSectorBizTypeBase(CardSectorBase.WalletSectorflag,m_wallet5);
        CardSectorBizTypeBase cardSectorBizBase9=new CardSectorBizTypeBase(CardSectorBase.AppSectorflag,m_app1);
        CardSectorBizTypeBase cardSectorBizBase10=new CardSectorBizTypeBase(CardSectorBase.AppSectorflag,m_app2);
        CardSectorBizTypeBase cardSectorBizBase11=new CardSectorBizTypeBase(CardSectorBase.AppSectorflag,m_app3);
        CardSectorBizTypeBase cardSectorBizBase12=new CardSectorBizTypeBase(CardSectorBase.AppSectorflag,m_app4);
        CardSectorBizTypeBase cardSectorBizBase13=new CardSectorBizTypeBase(CardSectorBase.AppSectorflag,m_app5);
        CardSectorBizTypeBase cardSectorBizBase14=new CardSectorBizTypeBase(CardSectorBase.AppSectorflag,m_app6);
        CardSectorBizTypeBase cardSectorBizBase15=new CardSectorBizTypeBase(CardSectorBase.AppSectorflag,m_app7);
        cardSectorBizBaseList.add(cardSectorBizBase1);
        cardSectorBizBaseList.add(cardSectorBizBase2);
        cardSectorBizBaseList.add(cardSectorBizBase3);
        cardSectorBizBaseList.add(cardSectorBizBase4);
        cardSectorBizBaseList.add(cardSectorBizBase5);
        cardSectorBizBaseList.add(cardSectorBizBase6);
        cardSectorBizBaseList.add(cardSectorBizBase7);
        cardSectorBizBaseList.add(cardSectorBizBase8);
        cardSectorBizBaseList.add(cardSectorBizBase9);
        cardSectorBizBaseList.add(cardSectorBizBase10);
        cardSectorBizBaseList.add(cardSectorBizBase11);
        cardSectorBizBaseList.add(cardSectorBizBase12);
        cardSectorBizBaseList.add(cardSectorBizBase13);
        cardSectorBizBaseList.add(cardSectorBizBase14);
        cardSectorBizBaseList.add(cardSectorBizBase15);
        return cardSectorBizBaseList;
    }



    public byte getM_commonsector() {
        return m_commonsector;
    }

    public void setM_commonsector(byte m_commonsector) {
        this.m_commonsector = m_commonsector;
    }

    public byte getM_specialsector() {
        return m_specialsector;
    }

    public void setM_specialsector(byte m_specialsector) {
        this.m_specialsector = m_specialsector;
    }

    public byte getM_activesector() {
        return m_activesector;
    }

    public void setM_activesector(byte m_activesector) {
        this.m_activesector = m_activesector;
    }

    public byte getM_wallet1() {
        return m_wallet1;
    }

    public void setM_wallet1(byte m_wallet1) {
        this.m_wallet1 = m_wallet1;
    }

    public byte getM_wallet2() {
        return m_wallet2;
    }

    public void setM_wallet2(byte m_wallet2) {
        this.m_wallet2 = m_wallet2;
    }

    public byte getM_wallet3() {
        return m_wallet3;
    }

    public void setM_wallet3(byte m_wallet3) {
        this.m_wallet3 = m_wallet3;
    }

    public byte getM_wallet4() {
        return m_wallet4;
    }

    public void setM_wallet4(byte m_wallet4) {
        this.m_wallet4 = m_wallet4;
    }

    public byte getM_wallet5() {
        return m_wallet5;
    }

    public void setM_wallet5(byte m_wallet5) {
        this.m_wallet5 = m_wallet5;
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
