package com.test.health.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.test.baselibrary.base.BaseFragment;
import com.test.health.R;

/**
 * Created by lady_zhou on 2018/3/28.
 */

public class HotFragment extends BaseFragment {
    public static final String TAG = HotFragment.class.getSimpleName();//得到类名

    /**
     * 使用单例
     *
     * @return
     */
    public static HotFragment newInstance() {
        HotFragment fragment = new HotFragment();
        return fragment;
    }

    @Override
    protected void initData(Bundle arguments, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
