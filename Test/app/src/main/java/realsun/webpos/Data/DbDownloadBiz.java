/*

package realsun.webpos.Data;

import com.example.administrator.test.Activity.MainActivity;
import com.example.administrator.test.service.HttpParams;
import com.example.administrator.test.smartdevice.utils.StringUtility;
import com.example.administrator.test.util.WebDbConfig;

import java.util.Date;


*/
/**
 * Created by hantao on 2017/11/13.
 *//*



public class DbDownloadBiz {
    public  void  startDataDownload(Class<? extends SaveDataDownload> saveDataDownload, String downloadDatatype, MainActivity mainActivity){
        if (!downloadDatatype.equals("")) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("postype", mainActivity.webPosDefine.getPostype());
            httpParams.put("enterprisecode", mainActivity.webPosDefine.getEnterprisecode());
            httpParams.put("taskresid", "558552009807");
            httpParams.put("winno", mainActivity.webPosDefine.getWinno());
            httpParams.put("dates", StringUtility.dateToString(new Date(), "yyyyMMdd"));
            httpParams.put("datatype", downloadDatatype);
            if (mainActivity.webPosDefine.getOrderupdateurl() != null) {

                DbDownloadParm.Builder builder = new DbDownloadParm.Builder()
                        .setRequestUrl(mainActivity.webPosDefine.getOrderupdateurl())
                       // .setService(DbOfOdersUpdateService.class)
                        .setSaveDataDownload(saveDataDownload)
                        .setRequestParams(httpParams)
                        .setSilentDownload(true)
                        .setDataBaseUrl(WebDbConfig.m_BASE_URL);

              //  RealsunChecker.startVersionCheck(mainActivity, builder.build());
            }
        }


    }
}

*/
