/*
package com.example.administrator.test.service;

import android.content.Context;

import com.allenliu.versionchecklib.core.AVersionService;
import com.realsun.utils.AppClass;
import com.smartdevice.utils.StringUtility;

public class UpdateService extends AVersionService {
    public UpdateService() {
    }



    @Override
    public void onResponses(AVersionService aVersionService, String s) {
        try {
            String[] sv = StringUtility.spiltStrings(s, ",");
            String strVer = sv[1];
            String strUrl = sv[2];
            String strForce = sv[3];
            String strTitle = sv[4];
            String strMessage = sv[5];
            Context context = getApplicationContext();
            int verCode = AppClass.getVersionCode(context);
            int serverVercode = Integer.valueOf(strVer);
            if (serverVercode > verCode) {
                this.showVersionDialog(strUrl, strTitle, strMessage);

            }
        }
        catch (Exception e)
        {

        }

    }
}
*/
