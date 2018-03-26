package com.test.health.wight;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.test.health.R;

/**
 * Created by lady_zhou on 2018/3/23.
 */

public class BottomNavigatorView extends LinearLayoutCompat {
    public interface OnBottomNavigatorViewItemClickListener {
        void OnBottomNavigatorViewItemClickListener(int position, View view);
    }

    private OnBottomNavigatorViewItemClickListener mOnBottomNavigatorViewItemClickListener;

    public BottomNavigatorView(Context context) {
        this(context, null);
    }

    public BottomNavigatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.layout_bottom_navigator, this);

        for (int i = 0; i < getChildCount(); i++) {
             View view = getChildAt(i);//得到当前的view

            final int I = i;

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnBottomNavigatorViewItemClickListener.OnBottomNavigatorViewItemClickListener(I, v);
                }
            });
        }

    }

    public void select(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            //如果循环的值与传入的值相同  那么该view就是被选中了
            if (i == position) {
                selectChild(child, true);
            } else {
                selectChild(child, false);
            }
        }
    }

    /**
     * 选择底部按钮的处理
     *
     * @param child  传入的view
     * @param select 是否被选中
     */
    private void selectChild(View child, boolean select) {
        //当传入的view 属于viewGroup时
        if (child instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) child;
            group.setSelected(select);
            for (int i = 0; i < group.getChildCount(); i++) {
                selectChild(group.getChildAt(i), select);
            }
        } else {
            child.setSelected(select);

        }
    }

    /**
     * 给出入口
     *
     * @param onBottomNavigatorViewItemClickListener
     */
    public void setOnBottomNavigatorViewItemClickListener(OnBottomNavigatorViewItemClickListener onBottomNavigatorViewItemClickListener) {
        this.mOnBottomNavigatorViewItemClickListener = onBottomNavigatorViewItemClickListener;

    }
}
