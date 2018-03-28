package com.example.administrator.test.Fragment;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.R;
import com.example.administrator.test.core.DbDownloadListener;
import com.example.administrator.test.smartdevice.utils.StringUtility;
import com.example.administrator.test.util.ConnectionChangeReceiver;
import com.example.administrator.test.util.WebClientConnection;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.FindMultiCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;


import realsun.webpos.Nfc;
import realsun.webpos.model.DinnerTimesOfCatteen;

import static com.alibaba.fastjson.JSON.parseArray;

/**
 * Created by hantao on 2017/9/2.
 */

public class FetchOrderFrag extends BaseFragment {
    private NfcAdapter nfcAdapter;
    private TextView textCardview;
    private TextView textViewDataDownload1;
    private Button buttonBack;
    private PendingIntent pendingIntent;
    private IntentFilter[] mFilters;
    private TextView timeView;
    private    boolean processcard=false;
    private TextView textViewCanteenRoom;
    private TextView textViewWinName;
    private String[][] mTechLists;
    private Nfc mynfc;
    private Intent intents;
    private int totalSaved;
    private TextView textViewDinnerType;
    private DinnerTimesOfCatteen dinnerTimesOfCatteen=new DinnerTimesOfCatteen();
    private List<DinnerTimesOfCatteen> dinnerTimesOfCatteenList=new ArrayList<DinnerTimesOfCatteen>();
    private List<DinnerTimesOfCatteen> oldOfdinnerTimesOfCatteenList;
    int[] colorArray = new int[] { Color.BLUE, Color.GREEN, Color.BLUE,
            Color.YELLOW, Color.WHITE };
    int colorIndex = 0;
    Button buttonScanner;

    private static final String FONT_DIGITAL_7 = "fonts" + File.separator
            + "digital-7.ttf";

    private static final String DATE_FORMAT = "%02d:%02d:%02d";
    private static final int REFRESH_DELAY = 500;
    private static boolean stoptaskofCheckDinnerTimeParmOnce=false;
    private  static String downloadDatatype="";
    private  static ArrayList<Hashtable> downloadDatatypes=new ArrayList<Hashtable>();
    private  static int downloadIndex=-1;
    private byte password[] = { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
            (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
            (byte) 0xff, (byte) 0xff, (byte) 0xff };

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);

    }
    private void CheckWebConnection()
    {
        Date dtNow=new Date();

        WebClientConnection wcc = ConnectionChangeReceiver.getInstence().systemwcc;

        String strToday= StringUtility.dateToString(dtNow,"yyyyMMdd");
        String strConDay=StringUtility.dateToString(wcc.conTime,"yyyyMMdd");
        if (!strToday.equals(strConDay)){
            ConnectionChangeReceiver.getInstence().loginService(parent);

        }
    }
    @Override
    public void CardIn(String cardno) {
        super.CardIn(cardno);


        //DealWithCard(cardno);

    }
    private Timer timer;
    private Timer timerClock;
    ListView listView;
    private TimerTask taskofClock;
    private TimerTask taskofCheckDinnerTimeParmOnce;
    private void setCheckDinnerTimeParmOnceTimerTask(long delay)
    {
        taskofCheckDinnerTimeParmOnce=new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
               if (stoptaskofCheckDinnerTimeParmOnce)
               {

               }
               else
               {
                  // handler.sendMessage(message);
               }

            }
        };

        timer.schedule(taskofCheckDinnerTimeParmOnce, delay);
    }
    private void setTimerOftaskofClock()
    {

        timerClock.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 2;

              //  handler.sendMessage(message);
            }
        }, 500,500);
    }



}
