package com.test.health.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.test.baselibrary.base.BaseActivity;
import com.test.baselibrary.base.TitleActivity;
import com.test.health.MockData.MockData;
import com.test.health.R;
import com.test.health.bean.MealOfPeopleBean;
import com.test.health.ui.adapter.FirstFootAdapter;
import com.test.health.ui.adapter.MealInfoProjectAdapter;
import com.test.health.ui.adapter.MealOfPeopleAdapter;
import com.test.health.ui.adapter.MealOfRecommendAdapter;
import com.test.health.wight.ScrollChangedScrollView;
import com.test.health.wight.SpaceItemDecoration;

/**
 * 套餐页面
 */
public class SetMealActivity extends TitleActivity {
    private TextView mTvPric;//新价格
    private TextView mTvOldPrice;//老价格

    /**
     * 是否是ScrollView主动动作
     * false:是ScrollView主动动作
     * true:是TabLayout 主动动作
     */
    private boolean tagFlag = false;
    /**
     * 用于切换内容模块，相应的改变导航标签，表示当一个所处的位置
     */
    private int lastTagIndex = 0;
    /**
     * 用于在同一个内容模块内滑动，锁定导航标签，防止重复刷新标签
     * true: 锁定
     * false ; 没有锁定
     */
    private boolean tabFlagLock = false;

    // 头部导航标签
    private String[] tabTag = {"套餐简介", "适用分院", "包含项目", "推荐套餐"};

    private ScrollChangedScrollView mScrollChangedScrollView;
    private TabLayout mTabLayout;

    private RecyclerView mRvPeople;
    private RecyclerView mRvMeal;
    private RecyclerView mRvProject;
    private RecyclerView mRvRecommend;

    private MealInfoProjectAdapter mMealInfoProjectAdapter;
    private MealOfPeopleAdapter mMealOfPeopleAdapter;
    private MealOfRecommendAdapter mMealOfRecommendAdapter;

    private View mViewMealIntro;
    private View mViewBranchHospital;
    private View mViewProject;
    private View mViewRecommend;

    @Override
    protected int getContentResId() {
        return R.layout.activity_set_meal;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTvOldPrice = findViewById(R.id.tv_price_old);
        mTvPric = findViewById(R.id.tv_price);
        mTabLayout = findViewById(R.id.tablayout);
        mScrollChangedScrollView = findViewById(R.id.scrollChangedScrollView);

        mRvPeople = findViewById(R.id.rv_people);
        mRvMeal = findViewById(R.id.rv_meal);
        mRvProject = findViewById(R.id.rv_project);
        mRvRecommend = findViewById(R.id.rv_list);

        mViewMealIntro = findViewById(R.id.ll_meal_intro);
        mViewBranchHospital = findViewById(R.id.tv_branch_hospital);
        mViewProject = findViewById(R.id.ll_project);
        mViewRecommend = findViewById(R.id.ll_recommend);

    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {
        setTitleText("套餐详情");
        setLeftIcon(R.mipmap.back);

        setBottomData();

//        for (int i = 0; i < tabTag.length; i++) {
        for (String tag : tabTag){
            //只设置在字上边有图标，并且一直存在
            mTabLayout.addTab(mTabLayout.newTab().setText(tag));
//            mTabLayout.addTab(mTabLayout.newTab().setCustomView(setTab(tabTag[i], R.mipmap.tag, i)));
    }

    setRecycleView();

}

    private void setRecycleView() {
        //适合人群
        mRvPeople.setLayoutManager(new GridLayoutManager(this, 3));
        mMealOfPeopleAdapter = new MealOfPeopleAdapter(MockData.getPeopleDatas(2, false));
        mRvPeople.setAdapter(mMealOfPeopleAdapter);

        //套餐特点
        mRvMeal.setLayoutManager(new GridLayoutManager(this, 3));
        mMealOfPeopleAdapter = new MealOfPeopleAdapter(MockData.getPeopleDatas(8, true));
        mRvMeal.setAdapter(mMealOfPeopleAdapter);

        //包含项目
        mRvProject.setLayoutManager(new LinearLayoutManager(this));
        mMealInfoProjectAdapter = new MealInfoProjectAdapter(MockData.getProjecDatas(10));
        mRvProject.setAdapter(mMealInfoProjectAdapter);

        //推荐套餐
        //瀑布流
//        mRvRecommend.setLayoutManager(new StaggeredGridLayoutManager(2,
//                StaggeredGridLayoutManager.VERTICAL));
        mRvRecommend.setLayoutManager(new GridLayoutManager(this, 2));
        mMealOfRecommendAdapter = new MealOfRecommendAdapter(MockData.getCommodityDatas(4, false));
        int space = getResources().getDimensionPixelSize(R.dimen.dp_10);
        mRvRecommend.addItemDecoration(new SpaceItemDecoration(space, 2));
        mRvRecommend.setAdapter(mMealOfRecommendAdapter);
    }

    private View setTab(String name, int iconID) {
        View newtab = LayoutInflater.from(this).inflate(R.layout.layout_set_tablayout, null);
        TextView tv = newtab.findViewById(R.id.tabtext);
        tv.setText(name);
        ImageView im = newtab.findViewById(R.id.tabicon);
        im.setImageResource(iconID);
//        if (position == 0) {
//            tv.setTextColor(getResources().getColor(R.color.text_orange));
//            im.setImageResource(iconID);
//
//        } else {
//            tv.setTextColor(getResources().getColor(R.color.text_gray));
//            im.setImageResource(0);
//
//        }
        return newtab;
    }

    /**
     * 设置底部数据
     */
    private void setBottomData() {
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
    }

    @Override
    protected void initListener() {
        mScrollChangedScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //表明当前的动作是由 ScrollView 触发和主导
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    tagFlag = true;
                }
                return false;
            }
        });
        mScrollChangedScrollView.setScrollViewListener(new ScrollChangedScrollView.ScrollViewListener() {

            @Override
            public void onScrollChanged(NestedScrollView scrollView, int x, int y, int oldx, int oldy) {
                scrollRefreshNavigationTag(scrollView);
            }

            @Override
            public void onScrollStop(boolean isStop) {
            }
        });
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //表明当前的动作是由 TabLayout 触发和主导
                tagFlag = false;
                // 根据点击的位置，使ScrollView 滑动到对应区域
                int position = tab.getPosition();
                // 计算点击的导航标签所对应内容区域的高度
                int targetY = 0;
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        targetY = mViewBranchHospital.getTop();
                        break;
                    case 2:
                        targetY = mViewProject.getTop();
                        break;
                    case 3:
                        targetY = mViewRecommend.getTop();
                        break;
                    default:
                        break;
                }
                // 移动到对应的内容区域
                mScrollChangedScrollView.smoothScrollTo(0, targetY + 5);
