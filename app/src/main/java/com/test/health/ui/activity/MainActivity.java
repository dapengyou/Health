package com.test.health.ui.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.test.baselibrary.base.BaseActivity;
import com.test.health.R;
import com.test.health.fragmentNavigator.FragmentNavigator;
import com.test.health.fragmentNavigator.FragmentNavigatorAdapter;
import com.test.health.ui.adapter.MainFragmentAdapter;
import com.test.health.wight.BottomNavigatorView;

public class MainActivity extends BaseActivity implements BottomNavigatorView.OnBottomNavigatorViewItemClickListener {
    private BottomNavigatorView mBottomNavigatorView;
    private FragmentNavigator mFragmentNavigator;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //有可能是在无可用的Activity或screen情况下，获取当前的窗口 并将窗口设置为透明色
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//解决地图黑屏
        mFragmentNavigator = new FragmentNavigator(getSupportFragmentManager(), new MainFragmentAdapter(), R.id.main_container);
        mFragmentNavigator.setDefaultPosition(0);
        mFragmentNavigator.onCreate(savedInstanceState);

        mBottomNavigatorView = findViewById(R.id.main_bottom_navigator);
        if (mBottomNavigatorView != null) {
            mBottomNavigatorView.setOnBottomNavigatorViewItemClickListener(this);
        }

        setCurrentTab(mFragmentNavigator.getCurrentPosition());
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
    public void OnBottomNavigatorViewItemClickListener(int position, View view) {
        //需要传入点击的位置
        setCurrentTab(position);
    }

    private void setCurrentTab(int position) {
        //底部位置选择
        mBottomNavigatorView.select(position);
        //Fragment 的选择
        mFragmentNavigator.showFragment(position);
    }
}
