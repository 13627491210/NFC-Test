/*
package com.example.administrator.test.Fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.realsun.Interfaces.CardGuidNotify;
import com.realsun.Interfaces.KeyChangedNotify;
import com.realsun.card.CommonSector;
import com.realsun.card.DirectorySector;
import com.realsun.dbdownload.core.DbDownloadListener;
import com.realsun.dbdownload.core.DbDownloadParm;
import com.realsun.dbdownload.core.RealsunChecker;
import com.realsun.service.DbOfOdersUpdateService;
import com.realsun.utils.KeyGenerate;
import com.realsun.utils.MifareCard;
import com.realsun.utils.WebDbConfig;
import com.realsun.webpos.biz.CardInfoBiz;
import com.realsun.webpos.model.Cardinfo;
import com.smartdevice.utils.StringUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;


*/
/**
 * Created by hantao on 2017/9/2.
 *//*


public class HomeFrag extends BaseFragment implements DbDownloadListener,View.OnClickListener{
    private EditText editTextSec;
    private EditText editTextGuid;
    private EditText editTextOldPass;
    private EditText editTextNewPass;

    private EditText editTextBlock1;
    private EditText editTextBlock2;
    private EditText editTextBlock3;
    private Button buttonGenPass;
    private Button buttonChangePass;
    private Button buttonGetGuid;
    private Button buttonWriteCommon;
    private Button buttonReadCommon;
    private Button buttonDownloadCardinfo;
    private Button buttonReadcard;
    private TextView textViewDownloadinfo;
    private TextView textViewBadgeno ;
    private TextView textViewName;
    private TextView textViewPersonid;
    private TextView textViewCardguid;
    private TextView textViewStart;
    private TextView textViewEnd;
    private TextView textViewCardType;

    private  static String downloadDatatype="";
    private  static int downloadIndex=-1;
    private byte[] keyA ={ 0, 0, 0, 0, 0, 0 };
    private    Cardinfo cardinfo=new Cardinfo();
    private  static ArrayList<Hashtable> downloadDatatypes=new ArrayList<Hashtable>();
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);

    }

    @Override
    public void CardIn(String cardno) {
        super.CardIn(cardno);

        getCardinfo(cardno);

    }
    private  void setColorRed()
    {


        textViewBadgeno.setTextColor(Color.RED);
        textViewName.setTextColor(Color.RED);

        textViewPersonid.setTextColor(Color.RED);
        textViewCardguid.setTextColor(Color.RED);
        textViewStart.setTextColor(Color.RED);
        textViewEnd.setTextColor(Color.RED);
    }
    private  void setColorBlue()
    {


        textViewBadgeno.setTextColor(Color.BLUE);
        textViewName.setTextColor(Color.BLUE);

        textViewBadgeno.setTextColor(Color.BLUE);
        textViewName.setTextColor(Color.BLUE);

        textViewPersonid.setTextColor(Color.BLUE);
        textViewCardguid.setTextColor(Color.BLUE);
        textViewStart.setTextColor(Color.BLUE);
        textViewEnd.setTextColor(Color.BLUE);
    }
    private void readCard(long cardno)
    {
        int intSector=1;
        CardBizBase cardBizBase=new CardBizBase(0,16,parent.tag,parent.mfcintent,parent.mfc, cardno);
        DirectorySector directorySector=new DirectorySector(0, cardno,parent.tag,keyA,parent.mfcintent,parent.mfc);
        directorySector.setM_app1((byte)-1);
        directorySector.setM_app2((byte)-1);
        directorySector.setM_app3((byte)-1);
        directorySector.setM_app4((byte)-1);
        directorySector.setM_app5((byte)-1);
        directorySector.setM_app6((byte)-1);
        directorySector.setM_app7((byte)-1);
        directorySector.setM_wallet5((byte)-1);

        //  cardBizBase.setDirectorySector();
        clearCardinfoview();
        Integer result= cardBizBase.GetCard();
        if (result.equals(0))
        {

            textViewBadgeno .setText(cardBizBase.getCommonSector().getM_cardholderid());
            textViewName.setText(cardBizBase.getCommonSector().getM_cardholdername());
            textViewPersonid.setText(String.valueOf(cardBizBase.getCommonSector().getM_cardno()));
            textViewCardguid.setText(cardinfo.getCardNo());
            textViewStart.setText(cardBizBase.getCommonSector().getM_startdate());
            textViewEnd.setText(cardBizBase.getCommonSector().getM_enddate());
            setColorBlue();

        }
        else
        {
            parent.showToast(parent,"读卡失败，错误代码:"+ String.valueOf(result)+"错误信息:"+cardBizBase.getError());

        }

    }
    private void releaseCard()
    {
        if ((cardinfo.getBadgeno().isEmpty())||(cardinfo.getBadgeno().equals(null)))
        {
            parent.showToast(parent,"当前办卡信息!无法发行卡片。");
            return ;

        }

        int intSector=1;
        CardBizBase cardBizBase=new CardBizBase(0,16,parent.tag,parent.mfcintent,parent.mfc, Long.valueOf(cardinfo.getCardNo()));
        DirectorySector directorySector=new DirectorySector(0, Long.valueOf(cardinfo.getCardNo()),parent.tag,keyA,parent.mfcintent,parent.mfc);

        //  cardBizBase.setDirectorySector();
        Integer result= cardBizBase.GetCard();

        if (!result.equals(0))
        {
            CommonSector commonSector=new CommonSector(intSector, Long.valueOf(cardinfo.getCardNo()),parent.tag,keyA,parent.mfcintent,parent.mfc);
            long card_id= Long.valueOf(cardinfo.getHolderCode());
            commonSector.setM_cardno(card_id);
            commonSector.setM_cardholderid(cardinfo.getBadgeno());
            commonSector.setM_cardholdername(cardinfo.getHolderName());
            commonSector.setM_cardtype(0);
            commonSector.setM_daylimit(4);
            commonSector.setM_timelimit(1);
            commonSector.setM_deptcode(0);
            String strStart;
            String strEnd;
            strStart=StringUtility.dateToString(cardinfo.getStartDate(),"yyyy-MM-dd");
            strEnd=StringUtility.dateToString(cardinfo.getEndDate(),"yyyy-MM-dd");
            commonSector.setM_startdate(strStart);
            commonSector.setM_enddate(strEnd);

            if (!cardBizBase.RealseCard(commonSector)){
                parent.showToast(parent,"发卡失败:"+cardBizBase.getError());

            }else
            {
                parent.showToast(parent,"发卡完成" );
            }
        }
        else
        {
            parent.showToast(parent,"此卡已经发行");
        }


    }
    private  void  clearCardinfoview()
    {
        textViewBadgeno .setText("");
        textViewName.setText("");
        textViewPersonid.setText("");
        textViewCardguid.setText("");
        textViewStart.setText("");
        textViewEnd.setText("");
        textViewCardType.setText("");
    }

    private void getCardinfo(String cardno)
    {

        cardinfo= CardInfoBiz.fetchCardinfo(cardno);
        if (cardinfo.getBadgeno().isEmpty()||cardinfo.getBadgeno().equals(null))
        {
            clearCardinfoview();
            buttonGetGuid.setEnabled(false);
            buttonGetGuid.setEnabled(false);
            parent.showToast(parent,"根据卡号查询设备中无对应的发卡信息，请下载办卡信息后发卡");
        }
        else
        {
            buttonGetGuid.setEnabled(true);
            textViewBadgeno .setText(cardinfo.getBadgeno());
            textViewName.setText(cardinfo.getHolderName());
            textViewPersonid.setText(cardinfo.getHolderCode());
            textViewCardguid.setText(cardinfo.getCardNo());
            String strStart;
            String strEnd;
            strStart=StringUtility.dateToString(cardinfo.getStartDate(),"yyyy-MM-dd");
            strEnd=StringUtility.dateToString(cardinfo.getEndDate(),"yyyy-MM-dd");
            textViewStart.setText(strStart);
            textViewEnd.setText(strEnd);
            textViewCardType.setText(cardinfo.getCardType());
            buttonGetGuid.setEnabled(true);
            setColorRed();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // TODO Auto-generated method stub
        View aView = inflater.inflate(R.layout.home_frag, container, false);
        parent.setTitle("主页");
        editTextSec=(EditText)aView.findViewById(R.id.editTextSec);
        editTextGuid=(EditText)aView.findViewById(R.id.editTextGuid);
        editTextOldPass=(EditText)aView.findViewById(R.id.editTextOldPass);
        editTextNewPass=(EditText)aView.findViewById(R.id.editTextNewPass);
        editTextBlock1=(EditText)aView.findViewById(R.id.editTextBlock1);
        editTextBlock2=(EditText)aView.findViewById(R.id.editTextBlock2);
        editTextBlock3=(EditText)aView.findViewById(R.id.editTextBlock3);
        buttonGenPass=(Button)aView.findViewById(R.id.buttonGenPass);
        buttonChangePass=(Button)aView.findViewById(R.id.buttonChangePass);
        buttonGetGuid=(Button)aView.findViewById(R.id.buttonGetGuid);
        buttonWriteCommon=(Button)aView.findViewById(R.id.buttonWriteCommon);
        buttonReadCommon=(Button)aView.findViewById(R.id.buttonReadCommon);
        buttonDownloadCardinfo=(Button)aView.findViewById(R.id.buttonDownloadCardinfo);
        textViewDownloadinfo=(TextView) aView.findViewById(R.id.textViewDownloadinfo);
        textViewBadgeno =(TextView) aView.findViewById(R.id.textViewBadgeno);
        textViewName=(TextView) aView.findViewById(R.id.textViewName);
         textViewPersonid=(TextView) aView.findViewById(R.id.textViewPersonid);
          textViewCardguid=(TextView) aView.findViewById(R.id.textViewCardguid);
        textViewStart=(TextView) aView.findViewById(R.id.textViewStart);
        textViewEnd=(TextView) aView.findViewById(R.id.textViewEnd);
        textViewCardType=(TextView) aView.findViewById(R.id.textViewCardType);
        buttonReadcard=(Button)  aView.findViewById(R.id.buttonReadcard);
        buttonGetGuid.setOnClickListener(this);
        buttonGenPass.setOnClickListener(this);
        buttonChangePass.setOnClickListener(this);
        buttonWriteCommon.setOnClickListener(this);
        buttonReadCommon.setOnClickListener(this);
        buttonDownloadCardinfo.setOnClickListener(this);
        buttonReadcard.setOnClickListener(this);
        parent.processCardIn=true;
        parent.cardInFrag=parent.homeFrag;
        downloadDatatypes.clear();

        Hashtable hashtable2=new Hashtable();
        Hashtable hashtable3=new Hashtable();

        hashtable2.put("cardinfo",SaveCardinfoDataDownload.class);
        downloadDatatypes.add(hashtable2);
        hashtable3.put("cardblacklist",SaveCardblacklistDataDownload.class);
        downloadDatatypes.add(hashtable3);
        return aView;
    }



    public static HomeFrag newInstance(Bundle args) {
        HomeFrag f = new HomeFrag();

        f.setArguments(args);
        return f;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonGenPass: {

                byte[] ffkey ={ (byte) 255, (byte) 255, (byte) 255, (byte) 255,(byte) 255, (byte) 255 };
                String strKey = "";
                long aGuid = 0;
                final int  sec =    Integer.valueOf(editTextSec.getText().toString());
                KeyGenerate kg=new KeyGenerate();
//                kg.Key1 = key1.Text;
//                kg.Key2 = key2.Text;
//                kg.Key3 = key3.Text;
                  aGuid= Long.valueOf(editTextGuid.getText().toString());


                    kg.GenKey(aGuid, sec,  keyA);
                    strKey=KeyGenerate.bytes2HexString(keyA);
                    editTextNewPass.setText(strKey);
                    editTextOldPass.setText(strKey);

            }
            break;
            case R.id.buttonChangePass: {
                final int  sec =    Integer.valueOf(editTextSec.getText().toString());
                MifareCard.getCardGuid(parent.mfcintent, parent.mfc, new CardGuidNotify() {
                    @Override
                    public void getCardGuid(long cardno) {
                        MifareCard.WriteKeyA(parent.getIntent(),parent.mfc,parent.tag, sec, keyA,true, new KeyChangedNotify() {
                            @Override
                            public void result(boolean success, String message) {
                                if(success)
                                {
                                    parent.showToast(parent,"修改密码成功");
                                }
                                else
                                {
                                    parent.showToast(parent,"修改密码失败:"+message);

                                }

                            }
                        });


                    }
                },false);

            }
            break;
            case R.id.buttonReadcard:{

                MifareCard.getCardGuid(parent.mfcintent, parent.mfc, new CardGuidNotify() {
                    @Override
                    public void getCardGuid(long cardno) {
                        readCard(cardno);
                    }
                },false);
                break;
            }
            case R.id.buttonGetGuid: {

                MifareCard.getCardGuid(parent.mfcintent, parent.mfc, new CardGuidNotify() {
                    @Override
                    public void getCardGuid(long cardno) {
                      //  editTextGuid.setText(String.valueOf(cardno));
                        getCardinfo(String.valueOf(cardno));
                        if ((!cardinfo.getBadgeno().isEmpty())&&(!cardinfo.getBadgeno().equals(null)))
                        {
                             releaseCard();

                        }
                        else
                        {
                            parent.showToast(parent,"根据卡号查询设备中无对应的发卡信息，请下载办卡信息后发卡");
                        }

                    }
                },false);

            }
            break;
            case R.id.buttonDownloadCardinfo:{
                startDownload();
                break;
            }
            case R.id.buttonWriteCommon:{
                MifareCard.getCardGuid(parent.mfcintent, parent.mfc, new CardGuidNotify() {
                    @Override
                    public void getCardGuid(long cardno) {
                        editTextGuid.setText(String.valueOf(cardno));
                        int intSector=1;
                        CardBizBase cardBizBase=new CardBizBase(0,16,parent.tag,parent.mfcintent,parent.mfc,cardno);
                        DirectorySector directorySector=new DirectorySector(0,cardno,parent.tag,keyA,parent.mfcintent,parent.mfc);

                      //  cardBizBase.setDirectorySector();
                        Integer result= cardBizBase.GetCard();

                        if (!result.equals(0))
                        {
                            CommonSector commonSector=new CommonSector(intSector,cardno,parent.tag,keyA,parent.mfcintent,parent.mfc);
                            long card_id= Long.valueOf("55419559264164");
                            commonSector.setM_cardno(card_id);
                            commonSector.setM_cardholderid("820000000012");
                            commonSector.setM_cardholdername("张国荣");
                            commonSector.setM_cardtype(0);
                            commonSector.setM_daylimit(4);
                            commonSector.setM_timelimit(1);
                            commonSector.setM_deptcode(0);
                            commonSector.setM_startdate("2000-01-01");
                            commonSector.setM_enddate("2019-01-01");
                            commonSector.setM_carddeposit(58);
                            if (!cardBizBase.RealseCard(commonSector)){
                                parent.showToast(parent,"发卡失败:"+cardBizBase.getError());

                            }else
                            {
                                parent.showToast(parent,"发卡完成" );
                            }
                            ;
                        }
                        else
                        {
                            parent.showToast(parent,"读公共扇区完成:"+cardBizBase.getCommonSector() .getM_cardholderid()+cardBizBase.getCommonSector().getM_cardholdername()+ String.valueOf(cardBizBase.getCommonSector().getM_cardno()));
                        }


                    }
                },false);

            }
            break;
            case R.id.buttonReadCommon:{

                MifareCard.getCardGuid(parent.mfcintent, parent.mfc, new CardGuidNotify() {
                    @Override
                    public void getCardGuid(long cardno) {
                        editTextGuid.setText(String.valueOf(cardno));
                        int intSector=1;
                        KeyGenerate kg=new KeyGenerate();
                        kg.GenKey(cardno, intSector,  keyA);
                        CommonSector commonSector=new CommonSector(intSector,cardno,parent.tag,keyA,parent.mfcintent,parent.mfc);


                        if (commonSector.ReadBizDataFromSector())
                        {

                            parent.showToast(parent,"读公共扇区完成:"+commonSector.getM_cardholderid()+commonSector.getM_cardholdername()+ String.valueOf(commonSector.getM_cardno()));
                        }
                        else
                        {
                            parent.showToast(parent,"读公共扇区:"+commonSector.getM_error().toString());
                        }
                    }
                },false);
            }
            break;
        }

    }
    private  void startDownload()
    {
        downloadIndex=0;

        Hashtable hashtable=downloadDatatypes.get(downloadIndex);

        downloadDatatype=(String)hashtable.keySet().iterator().next();
        Class<? extends SaveDataDownload> aClass=    (Class<? extends SaveDataDownload>) hashtable.get(downloadDatatype);

        startDataDownload(aClass);

    }
    private void  continueDownload()
    {
        if (downloadIndex>=0 && downloadIndex<(downloadDatatypes.size() -1))
        {
            downloadIndex++;

            Hashtable hashtable=downloadDatatypes.get(downloadIndex);
            downloadDatatype=(String)hashtable.keySet().iterator().next();

            Class<? extends SaveDataDownload> aClass=    (Class<? extends SaveDataDownload>) hashtable.get(downloadDatatype);
            startDataDownload(aClass);
        }
        else
        {
            downloadDatatype="";
            downloadIndex=-1;
            parent.showToast("全部下载完成.");
        }
    }
    public  void  startDataDownload( Class<? extends SaveDataDownload> saveDataDownload){
        if (!downloadDatatype.equals("")) {
            com.realsun.service.HttpParams httpParams = new com.realsun.service.HttpParams();
            httpParams.put("postype", parent.webPosDefine.getPostype());
            httpParams.put("enterprisecode", parent.webPosDefine.getEnterprisecode());
            httpParams.put("taskresid", "558552009807");
            httpParams.put("winno", parent.webPosDefine.getWinno());
            httpParams.put("dates", StringUtility.dateToString(new Date(), "yyyyMMdd"));
            httpParams.put("datatype", downloadDatatype);
            if (parent.webPosDefine.getOrderupdateurl() != null) {
                DbOfOdersUpdateService.UserDbDownloadListener = this;
                DbDownloadParm.Builder builder = new DbDownloadParm.Builder()
                        .setRequestUrl(parent.webPosDefine.getOrderupdateurl())
                        .setService(DbOfOdersUpdateService.class)
                        .setSaveDataDownload(saveDataDownload)
                        .setRequestParams(httpParams)
                        .setSilentDownload(true)
                        .setDataBaseUrl(WebDbConfig.m_BASE_URL);

                RealsunChecker.startVersionCheck(parent, builder.build());
            }
        }


    }
    @Override
    public void onCheckerDownloading(double progress) {
        textViewDownloadinfo.setText("正在下载"+downloadDatatype+",当前进度:"+ String.format("%.2f",progress)+"%");

    }

    @Override
    public void onCheckerDownloadSuccess() {
        parent.showToast(downloadDatatype+"下载完成.");
        textViewDownloadinfo.setText("");
        continueDownload();
    }

    @Override
    public void onCheckerDownloadFail() {
        parent.showToast(downloadDatatype+"下载失败.");
        textViewDownloadinfo.setText("");
        continueDownload();

    }
}*/
