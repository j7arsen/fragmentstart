package com.example.j7ars.fragmentstartproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.j7ars.fragmentstartproject.dataclasses.Animation;
import com.example.j7ars.fragmentstartproject.dataclasses.Pair;
import com.example.j7ars.fragmentstartproject.fragment.BaseContentFragment;
import com.example.j7ars.fragmentstartproject.fragment.FragmentIntent;

/**
 * Created by j7ars on 18.01.2018.
 */

public class TestFragment1 extends BaseContentFragment {

    private View mToolbar;
    private Button mBtnNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test1, container, false);
        mToolbar = (View) view.findViewById(R.id.navigation_bar);
        mBtnNext = (Button) view.findViewById(R.id.btn_next);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListeners();
    }

    private void setListeners() {
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mActivity instanceof MainActivity) {
                    ((MainActivity) mActivity).openTest2Screen("Test Data", new FragmentIntent(1000, TestFragment1.this));
                }
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Pair data) {
        Log.i("Data", "Data = " + (String) data.getValue());
    }

    @Override
    public String getFragmentTag() {
        return TestFragment1.class.getName();
    }

    @Override
    public Animation getUIAnimation() {
        return null;
    }

    @Override
    public void initToolbar() {
        initDefaultToolbar(mToolbar);
        setTitle("Test1");
    }
}
