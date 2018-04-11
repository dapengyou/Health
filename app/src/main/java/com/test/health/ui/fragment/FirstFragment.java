package com.test.health.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.baselibrary.base.BaseFragment;
import com.test.health.App;
import com.test.health.MockData.MockData;
import com.test.health.R;
import com.test.health.glid.GlideImageLoader;
import com.test.health.ui.activity.SetMealActivity;
import com.test.health.ui.adapter.FirstFootAdapter;
import com.test.health.ui.adapter.FirstHeadAdapter;
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

    private Banner mBanner;
    private TextView mTvHealthExamination;//体检
    private TextView mTvDentistry;//齿科
    private TextView mTvGene;//基因
    private TextView mTvRegistration;//挂号

    private ImageView mIvOldOne;
    private ImageView mIvOldTwo;
    private ImageView mIvOldThree;

    private View mViewTabOne;
    private View mViewTabTwo;

    private RecyclerView mRecyclerView;
    private RecyclerView mVerticalRecycleView;

    private FirstHeadAdapter mFirstHeadAdapter;
    private FirstFootAdapter mFirstFootAdapter;

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
        mBanner.setImages(App.images).setImageLoader(new GlideImageLoader()).start();

        //这里后续可以加一个获取城市的接口
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView = findViewById(R.id.rl_list);//横向的RecycleView
        mVerticalRecycleView = findViewById(R.id.rv_list);//纵向的RecycleView

        mBanner = findViewById(R.id.banner);

        mTvHealthExamination = findViewById(R.id.tv_health_examination);
        mTvDentistry = findViewById(R.id.tv_dentistry);
        mTvGene = findViewById(R.id.tv_gene);
        mTvRegistration = findViewById(R.id.tv_registration);

        mIvOldOne = findViewById(R.id.iv_old_one);
        mIvOldTwo = findViewById(R.id.iv_old_two);
        mIvOldThree = findViewById(R.id.iv_old_three);

        mViewTabOne = findViewById(R.id.rl_tab1);
        mViewTabTwo = findViewById(R.id.rl_tab2);

        initAdapter();
    }

    private void initAdapter() {
        //横向RecycleView
        LinearLayoutManager linearLayout = new LinearLayoutManager(mActivity);
        linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayout.canScrollHorizontally();
        mRecyclerView.setLayoutManager(linearLayout);
        mFirstHeadAdapter = new FirstHeadAdapter(MockData.getDatas(5));
        mRecyclerView.setAdapter(mFirstHeadAdapter);

        //纵向RecycleView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.canScrollVertically();
        mVerticalRecycleView.setLayoutManager(linearLayoutManager);
        mFirstFootAdapter = new FirstFootAdapter(MockData.getCommodityDatas(10));
        mVerticalRecycleView.setAdapter(mFirstFootAdapter);
    }

    @Override
    protected void initListener() {
        //跳转到套现详情
        mFirstFootAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity, SetMealActivity.class));
            }
        });
    }

    @Override
    protected void onViewClick(View v) {

    }
}
