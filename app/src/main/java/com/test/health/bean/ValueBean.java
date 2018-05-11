package com.test.health.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.test.health.ui.adapter.CollapsibleAdapter;

import java.io.Serializable;

/**
 * Created by lady_zhou on 2018/4/24.
 */

public class ValueBean implements Serializable, MultiItemEntity {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getItemType() {
        return CollapsibleAdapter.TYPE_BODY;
    }
}
