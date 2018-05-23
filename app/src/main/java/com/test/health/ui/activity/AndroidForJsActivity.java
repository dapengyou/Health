package com.test.health.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.test.baselibrary.base.BaseActivity;
import com.test.health.R;
import com.test.health.inter.JsBrige;
import com.test.health.inter.JsInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndroidForJsActivity extends BaseActivity implements JsBrige {
    //    @BindView(R.id.tv_text)
    private TextView mTvText;

    //    @BindView(R.id.webview)
    private WebView mWebView;
    private EditText mEditText;
    private Button mBtClick;

    private Handler mHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_android_for_js;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        ButterKnife.bind(this);
        mWebView = findViewById(R.id.webview);
        mTvText = findViewById(R.id.tv_text);
        mEditText = findViewById(R.id.edittext);
        mBtClick = findViewById(R.id.bt_click);
        mHandler = new Handler();
    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {
        //允许webView加载js代码
        mWebView.getSettings().setJavaScriptEnabled(true);
        //给webView添加js接口,这里的name是在js中用到的
        mWebView.addJavascriptInterface(new JsInterface(this), "androidforjs");
        //加载本的html
        mWebView.loadUrl("file:///android_asset/jsForAndroid.html");

        //打开允许调试的开关
        mWebView.setWebContentsDebuggingEnabled(true);

    }

    @Override
    protected void initListener() {
        mBtClick.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.bt_click:
                String str = mEditText.getText().toString();
                //android  调用js 方法
                mWebView.loadUrl("javascript:if(window.remote){window.remote('" + str + "')}");
                break;
        }
    }

    @Override
    public void setTextValue(final String value) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTvText.setText(value);
            }
        });
    }
}
