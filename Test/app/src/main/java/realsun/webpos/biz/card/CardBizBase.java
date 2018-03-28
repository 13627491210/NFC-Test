package realsun.webpos.biz.card;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;


import com.example.administrator.test.card.ActiveSector;
import com.example.administrator.test.card.AppSector;
import com.example.administrator.test.card.CardBase;
import com.example.administrator.test.card.CardSectorBase;
import com.example.administrator.test.card.CommonSector;
import com.example.administrator.test.card.DirectorySector;
import com.example.administrator.test.card.SpecialSector;
import com.example.administrator.test.card.WalletSector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hantao on 2017/11/9.
 */

public class CardBizBase extends CardBase {

    private DirectorySector directorySector;

    private  int dirSectorNO=0;

    private CommonSector commonSector;
    private AppSector appSector;
    private List<WalletSector> wallets=new ArrayList<WalletSector>();
    private List<AppSector> apps=new ArrayList<AppSector>();
    private SpecialSector specialSector;
    private ActiveSector activeSector;

    public void setCommonSector(CommonSector commonSector) {
        this.commonSector = commonSector;
    }

    public AppSector getAppSector() {
        return appSector;
    }

    public void setAppSector(AppSector appSector) {
        this.appSector = appSector;
    }

    public List<WalletSector> getWalletSectors() {
        return wallets;
    }

    public DirectorySector getDirectorySector() {
        return directorySector;
    }

    public void setDirectorySector(DirectorySector directorySector) {
        this.directorySector = directorySector;
    }

    public CardBizBase(int dirSectorNO, int intSectors, Tag tag, Intent intent, MifareClassic mfc, long cardguid) {
        super(  intSectors, tag, intent, mfc, cardguid);
        directorySector=new DirectorySector(this.getCardSectorList().get(dirSectorNO));
    }



    private boolean  buildCardByDirectory()
    {

            //List<CardSectorBizTypeBase> cardSectorBizBaseList= this.directorySector.getCardSectorBizBaseList();
        List<CardSectorBizTypeBase> cardSectorBizBaseList = this.directorySector.getCardSectorBizBaseList();
            for (CardSectorBizTypeBase cardSectorBizBase:cardSectorBizBaseList)
                   {
                       byte sector=cardSectorBizBase.getSector();
                       String biztype= cardSectorBizBase.getBiztype();
                       if (sector>0)
                       {

                           this.getCardSectorList().get(sector).setUsed(true);
                           if (biztype== CardSectorBase.CommonSectorflag)
                           {
                               commonSector=new CommonSector(this.getCardSectorList().get(sector));
                           }
                           if (biztype== CardSectorBase.WalletSectorflag)
                           {

                               WalletSector walletSector;
                               walletSector=new WalletSector(this.getCardSectorList().get(sector));
                               wallets.add(walletSector);
                           }
                           if (biztype==CardSectorBase.AppSectorflag)
                           {
                              // AppSector appSector;
                               appSector=new AppSector(this.getCardSectorList().get(sector));
                               apps.add(appSector);


                           }
                           if (biztype==CardSectorBase.ActiveSectorflag)
                           {
                               activeSector=new ActiveSector(this.getCardSectorList().get(sector));


                           }
                           if (biztype==CardSectorBase.SpecialSectorflag)
                           {
                               specialSector=new SpecialSector(this.getCardSectorList().get(sector));

                           }
                       }

            }
            return true;
        }



    public CommonSector getCommonSector() {
        return commonSector;
    }
    private   boolean writeCardDirectorySector(DirectorySector directorySector){

        if (directorySector.WriteBizDataToSector()){
            if (directorySector.ReadBizDataFromSector()){
                return true;
            }
        }
        return false;
    }
    public boolean writeCardCommonSector(CommonSector commonSector){

       if (commonSector.WriteBizDataToSector()){
           if (commonSector.ReadBizDataFromSector()){
               return true;
           }
       }
       return false;

   }
   private int isCardRelased(){

       if (this.isCardInitialized())
       {
           if (!buildCardByDirectory())
           {
               this.setError("卡片已经初始化，构建卡片数据失败");
               return -5;
           }
            if (this.commonSector.ReadBizDataFromSector())
            {

                return 0;
            }
            else
            {
                this.setError("卡片已经初始化，但读取公共扇区失败.");
                return -1;
            }

       }
       else
       {
           this.setError("卡片未初始化");
           return -2;
       }

   }
   private boolean isCardInitialized(){
        return  this.directorySector.ReadBizDataFromSector();
   }

