/*

package com.example.administrator.test.core;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;


import com.example.administrator.test.util.ConnectionChangeReceiver;
import com.example.administrator.test.util.WebClientConnection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


import realsun.webpos.Data.DataDownLoadNotify;
import realsun.webpos.webclient.DataResult;
import realsun.webpos.webclient.GetDatatableParm;
import realsun.webpos.webclient.WebDataNotifier;
import realsun.webpos.webclient.WebDataService;

import static android.content.Context.NOTIFICATION_SERVICE;


*/
/**
 * Created by hantao on 2017/9/12.
 *//*



public class DbDownloadManager {
    private static int lastProgress = 0;

    public static void downloadDb(final Context context , final DbDownloadParm versionParams, final DbDownloadListener listener) {
//        if (url == null || url.isEmpty()) {
//            listener.onCheckerDownloadFail();
//            return;
//        }
        if (!versionParams.getIsRun().equals("Y"))
        {
            listener.onCheckerDownloadSuccess();
            return;
        }
       */
/* if (versionParams.isSilentDownload()) {
           silentdownloadDb(context, versionParams, listener);
            return;
        }*//*

        lastProgress = 0;

        final NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
       // Intent intent = new Intent(context, versionParams.getCustomDownloadActivityClass());
      //  intent.putExtra("isRetry", false);
      //  PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        //builder.setContentIntent(pendingIntent);
       // builder.setSmallIcon(com.allenliu.versionchecklib.R.mipmap.ic_launcher);
       // builder.setContentTitle(context.getString(com.allenliu.versionchecklib.R.string.app_name));
       // builder.setTicker(context.getString(com.allenliu.versionchecklib.R.string.versionchecklib_downloading));
      //  builder.setContentText(String.format(context.getString(com.allenliu.versionchecklib.R.string.versionchecklib_download_progress), 0));
        Notification notification = builder.build();
        notification.vibrate = new long[]{500, 500};
        notification.defaults = Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;
        manager.notify(0, notification);
        try {

            DataDownLoadNotify ddn=  versionParams.getSaveDataDownload().getConstructor().newInstance();
            ddn.SetDbDownloadParm(versionParams);


            prePareDownloadData(versionParams,listener, ddn);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }


    private static void silentdownloadDb(final Context context, final DbDownloadParm versionParams, final DbDownloadListener listener) {

        try {

            DataDownLoadNotify ddn=  versionParams.getSaveDataDownload().getConstructor().newInstance();
            ddn.SetDbDownloadParm(versionParams);


            prePareDownloadData(versionParams,listener, ddn);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

//    public static boolean checkAPKIsExists(Context context, String downloadPath) {
//        File file = new File(downloadPath);
//        boolean result = false;
//
//        return result;
//
//    }
private static void downloadData(final GetDatatableParm getDataParm, final WebDataService wds, final double diffs, boolean endofrows , final DbDownloadListener listener, final DataDownLoadNotify ddn){

    final WebClientConnection wcc = ConnectionChangeReceiver.getInstence().systemwcc;
    if (endofrows){

        listener.onCheckerDownloadSuccess();
        return;

    }
    else {
        if (diffs >100)
            listener.onCheckerDownloading(100);
        else  listener.onCheckerDownloading(diffs);;


    }

    wds.retrieve2(getDataParm, wcc, new WebDataNotifier(){

        @Override
        public void result(DataResult result) {
            // TODO Auto-generated method stub
            if (result.error==0 && result.total>0 )
            {

                getDataParm.pageindex++;

                double  diff;
                if (result.total/getDataParm.pagesize>0)
                {
                    diff=(double)100/(result.total/getDataParm.pagesize)+diffs;

                }
                else
                {
                    diff=100;
                }
                String rt="Y";
                if (result.data.size()>0)
                {
                    rt=ddn.Result(result.data,result);
                }

                if (rt.equals("Y"))
                {
                    if (getDataParm.pagesize*(getDataParm.pageindex-1) >=result.total)
                    {
                        downloadData(getDataParm,wds,diffs,true,listener,ddn);
                    }
                    else
                    {
                        downloadData(getDataParm,wds,diff,false,listener,ddn);

                    }


                }
                else
                {
                    listener.onCheckerDownloadFail();
                    return;

                }

            }
            else
            {
               // String rt=ddn.Result(new ArrayList(),result);

               // if (rt.equals("Y"))
               // {
                   // downloadData(getDataParm,wds,diffs,true,listener,ddn);
               // }
               // else
                //{


                    listener.onCheckerDownloadFail();
                    return;

               // }


            }

        }

    });




}
    public static void prePareDownloadData(DbDownloadParm  aDbDownloadParm, final DbDownloadListener listener, DataDownLoadNotify ddn){

        final WebDataService wds=new WebDataService();
        final ArrayList data=new ArrayList();

        final GetDatatableParm getDataParm=aDbDownloadParm.getGetDatatableParm();
        getDataParm.DataBaseUrl=aDbDownloadParm.getDatabaseurl();
        downloadData(getDataParm,wds,0.00,false,listener,ddn);
    }
}

*/
