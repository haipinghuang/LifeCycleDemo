package com.hai.lifecycledemo.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 懒加载Fragment,数据延迟到view可见时加载.
 * 用户与ViewPage结合使用
 * Created by huanghp on 2019/8/12.
 * Email h1132760021@sina.com
 */
public abstract class LazyFragment extends Fragment {
    private boolean isViewCreated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) loadData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        initView(view);
        loadData();
    }

    protected abstract void initView(View view);

    private void loadData() {
        if (getUserVisibleHint() && isViewCreated) delayLoadData();
    }

    protected void delayLoadData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
    }

}

