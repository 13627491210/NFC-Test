package com.example.administrator.test.Activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.test.Fragment.BaseFragment;
import com.example.administrator.test.Interfaces.CardGuidNotify;
import com.example.administrator.test.R;
import com.example.administrator.test.util.ConnectionChangeReceiver;
import com.example.administrator.test.util.MifareCard;
import com.example.administrator.test.util.WebClientConnection;

import org.litepal.LitePal;

import realsun.webpos.biz.WebPosDefineBiz;
import realsun.webpos.model.Teacher;
import realsun.webpos.model.WebPosDefine;
import realsun.webpos.webclient.DataResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    public MifareClassic mfc;
    public Intent mfcintent;
    private Intent intents;
    public Tag tag;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private Button mButton;
    private EditText mname, mpassword;
    private long exitTime = 0;
    private DataResult result;
    private boolean isnews = true;
    public Boolean processCardIn=false;
    private TextToSpeech myTTS;
    public BaseFragment cardInFrag=null;
    public ConnectionChangeReceiver mConnectivityReceiver = ConnectionChangeReceiver.getInstence();
    public static WebClientConnection systemwcc = new WebClientConnection();
    private Teacher teacher = new Teacher();
    public WebPosDefine webPosDefine = new WebPosDefine();
    public DataResult getResult() {
        return result;
    }

    public void setResult(DataResult result) {
        this.result = result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        LitePal.initialize(this);
        nfcAdapter= NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC", Toast.LENGTH_SHORT).show();
            //return;
        }
        else
        {
            if (!nfcAdapter.isEnabled()) {
                Toast.makeText(this, "NFC...", Toast.LENGTH_SHORT).show();
                // return;
            }
            pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
            ndef.addCategory("*/*");
            mFilters = new IntentFilter[] { ndef };
            mTechLists = new String[][] {new String[] { MifareClassic.class.getName() },new String[] { NfcA.class.getName() }};

        }
        //network check
        mConnectivityReceiver = ConnectionChangeReceiver.getInstence();
        mConnectivityReceiver.mainActivity = this;
        IntentFilter filter = new IntentFilter();
        filter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mConnectivityReceiver, filter);
    }

      /*  if (!WebPosDefineBiz.CheckLocalWebPosDefine())
        {
           // SettingFrag();

        }
        else
        {
            webPosDefine=WebPosDefineBiz.GetLocalPosDefine();
            //homeFragClassName = WebPosDefineBiz.GetLocalPosDefine().getClassname();
            //homeFrag =(BaseFragment) Fragment.instantiate(this,homeFragClassName);
            // upgrageApp();

            HomeFrag();


        }
    }*/

    private void initView() {
        mname = (EditText) findViewById(R.id.editText3);
        mpassword = (EditText) findViewById(R.id.editText4);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String name, passwoed;
                String user = "000";
                String userpassword = "000";

                name = mname.getText().toString();
                passwoed = mpassword.getText().toString();

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                if (name.equals(user) && passwoed.equals(userpassword)) {
                    /*new AlertDialog.Builder(this)
                        .setTitle("标题")
                        .setMessage("简单消息框")
                        .setPositiveButton("确定", null).show();*/
                    builder.setMessage("登录成功");

                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                            startActivity(intent);
                        }

                    });
                } else {
                    builder.setMessage("登录失败");
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
                builder.show();
                break;
            default:
                break;

        }
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
        } else {
            onDestroy();
        }
    }

    private void onDestory() {
        super.onDestroy();
        System.exit(0);
    }
    public void HomeFrag( ) {
        if (!WebPosDefineBiz.CheckLocalWebPosDefine())
        {
            // SettingFrag();
            return ;
        }
        Bundle bundle = new Bundle();




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
}