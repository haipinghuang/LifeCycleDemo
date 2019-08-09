package com.hai.lifecycledemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * view生命周期
 * 周期：MyTextview-setMethod-onAttachedToWindow-onMeasure-onLayout-onDraw-onDetachedFromWindow
 * Created by huanghp on 2019/8/8.
 * Email h1132760021@sina.com
 */
public class MyTextview extends TextView {
    private static final String TAG = "MyTextview";

    public MyTextview(Context context) {
        super(context);
        Log.i(TAG, "MyTextview() called with: context = [" + context + "]");
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "MyTextview() called with: context = [" + context + "], attrs = [" + attrs + "]");
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "MyTextview() called with: , attrs = [" + attrs + "], defStyleAttr = [" + defStyleAttr + "]");
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.i(TAG, "MyTextview() called with: , attrs = [" + attrs + "], defStyleAttr = [" + defStyleAttr + "], defStyleRes = [" + defStyleRes + "]");
    }

    public void setMethod() {
        Log.i(TAG, "setMethod() called");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure() called with: widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "onLayout() called with: changed = [" + changed + "], left = [" + left + "], top = [" + top + "], right = [" + right + "], bottom = [" + bottom + "]");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw() called with: canvas = [" + canvas + "]");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow() called");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow() called");
    }
}
