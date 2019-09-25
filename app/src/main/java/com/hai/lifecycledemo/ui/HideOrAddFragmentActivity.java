package com.hai.lifecycledemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.hai.lifecycledemo.R;
import com.hai.lifecycledemo.ui.base.LifeCycleActivity;

/**
 * Fragment与Activity在Hide or add切换管理下的交叉生命周期
 * onCreate-onCreateView-
 *      (Fragment)onAttach-
 * onAttachFragment-
 *      (Fragment)onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-
 * onStart-onPostCreate-onResume-
 *      (Fragment)onResume-
 * onResumeFragments-onPostResume-onAttachedToWindow-
 *
 * 切换新tab
 *      (Fragment)onAttach-
 * onAttachFragment-
 *      (Fragment)onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-onResume-
 *
 * 切换旧tab
 *       无log
 *
 * 跳转到其它Activity(不finish自己)
 *      (Fragment)onPause-
 * onPause-onSaveInstanceState-
 *      (Fragment)onStop-
 * onStop-
 *
 * Created by huanghp on 2019/9/25.
 * Email h1132760021@sina.com
 */
public class HideOrAddFragmentActivity extends AppCompatActivity {
    String[] titles = new String[]{"one", "two", "three", "four"};
    Fragment lastFragment;
    int lastSelectPos = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_or_add_fragment);
        setTitle("HideOrAddFragmentActivity");

        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title).setTag(title));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager sfm = getSupportFragmentManager();
                FragmentTransaction ftr = sfm.beginTransaction();
                if (tab.getPosition() == lastSelectPos) return;
                else {
                    //hide上一个
                    if (lastFragment.isAdded()) ftr.hide(lastFragment);
                    Fragment nextFragment = sfm.findFragmentByTag((String) tab.getText());
                    if (nextFragment == null) {
                        nextFragment = TabFragment.newInstance(titles[tab.getPosition()]);
                    }
                    //show下一个
                    if (nextFragment.isAdded()) ftr.show(nextFragment);
                    else ftr.add(R.id.flContainer, nextFragment, titles[tab.getPosition()]);

                    lastSelectPos = tab.getPosition();
                    lastFragment = nextFragment;
                    ftr.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FragmentTransaction ftr = getSupportFragmentManager().beginTransaction();
        ftr.add(R.id.flContainer, (lastFragment = TabFragment.newInstance(titles[0])), titles[0]);
        ftr.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hide_or_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.goActivity) {
            startActivity(new Intent(this, SubActivity.class));
        }
        return true;
    }
}
