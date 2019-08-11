package com.hai.lifecycledemo.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TabHost;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;

import com.hai.lifecycledemo.R;

/**
 * Fragment在TabHost管理下的生命周期
 * onAttach-onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
 * 切换到新tab
 * onAttach-onCreate-(onPause-onStop-onDestroyView)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
 * 切换到旧tab
 * (onPause-onStop-onDestroyView)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
 * Created by huanghp on 2019/8/11.
 * Email h1132760021@sina.com
 */
public class TabHostActivity extends FragmentActivity {
    private static final String TAG = "TabHostActivity";
    FrameLayout tabContent;
    FragmentTabHost tabhost;
    private String tabSpecs[] = {"首页", "消息", "好友", "广场"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);
        init();
    }

    private void init() {
        tabhost = findViewById(android.R.id.tabhost);
        tabContent = findViewById(android.R.id.tabcontent);
        tabhost.setup(this, getSupportFragmentManager(), R.id.tabContent);
        for (int i = 0; i < tabSpecs.length; i++) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(tabSpecs[i]).setIndicator(tabSpecs[i]);
            Bundle b = new Bundle();
            b.putString("title", tabSpecs[i]);
            tabhost.addTab(tabSpec, TabFragment.class, b);
        }
        tabhost.setBackgroundColor(Color.WHITE);
    }
}
