package com.test.health.ui.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.health.R;
import com.test.health.bean.ValueBean;

import java.util.List;

public class NestAdapter extends BaseQuickAdapter<ValueBean, BaseViewHolder> {
    public NestAdapter(List<ValueBean> data) {
        super(R.layout.item_content, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ValueBean item) {
        helper.setText(R.id.tv_content, item.getValue());
    }
}
