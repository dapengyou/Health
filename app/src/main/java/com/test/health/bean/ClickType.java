package com.test.health.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by lady_zhou on 2018/5/11.
 */

public class ClickType  implements MultiItemEntity{
    public int Type;
    public static final int TYPE_HEAD = 0;
    public static final int TYPE_BODY = 1;

    public ClickType(final int type) {
        Type = type;
    }
    @Override
    public int getItemType() {
        return 0;
    }
}
