package com.test.health.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.test.baselibrary.base.BaseActivity;
import com.test.baselibrary.base.TitleActivity;
import com.test.health.R;

/**
 * 套餐页面
 */
public class SetMealActivity extends TitleActivity {

    @Override
    protected int getContentResId() {
        return R.layout.activity_set_meal;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {
        setTitleText("套餐详情");
        setLeftIcon(R.mipmap.back);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
