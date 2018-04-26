package com.test.health.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.test.baselibrary.base.TitleActivity;
import com.test.health.R;

public class AddressActivity extends TitleActivity {

    @Override
    protected int getContentResId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
//        this.overridePendingTransition(R.animator.anim_bottom_in,0);

    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {
        setLeftIcon(R.mipmap.back);
        setTitleText("选择城市");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onViewClick(View v) {

    }

    @Override
    protected void onLeftClick(View leftTv) {
        super.onLeftClick(leftTv);
        this.overridePendingTransition(0, R.animator.anim_bottom_out);
        finish();
    }
}
