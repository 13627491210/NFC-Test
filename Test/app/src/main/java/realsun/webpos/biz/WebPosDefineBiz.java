package realsun.webpos.biz;

import android.database.sqlite.SQLiteDatabase;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.util.AppClass;
import com.example.administrator.test.util.WebClientConnection;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import realsun.webpos.model.PosOrder;
import realsun.webpos.model.Teacher;
import realsun.webpos.model.WebPosDefine;
import realsun.webpos.webclient.DataResult;
import realsun.webpos.webclient.GetDatatableParm;
import realsun.webpos.webclient.SaveDataTableParm;
import realsun.webpos.webclient.WebDataNotifier;
import realsun.webpos.webclient.WebDataService;

/**
 * Created by hantao on 2017/9/3.
 */

public class WebPosDefineBiz {
   //574185879466
    //530122554910
    final static String resid="574185879466";
    public static boolean CheckLocalWebPosDefine(){
        WebPosDefine webPosDefine= GetLocalPosDefine();
        if (webPosDefine.getWinno()<-100)
        {
            return false;
        }
        if (webPosDefine.getClassname()=="unkown")
        {
            return false;
        }
        if (webPosDefine.getPostype()=="unkown")
        {
            return false;
        }
        if (webPosDefine.getClassname()==null)
        {
            return false;
        }
        try{
            return AppClass.isClass(webPosDefine.getClassname());

        }
        catch (Exception ex)
        {
            return false;
        }




    }
    public static boolean CheckLocalWebPosDefine(WebPosDefine webPosDefine){

        if (webPosDefine.getWinno()<-100)
        {
            return false;
        }
        if (webPosDefine.getClassname()=="unkown")
        {
            return false;
        }
        if (webPosDefine.getPostype()=="unkown")
        {
            return false;
        }
        return AppClass.isClass(webPosDefine.getClassname());



    }
    public static WebPosDefine GetLocalPosDefine(){
     //   testsave();

        List<WebPosDefine> webPosDefineList  = DataSupport.findAll(WebPosDefine.class);
        try
        {
            if (webPosDefineList.size()>0)
            {
                return webPosDefineList.get(0);

            }

        }
        catch (Exception e)
        {
            WebPosDefine posDefine=new WebPosDefine();
            posDefine._dberror=e.getMessage();
            return posDefine;

        }
          return new WebPosDefine();

    }
    public  static  void testsave(){
        SQLiteDatabase db = LitePal.getDatabase();

        WebPosDefine posDefine=new WebPosDefine();
        posDefine.setWinno(1);

        posDefine.REC_ID="1211";
        posDefine.REC_RESID="122";
        PosOrder posorder=new PosOrder();
        posorder.setBadgeno("0123456");
        posorder.setCardno("16740988324234");
        posorder.setOrderno("2017089820031");
        boolean test=posorder.save();
        DataSupport.deleteAll(WebPosDefine.class,"winno = ?" , String.valueOf(posDefine.getWinno()));
        test=posDefine.save();



    }
    public static void UpdateWebPosDefine(WebPosDefine webPosDefine, WebClientConnection wcc, final WebPosDefineNotify webPosDefineNotify)
    {
        final WebDataService wds=new WebDataService();

        webPosDefine._id=1;
        webPosDefine._state="modified";

        SaveDataTableParm parm=new SaveDataTableParm();
        parm.resid=resid;
        parm.dataoflist.add(webPosDefine);
        wds.save(parm, wcc, new WebDataNotifier(){
            WebPosDefine posDefine=new WebPosDefine();
            @Override
            public void result(DataResult result) {
                // TODO Auto-generated method stub
                try
                {
                    if (result.error==0)
                    {
                        if (result.data.size()>0)
                        {
                            String str=result.data.get(0).toString();
                            posDefine= JSON.parseObject(str, WebPosDefine.class);
                        }
                    }

                }
                catch (Exception e){


                    posDefine._error=e.getMessage();

                }
                webPosDefineNotify.result(posDefine,result);
            }});
    }
    public    static   void GetWebPosDefine(int winno, WebClientConnection wcc,final WebPosDefineNotify webPosDefineNotify){
        GetDatatableParm getDataParm=new GetDatatableParm();
        final WebDataService wds=new WebDataService();
        getDataParm.resid=resid;
        getDataParm.cmswhere="";
        wds.retrieve(getDataParm,  wcc, new WebDataNotifier(){

            @Override
            public void result(DataResult result) {
                WebPosDefine posDefine=new WebPosDefine();
                try
                {
                    if (result.error==0)
                    {
                        if (result.data.size()>0)
                        {
                            String str=result.data.get(0).toString();
                            posDefine= JSON.parseObject(str, WebPosDefine.class);
                        }
                    }

                }
                catch (Exception e){


                    posDefine._error=e.getMessage();

                }
                webPosDefineNotify.result(posDefine,result);

            }});

    }

    public static boolean UpdateLocalWebPosDefine(WebPosDefine webPosDefine){


        try
        {
            DataSupport.deleteAll(WebPosDefine.class);

		boolean result=false;
            result= webPosDefine.save();


        return result;



        }
        catch (Exception e)
        {


        }
        return false;

    }
/*    public  static String GetPosCurrentDates(){
        Date date=new Date();
        return StringUtility.dateToString(date,"yyyyMMdd");

    }
    public  static int GetPosCurrentDinnerType(List<DinnerTimesOfCatteen> DinnerTimesOfCatteens){
       int hour= Integer.valueOf(StringUtility.getHour());
        int minute= Integer.valueOf(StringUtility.getMinute());

        return  DinnerTimesOfCatteenBiz.GetDinnerType(hour,minute,DinnerTimesOfCatteens);

    }
    public  static DinnerTimesOfCatteen GetPosCurrentDinnerTimesOfCatteen(List<DinnerTimesOfCatteen> DinnerTimesOfCatteens){
        int hour= Integer.valueOf(StringUtility.getHour());
        int minute= Integer.valueOf(StringUtility.getMinute());

        return  DinnerTimesOfCatteenBiz.GetDinnerTimesOfCatteen(hour,minute,DinnerTimesOfCatteens);

    }*/
public static void UpdateTeacher(final realsun.webpos.model.Teacher teacher, WebClientConnection wcc, final WebTeacherNotify webTeacherNotify)
{
    final WebDataService wds=new WebDataService();

    teacher._id=1;
    teacher._state="modified";
    SaveDataTableParm parm=new SaveDataTableParm();
    parm.resid=resid;
    parm.dataoflist.add(teacher);
    wds.save(parm, wcc, new WebDataNotifier(){
        Teacher teacher2=new Teacher();
        @Override
        public void result(DataResult result) {
            // TODO Auto-generated method stub
            try
            {
                if (result.error==0)
                {
                    if (result.data.size()>0)
                    {
                        String str=result.data.get(0).toString();
                        teacher2= JSON.parseObject(str, Teacher.class);
                    }
                }

            }
            catch (Exception e){


                teacher._error=e.getMessage();

            }
            webTeacherNotify.result(teacher2,result);
        }});
}
}

