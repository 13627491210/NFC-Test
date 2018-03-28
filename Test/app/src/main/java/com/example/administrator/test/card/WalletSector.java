package com.example.administrator.test.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;

import static com.example.administrator.test.card.HsopCardData.CheckData;
import static com.example.administrator.test.card.HsopCardData.CheckWalletData;

/**
 * Created by hantao on 2017/11/8.
 */

public class WalletSector extends CardSector {
    public WalletSector(int m_sector, long cardguid, Tag tag, byte[] myKeyA, Intent intent, MifareClassic mfc) {
        super(m_sector, cardguid, tag, myKeyA, intent, mfc);
    }
    public WalletSector(CardSector cardSector) {
        super(cardSector.getM_sector() ,cardSector.getCardguid() ,cardSector.getTag(),cardSector.getMyKeyA(),cardSector.getIntent(),cardSector.getMfc() );
    }
    private int m_Wallet1Money=0;
    private int m_Wallet1TotalTransactions=0;
    private int m_Wallet1DayTotalSpending = 0;

    private long  m_TransactionsTime=0;
    private int m_Wallet1TransactionAmount =0;
    private int m_Wallet1LastAmount=0;

    public int getM_Wallet1Money() {
        return m_Wallet1Money;
    }

    public void setM_Wallet1Money(int m_Wallet1Money) {
        this.m_Wallet1Money = m_Wallet1Money;
    }

    public int getM_Wallet1TotalTransactions() {
        return m_Wallet1TotalTransactions;
    }

    public void setM_Wallet1TotalTransactions(int m_Wallet1TotalTransactions) {
        this.m_Wallet1TotalTransactions = m_Wallet1TotalTransactions;
    }

    public int getM_Wallet1DayTotalSpending() {
        return m_Wallet1DayTotalSpending;
    }

    public void setM_Wallet1DayTotalSpending(int m_Wallet1DayTotalSpending) {
        this.m_Wallet1DayTotalSpending = m_Wallet1DayTotalSpending;
    }



    public long getM_TransactionsTime() {
        return m_TransactionsTime;
    }

    public void setM_TransactionsTime(long m_TransactionsTime) {
        this.m_TransactionsTime = m_TransactionsTime;
    }

    public int getM_Wallet1TransactionAmount() {
        return m_Wallet1TransactionAmount;
    }

    public void setM_Wallet1TransactionAmount(int m_Wallet1TransactionAmount) {
        this.m_Wallet1TransactionAmount = m_Wallet1TransactionAmount;
    }

    public int getM_Wallet1LastAmount() {
        return m_Wallet1LastAmount;
    }

    public void setM_Wallet1LastAmount(int m_Wallet1LastAmount) {
        this.m_Wallet1LastAmount = m_Wallet1LastAmount;
    }

    @Override
    public boolean pushData() {
        byte[] tmpBlock1 = new byte[16];
        byte[] tmpBlock2 = new byte[16];
        byte[] tmpBlock3 = new byte[16];
        this.clear();

        tmpBlock1[3] =(byte)(  m_Wallet1Money / (65536*256));
        tmpBlock1[2] = (byte)((m_Wallet1Money - tmpBlock1[3]*65536*256) / 65536);
        tmpBlock1[1] = (byte)((m_Wallet1Money - tmpBlock1[3]*65536*256 - tmpBlock1[2]*65536) / 256);
        tmpBlock1[0] =(byte)( m_Wallet1Money % 256);
        tmpBlock1[4] = (byte)(~ tmpBlock1[0]);
        tmpBlock1[5] =(byte) (~ tmpBlock1[1]);
        tmpBlock1[6] =(byte) (~ tmpBlock1[2]);
        tmpBlock1[7] =(byte) (~ tmpBlock1[3]);
        tmpBlock1[8] =(byte)( m_Wallet1TotalTransactions % 256);
        tmpBlock1[9] =(byte)( m_Wallet1TotalTransactions / 256);
        tmpBlock1[10] =(byte) (~ tmpBlock1[8]);
        tmpBlock1[11] =(byte) (~ tmpBlock1[9]);
        tmpBlock1[12] =(byte) (m_Wallet1DayTotalSpending % 256);
        tmpBlock1[13] =(byte) (m_Wallet1DayTotalSpending / 256);
        tmpBlock1[14] =(byte) (~ tmpBlock1[12]);
        tmpBlock1[15] =(byte) (~ tmpBlock1[13]);
        //
        pushLongToFirst8Bytes(m_TransactionsTime,tmpBlock2);
        tmpBlock2[15] =(byte) CheckData(tmpBlock2);
        //
        if ( m_Wallet1TransactionAmount>=0)
        {
            tmpBlock3[6] =(byte) (m_Wallet1TransactionAmount / 65536);
            tmpBlock3[5] =(byte) ((m_Wallet1TransactionAmount - tmpBlock3[6]*65536) / 256);
            tmpBlock3[4] =(byte) (m_Wallet1TransactionAmount % 256);
        }
        else
        {
            m_Wallet1TransactionAmount =(byte)(-m_Wallet1TransactionAmount);
            tmpBlock3[6] =(byte) (m_Wallet1TransactionAmount / 65536);
            tmpBlock3[5] =(byte) ((m_Wallet1TransactionAmount-tmpBlock3[6]*65536) / 256);
            tmpBlock3[4] =(byte) (m_Wallet1TransactionAmount % 256);
            tmpBlock3[6] =(byte)  (tmpBlock3[6] + 128);
        }

        tmpBlock3[10] =(byte) (m_Wallet1LastAmount / (65536*256));
        tmpBlock3[9] =(byte) ((m_Wallet1LastAmount -tmpBlock3[10]*(65536*256)) / 65536);
        tmpBlock3[8] =(byte) ((m_Wallet1LastAmount -tmpBlock3[10]*(65536*256)-tmpBlock3[9]*65536) / 256);
        tmpBlock3[7] =(byte)(m_Wallet1LastAmount % 256);
        tmpBlock3[15] =(byte) CheckData(tmpBlock3);
        this.setM_block1(tmpBlock1.clone());
        this.setM_block2(tmpBlock2.clone());
        this.setM_block3(tmpBlock3.clone());

        return true;
    }

    @Override
    public boolean retrieveData() {
        long tmpdDate = 0;
        byte tmp = 0;
        byte[] Block1=getM_block1();
        byte[] Block2=getM_block2();
        byte[] Block3=getM_block3();
        if (CheckWalletData(Block1, 0, 3) &&
                CheckWalletData(Block1, 8, 9) &&
                CheckWalletData(Block1, 12, 13) &&
                CheckData(Block2) == Block2[15]&&
                CheckData(Block3) == Block3[15])
        {
            m_Wallet1Money= Block1[3]*65536*256 + Block1[2]*65536 + Block1[1]*256 + Block1[0];
            m_Wallet1TotalTransactions = Block1[9]*256 + Block1[8];
            m_Wallet1DayTotalSpending = Block1[13] * 256 + Block1[12];



            if (Block3[6] >= 128)
            {
                tmp = (byte)(Block3[6] - 128);
                m_Wallet1TransactionAmount = -(tmp * 65536 + Block3[5] * 256 + Block3[4]);
            }
            else
            {
                tmp = Block3[6];
                m_Wallet1TransactionAmount = tmp * 65536 + Block3[5] * 256 + Block3[4];

            }
            m_Wallet1LastAmount = Block3[10] * 65536 * 256 + Block3[9] * 65536 + Block3[8] * 256 + Block3[7];
            m_TransactionsTime=getLongFromFirs8Bytes(Block2);
            return true;

        }

        return false;
    }
}
