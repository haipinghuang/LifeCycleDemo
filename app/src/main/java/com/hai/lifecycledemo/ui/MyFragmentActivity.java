package com.hai.lifecycledemo.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hai.lifecycledemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by huanghp on 2018/9/10.
 * Email h1132760021@sina.com
 */
public class MyFragmentActivity extends AppCompatActivity {
    ViewPager viewPager;
    TextView tvIndex;
    String[] titles = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "night", "ten", "eleven", "twive"};
    List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfragment);

        viewPager = (ViewPager) findViewById(R.id.viewPage);
        tvIndex = (TextView) findViewById(R.id.tvIndex);

        for (int i = 0; i < titles.length; i++) {
            list.add(TabFragment.newInstance(titles[i]));
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.e("FragmentPagerAdapter", "getItem: " + position);
//                return TabFragment.newInstance(titles[position]);
                return list.get(position);
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
