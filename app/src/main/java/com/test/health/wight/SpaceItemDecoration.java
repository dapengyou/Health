package com.test.health.wight;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 设置recycleView  Item之间的距离
 *
 * Created by lady_zhou on 2018/4/17.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int number;

    public SpaceItemDecoration(int space, int number) {
        this.space = space;
        this.number = number;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) % number == 0) {
            outRect.left = 0;
        }
    }
}
