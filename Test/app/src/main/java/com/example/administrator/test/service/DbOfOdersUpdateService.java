/*
package com.example.administrator.test.service;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;

import realsun.webpos.webclient.DataResult;

*/
/**
 * Created by hantao on 2017/9/15.
 *//*


public class DbOfOdersUpdateService extends ADbVersionService {
    public DbOfOdersUpdateService() {
    }

    @Override
    public void onResponses(ADbVersionService service, String response) {
        try {
            DataResult dataResult=   JSON.parseObject(response,DataResult.class);
            if (dataResult.error==0)
            {
                paramBundle=new Bundle();

                paramBundle.putString("Datadownloadparm",dataResult.data.toString());
                this.showVersionDialog("下载数据","订单记录",paramBundle);
            }


            return ;
        }
        catch (Exception e)
        {
            e.getMessage();

        }

    }
}
*/
