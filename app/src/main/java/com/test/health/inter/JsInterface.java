package com.test.health.inter;

import android.util.Log;
import android.webkit.JavascriptInterface;

import java.util.logging.Handler;

/**
 * Created by lady_zhou on 2018/5/18.
 */

public class JsInterface {
    private static final String TAG = "androidforjs";

    private JsBrige mJsBrige;

    /**
     * 添加构造方法使这个类可以使用jsbrige接口
     *
     * @param jsBrige
     */
    public JsInterface(JsBrige jsBrige) {
        this.mJsBrige = jsBrige;
    }

    /**
     * 此方法不在主线程
     *
     * @param value
     */
    @JavascriptInterface
    public void setValue(String value) {
        Log.d(TAG, "value = " + value);
        mJsBrige.setTextValue(value);
    }
}
