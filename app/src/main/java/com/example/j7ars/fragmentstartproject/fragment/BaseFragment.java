package com.example.j7ars.fragmentstartproject.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.j7ars.fragmentstartproject.activity.BaseActivity;
import com.example.j7ars.fragmentstartproject.activity.BaseContentContainerActivity;
import com.example.j7ars.fragmentstartproject.dataclasses.Pair;
import com.example.j7ars.fragmentstartproject.screen.creator.ScreenCreator;

/**
 * Created by j7ars on 18.01.2018.
 */

public abstract class BaseFragment extends Fragment implements IFragmentResultCallBack {

    private static final String SAVE_FRAGMENT_RESULT_CALLBACK = "BaseFragment.SAVE_FRAGMENT_RESULT_CALLBACK";
    private static final String SAVE_REQUEST_CODE = "BaseFragment.SAVE_REQUEST_CODE";

    protected IFragmentResultCallBack mFragmentResultCallback;
    protected int mRequestCode;

    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseContentContainerActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseContentContainerActivity) {
            mActivity = (BaseActivity) activity;
        }
    }

    public void setFragmentResultCallback(IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        this.mFragmentResultCallback = iFragmentResultCallBack;
        this.mRequestCode = requestCode;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mFragmentResultCallback = (IFragmentResultCallBack) savedInstanceState.getSerializable(SAVE_FRAGMENT_RESULT_CALLBACK);
            mRequestCode = savedInstanceState.getInt(SAVE_REQUEST_CODE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(SAVE_FRAGMENT_RESULT_CALLBACK, mFragmentResultCallback);
        outState.putInt(SAVE_REQUEST_CODE, mRequestCode);
        super.onSaveInstanceState(outState);
    }

    protected ScreenCreator getScreenCreator() {
        return ScreenCreator.getInstance();
    }

    public abstract String getFragmentTag();

    public void setResult(int resultCode) {
        if (mFragmentResultCallback != null) {
            mFragmentResultCallback.onFragmentResult(mRequestCode, resultCode, null);
        }
    }

    public void setResult(int resultCode, Pair data) {
        if (mFragmentResultCallback != null) {
            mFragmentResultCallback.onFragmentResult(mRequestCode, resultCode, data);
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Pair data) {
        setResult(resultCode, data);
    }

    protected FragmentManager getCurrentFragmentManager() {
        return getChildFragmentManager();
    }

    protected FragmentTransaction getCurrentFragmentTransaction() {
        return getChildFragmentManager().beginTransaction();
    }

    protected void replaceFragmentWithAllowStateLoss(@IdRes int layoutId, Fragment fragment, FragmentTransaction transaction) {
        replaceFragmentWithAllowStateLoss(layoutId, fragment, null, transaction);
    }

    protected void replaceFragmentWithAllowStateLoss(@IdRes int layoutId, Fragment fragment, String tag, FragmentTransaction transaction) {
        replaceFragmentWithAllowStateLoss(layoutId, fragment, tag, transaction, false);
    }

    protected void replaceFragmentWithAllowStateLoss(@IdRes int layoutId, Fragment fragment, String tag, FragmentTransaction transaction, boolean addToBackStack) {
        if (tag == null) {
            if (addToBackStack) {
                transaction.replace(layoutId, fragment).addToBackStack(null).commitAllowingStateLoss();
            } else {
                transaction.replace(layoutId, fragment).commitAllowingStateLoss();
            }
        } else {
            if (addToBackStack) {
                transaction.replace(layoutId, fragment, tag).addToBackStack(tag).commitAllowingStateLoss();
            } else {
                transaction.replace(layoutId, fragment, tag).commitAllowingStateLoss();
            }
        }
    }

    protected void replaceFragment(@IdRes int layoutId, Fragment fragment, FragmentTransaction transaction) {
        replaceFragment(layoutId, fragment, null, transaction);
    }

    protected void replaceFragment(@IdRes int layoutId, Fragment fragment, String tag, FragmentTransaction transaction) {
        replaceFragment(layoutId, fragment, tag, transaction, false);
    }

    protected void replaceFragment(@IdRes int layoutId, Fragment fragment, String tag, FragmentTransaction transaction, boolean addToBackStack) {
        if (tag == null) {
            if (addToBackStack) {
                transaction.replace(layoutId, fragment).addToBackStack(null).commit();
            } else {
                transaction.replace(layoutId, fragment).commit();
            }
        } else {
            if (addToBackStack) {
                transaction.replace(layoutId, fragment, tag).addToBackStack(tag).commit();
            } else {
                transaction.replace(layoutId, fragment, tag).commit();
            }
        }
    }

    public void popBackStackFragment(String tag) {
        Log.i("TAG", "TAG = " + tag);
        if (tag != null) {
            getParentFragment().getChildFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getParentFragment().getChildFragmentManager().popBackStack();
        }
    }

    public void popBackStackImmediateFragment(String tag) {
        if (tag != null) {
            getCurrentFragmentManager().popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getCurrentFragmentManager().popBackStackImmediate();
        }
    }

    public void onBackPressed() {
        return;
    }

}
