package com.test.health.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.baselibrary.Utils.GlideUtil;
import com.test.health.R;
import com.test.health.bean.FirstCommodityBean;

import java.util.List;

/**
 * Created by lady_zhou on 2018/4/9.
 */

public class MealOfRecommendAdapter extends BaseQuickAdapter<FirstCommodityBean, BaseViewHolder> {

    public MealOfRecommendAdapter(List<FirstCommodityBean> data) {
        super(R.layout.item_meal_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FirstCommodityBean item) {
        GlideUtil.loadHeaderImage(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.iv_image));

        if (item.isHaveSex()) {
            //例  成人高端体检套餐（女）
            helper.setText(R.id.tv_title, item.getTitle() + "(" + item.getSex() + ")");
        } else {
            helper.setText(R.id.tv_title, item.getTitle());
        }
        //金额
        helper.setText(R.id.tv_price, "￥" + item.getPrice() + "");
        //预约人数
        helper.setText(R.id.tv_people_number, item.getPeopleNumber() + "人已约");
    }
}
