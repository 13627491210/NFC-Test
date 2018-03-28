/*
package realsun.webpos.Data;

import com.alibaba.fastjson.JSON;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import realsun.webpos.model.Cardinfo;
import realsun.webpos.webclient.DataResult;

*/
/**
 * Created by hantao on 2017/10/30.
 *//*


public class SaveCardinfoDataDownload extends SaveDataDownload implements  DataDownLoadNotify{
    private List<Cardinfo> cardinfoList;
    private List<Cardinfo> newcardinfoList=new ArrayList<Cardinfo>();
    public SaveCardinfoDataDownload() {
      //  cardinfoList =DataSupport.findAll(Cardinfo.class);
      //  DataSupport.deleteAll(Cardinfo.class);
    }
    @Override
    public String Result(List data, DataResult result) {
        try {
            List<Cardinfo> cardinfos = JSON.parseArray(data.toString(), Cardinfo.class);
            newcardinfoList.addAll(cardinfos);
            for (Cardinfo card:cardinfos) {
                List<Cardinfo> cards = DataSupport.where("cardNo = ?", card.getCardNo()).find(Cardinfo.class);
                if (cards.size()==0)
                {
                    Cardinfo oneCard=new Cardinfo();
                    oneCard.copyNewCard(card);
                    oneCard.save();
                }
                else
                {
                    Cardinfo oneCard= (cards.get(0));
                    oneCard.updateCard(card);

                    oneCard.save();
                }



            }
            if (newcardinfoList.size()==result.total)
            {
//                for (int i=0;i<newcardinfoList.size();i++)
//                {
//                    Cardinfo cardinfo=new Cardinfo();
//                    cardinfo=newcardinfoList.get(i);
//                    cardinfo.save();
//
//                }

                String messages="cardinfo="+ String.valueOf(newcardinfoList.size());
                this.SaveResult("Y",messages,newcardinfoList.size(),0);
            }


        }
        catch (Exception ex)
        {
            //DataSupport.deleteAll(Cardinfo.class);

           // DataSupport.saveAll(cardinfoList);

            this.SaveResult("N",ex.getMessage(),0,0);
            return "N";
        }
        return "Y";
    }
}
*/
