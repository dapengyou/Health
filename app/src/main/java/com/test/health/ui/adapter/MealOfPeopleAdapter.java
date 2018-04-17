package com.test.health.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.baselibrary.Utils.GlideUtil;
import com.test.health.R;
import com.test.health.bean.MealOfPeopleBean;

import java.util.List;

/**
 * Created by lady_zhou on 2018/4/16.
 */

public class MealOfPeopleAdapter extends BaseQuickAdapter<MealOfPeopleBean, BaseViewHolder> {
    public MealOfPeopleAdapter(List<MealOfPeopleBean> data) {
        super(R.layout.item_info_people, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MealOfPeopleBean item) {
        GlideUtil.loadSquareHeaderImage(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_title, item.getTitle());

        if (item.isShow()) {
            helper.setVisible(R.id.tv_introduction, true);
            helper.setText(R.id.tv_introduction, item.getIntroduction());
        } else {
            helper.setVisible(R.id.tv_introduction, false);
        }
    }
}
