package com.test.health.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
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
    private MyLocationStyle myLocationStyle;

    private boolean mIsShowBack = false;  //是否显示背面

    //侧滑栏
    private DrawerLayout mDrawerLayout;
    private FrameLayout mViewDrawerRight;

    //选择栏
    private View mViewCity;//城市
    private View mViewHospital;//分院
    private View mViewChoose;//筛选

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

        mDrawerLayout = findViewById(R.id.drawerLayout);
        //关闭手势滑动
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
        mViewDrawerRight = findViewById(R.id.right_drawer_layout);
        initDrawer();

        mViewCity = findViewById(R.id.ll_choose_city);
        mViewHospital = findViewById(R.id.ll_choose_hospital);
        mViewChoose = findViewById(R.id.ll_choose);


        setAnimators(); // 设置动画
        setCameraDistance(); // 设置镜头距离
    }

    private void initDrawer() {
        //设置透明

        View view = getLayoutInflater().inflate(R.layout.layout_right, null);
        TextView text = view.findViewById(R.id.text);

        text.setText("右边测试菜单");

        mViewDrawerRight.addView(view);
    }

    /**
     * 初始化地图控制器对象
     */
    private void initMap() {
        aMap = mapView.getMap();
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
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
        mViewChoose.setOnClickListener(this);
        mViewHospital.setOnClickListener(this);
        mViewCity.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_image:
                flipCard();
                break;
            case R.id.ll_choose_city:

                break;
            case R.id.ll_choose_hospital:

                break;
            case R.id.ll_choose:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }
}
