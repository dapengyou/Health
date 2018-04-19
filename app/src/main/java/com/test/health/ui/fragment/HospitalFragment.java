package com.test.health.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.test.baselibrary.base.BaseFragment;
import com.test.health.MockData.MockData;
import com.test.health.R;
import com.test.health.ui.adapter.FirstFootAdapter;

/**
 * Created by lady_zhou on 2018/3/28.
 */

public class HospitalFragment extends BaseFragment {
    public static final String TAG = HospitalFragment.class.getSimpleName();//得到类名

    private View mViewFront;//前面
    private View mViewBackMap;//后面地图
    private RecyclerView mRvHospital;
    private FirstFootAdapter mFirstFootAdapter;

    private ImageView mIvLocation;//定位图标
    private AnimatorSet mRightOutAnimatorSet, mLeftInAnimatorSet;       //组合动画

    //地图
    private MapView mapView;
    private AMap aMap;

    private boolean mIsShowBack = false;  //是否显示背面


    /**
     * 使用单例
     *
     * @return
     */
    public static HospitalFragment newInstance() {
        HospitalFragment fragment = new HospitalFragment();
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hospital;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mViewFront = findViewById(R.id.ll_front);
        mViewBackMap = findViewById(R.id.ll_back_map);
        mRvHospital = findViewById(R.id.rv_list);
        mIvLocation = findViewById(R.id.iv_image);


        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        initMap();

        setAnimators(); // 设置动画
        setCameraDistance(); // 设置镜头距离
    }

    /**
     * 初始化地图
     */
    private void initMap() {
        aMap = mapView.getMap();
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

    @Override
    protected void initData(Bundle arguments, Bundle savedInstanceState) {
        mRvHospital.setLayoutManager(new LinearLayoutManager(mActivity));
        mFirstFootAdapter = new FirstFootAdapter(MockData.getCommodityDatas(10, false));
        mRvHospital.setAdapter(mFirstFootAdapter);
    }

    @Override
    protected void initListener() {
        mIvLocation.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_image:
                flipCard();
                break;
        }
    }

    private void setAnimators() {
        mRightOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(mActivity, R.animator.anim_right_out);
        mLeftInAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(mActivity, R.animator.anim_left_in);

        // 设置点击事件
        mRightOutAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                //动画开始
                mIvLocation.setClickable(false);
            }
        });

        mLeftInAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //动画完成后
                mIvLocation.setClickable(true);
            }
        });
    }

    //改变视角距离，靠近屏幕
    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mViewFront.setCameraDistance(scale);
        mViewBackMap.setCameraDistance(scale);
    }

    //翻转卡片
    private void flipCard() {
        if (!mIsShowBack) {  // 正面朝上
            mRightOutAnimatorSet.setTarget(mViewFront);
            mLeftInAnimatorSet.setTarget(mViewBackMap);
            mRightOutAnimatorSet.start();
            mLeftInAnimatorSet.start();
            mIsShowBack = true;
        } else { // 背面朝上
            mRightOutAnimatorSet.setTarget(mViewBackMap);
            mLeftInAnimatorSet.setTarget(mViewFront);
            mRightOutAnimatorSet.start();
            mLeftInAnimatorSet.start();
            mIsShowBack = false;
        }
    }
}
