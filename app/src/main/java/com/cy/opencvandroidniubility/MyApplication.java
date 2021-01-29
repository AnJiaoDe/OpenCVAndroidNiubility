package com.cy.opencvandroidniubility;

import android.app.Application;
import android.util.Log;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.osgi.OpenCVNativeLoader;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/12/2 16:18
 * @UpdateUser:
 * @UpdateDate: 2020/12/2 16:18
 * @UpdateRemark:
 * @Version:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OpenCVUtils.init();
    }
}
