package com.example.administrator.test.Activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.Fragment.BaseFragment;
import com.example.administrator.test.Interfaces.CardGuidNotify;
import com.example.administrator.test.R;
import com.example.administrator.test.card.AppSector;
import com.example.administrator.test.card.CardSector;
import com.example.administrator.test.card.CardSectorBase;
import com.example.administrator.test.card.CommonSector;
import com.example.administrator.test.card.DirectorySector;
import com.example.administrator.test.smartdevice.utils.StringUtility;
import com.example.administrator.test.util.ConnectionChangeReceiver;
import com.example.administrator.test.util.KeyGenerate;
import com.example.administrator.test.util.MifareCard;
import com.example.administrator.test.util.WebClientConnection;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import realsun.webpos.biz.CardInfoBiz;
import realsun.webpos.biz.WebPosDefineBiz;
import realsun.webpos.biz.WebPosDefineNotify;
import realsun.webpos.biz.card.CardBizBase;
import realsun.webpos.model.*;
import realsun.webpos.model.Teacher;
import realsun.webpos.webclient.DataResult;
import realsun.webpos.webclient.GetDatatableParm;
import realsun.webpos.webclient.SaveDataTableParm;
import realsun.webpos.webclient.WebDataNotifier;
import realsun.webpos.webclient.WebDataService;

public class Main2Activity extends AppCompatActivity  implements  View.OnClickListener {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    public MifareClassic mfc;
    public Intent mfcintent;
    private Intent intents;
    public Tag tag;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    public Boolean processCardIn=true;
    public BaseFragment cardInFrag=null;
    private boolean isnews = true;


    private  static String downloadDatatype="";
    private  static int downloadIndex=-1;
    private byte[] keyA ={ 0, 0, 0, 0, 0, 0 };
    private    Cardinfo cardinfo=new Cardinfo();
    private  static ArrayList<Hashtable> downloadDatatypes=new ArrayList<Hashtable>();


    final static String resid="574185879466";
    public MainActivity parent;
    private EditText editWinno;
    private EditText editName;
    private EditText editPhoto;
    private EditText editTid;
    private EditText editDateTime;
    private Button buttonGetPos;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Button buttonInsert;
    private Button buttonDown;
    private Button buttonWrite;
    private Button buttonRead;
    private WebPosDefine m_webPosDefine = new WebPosDefine();
    public WebPosDefine webPosDefine = new WebPosDefine();
    private com.example.administrator.test.Model.Teacher teacher = new com.example.administrator.test.Model.Teacher();
    public ConnectionChangeReceiver mConnectivityReceiver = ConnectionChangeReceiver.getInstence();
    public static WebClientConnection systemwcc = new WebClientConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LitePal.initialize(this);

