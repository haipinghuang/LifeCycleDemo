package com.hai.lifecycledemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hai.lifecycledemo.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clk(View view) {
        switch (view.getId()) {
            case R.id.btnViewInAActivity:
                startActivity(new Intent(this, ViewInAActivity.class));
                break;
            case R.id.btnFragmentInAActivity:
                startActivity(new Intent(this, FragmentInAActivity.class));
                break;
            case R.id.btnTabHostActivity:
                startActivity(new Intent(this, TabHostActivity.class));
                break;
            case R.id.btnPageAdapterActivity:
                startActivity(new Intent(this, PageAdapterActivity.class));
                break;
            case R.id.btnStatePageAdapterActivity:
                startActivity(new Intent(this, StatePageAdapterActivity.class));
                break;
            case R.id.btnHideOrAddFragmentActivity:
                startActivity(new Intent(this, HideOrAddFragmentActivity.class));
                break;
        }
    }
}
