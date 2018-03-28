/*
package com.example.administrator.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.telecom.Call;


import com.example.administrator.test.core.DbDownloadListener;
import com.example.administrator.test.core.DbDownloadParm;

import java.io.IOException;

import javax.security.auth.callback.Callback;


*/
/**
 * Created by hantao on 2017/9/12.
 *//*


public abstract class ADbVersionService extends Service implements DbDownloadListener {
    protected DbDownloadParm versionParams;
    public static final String dbDownload_PARAMS_KEY = "dbDownload_PARAMS_KEY";
    public static final String dbDownload_PARAMS_EXTRA_KEY = "dbDownload_PARAMS_EXTRA_KEY";
    public static DbDownloadListener UserDbDownloadListener=null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            versionParams = intent.getParcelableExtra(dbDownload_PARAMS_KEY);

            requestVersionUrlSync();
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private void requestVersionUrlSync() {
        requestVersionUrl();
    }

    public abstract void onResponses(ADbVersionService service, String response);

    Callback stringCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            pauseRequest();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                final String result = response.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        onResponses(ADbVersionService.this, result);

                    }
                });
            } else {
                pauseRequest();
            }
        }

    };

    */
/**
     * 间隔请求
     *//*

    private void pauseRequest() {
        long pauseTime = versionParams.getPauseRequestTime();
        //不为-1 间隔请求
        if (pauseTime > 0) {
            //ALog.e("请求版本接口失败，下次请求将在" + pauseTime + "ms后开始");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestVersionUrlSync();
                }
            }, pauseTime);
        }
    }

    private void requestVersionUrl() {
        OkHttpClient client = RealsunHttp.getHttpClient();
        HttpRequestMethod requestMethod = versionParams.getRequestMethod();
        Request request = null;
        switch (requestMethod) {
            case GET:
                request = RealsunHttp.get(versionParams).build();
                break;
            case POST:
                request = RealsunHttp.post(versionParams).build();
                break;
            case POSTJSON:
                request = RealsunHttp.postJson(versionParams).build();
                break;
        }
        client.newCall(request).enqueue(stringCallback);
    }


    String downloadUrl, title, updateMsg;
    Bundle paramBundle;

//    public void showVersionDialog( String title, String updateMsg) {
//        showVersionDialog( title, updateMsg, null);
//    }

    public void showVersionDialog(String title, String updateMsg, Bundle paramBundle) {

        this.title = title;
        this.updateMsg = updateMsg;
        this.paramBundle = paramBundle;
        String strDatadownloadparm=this.paramBundle.getString("Datadownloadparm");
        Datadownloadparm datadownloadparm=  Datadownloadparm.parseObject(strDatadownloadparm);
        GetDatatableParm getDatatableParm=new GetDatatableParm();
        getDatatableParm.cmswhere=datadownloadparm.getStrCmswhere();
        getDatatableParm.resid=datadownloadparm.getStrResid();
        getDatatableParm.subresid=datadownloadparm.getStrSubResid();
        getDatatableParm.subcmscolumns=datadownloadparm.getStrSubColumns();
        getDatatableParm.pageindex=0;
        if (datadownloadparm.getStrPagesize().isEmpty()||datadownloadparm.getStrPagesize()==null)
        {
            getDatatableParm.pagesize=100;
        }
       else
        { getDatatableParm.pagesize= Integer.parseInt(datadownloadparm.getStrPagesize());}
        getDatatableParm.cmscolumns= datadownloadparm.getStrColumns();
        versionParams.setIsRun(datadownloadparm.getStrIsRun());
        versionParams.setGetDatatableParm(getDatatableParm);
        versionParams.setTaskid(datadownloadparm.getStrTaskid());
        versionParams.setUpdateMethod(datadownloadparm.getStrUpdateMethod());
        //如果后台定义了数据地址，使用后台定义的地址。
        if ((!datadownloadparm.getStrBaseUrl().isEmpty())&&datadownloadparm.getStrBaseUrl()!=null)
        {
            if (datadownloadparm.getStrBaseUrl().length()>0)
            {
                versionParams.setDatabaseurl(datadownloadparm.getStrBaseUrl());
            }
        }


        if (versionParams.isSilentDownload()) {
            silentDownload();
        } else {
            goToVersionDialog();
        }
    }

    private void silentDownload() {
        if (ADbVersionService.UserDbDownloadListener==null)
        { DbDownloadManager.downloadDb(getApplicationContext(),   versionParams, this);}
        else

        {
            DbDownloadManager.downloadDb(getApplicationContext(),   versionParams,ADbVersionService.UserDbDownloadListener);
        }

    }

    @Override
    public void onCheckerDownloading(double progress) {

    }

    @Override
    public void onCheckerDownloadSuccess() {

    }

    @Override
    public void onCheckerDownloadFail() {

    }

    private void goToVersionDialog() {
        Intent intent = new Intent(getApplicationContext(), versionParams.getCustomDownloadActivityClass());
        if (updateMsg != null)
            intent.putExtra("text", updateMsg);
        if (title != null)
            intent.putExtra("title", title);
        intent.putExtra(dbDownload_PARAMS_KEY, versionParams);
        if (paramBundle != null)
            intent.putExtra(dbDownload_PARAMS_EXTRA_KEY, paramBundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        stopSelf();
    }

    public void setVersionParams(DbDownloadParm versionParams) {
        this.versionParams = versionParams;
    }
}
*/
