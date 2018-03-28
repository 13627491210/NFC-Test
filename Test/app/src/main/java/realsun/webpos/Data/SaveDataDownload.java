/*

package realsun.webpos.Data;


import com.example.administrator.test.Model.TaskResultModel;
import com.example.administrator.test.core.DbDownloadParm;
import com.example.administrator.test.util.ConnectionChangeReceiver;

import java.util.List;

import realsun.webpos.webclient.DataResult;
import realsun.webpos.webclient.SaveDataTableParm;
import realsun.webpos.webclient.WebDataNotifier;
import realsun.webpos.webclient.WebDataService;


*/
/**
 * Created by hantao on 2017/9/21.
 *//*



public class SaveDataDownload  implements DataDownLoadNotify {
    private DbDownloadParm dbDownloadParm;
    public SaveDataDownload() {
    }
    public SaveDataDownload(DbDownloadParm dbDownloadParm) {
        this.dbDownloadParm=dbDownloadParm;
    }
    @Override
    public String Result(List data, DataResult result) {
        return "Y";
    }

    @Override
    public void SaveResult(String isfinish, String message, int mainTotal, int subTotal) {
        TaskResultModel taskResultModel=new TaskResultModel();
        taskResultModel.setTaskid(this.dbDownloadParm.getTaskid());
        taskResultModel.setIsFinish(isfinish);

        taskResultModel.setMessage(message);
        taskResultModel.setMaintotal(mainTotal);
        taskResultModel.setSubtotal(subTotal);
        taskResultModel.REC_RESID="558730700012";
        final WebDataService wds=new WebDataService();

        taskResultModel._id=1;
        taskResultModel._state="added";

        SaveDataTableParm parm=new SaveDataTableParm();
        parm.resid=  taskResultModel.REC_RESID;
        parm.dataoflist.add(taskResultModel);

        wds.save(parm,  ConnectionChangeReceiver.getInstence().systemwcc, new WebDataNotifier(){

            @Override
            public void result(DataResult result) {
                // TODO Auto-generated method stub
                try
                {
                    if (result.error==0)
                    {

                    }

                }
                catch (Exception e){




                }

            }});

    }

    @Override
    public void SetDbDownloadParm(DbDownloadParm adDbDownloadParm) {
        this.dbDownloadParm=adDbDownloadParm;
    }

}
*/
