package com.test.health.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.test.baselibrary.Utils.PinyinUtils;
import com.test.baselibrary.base.TitleActivity;
import com.test.health.R;
import com.test.health.bean.ContactSortBean;
import com.test.health.ui.adapter.AddressAdapter;
import com.test.health.wight.NavView;
import com.test.health.wight.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressActivity extends TitleActivity {
    private NavView mNavView;
    private TextView mTvTip;
    private ListView mListView;
    private AddressAdapter adapter;
    private List<ContactSortBean> SourceDateList;
    private EditText mEtSearch;


    @Override
    protected int getContentResId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mNavView = findViewById(R.id.navView);
        mTvTip = findViewById(R.id.tv_tip);
        mListView = findViewById(R.id.rv_list);
        mEtSearch = findViewById(R.id.et_search);

        setAdapter();
    }

    private void setAdapter() {
        SourceDateList = filledData(getResources().getStringArray(R.array.contacts));
        Collections.sort(SourceDateList, new PinyinComparator());
        adapter = new AddressAdapter(this, SourceDateList);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {
        setLeftIcon(R.mipmap.back);
        setTitleText("选择城市");

        mNavView.setTextView(mTvTip);
    }

    @Override
    protected void initListener() {
        mNavView.setListener(new NavView.onTouchCharacterListener() {
            @Override
            public void touchCharacterListener(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mListView.setSelection(position + 1);
                }
            }
        });

        //根据输入框输入值的改变来过滤搜索
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onViewClick(View v) {

    }

    @Override
    protected void onLeftClick(View leftTv) {
        super.onLeftClick(leftTv);
        this.overridePendingTransition(0, R.animator.anim_bottom_out);
        finish();
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<ContactSortBean> mSortList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            mSortList = SourceDateList;
        } else {
            mSortList.clear();
            for (ContactSortBean sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 || PinyinUtils.getPingYin(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                    mSortList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(mSortList, new PinyinComparator());
        adapter.updateListView(mSortList);
    }

    private List<ContactSortBean> filledData(String[] date) {
        List<ContactSortBean> mSortList = new ArrayList<>();
        ArrayList<String> indexString = new ArrayList<>();

        for (int i = 0; i < date.length; i++) {
            ContactSortBean sortModel = new ContactSortBean();
            sortModel.setName(date[i]);
            String pinyin = PinyinUtils.getPingYin(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
                if (!indexString.contains(sortString)) {
                    indexString.add(sortString);
                }
            }
            mSortList.add(sortModel);
        }
        Collections.sort(indexString);
        mNavView.setIndexText(indexString);
        return mSortList;
    }
}
