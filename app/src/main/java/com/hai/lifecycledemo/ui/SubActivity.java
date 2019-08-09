package com.hai.lifecycledemo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hai.lifecycledemo.R;

public class SubActivity extends AppCompatActivity {
    private static final String TAG = "SubActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        setTitle("subActivity");
        getIntent().getBundleExtra("");
//        ComAction comAction = getIntent().getParcelableExtra("comAction");
//        Log.e(TAG, "onCreate: =" + comAction.actionStatus.name());

    }

    public void clk(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn2:
//                Process.killProcess(Process.myPid());
                System.exit(0);
                break;
            default:
                finish();
                break;
        }

    }
}
