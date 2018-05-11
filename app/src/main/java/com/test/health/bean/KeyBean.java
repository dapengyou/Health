package com.test.health.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.test.health.ui.adapter.CollapsibleAdapter;

import java.io.Serializable;

/**
 * Created by lady_zhou on 2018/5/10.
 */

public class KeyBean extends AbstractExpandableItem<ValueBean> implements Serializable, MultiItemEntity {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return CollapsibleAdapter.TYPE_HEAD;
    }
}
