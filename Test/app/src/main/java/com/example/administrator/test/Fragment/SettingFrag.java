/*
package com.example.administrator.test.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.test.R;

import realsun.webpos.biz.WebPosDefineBiz;
import realsun.webpos.biz.WebPosDefineNotify;
import realsun.webpos.model.WebPosDefine;
import realsun.webpos.webclient.DataResult;

*/
/**
 * Created by hantao on 2017/9/2.
 *//*


public class SettingFrag extends BaseFragment implements View.OnClickListener {
    private EditText editWinno;
    private EditText editWinname;
    private EditText editDinnerRoom;
    private EditText editDeviceMac;
    private Button buttonGetPos;
    private Button buttonSetWinno;
    private WebPosDefine m_webPosDefine=new WebPosDefine();
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreateView(inflater, container, savedInstanceState);
        View aView = inflater.inflate(R.layout.activity_main2, container, false);
        buttonGetPos=aView.findViewById(R.id.buttonGetPos);
        buttonGetPos.setOnClickListener(this);
       // loadLocalWebPosDefine();
        return aView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static SettingFrag newInstance(Bundle args) {
        SettingFrag f = new SettingFrag();

        f.setArguments(args);
        return f;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonGetPos: {

              int winno= Integer.valueOf( editWinno.getText().toString());
              getWebPosDefine(winno);

            }
            break;

        }
    }
    private  void setM_webPosDefine(WebPosDefine webPosDefine){
        m_webPosDefine=webPosDefine;
        loadWebPosDefine(m_webPosDefine);

    }
   */
/* private  void applyWebPosDefine(final WebPosDefine webPosDefine){
        WebPosDefineBiz.UpdateWebPosDefine(webPosDefine, parent.mConnectivityReceiver.systemwcc, new WebPosDefineNotify() {
            @Override
            public void result(WebPosDefine posDefine, DataResult innerResult) {
                if (innerResult.error==0){
                }
                    if (posDefine.getWinno()==webPosDefine.getWinno())
                    {


                        setM_webPosDefine(posDefine);

                        if  (WebPosDefineBiz.UpdateLocalWebPosDefine(posDefine)==true)
                        {
                            *//*
*/
/*parent.webPosDefine=webPosDefine;
                            parent.showToast(parent,"当前窗口机注册成功");

                            parent.homeFragClassName=posDefine.getClassname();
                            parent.homeFrag=(BaseFragment) Fragment.instantiate(parent,posDefine.getClassname());
                            parent.HomeFrag();*//*
*/
/*


                      *//*
*/
/*  }
                        else
                        {
                            parent.showToast(parent,"当前窗口机注册失败");

                        }

                    }
                }
                else
                {
                    parent.showToast(parent,innerResult.message);

                }*//*
*/
/*
            }
        });

    }
    private  void  loadLocalWebPosDefine(){
        WebPosDefine posDefine=WebPosDefineBiz.GetLocalPosDefine();
       if (posDefine.getWinno()>0)
       {

           setM_webPosDefine(posDefine);
       }
       else
       {


           parent.showToast(parent,"窗口机未设置");
       }
    }*//*

    private  void getWebPosDefine(int winno){
        WebPosDefineBiz.GetWebPosDefine(winno, parent.mConnectivityReceiver.systemwcc, new WebPosDefineNotify() {
            @Override
            public void result(WebPosDefine posDefine, DataResult innerResult) {
                if (innerResult.error==0 && posDefine._error.isEmpty())
                {
                    setM_webPosDefine(posDefine);

                }
                else

                {
                    //parent.showToast(parent,innerResult.message);
                    Toast.makeText(parent,innerResult.message,Toast.LENGTH_SHORT).show();
                    editWinname.setText(innerResult.message);
                }

            }
        });
    }
    private void loadWebPosDefine(WebPosDefine webPosDefine){
        editWinno.setText(String.valueOf(webPosDefine.getWinno()));
        editWinname.setText(String.valueOf(webPosDefine.getWinnname()));
        editDinnerRoom.setText(String.valueOf(webPosDefine.getCateenname()));
        editDeviceMac.setText(String.valueOf(webPosDefine.getDevicemac()));
        if (webPosDefine.getWinno()==0)
        {
            buttonSetWinno.setEnabled(false);
        }
        else
        {
            buttonSetWinno.setEnabled(true);
        }
    }


}*/
