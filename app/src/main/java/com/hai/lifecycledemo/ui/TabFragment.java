package com.hai.lifecycledemo.ui;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hai.lifecycledemo.R;

/**
 * fileDesc
 * Created by huanghp on 2018/9/10.
 * Email h1132760021@sina.com
 */
public class TabFragment extends Fragment {
    private static final String TAG = "TabFragment";
    private String title;

    public static TabFragment newInstance(String title) {
        TabFragment fragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, title + " setUserVisibleHint with: isVisibleToUser = [" + isVisibleToUser + "]");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        title = (String) getArguments().getString("title");
        Log.i(TAG, title + " onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, title + " onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, title + " onCreateView: ");
        return inflater.inflate(R.layout.fragment_tab, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.tvTitle)).setText(title);
        Log.i(TAG, title + " onViewCreated: ");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, title + " onActivityCreated: ");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, title + " onStart: ");

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, title + " onResume: ");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, title + " onPause: ");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, title + " onStop: ");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, title + " onDestroyView: ");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, title + " onDestroy: ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, title + " onDetach: ");

    }
}
