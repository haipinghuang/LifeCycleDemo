package com.hai.lifecycledemo.ui;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hai.lifecycledemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment在FragmentPagerAdapter管理下的生命周期
 * setUserVisibleHint-onAttach-onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
 * 向右切换超过缓存数量后
 * setUserVisibleHint-onAttach-onCreate-(onPause-onStop-onDestroyView)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
 * 向左切换超过缓存数量后
 * setUserVisibleHint-onCreateView-onViewCreated-onActivityCreated-(onPause-onStop-onDestroyView)-onStart-onResume
 * Created by huanghp on 2018/9/10.
 * Email h1132760021@sina.com
 */
public class PageAdapterActivity extends FragmentActivity {
    ViewPager viewPager;
    TextView tvIndex;
    String[] titles = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "night", "ten", "eleven", "twive"};
    SparseArray<Fragment> map = new SparseArray();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_adapter);

        viewPager = (ViewPager) findViewById(R.id.viewPage);
        tvIndex = (TextView) findViewById(R.id.tvIndex);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.e("FragmentPagerAdapter", "getItem: " + position);
                return TabFragment.newInstance(titles[position]);
//                return list.get(position);
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
//        viewPager.setOffscreenPageLimit(titles.length);
        tvIndex.setText((viewPager.getCurrentItem() + 1) + "");
    }
}