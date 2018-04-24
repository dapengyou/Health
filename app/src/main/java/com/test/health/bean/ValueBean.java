package com.test.health.bean;

import java.io.Serializable;

/**
 * Created by lady_zhou on 2018/4/24.
 */

public class ValueBean implements Serializable {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
