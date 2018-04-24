package com.test.health.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lady_zhou on 2018/4/23.
 */

public class HospitalAnalysisBean implements Serializable {
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
}
