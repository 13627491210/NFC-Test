package realsun.webpos.biz;


import realsun.webpos.model.Teacher;
import realsun.webpos.model.WebPosDefine;
import realsun.webpos.webclient.DataResult;

/**
 * Created by hantao on 2017/9/3.
 */

public interface WebPosDefineNotify {
    public  void result(WebPosDefine posDefine, DataResult innerResult) ;
    //public  void result2(Teacher teacher, DataResult innerResult) ;
}