        RelativeLayout view = (RelativeLayout) findViewById(R.id.main2view);
        editWinno = findViewById(R.id.editWinno);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonGetPos = findViewById(R.id.buttonGetPos);
        buttonDown = findViewById(R.id.buttonDown);
        buttonWrite = findViewById(R.id.buttonWrite);
        buttonRead = findViewById(R.id.buttonRead);
        editName = findViewById(R.id.editName);
        editPhoto = findViewById(R.id.editPhoto);
        editTid = findViewById(R.id.editAd);
        editDateTime = findViewById(R.id.editDateTime);
        buttonDelete.setOnClickListener(this);
        buttonGetPos.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonInsert.setOnClickListener(this);
        buttonWrite.setOnClickListener(this);
        buttonRead.setOnClickListener(this);
        buttonDown.setOnClickListener(this);
        mConnectivityReceiver = ConnectionChangeReceiver.getInstence();

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
       //mConnectivityReceiver.mainActivity = this;
        IntentFilter filter = new IntentFilter();
        filter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mConnectivityReceiver, filter);
    }
        //初始化一个Adapter

        /*TeacherAdapter teacherAdapter = new TeacherAdapter(this, R.layout.teacher_item, Teacher.getAllTeachers());

        ListView listView = (ListView) findViewById(R.id.teacher_listView);

        listView.setAdapter(teacherAdapter);*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonGetPos: {
                int winno = Integer.valueOf(editWinno.getText().toString());
                getWebPosDefine(winno);

            }

            break;
            case R.id.buttonDelete: {
                System.out.println("进入了");
                testdeletedata();
                // DataSupport.find(Teacher.class,2);
                // DataSupport.delete(Teacher.class,2);
            }
            break;
            case R.id.buttonUpdate: {
                // DataSupport.update(realsun.webpos.model.Teacher.class,1);
                System.out.println("进入了");
                testupdatedata();
            }
            break;
            case R.id.buttonInsert: {
                System.out.println("进入了");
                LitePal.getDatabase();
                testofwebdata();

            }
            break;
            /*case R.id.buttonDown:{
                System.out.println("进入了");
                LitePal.getDatabase();
                testdowndata();

            }
            break;*/
            case R.id.buttonRead: {
                System.out.println("进入了");
                MifareCard.getCardGuid(mfcintent, mfc, new CardGuidNotify() {
                    @Override
                    public void getCardGuid(long cardno) {
                        readCard(cardno);
                    }
                }, false);

            }
            break;
            case R.id.buttonWrite: {
                System.out.println("进入了");
                MifareCard.getCardGuid(mfcintent, mfc, new CardGuidNotify() {
                    @Override
                    public void getCardGuid(long cardno) {
                        //  editTextGuid.setText(String.valueOf(cardno));
                        getCardinfo(String.valueOf(cardno));
                        if ((!teacher.getName().isEmpty()) && (!teacher.getName().equals(null))) {
                            releaseCard();

                        } else {
                            //parent.showToast(parent,"根据卡号查询设备中无对应的发卡信息，请下载办卡信息后发卡");
                            System.out.println("根据卡号查询设备中无对应的发卡信息，请下载办卡信息后发卡");
                            //Toast.makeText(this, "根据卡号查询设备中无对应的发卡信息，请下载办卡信息后发卡", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, false);


            }
            break;
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
                                int intSector = 9;
                                Date currentTime = new Date();//currentTime就是系统当前时间
                                String cno = String.valueOf(cardno);
                                teacher = CardInfoBiz.fetchTeacherinfo(cno);
                                System.out.println("时间"+teacher.getDatetime());
                                teacher.setDatetime(new Date());
                                teacher.save();
                                System.out.println("时间"+teacher.getDatetime());
                                CardBizBase cardBizBase = new CardBizBase(0,16,tag,mfcintent,mfc,cardno);
                                KeyGenerate kg=new KeyGenerate();
                                kg.GenKey(cardno, intSector,  keyA);
                                AppSector appSector = new AppSector(intSector,Long.valueOf(cardno),tag,keyA,mfcintent,mfc);
                                appSector.ReadBizDataFromSector();
                                System.out.println("时间"+appSector.getM_datetime());
                                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd");
                                Date datetime1 =null;
                                Date datetime2 = null;
                                Date datetime3 = null;
                                Date datetime4 = null;
                                Date datetime5 = null;
                                Date datetime6 = null;
                                String gg= "";
                                try {
                                    //将时间转化成相同格式的Date类型
                                    String a  = fmt.format(currentTime);
                                    datetime1 = fmt.parse(a);
                                    String b  = fmt2.format(currentTime);
                                    datetime2 = fmt2.parse(b);
                                    String c = appSector.getM_datetime().toString();
                                    datetime3 = fmt.parse(c);
                                    String d = appSector.getM_datetime().toString();
                                    datetime4 = fmt2.parse(d);
                                    String dd = fmt2.format(datetime4);
                                    String e  = dd+" 22:30:00";
                                    datetime5 = fmt2.parse(e);
                                    String f  = dd+" 24:00:00";
                                    datetime6 = fmt2.parse(f);
                                    gg = fmt.format(currentTime);
                                    /*
                                    datetime1 = fmt.parse(currentTime1);
                                    datetime2 = fmt2.parse(currentTime1);
                                    datetime3 = fmt.parse(appSector.getM_datetime());
                                    datetime4 = fmt2.parse(appSector.getM_datetime());
                                    datetime5 = fmt2.parse(appSector.getM_datetime()+"22:30:00");
                                    datetime6 = fmt2.parse(appSector.getM_datetime()+"24:00:00");
                                   */// String datetime33 = fmt2.parse(currentTime.toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                // Long time=(datetime3.getTime() - strbeginDate.getTime());
                                // System.out.println("时间的差值="+time);
                                if(datetime2.equals(datetime4)){
                                    if((datetime1.getHours()-datetime3.getHours()>3)){
                                        Toast.makeText(getApplicationContext(),"刷卡成功",Toast.LENGTH_SHORT).show();
                                        System.out.println("刷卡成功");
                                        appSector.setM_datetime(currentTime.toString());
                                        teacher.setDatetime(currentTime);
                                        teacher.update(cardno);
                                    }else{
                                        Toast.makeText(getApplicationContext(),"您已经刷过一次了！",Toast.LENGTH_SHORT).show();
                                        System.out.println("您已经刷过一次了！");
                                    }

                                }else{
                                    if((datetime3.getTime()-datetime5.getTime())>0&&datetime6.getTime()-datetime3.getTime()>0){
                                        Toast.makeText(getApplicationContext(),"您已经刷过一次卡了",Toast.LENGTH_SHORT).show();
                                        System.out.println("您已经刷过一次卡了");
                                    }else{
                                        Toast.makeText(getApplicationContext(),"刷卡成功",Toast.LENGTH_SHORT).show();
                                        System.out.println("刷卡成功");
                                        appSector.setM_datetime(gg);
                                        appSector.WriteBizDataToSector();
                                        teacher = CardInfoBiz.fetchTeacherinfo(cno);
                                        teacher.setDatetime(currentTime);
                                        teacher.save();
                                    }
                                }
















                              /*  if (datetime22.equals(datetime3)) {
                                    System.out.println("满足条件！");
                                    if ((datetime.getTime() - strbeginDate.getTime()) > 0 && (strendDate.getTime() - datetime.getTime()) > 0) {
                                        Toast.makeText(getApplicationContext(), "您已经在当前时间段刷过一次了！", Toast.LENGTH_LONG).show();
                                    }
                                    if ((currentTime.getTime() - strbeginDate.getTime()) > 0 && (strendDate.getTime() - currentTime.getTime()) > 0) {
                                        Toast.makeText(getApplicationContext(), "当前时间在范围内", Toast.LENGTH_LONG).show();
                                        System.out.println("当前时间在范围内");

                                } else {
                                    System.out.println("当前时间不在范围内");
                                }
                            }else

                            {
                                System.out.println("满足条件");
                            }*/
                        /*if(datetime.getTime()-strbeginDate.getTime() < 0 && (strendDate.getTime() - datetime.getTime()) < 0) {
                            if ((currentTime.getTime() - strbeginDate.getTime()) > 0 && (strendDate.getTime() - currentTime.getTime()) > 0) {
                                cardinfo.setDatetime(currentTime);
                                cardinfo.save();
                                Toast.makeText(getApplicationContext(), "当前时间在范围内", Toast.LENGTH_LONG).show();
                                System.out.println("当前时间在范围内");
                            } else {
                                System.out.println("当前时间不在范围内");
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "您已经刷过一次卡了！请勿重复刷卡", Toast.LENGTH_LONG).show();
                        }*/
                              //  AppSector appSector = new AppSector(intSector,Long.valueOf(cardno),tag,keyA,mfcintent,mfc);
                           //     appSector.setM_datetime(currentTime);

                      //  cardBizBase.RealseCardByApp(appSector);
                      //  System.out.println("时间杀杀杀"+cardBizBase.getAppSector().getM_datetime());

                        //cardBizBase.writeCardCommonSector(commonSector);
                        //CardBizBase cardBizBase=new CardBizBase(0,16,tag,mfcintent,mfc,Long.valueOf(cardno));
                       // System.out.println("时间"+commonSector1.getM_carddatetime());

                        com.example.administrator.test.Model.Teacher teacher = new com.example.administrator.test.Model.Teacher();
                        String cardid = String.valueOf(cardno);
                         List<com.example.administrator.test.Model.Teacher> teachers = DataSupport.where("cardno=?",cardid).find(com.example.administrator.test.Model.Teacher.class);
                        editName = findViewById(R.id.editName);
                        editPhoto = findViewById(R.id.editPhoto);
                        String name = teachers.get(0).getName();
                        editName.setText(name);
                        editDateTime.setText(appSector.getM_datetime());
                        //editPhoto.setText(photo);

                    }
                },false);
            }
        }
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
        //replaceLayout( R.id.content, cardInFrag, true);

    }
    private void getWebPosDefine(int winno) {
        WebPosDefineBiz.GetWebPosDefine(winno,mConnectivityReceiver.systemwcc, new WebPosDefineNotify() {
            @Override
            public void result(WebPosDefine posDefine, DataResult innerResult) {
                if (innerResult.error == 0 && posDefine._error.isEmpty()) {
                    //setM_webPosDefine(posDefine);
                    Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT).show();

                    List<String> lists = new ArrayList<String>();
                    lists = innerResult.data;
                    Map<String,Object> map = new HashMap<String,Object>();
                    Iterator<String> maps = lists.iterator();
                    String a = maps.next();
                    for (int i = 0; i < lists.size(); i++) {
                        // System.out.println("数据"+lists.get(0)+"大小"+lists.size());
                        //Toast.makeText(getApplicationContext(), lists.size(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void testofwebdata(){
        GetDatatableParm getDataParm=new GetDatatableParm();

        final WebDataService wds=new WebDataService();
        getDataParm.resid="574185879466";
        getDataParm.cmswhere=""; ///*C3_542290897385='556913549137'*/
        wds.retrieve(getDataParm, mConnectivityReceiver.getInstence().systemwcc, new WebDataNotifier(){

            @Override
            public void result(DataResult result) {
                // TODO Auto-generated method stub
                try{
                    //String str=result.data.get(0).toString();
                    //List<Teacher> str2 = new ArrayList<Teacher>();
                    //str2.add(teacher);
                    //String aa = str2.get(0).toString();
                    //Teacher teacher= JSON.parseObject(str, Teacher.class);editTid = findViewById(R.id.editAd);
                    String Id = editTid.getText().toString();
                    editName = findViewById(R.id.editName);
                    String Name = editName.getText().toString();
                    editPhoto = findViewById(R.id.editPhoto);
                    String Photo = editPhoto.getText().toString();

                    String str=result.data.get(0).toString();
                    Teacher teacher= JSON.parseObject(str, Teacher.class);

                    com.example.administrator.test.Model.Teacher teacher1 = new com.example.administrator.test.Model.Teacher();
                    teacher1.setName(Name);
                    teacher1.setPhoto(Photo);
                    teacher1.setTid(Id);
                    teacher1.setCardno("3806442286");
                    teacher1.setEnddate(new Date());
                   // DataSupport.delete(com.example.administrator.test.Model.Teacher.class,1);
                    teacher1.save();
                    //teacher1.update(1);


                    teacher._id=16;
                    teacher._state="added";
                    SaveDataTableParm parm=new SaveDataTableParm();
                    parm.resid="574185879466";
                    parm.dataoflist.add(teacher);
                    wds.save(parm,  mConnectivityReceiver.getInstence().systemwcc, new WebDataNotifier(){

                        @Override
                        public void result(DataResult result) {
                            // TODO Auto-generated method stub
                            if(result.message.equals("操作成功")){
                                Toast.makeText(getApplicationContext(),"添加成功！",Toast.LENGTH_SHORT);
                                System.out.println("添加成功！");
                                editTid.getText().clear();
                                editName.getText().clear();
                                editPhoto.getText().clear();
                            }else{
                                Toast.makeText(getApplicationContext(),"添加失败！",Toast.LENGTH_SHORT);
                            }
                        }});

                }
                catch (Exception e){

                }

            }});
    }
    private void testupdatedata(){
        GetDatatableParm getDataParm=new GetDatatableParm();
        //final Teacher teacher = new Teacher();
        final WebDataService wds=new WebDataService();
        editTid = findViewById(R.id.editAd);
        final String Id = editTid.getText().toString();
        getDataParm.resid="574185879466";
        getDataParm.cmswhere="C3_574859259975="+Id; ///*C3_542290897385='556913549137'*/
        wds.retrieve(getDataParm, mConnectivityReceiver.getInstence().systemwcc, new WebDataNotifier(){

            @Override
            public void result(DataResult result) {
                // TODO Auto-generated method stub
                try{
                    String str=result.data.get(0).toString();
                    Teacher teacher= JSON.parseObject(str, Teacher.class);
                    editName = findViewById(R.id.editName);
                    editPhoto = findViewById(R.id.editPhoto);
                    String Name = editName.getText().toString();
                    String Photo = editPhoto.getText().toString();
                    teacher.setName(Name);
                    teacher.setPhoto(Photo);
                    teacher.setTid(Id);

                    teacher._id=3;
                    teacher._state="modified";
                    SaveDataTableParm parm=new SaveDataTableParm();
                    parm.resid="574185879466";
                    parm.dataoflist.add(teacher);
                    wds.save(parm,  mConnectivityReceiver.getInstence().systemwcc, new WebDataNotifier(){

                        @Override
                        public void result(DataResult result) {
                            // TODO Auto-generated method stub
                            if(result.message.equals("操作成功")){
                                Toast.makeText(getApplicationContext(),"修改成功！",Toast.LENGTH_SHORT);
                                System.out.println("修改成功！");
                                editTid.getText().clear();
                                editName.getText().clear();
                                editPhoto.getText().clear();
                            }else{
                                Toast.makeText(getApplicationContext(),"修改失败！",Toast.LENGTH_SHORT);
                            }
                        }});

                }
                catch (Exception e){

                }

            }});
    }
    private void testdeletedata(){
        GetDatatableParm getDataParm=new GetDatatableParm();
        //final Teacher teacher = new Teacher();
        final WebDataService wds=new WebDataService();
        editTid = findViewById(R.id.editAd);
        String Id = editTid.getText().toString();
        getDataParm.resid="574185879466";
        getDataParm.cmswhere="C3_574859259975="+Id; ///*C3_542290897385='556913549137'*/
        wds.retrieve(getDataParm, mConnectivityReceiver.getInstence().systemwcc, new WebDataNotifier(){
            @Override
            public void result(DataResult result) {
                // TODO Auto-generated method stub
                try{
                    String str=result.data.get(0).toString();
                    Teacher teacher= JSON.parseObject(str, Teacher.class);
                    editTid.getText().clear();
                    editName.getText().clear();
                    editPhoto.getText().clear();
                    teacher._id=3;
                    teacher._state="removed";
                    SaveDataTableParm parm=new SaveDataTableParm();
                    parm.resid="574185879466";
                    parm.dataoflist.add(teacher);
                    wds.save(parm,  mConnectivityReceiver.getInstence().systemwcc, new WebDataNotifier(){

                        @Override
                        public void result(DataResult result) {
                            // TODO Auto-generated method stub

                        }});

                }
                catch (Exception e){

                }

            }});
    }
    private void getCardinfo(String cardno)
    {

        teacher= CardInfoBiz.fetchTeacherinfo(cardno);
        if (teacher.getName().isEmpty()||teacher.getName().equals(null))
        {
            clearCardinfoview();
            buttonWrite.setEnabled(false);
            buttonWrite.setEnabled(false);
            Toast.makeText(this, "根据卡号查询设备中无对应的发卡信息，请下载办卡信息后发卡", Toast.LENGTH_SHORT).show();
            //parent.showToast(parent,"根据卡号查询设备中无对应的发卡信息，请下载办卡信息后发卡");
        }
        else
        {
            buttonWrite.setEnabled(true);
            editName.setText(teacher.getName());
            editPhoto.setText(teacher.getPhoto());
            editTid.setText(teacher.getTid());
            //editDateTime.setText(teacher.getDatetime().toString());
            //editEndDate.setText(teacher.getEnddate().toString());
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
            //Date date = new Date(System.currentTimeMillis());
           // String a = simpleDateFormat.format(date);
            String datetime = StringUtility.timeToString(teacher.getDatetime());
            String enddate = StringUtility.timeToString(teacher.getEnddate());
            editDateTime.setText(datetime);
            //String strStart;
            //String strEnd;
            //strStart= StringUtility.dateToString(cardinfo.getStartDate(),"yyyy-MM-dd");
            //strEnd=StringUtility.dateToString(cardinfo.getEndDate(),"yyyy-MM-dd");
            //textViewStart.setText(strStart);
            //textViewEnd.setText(strEnd);
            //textViewCardType.setText(cardinfo.getCardType());
            buttonWrite.setEnabled(true);
            setColorRed();

        }
    }
    private  void  clearCardinfoview()
    {
        editTid .setText("");
        editName.setText("");
        editPhoto.setText("");
        editDateTime.setText("");
    }

    private void releaseCard(){
        if ((teacher.getName().isEmpty()) || (teacher.getName().equals(null))) {
            //parent.showToast(parent,"当前办卡信息!无法发行卡片。");
            Toast.makeText(this, "当前办卡信息！无法发行卡片。", Toast.LENGTH_SHORT).show();
            return;

        }
        int intSector=1;
        CardBizBase cardBizBase=new CardBizBase(0,16,tag,mfcintent,mfc,Long.valueOf(teacher.getCardno()));
        DirectorySector directorySector=new DirectorySector(0,Long.valueOf(teacher.getCardno()),tag,keyA,mfcintent,mfc);

        //  cardBizBase.setDirectorySector();
        Integer result= cardBizBase.GetCard();
        Date currentTime = new Date();//currentTime就是系统当前时间
//定义时间的格式
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date strbeginDate = null;//起始时间
        Date strendDate = null;//结束时间
        Date datetime = null;
        String startdate = "2018-3-26 11:00:00";
        String enddate = "2018-3-26 15:00:00";
        try {
            strendDate = fmt.parse(enddate);
            strbeginDate = fmt.parse(startdate);//将时间转化成相同格式的Date类型
            String a = cardBizBase.getCommonSector().getM_carddatetime()+" 14:00:00";
            datetime = fmt.parse(a);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long time=(currentTime.getTime() - strbeginDate.getTime());
        System.out.println("时间的差值="+time);
        if(datetime.getTime()-strbeginDate.getTime() < 0 && (strendDate.getTime() - datetime.getTime()) < 0) {
            if ((currentTime.getTime() - strbeginDate.getTime()) > 0 && (strendDate.getTime() - currentTime.getTime()) > 0) {
                cardinfo.setDatetime(currentTime);
                cardinfo.save();
                Toast.makeText(this, "当前时间在范围内", Toast.LENGTH_LONG).show();
                System.out.println("当前时间在范围内");
            } else {
                System.out.println("当前时间不在范围内");
            }
        }else{
            Toast.makeText(this, "您已经刷过一次卡了！请勿重复刷卡", Toast.LENGTH_LONG).show();
        }
        if (!result.equals(0))
        {
            CommonSector commonSector=new CommonSector(intSector,Long.valueOf(teacher.getCardno()),tag,keyA,mfcintent,mfc);
            //commonSector.pushData();
            long card_id=Long.valueOf(teacher.getCardno());
            commonSector.setM_cardno(card_id);
            commonSector.setM_cardholderid(teacher.getTid());
            commonSector.setM_cardholdername(teacher.getName());
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
            Date date = new Date();
            DateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdate = myformat.format(date);
            //String datetime=StringUtility.dateToString(new Date());
            commonSector.setM_carddatetime(nowdate);

            if (!cardBizBase.RealseCard(commonSector)){
                Toast.makeText(this, "发卡失败", Toast.LENGTH_SHORT).show();
                //parent.showToast(parent,"发卡失败:"+cardBizBase.getError());

            }else
            {
                Toast.makeText(this, "发卡完成", Toast.LENGTH_SHORT).show();
               // parent.showToast(parent,"发卡完成" );
            }
        }
        else
        {
            Toast.makeText(this, "此卡已经发行", Toast.LENGTH_SHORT).show();
           // parent.showToast(parent,"此卡已经发行");
        }
    }
        private void readCard(long cardno)
        {
            int intSector=1;
            CardBizBase cardBizBase=new CardBizBase(0,16,tag,mfcintent,mfc, cardno);
            DirectorySector directorySector=new DirectorySector(0, cardno,tag,keyA,mfcintent,mfc);
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
                editTid .setText(cardBizBase.getCommonSector().getM_cardholderid());
                editName.setText(cardBizBase.getCommonSector().getM_cardholdername());
                editDateTime.setText(cardBizBase.getCommonSector().getM_carddatetime());
                //textViewPersonid.setText(String.valueOf(cardBizBase.getCommonSector().getM_cardno()));
               // textViewCardguid.setText(cardinfo.getCardNo());
              //  textViewStart.setText(cardBizBase.getCommonSector().getM_startdate());
              //  textViewEnd.setText(cardBizBase.getCommonSector().getM_enddate());
                setColorBlue();

            }
            else
            {
                //parent.showToast(parent,"读卡失败，错误代码:"+String.valueOf(result)+"错误信息:"+cardBizBase.getError());
                Toast.makeText(this, "读卡失败，错误代码"+String.valueOf(result)+"错误信息:"+cardBizBase.getError(), Toast.LENGTH_SHORT).show();

            }

        }
    private  void setColorBlue()
    {


        editName.setTextColor(Color.BLUE);
        editTid.setTextColor(Color.BLUE);

        editPhoto.setTextColor(Color.BLUE);
        editDateTime.setTextColor(Color.BLUE);
    }
    private  void setColorRed()
    {


        editName.setTextColor(Color.RED);
        editTid.setTextColor(Color.RED);

        editDateTime.setTextColor(Color.RED);
        editPhoto.setTextColor(Color.RED);
    }
    /*private  void startDownload()
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
    public  void  startDataDownload( Class<? extends SaveDataDownload>  saveDataDownload){
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
        textViewDownloadinfo.setText("正在下载"+downloadDatatype+",当前进度:"+String.format("%.2f",progress)+"%");

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

    }*/
}