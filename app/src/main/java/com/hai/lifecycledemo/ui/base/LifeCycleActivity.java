package com.hai.lifecycledemo.ui.base;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * activity lifecycle
 * 周期1：onCreate-onCreateView-onStart-onPostCreate-onResume-onResumeFragments-onPostResume-onAttachedToWindow-跳转activity-onPause-onStop-onDestroy-onDetachedFromWindow(finish)
 * 周期2：onCreate-onCreateView-onStart-onPostCreate-onResume-onResumeFragments-onPostResume-onAttachedToWindow-跳转activity-onPause-onSaveInstanceState-onStop(未finish)
 * 周期3：onRestart-onStart-onResume-onResumeFragments-onPostResume
 * Created by huanghp on 2019/8/9.
 * Email h1132760021@sina.com
 */
public class LifeCycleActivity extends AppCompatActivity {
    protected static final String TAG = "LifeCycleActivity";


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "onPostCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
    }


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        Log.i(TAG, "onCreateView() called with: parent = [" + parent + "], name = [" + name + "], context = [" + context + "], attrs = [" + attrs + "]");
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() called");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Log.i(TAG, "onResumeFragments() called");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume() called");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() called");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow() called");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.i(TAG, "onAttachFragment() called with: fragment = [" + fragment + "]");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged() called with: newConfig = [" + newConfig + "]");
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        Log.i(TAG, "onMultiWindowModeChanged() called with: isInMultiWindowMode = [" + isInMultiWindowMode + "], newConfig = [" + newConfig + "]");
    }
}
