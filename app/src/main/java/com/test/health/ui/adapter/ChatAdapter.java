package com.test.health.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.health.R;
import com.test.health.bean.ChatBean;

import java.util.List;


public class ChatAdapter extends BaseMultiItemQuickAdapter<ChatBean, BaseViewHolder> {

    public ChatAdapter(Context context, List data) {
        super(data);
        addItemType(ChatBean.JION, R.layout.item_chat_join);
        addItemType(ChatBean.NORMAL, R.layout.item_chat_normal);


    }

    @Override
    protected void convert(BaseViewHolder helper, ChatBean item) {
        switch (helper.getItemViewType()) {
            case ChatBean.JION:
            case ChatBean.NORMAL:
                helper.setText(R.id.tv_content, item.getMessage());
                break;

        }
    }

}
