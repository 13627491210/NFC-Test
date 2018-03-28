package realsun.webpos.biz;


import com.example.administrator.test.util.WebClientConnection;

import java.util.ArrayList;

import realsun.webpos.webclient.DataResult;
import realsun.webpos.webclient.GetDatatableParm;
import realsun.webpos.webclient.WebDataNotifier;
import realsun.webpos.webclient.WebDataService;

/**
 * Created by hantao on 2017/8/27.
 */

public class WebBaseBiz {
    public String resid="";
    public String cmswhere="";
    public String sub_resid="";
    public WebBaseBiz(String resid) {
        this.resid=resid;
    }

    public WebBaseBiz(String resid, String cmswhere) {
        this.resid=resid;
        this.cmswhere=cmswhere;
    }
    public WebBaseBiz(String resid, String sub_resid, String cmswhere) {
        this.resid=resid;
        this.cmswhere=cmswhere;
        this.sub_resid=sub_resid;
    }
    public void getWebDataByWhere(WebClientConnection wcc, WebDataNotifier wdn )
    {
        GetDatatableParm getDataParm=new GetDatatableParm();
        getDataParm.resid=this.resid;
        getDataParm.subresid=this.sub_resid;
        final WebDataService wds=new WebDataService();
        getDataParm.cmswhere=this.cmswhere;
        wds.retrieve(getDataParm, wcc, wdn);
    }
    public void getObjectByWhere(WebClientConnection   wcc, final Class clazz, final BaseObjectNotify bon )
    {
        GetDatatableParm getDataParm=new GetDatatableParm();
        getDataParm.resid=this.resid;
        getDataParm.subresid=this.sub_resid;
        final WebDataService wds=new WebDataService();
        getDataParm.cmswhere=this.cmswhere;

        wds.retrieve(getDataParm, wcc, new WebDataNotifier(){
            @Override
            public void result(DataResult result) {
                if (result.error==0)
                {
                    String str=result.data.get(0).toString();

                    bon.result(str,result);

                }
                else
                {
                    bon.result("{}",result);
                }
            }
        });
    }
    public void getObjectsByWhere(WebClientConnection   wcc, final Class clazz, final BaseObjectsNotify bons )
    {
        GetDatatableParm getDataParm=new GetDatatableParm();
        getDataParm.resid=this.resid;
        getDataParm.subresid=this.sub_resid;
        final WebDataService wds=new WebDataService();
        getDataParm.cmswhere=this.cmswhere;

        wds.retrieve2(getDataParm, wcc, new WebDataNotifier(){
            @Override
            public void result(DataResult result) {
                if (result.error==0)
                {


                    bons.result(result.data,result);

                }
                else
                {
                    bons.result(new ArrayList(),result);
                }
            }
        });
    }
}