    private boolean  releaseWallets()
    {
        for (WalletSector wallet:this.wallets
                ) {
            if (wallet.WriteBizDataToSector()==false){return false;};

        }
        return true;
    }
    private boolean  releaseApps()
    {
        for (AppSector app:this.apps
                ) {
            if (app.WriteBizDataToSector()==false){return false;};

        }
        return true;
    }
    public boolean RealseCard(DirectorySector directorySector,CommonSector commonSector){
        if (!(writeCardDirectorySector(directorySector))){

            this.setError("写目录扇区失败");
            return false;
        }
        return RealseCard(commonSector);

    }
    public boolean RealseCardByApp(AppSector appSector){

        if (!(this.isCardInitialized()))
        {
            this.setError( "卡未初始化");
        }
        if (!(this.buildCardByDirectory())){

            this.setError( "构建卡片数据失败");
            return false;
        }

        if (!(this.specialSector.WriteBizDataToSector()))
        {
            this.setError( "写specialSector失败");

            return false;
        }
        if (!(this.activeSector.WriteBizDataToSector()))
        {
            this.setError( "写activeSector失败");

            return false;
        }
        this.appSector.setM_datetime(appSector.getM_datetime());

        if (!(this.appSector.WriteBizDataToSector()))
        {
            this.setError( "写应用扇区失败");

            return false;
        }
        if (!(this.releaseWallets()))
        {
            this.setError( "写钱包失败");

            return false;
        }
        if (!(this.commonSector.WriteBizDataToSector()))
        {
            this.setError( "写公共扇区失败");

            return false;
        }
        return true;
    }
    public boolean RealseCard(CommonSector commonSector){

                if (!(this.isCardInitialized()))
                {
                    this.setError( "卡未初始化");
                }
                if (!(this.buildCardByDirectory())){

                    this.setError( "构建卡片数据失败");
                    return false;
                }



                if (!(this.specialSector.WriteBizDataToSector()))
                {
                    this.setError( "写specialSector失败");

                    return false;
                }
                if (!(this.activeSector.WriteBizDataToSector()))
                {
                    this.setError( "写activeSector失败");

                    return false;
                }
        this.commonSector.setM_cardno(commonSector.getM_cardno());
        this.commonSector.setM_cardholderid(commonSector.getM_cardholderid());
        this.commonSector.setM_cardholdername(commonSector.getM_cardholdername());
        this.commonSector.setM_cardtype(commonSector.getM_cardtype());
        this. commonSector.setM_daylimit(commonSector.getM_daylimit());
        this.commonSector.setM_timelimit(commonSector.getM_timelimit());
        this.commonSector.setM_deptcode(commonSector.getM_deptcode());
        this. commonSector.setM_startdate(commonSector.getM_startdate());
        this. commonSector.setM_enddate(commonSector.getM_enddate());
        this. commonSector.setM_carddeposit(58);

                if (!(this.commonSector.WriteBizDataToSector()))
                {
                    this.setError( "写公共扇区失败");

                    return false;
                }
                if (!(this.releaseWallets()))
                {
                    this.setError( "写钱包失败");

                    return false;
                }
                if (!(this.releaseApps()))
                {
                    this.setError( "写Apps失败");

                    return false;
                }
        return true;
    }




    public boolean LostCard(){
        this.getCommonSector().setM_carddeposit(CardBase.CardStatusOfLost);
        return this.getCommonSector().WriteBizDataToSector();

    }

    /// int=-2 卡未初始化 -1卡未发型 -3 钱包扇区错误 0:读卡成功
    public int GetCard(){

        int result=this.isCardRelased();
        if (result==0)
        {


            if (!(this.specialSector.ReadBizDataFromSector()))
            {
                this.setError( "卡已经发行，但读specialSector失败");

                return -6;
            }
            if (!(this.activeSector.ReadBizDataFromSector()))
            {
                this.setError( "卡已经发行，但读activeSector失败");

                return -7;
            }
            if (!(this.commonSector.ReadBizDataFromSector()))
            {
                this.setError( "卡已经发行，但读公共扇区失败");

                return -8;
            }

            if (this.commonSector.getM_carddeposit()==CardBase.CardStatusOfLost)
            {
                this.setError( "卡已经发行，但已经被挂失");

                return -10;
            }
            for (WalletSector wallet:wallets
                    ) {
                if (wallet.ReadBizDataFromSector()==false){
                    this.setError( "卡已经发行，但读钱包错误");
                    return -3;
                   };

            }
            for (AppSector app :apps)
            {
                if (app.ReadBizDataFromSector()==false)
                {
                    this.setError( "卡已经发行，但读app错误");
                    return -4;
                }
            }
        }
        return result;
    }

    public  boolean WriteWallet(int walletIndex){
        return true;
    }
    public  boolean WriteApp(int appIndex){
        return true;
    }
    public  boolean WriteActive(){
        return true;
    }


}
