package realsun.webpos.biz;


import realsun.webpos.model.Teacher;
import realsun.webpos.model.WebPosDefine;
import realsun.webpos.webclient.DataResult;

/**
 * Created by hantao on 2017/9/3.
 */

public interface WebTeacherNotify {
    public  void result(Teacher teacher, DataResult innerResult) ;
}
