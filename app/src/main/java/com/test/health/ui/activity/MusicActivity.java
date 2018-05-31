package com.test.health.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.test.baselibrary.base.BaseActivity;
import com.test.health.R;

public class MusicActivity extends BaseActivity {
    private MediaPlayer mp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_music;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mp = MediaPlayer.create(this, R.raw.test);
        mp.start();
    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onViewClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
