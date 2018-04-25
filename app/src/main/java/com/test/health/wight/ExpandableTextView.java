package com.test.health.wight;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.health.R;

/**
 * Created by lady_zhou on 2018/4/25.
 */

public class ExpandableTextView extends LinearLayout {
    public static final int DEFAULT_MAX_LINES = 3;//默认显示行数
    private boolean isExpandable;//是否是展开的
    private int showLines;//需要展示的总行数

    private TextView mTvContent;//内容
    private TextView mTvExpandable;//展开标签

    public ExpandableTextView(Context context) {
        super(context);
        initView();
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttribute(attrs);
        initView();
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(attrs);
        initView();
    }

    /**
     * 设置自己想要的属性
     */
    private void initAttribute(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.ExpandableTextView, 0, 0);
        try {
            showLines = typedArray.getInt(R.styleable.ExpandableTextView_showLines, DEFAULT_MAX_LINES);
        } finally {
            typedArray.recycle();//回收
        }
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_expandable, this);
        mTvContent = findViewById(R.id.tv_content);
        mTvExpandable = findViewById(R.id.tv_expand);

        if (showLines > 0) {
            mTvExpandable.setMaxLines(showLines);//默认显示设置的行数
        }

        mTvExpandable.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = mTvExpandable.getText().toString().trim();//获得展开/收缩按钮的文字内容
                if (string.equals("全文")) {
                    mTvContent.setMaxLines(Integer.MAX_VALUE);//显示全部行数
                    mTvExpandable.setText("收起");
                    setExpandable(true);
                } else {
                    mTvContent.setMaxLines(showLines);
                    mTvExpandable.setText("全文");
                    setExpandable(false);
                }
            }
        });
    }

    public void setText(String content) {
        mTvContent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                // 避免重复监听
                mTvContent.getViewTreeObserver().removeOnPreDrawListener(this);

                int linCount = mTvContent.getLineCount();
                if (linCount > showLines) {

                    if (isExpandable) {
                        mTvContent.setMaxLines(Integer.MAX_VALUE);
                        mTvExpandable.setText("收起");
                    } else {
                        mTvContent.setMaxLines(showLines);
                        mTvExpandable.setText("全文");
                    }
                    mTvExpandable.setVisibility(View.VISIBLE);
                } else {
                    mTvExpandable.setVisibility(View.GONE);
                }
                return false;
            }
        });
        mTvContent.setText(content);

    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

}
