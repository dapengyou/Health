package com.test.health.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.test.baselibrary.base.TitleActivity;
import com.test.health.MockData.MockData;
import com.test.health.R;
import com.test.health.ui.adapter.FirstFootAdapter;

/**
 * 套餐页面
 */
public class SetMealActivity extends TitleActivity {
    private TextView mTvPric;//新价格
    private TextView mTvOldPrice;//老价格
    private RecyclerView mRecyclerView;
    private FirstFootAdapter mAdapter;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    @Override
    protected int getContentResId() {
        return R.layout.activity_set_meal;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTvOldPrice = findViewById(R.id.tv_price_old);
        mTvPric = findViewById(R.id.tv_price);
        mRecyclerView = findViewById(R.id.rv_list);

//        mCollapsingToolbarLayout = findViewById(R.id.collapsing);
//        mToolbar = findViewById(R.id.toolbar);

//        setSupportActionBar(mToolbar);

    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {
        setTitleText("套餐详情");
        setLeftIcon(R.mipmap.back);

        String text = "￥1234.00";

        int start = text.indexOf(".");
        int end = text.length();

        Spannable textSpan = new SpannableStringBuilder(text);
        textSpan.setSpan(new AbsoluteSizeSpan(20), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(46), 1, start, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(20), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        //不知道为什么使用 mTvPric.setText("￥"+textSpan); 显示不出效果
        mTvPric.setText(textSpan);
        mTvOldPrice.setText(text);
        mTvOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);//划斜线

        //纵向RecycleView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.canScrollVertically();
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new FirstFootAdapter(MockData.getCommodityDatas(20));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
