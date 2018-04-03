package com.test.health.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.test.baselibrary.base.BaseFragment;
import com.test.health.App;
import com.test.health.R;
import com.test.health.glid.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lady_zhou on 2018/3/28.
 */

public class FirstFragment extends BaseFragment {
    public static final String TAG = FirstFragment.class.getSimpleName();//得到类名

    private Banner banner;

    /**
     * 使用单例
     *
     * @return
     */
    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    protected void initData(Bundle arguments, Bundle savedInstanceState) {
        banner.setImages(App.images).setImageLoader(new GlideImageLoader()).start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        banner = findViewById(R.id.banner);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
