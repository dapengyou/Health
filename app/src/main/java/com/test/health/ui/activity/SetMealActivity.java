package com.test.health.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.test.baselibrary.base.BaseActivity;
import com.test.baselibrary.base.TitleActivity;
import com.test.health.R;

/**
 * 套餐页面
 */
public class SetMealActivity extends TitleActivity {
    private TextView mTvPric;//新价格
    private TextView mTvOldPrice;//老价格

    @Override
    protected int getContentResId() {
        return R.layout.activity_set_meal;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTvOldPrice = findViewById(R.id.tv_price_old);
        mTvPric = findViewById(R.id.tv_price);

    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {
        setTitleText("套餐详情");
        setLeftIcon(R.mipmap.back);

        String text = "￥1234.00";

        int start = text.indexOf(".");
        int end = text.length();

        Spannable textSpan = new SpannableStringBuilder(text);
        textSpan.setSpan(new AbsoluteSizeSpan(20), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(46), 1, start, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(20), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        //不知道为什么使用 mTvPric.setText("￥"+textSpan); 显示不出效果
        mTvPric.setText(textSpan);
        mTvOldPrice.setText(text);
        mTvOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
