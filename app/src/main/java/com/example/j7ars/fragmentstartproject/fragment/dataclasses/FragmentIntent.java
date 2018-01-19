package com.example.j7ars.fragmentstartproject.fragment.dataclasses;

import com.example.j7ars.fragmentstartproject.fragment.IFragmentResultCallBack;

import java.io.Serializable;

/**
 * Created by j7ars on 18.01.2018.
 */

public class FragmentIntent implements Serializable {

    private int mRequestCode;
    private IFragmentResultCallBack mFragmentResultCallBack;

    public FragmentIntent(IFragmentResultCallBack mFragmentResultCallBack) {
        this.mFragmentResultCallBack = mFragmentResultCallBack;
    }

    public FragmentIntent(int mRequestCode, IFragmentResultCallBack mFragmentResultCallBack) {
        this.mRequestCode = mRequestCode;
        this.mFragmentResultCallBack = mFragmentResultCallBack;
    }

    public int getRequestCode() {
        return mRequestCode;
    }

    public void setRequestCode(int mRequestCode) {
        this.mRequestCode = mRequestCode;
    }

    public IFragmentResultCallBack getFragmentResultCallBack() {
        return mFragmentResultCallBack;
    }

    public void setFragmentResultCallBack(IFragmentResultCallBack mFragmentResultCallBack) {
        this.mFragmentResultCallBack = mFragmentResultCallBack;
    }

}
