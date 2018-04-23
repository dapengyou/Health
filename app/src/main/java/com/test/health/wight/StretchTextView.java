package com.test.health.wight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 可伸缩的textView
 *
 * Created by lady_zhou on 2018/4/20.
 */

public class StretchTextView extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {
    public StretchTextView(Context context) {
        super(context);
    }

    public StretchTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StretchTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {

    }
}
