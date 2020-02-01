package com.example.viewpagertest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager {

    //自定義viewpager重新複寫TouchEvent可自由開關左右滑動
    private boolean stopScroll;

    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (stopScroll){
            return false;
        }else {
            return super.onTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (stopScroll){
            return false;
        }else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    public void setStopScroll(boolean b){
        this.stopScroll = b;
    }
}
