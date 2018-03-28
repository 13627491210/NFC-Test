/*
package realsun.webpos.Data;

import com.alibaba.fastjson.JSON;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import realsun.webpos.model.CardBlackList;
import realsun.webpos.webclient.DataResult;

*/
/**
 * Created by hantao on 2017/10/30.
 *//*


public class SaveCardblacklistDataDownload extends SaveDataDownload implements DataDownLoadNotify {
        private List<CardBlackList> cardBlackLists;
    private List<CardBlackList> newcardBlackLists=new ArrayList<CardBlackList>();
    public SaveCardblacklistDataDownload() {
//        cardBlackLists = DataSupport.findAll(CardBlackList.class);
//        DataSupport.deleteAll(CardBlackList.class);
    }
    @Override
    public String Result(List data, DataResult result) {
        try {
            List<CardBlackList> cardBlackListList = JSON.parseArray(data.toString(), CardBlackList.class);
            newcardBlackLists.addAll(cardBlackListList);

            for (CardBlackList cardBlack:cardBlackListList) {
                List<CardBlackList> cardsBlack = DataSupport.where("cardNo = ?", cardBlack.getCardNo()).find(CardBlackList.class);
                if (cardsBlack.size()==0)
                {
                    CardBlackList oneCardBlack=new CardBlackList();
                    oneCardBlack.copyNewCardBlack(cardBlack);
                    oneCardBlack.save();
                }
                else
                {
                    CardBlackList oneCardBlack= (cardsBlack.get(0));
                    oneCardBlack.updateCardBlack(cardBlack);

                    oneCardBlack.save();
                }



            }

            if (newcardBlackLists.size()==result.total)
            {
//                for (int i=0;i<newcardBlackLists.size();i++)
//                {
//                    CardBlackList cardBlackList=new CardBlackList();
//                    cardBlackList=newcardBlackLists.get(i);
//                    cardBlackList.save();
//
//                }

                String messages="newcardBlackLists="+ String.valueOf(newcardBlackLists.size());
                this.SaveResult("Y",messages,newcardBlackLists.size(),0);
            }


        }
        catch (Exception ex)
        {
          //  DataSupport.deleteAll(CardBlackList.class);

          //  DataSupport.saveAll(cardBlackLists);

            this.SaveResult("N",ex.getMessage(),0,0);
            return "N";
        }
        return "Y";
    }
}
*/