//                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                changeTabNormal(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.tabicon);
        TextView txt_title = (TextView) view.findViewById(R.id.tabtext);

        txt_title.setTextColor(getResources().getColor(R.color.text_orange));
        if (txt_title.getText().toString().equals("套餐简介")) {
            img_title.setImageResource(R.mipmap.tag);
        } else if (txt_title.getText().toString().equals("适用分院")) {
            img_title.setImageResource(R.mipmap.tag);
        } else if (txt_title.getText().toString().equals("包含项目")) {
            img_title.setImageResource(R.mipmap.tag);
        } else {
            img_title.setImageResource(R.mipmap.tag);
        }
    }

    private void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.tabicon);
        TextView txt_title = (TextView) view.findViewById(R.id.tabtext);
        txt_title.setTextColor(getResources().getColor(R.color.text_gray));
        if (txt_title.getText().toString().equals("套餐简介")) {
            img_title.setImageResource(0);
        } else if (txt_title.getText().toString().equals("适用分院")) {
            img_title.setImageResource(0);
        } else if (txt_title.getText().toString().equals("包含项目")) {
            img_title.setImageResource(0);
        } else {
            img_title.setImageResource(0);
        }
    }

    @Override
    protected void onViewClick(View v) {

    }

    /**
     * 内容区域滑动刷新导航标签
     *
     * @param scrollView 内容模块容器
     */
    private void scrollRefreshNavigationTag(NestedScrollView scrollView) {
        if (scrollView == null) {
            return;
        }
        // 获得ScrollView滑动距离
        int scrollY = scrollView.getScrollY();
        // 确定ScrollView当前展示的顶部内容属于哪个内容模块
        if (scrollY > mViewRecommend.getTop()) {
            refreshTab(3);
        } else if (scrollY > mViewProject.getTop()) {
            refreshTab(2);
        } else if (scrollY > mViewBranchHospital.getTop()) {
            refreshTab(1);
        } else {
            refreshTab(0);
        }
    }

    /**
     * 刷新标签
     *
     * @param currentTagIndex 当前模块位置
     */
    private void refreshTab(int currentTagIndex) {
        // 上一个位置与当前位置不一致是，解锁内部锁，是导航可以发生变化
        if (lastTagIndex != currentTagIndex) {
            tabFlagLock = false;
        }
        if (!tabFlagLock) {
            // 锁定内部锁
            tabFlagLock = true;
            // 动作是由ScrollView触发主导的情况下，导航标签才可以滚动选中
            if (tagFlag) {
                mTabLayout.setScrollPosition(currentTagIndex, 0, true);
            }
        }
        lastTagIndex = currentTagIndex;
    }
}
