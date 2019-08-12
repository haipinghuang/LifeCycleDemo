package com.hai.lifecycledemo.ui;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hai.lifecycledemo.R;
import com.hai.lifecycledemo.ui.base.LifeCycleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment与FragmentAciviy在FragmentStatePagerAdapter管理下的交叉生命周期
 * onCreate-onCreateView-onStart-onPostCreate-onResume-onResumeFragments-onPostResume-onAttachedToWindow
 *      (Fragment)setUserVisibleHint-onAttach-
 * onAttachFragment
 *      (Fragment)onCreate-onCreateView-
 * onCreateView
 *      (Fragment)onViewCreated-onActivityCreated-onStart-onResume-
 *
 * 缓存满情况下切换ViewPage
 *      (Fragment)setUserVisibleHint-onAttach-
 * onAttachFragment
 *      (Fragment)onCreate-(onPause-onStop-onDestroyView-onDestroy-onDetach)-onCreateView-
 * onCreateView
 *      (Fragment)onViewCreated-onActivityCreated-onStart-onResume-
 *
 *
 * Fragment在FragmentStatePagerAdapter管理下的生命周期
 * setUserVisibleHint-onAttach-onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
 * 向右切换超过缓存数量后
 * setUserVisibleHint-onAttach-onCreate-(onPause-onStop-onDestroyView-onDestroy-onDetach)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
 * 向左切换超过缓存数量后
 * setUserVisibleHint-onAttach-onCreate-(onPause-onStop-onDestroyView-onDestroy-onDetach)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
 * Created by huanghp on 2018/9/10.
 * Email h1132760021@sina.com
 */
public class StatePageAdapterActivity extends LifeCycleActivity {
    ViewPager viewPager;
    TextView tvIndex;
    String[] titles = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "night", "ten", "eleven", "twive"};
    SparseArray<Fragment> map = new SparseArray();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_adapter);
        setTitle("StatePageAdapterActivity");

        viewPager = (ViewPager) findViewById(R.id.viewPage);
        tvIndex = (TextView) findViewById(R.id.tvIndex);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.e("FragmentPagerAdapter", "getItem: " + position);
//                if (map.get(position) == null)
//                    map.put(position, TabFragment.newInstance(titles[position]));
//                return map.get(position);
                return TabFragment.newInstance(titles[position]);
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tvIndex.setText((position + 1) + "");
            }
        });
//        viewPager.setOffscreenPageLimit(3);
        tvIndex.setText((viewPager.getCurrentItem() + 1) + "");
    }
}
