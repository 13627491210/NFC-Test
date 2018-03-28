package realsun.webpos.model;

import java.util.Hashtable;

/**
 * Created by hantao on 2017/9/16.
 */

public class Custormerfunctiondata {
    private String methodname;
    private String requestid = "0";
    private String rtcols = "";
    private String debug = "0";
    private Hashtable hs = new Hashtable();
    private String returnparm="";

    public String getReturnparm() {
        return returnparm;
    }

    public void setReturnparm(String returnparm) {
        this.returnparm = returnparm;
    }

    public String getMethodname() {

        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getRtcols() {
        return rtcols;
    }

    public void setRtcols(String rtcols) {
        this.rtcols = rtcols;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public Hashtable getHs() {
        return hs;
    }

    public void setHs(Hashtable hs) {
        this.hs = hs;
    }
}
