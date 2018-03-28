
package realsun.webpos.Data;




import java.util.List;

import realsun.webpos.webclient.DataResult;



import java.util.List;

import realsun.webpos.webclient.DataResult;

/**
 * Created by hantao on 2017/9/7.
 */


public interface DataDownLoadNotify {

       String Result(List data, DataResult result);
       void   SaveResult(String isfinish, String message, int a, int b);
      // void   SetDbDownloadParm(DbDownloadParm dDbDownloadParm);

}

