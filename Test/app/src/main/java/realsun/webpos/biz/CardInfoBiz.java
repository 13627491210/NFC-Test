package realsun.webpos.biz;


import com.example.administrator.test.Model.Teacher;

import org.litepal.crud.DataSupport;

import java.util.List;

import realsun.webpos.model.Cardinfo;

/**
 * Created by hantao on 2017/11/13.
 */

public class CardInfoBiz {
    public static Cardinfo fetchCardinfo(String strCardguid)
    {
        List<Teacher> teachers = DataSupport.where("cardno=?",strCardguid).find(Teacher.class);
        List<Cardinfo> cards = DataSupport.where("cardNo = ?", strCardguid).find(Cardinfo.class);
        if (cards.size()>0)
        {
            return cards.get(0);
        }
        else
        {
            return new Cardinfo();
        }

    }
    public static Teacher fetchTeacherinfo(String strCardguid)
    {
        List<Teacher> teachers = DataSupport.where("cardno=?",strCardguid).find(Teacher.class);
        if (teachers.size()>0)
        {
            return teachers.get(0);
        }
        else
        {
            return new Teacher();
        }

    }
}
