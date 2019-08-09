package com.hai.lifecycledemo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hai.lifecycledemo.R;
import com.hai.lifecycledemo.widget.MyTextview;

/**
* activity和view的交叉生命周期
 * onCreate-onCreateView-
 *      (view)MyTextview-setMethod-
 * onStart-onPostCreate-onResume-onResumeFragments-onPostResume-onAttachedToWindow-
 *      (view)onAttachedToWindow-onMeasure-onLayout-onDraw-
 * onPause-onStop-onDestroy(finish情况下执行)-
 *      (view)onDetachedFromWindow
 * onDetachedFromWindow
* Created by huanghp on 2019/8/9.
* Email h1132760021@sina.com
*/
public class ViewInAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ((MyTextview) findViewById(R.id.mytv)).setMethod();
    }

    public void clk(View view) {
        finish();
    }
}
