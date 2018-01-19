package com.example.j7ars.fragmentstartproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.j7ars.fragmentstartproject.dataclasses.Animation;
import com.example.j7ars.fragmentstartproject.dataclasses.Pair;
import com.example.j7ars.fragmentstartproject.dataclasses.SlideAnimation;
import com.example.j7ars.fragmentstartproject.fragment.BaseContentFragment;
import com.example.j7ars.fragmentstartproject.fragment.dataclasses.FragmentIntent;

/**
 * Created by j7ars on 18.01.2018.
 */

public class TestFragment2 extends BaseContentFragment {

    public static final String TEST_DATA = "TestFragment2.TEST_DATA";

    private View mToolbar;
    private Button mBtnNext;

    private String mTestData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArgs();
    }

    private void getArgs() {
        if (getArguments() != null) {
            mTestData = getArguments().getString(TEST_DATA);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test2, container, false);
        mBtnNext = (Button) view.findViewById(R.id.btn_next);
        mToolbar = (View) view.findViewById(R.id.navigation_bar);
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
                /*if (mActivity instanceof MainActivity) {
                    ((MainActivity) mActivity).openTest3Screen(new FragmentIntent(2000, TestFragment2.this));
                }*/
                if(getParentFragment() instanceof TestContainerFragment){
                    ((TestContainerFragment) getParentFragment()).openTest3Screen(new FragmentIntent(2000, TestFragment2.this));
                }
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Pair data) {
        switch (requestCode){
            case 2000:
                if(resultCode == 2001){
                    setResult(1001, data);
                    popBackStackFragment(getFragmentTag());
                }
                break;
        }

    }

    @Override
    public String getFragmentTag() {
        return TestFragment2.class.getName();
    }

    @Override
    public Animation getUIAnimation() {
        return new SlideAnimation();
    }

    @Override
    public void initToolbar() {
        initDefaultBackToolbar(mToolbar);
        setTitle("Test2");
    }

}
