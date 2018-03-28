/*
package com.example.administrator.test.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.test.Fragment.BaseFragment;
import com.example.administrator.test.Interfaces.CardGuidNotify;
import com.example.administrator.test.R;
import com.example.administrator.test.util.MifareCard;


public class Homefrag extends BaseActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    public MifareClassic mfc;
    public Intent mfcintent;
    private Intent intents;
    public Tag tag;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private boolean isnews = true;
    public Boolean processCardIn=false;
    private TextToSpeech myTTS;
    public BaseFragment cardInFrag=null;
    private byte password[] = { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
            (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
            (byte) 0xff, (byte) 0xff, (byte) 0xff };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_frag);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.isEnabled();
        if (nfcAdapter == null) {
            showToast(this, "nfcAdapter == null");
            //return;
        } else {
            if (!nfcAdapter.isEnabled()) {
                showToast(this, "!nfcAdapter.isEnabled");
                // return;
            }
            pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
            ndef.addCategory("**

/*");
            mFilters = new IntentFilter[]{ndef};
            mTechLists = new String[][]{new String[]{MifareClassic.class.getName()}, new String[]{NfcA.class.getName()}};

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!(nfcAdapter==null)){
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, mFilters,
                    mTechLists);
            if (isnews) {
                if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction())) {


                    intents = getIntent();
                    isnews = false;
                }
            }

        }


    }
    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);
        intents = intent;
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            System.out.println("在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里在这里");
            boolean auth = false;
            String cardStr = "";
            mfcintent = intent;
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            mfc = MifareClassic.get(tag);
            if (processCardIn)
            {
                MifareCard.getCardGuid(mfcintent, mfc, new CardGuidNotify() {
                    @Override
                    public void getCardGuid(long cardno) {
                        CardInFrag(String.valueOf(cardno));

                    }
                },false);
            }

        }
    }
    public void replaceLayout(int layoutRid, Fragment fragment, boolean isBack) {
        FragmentManager manager = getFragmentManager();


        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layoutRid, fragment);
        transaction.commit();


    }
    private void CardInFrag(String cardno){

        Bundle bundle = new Bundle();
        bundle.putCharSequence("cardno",cardno);
        if (cardInFrag==null) {
            return;
        }
        else

        {
            if (cardInFrag.isVisible())
            {
                cardInFrag.CardIn(cardno);
            }
            else
            {
                cardInFrag.setArguments(bundle);

            }

        }
        replaceLayout( R.id.content, cardInFrag, true);

    }
    */
/*private void FetchOrderFrag(String cardno) {


        Bundle bundle = new Bundle();
        bundle.putCharSequence("cardno",cardno);

        FetchOrderFrag fetchOrderFrag = FetchOrderFrag.newInstance(bundle);

        replaceLayout( R.id.content, fetchOrderFrag, true);
        //  changeBtn(friends_button);
    }*//*

}*/
