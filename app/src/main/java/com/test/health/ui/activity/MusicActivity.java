package com.test.health.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.baselibrary.CustomView.LricView;
import com.test.baselibrary.base.BaseActivity;
import com.test.health.R;
import com.test.health.bean.DummyContent;
import com.test.health.ui.fragment.TopGradualFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicActivity extends BaseActivity implements TopGradualFragment.OnListFragmentInteractionListener {
    private MediaPlayer mp;
    private Fragment mFragment;

    @BindView(R.id.framelayout)
    FrameLayout mFrameLayout;

    @BindView(R.id.tv_name)
    TextView mTvName;

    @BindView(R.id.lricView)
    LricView mLricView;

    private boolean isShowWord = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_music;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mp = MediaPlayer.create(this, R.raw.test);
        mp.start();

        mFragment = TopGradualFragment.newInstance(1);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rl_layout, mFragment)
                .commit();
        ButterKnife.bind(this);

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

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mFragment != null) {
            mFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick({R.id.framelayout, R.id.tv_name, R.id.lricView})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.framelayout:
            case R.id.tv_name:
            case R.id.lricView:
                if (isShowWord) {
                    isShowWord = false;
                    mTvName.setVisibility(View.VISIBLE);
                    mLricView.setVisibility(View.GONE);
                } else {
                    isShowWord = true;
                    mTvName.setVisibility(View.GONE);
                    mLricView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
