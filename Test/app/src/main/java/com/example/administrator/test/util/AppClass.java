package com.example.administrator.test.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by hantao on 2017/9/8.
 */

public class AppClass {
    public static boolean isClass(String className){
        try {
            Class aClass = Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
}
