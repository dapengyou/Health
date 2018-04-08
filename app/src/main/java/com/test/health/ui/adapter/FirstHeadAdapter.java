package com.test.health.ui.adapter;

import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.baselibrary.Utils.GlideUtil;
import com.test.health.R;
import com.test.health.bean.FirstAdvertisingBean;

import java.util.List;

/**
 * Created by lady_zhou on 2018/4/3.
 */

public class FirstHeadAdapter extends BaseQuickAdapter<FirstAdvertisingBean, BaseViewHolder> {

    public FirstHeadAdapter(List<FirstAdvertisingBean> data) {
        super(R.layout.item_first_advertising, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FirstAdvertisingBean item) {
        GlideUtil.loadHeaderImage(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.iv_image));

        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_date, item.getTime());
    }
}
