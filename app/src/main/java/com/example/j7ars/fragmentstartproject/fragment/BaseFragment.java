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
import com.example.j7ars.fragmentstartproject.activity.BaseContainerActivity;
import com.example.j7ars.fragmentstartproject.dataclasses.Pair;
import com.example.j7ars.fragmentstartproject.screen.creator.ScreenCreator;

/**
 * Created by j7ars on 18.01.2018.
 */

public abstract class BaseFragment extends Fragment implements IFragmentResultCallBack {

    private static final String SAVE_FRAGMENT_INTENT = "BaseFragment.SAVE_FRAGMENT_INTENT";

    protected FragmentIntent mFragmentIntent;

    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseContainerActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseContainerActivity) {
            mActivity = (BaseActivity) activity;
        }
    }

    public void setFragmentIntent(FragmentIntent mFragmentIntent) {
        this.mFragmentIntent = mFragmentIntent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mFragmentIntent = (FragmentIntent) savedInstanceState.getSerializable(SAVE_FRAGMENT_INTENT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(SAVE_FRAGMENT_INTENT, mFragmentIntent);
        super.onSaveInstanceState(outState);
    }

    protected ScreenCreator getScreenCreator() {
        return ScreenCreator.getInstance();
    }

    public abstract String getFragmentTag();

    public void setResult(int resultCode) {
        if (mFragmentIntent != null && mFragmentIntent.getFragmentResultCallBack() != null) {
            mFragmentIntent.getFragmentResultCallBack().onFragmentResult(mFragmentIntent.getRequestCode(), resultCode, null);
        }
    }

    public void setResult(int resultCode, Pair data) {
        if (mFragmentIntent != null && mFragmentIntent.getFragmentResultCallBack() != null) {
            mFragmentIntent.getFragmentResultCallBack().onFragmentResult(mFragmentIntent.getRequestCode(), resultCode, data);
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
        if(getParentFragment() != null){
            popBackStackFragment(getFragmentTag());
        } else{
            mActivity.popBackStackFragment(getFragmentTag());
        }
    }

}
