package com.hai.lifecycledemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hai.lifecycledemo.R;

/**
 * Fragment直接写入xml中，Fragment和Fctivity的交叉生命周期
 * 周期1(Activity结束)：
 * onCreate-onCreateView-
 *      (Fragment)onAttach-
 * onAttachFragment-
 *      (Fragment)onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-
 * onStart-onPostCreate-onResume-
 *      (Fragment)onResume-
 * onResumeFragments-onPostResume-onAttachedToWindow-
 *      (Fragment)onPause-
 * onPause-
 *      (Fragment)onStop-
 * onStop-
 *      (Fragment)onDestroyView-onDestroy-onDetach-
 * onDestroy-onDetachedFromWindow
 * 周期2：
 *
 * Created by huanghp on 2019/8/9.
 * Email h1132760021@sina.com
 */
public class FragmentInAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_in_a);

    }

    public void clk(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
