package com.example.j7ars.fragmentstartproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.j7ars.fragmentstartproject.R;
import com.example.j7ars.fragmentstartproject.dataclasses.Animation;

/**
 * Created by j7ars on 18.01.2018.
 */

public abstract class BaseContentFragment extends BaseFragment {

    protected Toolbar mToolbar;
    protected TextView mTitleTextView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    protected void initDefaultToolbar(View toolbar) {
        if (toolbar != null) {
            mToolbar = (Toolbar) toolbar.findViewById(R.id.navigation_bar);
            mTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        }
    }

    protected void initDefaultBackToolbar(View toolbar) {
        initDefaultToolbar(toolbar);
        if (toolbar != null) {
            mActivity.setSupportActionBar(mToolbar);
            if (mActivity.getSupportActionBar() != null) {
                mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
                mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.onBackPressed();
                }
            });

        }
    }

    public void setTitle(CharSequence title) {
        if (mTitleTextView != null && title != null) {
            mTitleTextView.setText(title);
        }
    }

    public void setTitle(int titleId) {
        if (mTitleTextView != null) {
            mTitleTextView.setText(null);
        }
    }

    public abstract Animation getUIAnimation();

    public abstract void initToolbar();

}
