package com.test.health.wight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


/**
 * 滑动监听的ScrollView
 *
 * Created by lady_zhou on 2018/4/10.
 */

public class ScrollChangedScrollView extends ScrollView {
    private ScrollViewListener mScrollViewListener;
    private final int handleWhatId = 65984;
    private int lastY = 0;
    private int timeInterval = 20;//时间间隔

    //页面滚动
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case handleWhatId:
                    //滑动停止
                    if (lastY == getScrollY()) {
                        if (mScrollViewListener != null) {
                            mScrollViewListener.onScrollStop(true);
                        }
                    } else {
                        //还在滑动
                        if (mScrollViewListener != null) {
                            mScrollViewListener.onScrollStop(false);
                        }
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(handleWhatId, this), timeInterval);
                        lastY = getScrollY();
                    }
                    break;
            }
        }
    };

    public interface ScrollViewListener {
        /**
         * 滑动监听
         *
         * @param scrollView ScrollView控件
         * @param x          x轴坐标
         * @param y          y轴坐标
         * @param oldx       上一个x轴坐标
         * @param oldy       上一个y轴坐标
         */
        void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy);

        /**
         * 是否滑动停止
         *
         * @param isScrollStop true:滑动停止;false:未滑动停止
         */
        void onScrollStop(boolean isScrollStop);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        //判断  当传入监听事件时，进行滚动改变
        if (mScrollViewListener != null) {
            mScrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }


    public ScrollChangedScrollView(Context context) {
        super(context);
    }

    public ScrollChangedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollChangedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //开放回调入口
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.mScrollViewListener = scrollViewListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            mHandler.sendMessageDelayed(mHandler.obtainMessage(handleWhatId, this), timeInterval);
        }
        return super.onTouchEvent(ev);
    }
}
