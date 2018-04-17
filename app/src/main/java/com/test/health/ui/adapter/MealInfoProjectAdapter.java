package com.test.health.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.health.R;
import com.test.health.bean.MealInfoProdectBean;

import java.util.List;

/**
 * Created by lady_zhou on 2018/4/13.
 */

public class MealInfoProjectAdapter extends BaseQuickAdapter<MealInfoProdectBean, BaseViewHolder> {
    public MealInfoProjectAdapter(List<MealInfoProdectBean> data) {
        super(R.layout.item_meal_info_project, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MealInfoProdectBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
    }
}
