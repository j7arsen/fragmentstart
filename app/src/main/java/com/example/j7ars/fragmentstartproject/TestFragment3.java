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

/**
 * Created by j7ars on 18.01.2018.
 */

public class TestFragment3 extends BaseContentFragment {

    private View mToolbar;
    private Button mBtnNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test3, container, false);
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
                setResult(2001, new Pair("TestTestTest"));
                popBackStackFragment(getFragmentTag());
            }
        });
    }

    @Override
    public void onBackPressed() {
        popBackStackFragment(getFragmentTag());
        setResult(2001, new Pair("TestTestTest"));
    }

    @Override
    public String getFragmentTag() {
        return TestFragment3.class.getName();
    }

    @Override
    public Animation getUIAnimation() {
        return new SlideAnimation();
    }

    @Override
    public void initToolbar() {
        initDefaultBackToolbar(mToolbar);
        setTitle("Test3");
    }
}
