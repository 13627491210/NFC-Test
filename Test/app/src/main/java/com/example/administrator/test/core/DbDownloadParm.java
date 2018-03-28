/*

package com.example.administrator.test.core;

import android.os.Parcel;
import android.os.Parcelable;

import com.allenliu.versionchecklib.core.http.HttpHeaders;
import com.example.administrator.test.service.HttpParams;
import com.example.administrator.test.service.HttpRequestMethod;
import com.example.administrator.test.util.WebClientConnection;

import realsun.webpos.Data.SaveDataDownload;
import realsun.webpos.model.BaseRecord;
import realsun.webpos.webclient.GetDatatableParm;



*/
/**
 * Created by hantao on 2017/9/12.
 *//*



public class DbDownloadParm  implements Parcelable {
    private String requestUrl;
    private String downloadAPKPath;
    private WebClientConnection wcc;
    private String databaseurl;

    public String getDatabaseurl() {
        return databaseurl;
    }

    public void setDatabaseurl(String databaseurl) {
        this.databaseurl = databaseurl;
    }

    public String getUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod(String updateMethod) {
        this.updateMethod = updateMethod;
    }

    public String getIsRun() {
        return isRun;
    }

    public void setIsRun(String isRun) {
        this.isRun = isRun;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    private String updateMethod;
    private String isRun;
    private String taskid;


    public GetDatatableParm getGetDatatableParm() {
        return getDatatableParm;
    }

    public void setGetDatatableParm(GetDatatableParm getDatatableParm) {
        this.getDatatableParm = getDatatableParm;
    }

    private GetDatatableParm getDatatableParm;
    public Class<? extends BaseRecord> getDownloadClazz() {
        return downloadClazz;
    }

    public void setDownloadClazz(Class<? extends BaseRecord> downloadClazz) {
        this.downloadClazz = downloadClazz;
    }

    private Class<? extends BaseRecord> downloadClazz;

    public Class<? extends SaveDataDownload> getSaveDataDownload() {
        return saveDataDownload;
    }

    public void setSaveDataDownload(Class<? extends SaveDataDownload> saveDataDownload) {
        this.saveDataDownload = saveDataDownload;
    }

    private Class<? extends SaveDataDownload> saveDataDownload  ;
    private com.allenliu.versionchecklib.core.http.HttpHeaders httpHeaders;
    private long pauseRequestTime;
    private HttpRequestMethod requestMethod;
    private HttpParams requestParams;
 //   private Class<? extends DbDownloadDialogActivity> customDownloadActivityClass;
    //    public boolean isForceUpdate;
    public boolean isForceRedownload;
    public boolean isSilentDownload;
    //private Class<? extends ADbVersionService> service;

    private DbDownloadParm() {
    }
    private DbDownloadParm(String requestUrl, Class<? extends BaseRecord> downloadClazz, HttpHeaders httpHeaders, long pauseRequestTime, HttpRequestMethod requestMethod, HttpParams requestParams, Class customDownloadActivityClass, boolean isForceRedownload, boolean isSilentDownload, Class<? extends ADbVersionService> service, WebClientConnection wcc, Class<?  extends  SaveDataDownload> saveDataDownload) {
        this.requestUrl = requestUrl;
        this.downloadClazz = downloadClazz;
        this.httpHeaders = httpHeaders;
        this.pauseRequestTime = pauseRequestTime;
        this.requestMethod = requestMethod;
        this.requestParams = requestParams;
       // this.customDownloadActivityClass = customDownloadActivityClass;
        this.wcc=wcc;
        this.isForceRedownload = isForceRedownload;
        this.isSilentDownload = isSilentDownload;
       // this.service = service;
        this.saveDataDownload=saveDataDownload;
        if (this.service == null) {
            throw new RuntimeException("you must define your service which extends AVService.");
        }
        if (requestUrl == null) {
            throw new RuntimeException("requestUrl is needed.");
        }
    }


    public Class<? extends ADbVersionService> getService() {
        return service;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getDownloadAPKPath() {
        return downloadAPKPath;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public long getPauseRequestTime() {
        return pauseRequestTime;
    }

    public HttpRequestMethod getRequestMethod() {
        return requestMethod;
    }

    public HttpParams getRequestParams() {
        return requestParams;
    }

    public Class getCustomDownloadActivityClass() {
   //     return customDownloadActivityClass;
    }
    public WebClientConnection getWcc(){return wcc;}

    public boolean isForceRedownload() {
        return isForceRedownload;
    }

    public boolean isSilentDownload() {
        return isSilentDownload;
    }

    public static class Builder {

        DbDownloadParm params;

        public Builder() {
            params = new DbDownloadParm();
           // params.downloadAPKPath = FileHelper.getDownloadApkCachePath();
            params.pauseRequestTime = 1000 * 30;
            params.requestMethod = HttpRequestMethod.GET;
        //    params.customDownloadActivityClass = DbDownloadDialogActivity.class;
//            this.isForceUpdate = false;
            params.isForceRedownload = false;
            params.isSilentDownload = false;
        }

        public DbDownloadParm.Builder setRequestUrl(String requestUrl) {
            params.requestUrl = requestUrl;
            return this;
        }

        public DbDownloadParm.Builder setDownloadAPKPath(String downloadAPKPath) {
            params.downloadAPKPath = downloadAPKPath;
            return this;
        }

        public DbDownloadParm.Builder setHttpHeaders(HttpHeaders httpHeaders) {
            params.httpHeaders = httpHeaders;
            return this;
        }

        public DbDownloadParm.Builder setPauseRequestTime(long pauseRequestTime) {
            params.pauseRequestTime = pauseRequestTime;
            return this;
        }

        public DbDownloadParm.Builder setRequestMethod(HttpRequestMethod requestMethod) {
            params.requestMethod = requestMethod;
            return this;
        }

        public DbDownloadParm.Builder setRequestParams(HttpParams requestParams) {
            params.requestParams = requestParams;
            return this;
        }

        public DbDownloadParm.Builder setCustomDownloadActivityClass(Class customDownloadActivityClass) {
          //  params.customDownloadActivityClass = customDownloadActivityClass;
            return this;
        }
        public DbDownloadParm.Builder setDbDownloadDbClass(Class<? extends  BaseRecord> dbClass){
            params.downloadClazz=dbClass;
            return this;
        }

        public DbDownloadParm.Builder setForceRedownload(boolean forceRedownload) {
            params.isForceRedownload = forceRedownload;
            return this;
        }

        public DbDownloadParm.Builder setSilentDownload(boolean silentDownload) {
            params.isSilentDownload = silentDownload;
            return this;
        }

      //  public DbDownloadParm.Builder setService(Class<? extends ADbVersionService> service) {
           // params.service = service;
       //     return this;
        //}
        public DbDownloadParm.Builder setWcc(WebClientConnection wcc) {
            params.wcc = wcc;
            return this;
        }
        public DbDownloadParm.Builder setSaveDataDownload(Class<? extends SaveDataDownload> saveDataDownload){
            params.saveDataDownload=saveDataDownload;
            return this;
        }
        public DbDownloadParm.Builder setDataBaseUrl(String url){
            params.setDatabaseurl(url);
            return this;
        }

        public DbDownloadParm build() {
            return params;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.requestUrl);
        dest.writeString(this.downloadAPKPath);
        dest.writeSerializable(this.httpHeaders);
        dest.writeLong(this.pauseRequestTime);
        dest.writeInt(this.requestMethod == null ? -1 : this.requestMethod.ordinal());
        dest.writeSerializable(this.requestParams);
      //  dest.writeSerializable(this.customDownloadActivityClass);
        dest.writeSerializable(this.downloadClazz);
        dest.writeByte(this.isForceRedownload ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSilentDownload ? (byte) 1 : (byte) 0);
       // dest.writeSerializable(this.service);
        dest.writeSerializable(this.saveDataDownload);


    }

    protected DbDownloadParm(Parcel in) {
        this.requestUrl = in.readString();
        this.downloadAPKPath = in.readString();
        this.httpHeaders = (HttpHeaders) in.readSerializable();
        this.pauseRequestTime = in.readLong();
        int tmpRequestMethod = in.readInt();
        this.requestMethod = tmpRequestMethod == -1 ? null : HttpRequestMethod.values()[tmpRequestMethod];
        this.requestParams = (HttpParams) in.readSerializable();
      //  this.customDownloadActivityClass = (Class<? extends DbDownloadDialogActivity>) in.readSerializable();
        this.downloadClazz=(Class<? extends BaseRecord>) in.readSerializable() ;
        this.isForceRedownload = in.readByte() != 0;
        this.isSilentDownload = in.readByte() != 0;
       // this.service = (Class<? extends ADbVersionService>) in.readSerializable();
       // this.saveDataDownload=(Class<? extends SaveDataDownload>) in.readSerializable();
    }

    public static final Parcelable.Creator<DbDownloadParm> CREATOR = new Parcelable.Creator<DbDownloadParm>() {
        @Override
        public DbDownloadParm createFromParcel(Parcel source) {
            return new DbDownloadParm(source);
        }

        @Override
        public DbDownloadParm[] newArray(int size) {
            return new DbDownloadParm[size];
        }
    };
}

*/
