package com.test.health;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.test.health.wight.BottomNavigatorView;

public class MainActivity extends AppCompatActivity implements BottomNavigatorView.OnBottomNavigatorViewItemClickListener {
    private BottomNavigatorView mBottomNavigatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigatorView = findViewById(R.id.main_bottom_navigator);
        if (mBottomNavigatorView != null) {
            mBottomNavigatorView.setOnBottomNavigatorViewItemClickListener(this);
        }

        setCurrentTab(0);

    }

    @Override
    public void OnBottomNavigatorViewItemClickListener(int position, View view) {
        Toast.makeText(this, "点击了" + position, Toast.LENGTH_SHORT).show();
        //需要传入点击的位置
        setCurrentTab(position);
    }

    private void setCurrentTab(int position) {
        //底部位置选择
        mBottomNavigatorView.select(position);
        //Fragment 的选择
    }
}
