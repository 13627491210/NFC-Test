package com.example.administrator.test.core;

/**
 * Created by hantao on 2017/9/12.
 */

public interface DbDownloadListener {
    void onCheckerDownloading(double progress);
    void onCheckerDownloadSuccess();
    void onCheckerDownloadFail();
}
