package com.test.health.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.test.health.ui.adapter.CollapsibleAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lady_zhou on 2018/4/23.
 */

public class HospitalAnalysisBean extends AbstractExpandableItem<ValueBean> implements Serializable, MultiItemEntity {
    private String key;
    private List<ValueBean> valueBean;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<ValueBean> getValueBean() {
        return valueBean;
    }

    public void setValueBean(List<ValueBean> valueBean) {
        this.valueBean = valueBean;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return CollapsibleAdapter.TYPE_HEAD;
    }

    public HospitalAnalysisBean() {

    }

    public HospitalAnalysisBean(int itemType, String key) {
        this.key = key;
    }

    public HospitalAnalysisBean(int itemType, List<ValueBean> valueBean) {
        this.valueBean = valueBean;
    }
}
