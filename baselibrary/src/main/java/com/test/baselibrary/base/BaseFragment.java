package com.test.baselibrary.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by lady_zhou on 2018/1/9.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() > 0) {
            //避免没有设置id的异常
            try {
                mRootView = inflater.inflate(getLayoutId(), container, false);

            } catch (Exception e) {
                e.getStackTrace();
            }

        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView(savedInstanceState);
        initData(getArguments(), savedInstanceState);
        initListener();
    }

    /**
     * 初始化数据
     *
     * @param arguments
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle arguments, Bundle savedInstanceState);

    /**
     * 获得相应的layoutId
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);


    /**
     * 初始化点击事件
     */
    protected abstract void initListener();

    /**
     * 当View被点击时调用
     *
     * @param v
     */
    protected abstract void onViewClick(View v);

    @Override
    public void onClick(View v) {
        onViewClick(v);
    }

    /**
     * 显示短Toast
     *
     * @param message
     */
    protected void show(String message) {
        show(message, false);
    }

    /**
     * 显示传入ResId 的 Toast
     *
     * @param resId
     */
    protected void show(int resId) {
        show(getString(resId), false);
    }

    /**
     * 显示toast
     *
     * @param message 显示的内容
     * @param isLong  是否是长Toast
     */
    protected void show(String message, boolean isLong) {
        if (isLong) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
