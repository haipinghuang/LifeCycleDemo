package com.hai.lifecycledemo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hai.lifecycledemo.R;
import com.hai.lifecycledemo.ui.base.LifeCycleFragment;

/**
 * fileDesc
 * Created by huanghp on 2019/8/9.
 * Email h1132760021@sina.com
 */
public class MyFragment extends LifeCycleFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_my, container);
    }
}
