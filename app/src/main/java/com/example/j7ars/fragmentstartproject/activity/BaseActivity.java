package com.example.j7ars.fragmentstartproject.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.j7ars.fragmentstartproject.screen.creator.ScreenCreator;

/**
 * Created by j7ars on 18.01.2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    protected abstract int getLayout();

    protected ScreenCreator getScreenCreator() {
        return ScreenCreator.getInstance();
    }

    protected FragmentManager getCurrentFragmentManager() {
        return getSupportFragmentManager();
    }

    protected FragmentTransaction getCurrentFragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }

    protected void replaceFragmentWithAllowStateLoss(@IdRes int layoutId, Fragment fragment, FragmentTransaction transaction) {
        replaceFragmentWithAllowStateLoss(layoutId, fragment, null, transaction);
    }

    protected void replaceFragmentWithAllowStateLoss(@IdRes int layoutId, Fragment fragment, String tag, FragmentTransaction transaction) {
        replaceFragmentWithAllowStateLoss(layoutId, fragment, tag, transaction, false);
    }

    protected void replaceFragmentWithAllowStateLoss(@IdRes int layoutId, Fragment fragment, String tag, FragmentTransaction transaction, boolean addToBackStack) {
        if (tag == null) {
            if(addToBackStack){
                transaction.replace(layoutId, fragment).addToBackStack(null).commitAllowingStateLoss();
            } else{
                transaction.replace(layoutId, fragment).commitAllowingStateLoss();
            }
        } else {
            if(addToBackStack){
                transaction.replace(layoutId, fragment, tag).addToBackStack(tag).commitAllowingStateLoss();
            } else{
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
            if(addToBackStack){
                transaction.replace(layoutId, fragment).addToBackStack(null).commit();
            } else{
                transaction.replace(layoutId, fragment).commit();
            }
        } else {
            if(addToBackStack){
                transaction.replace(layoutId, fragment, tag).addToBackStack(tag).commit();
            } else{
                transaction.replace(layoutId, fragment, tag).commit();
            }
        }
    }

    public void popBackStackFragment(String tag){
        if(tag != null){
            getCurrentFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getCurrentFragmentManager().popBackStack();
        }
    }

    public void popBackStackImmediateFragment(String tag){
        if(tag != null){
            getCurrentFragmentManager().popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getCurrentFragmentManager().popBackStackImmediate();
        }
    }

    private static class ProgressViewNotAttachedException extends RuntimeException {
        ProgressViewNotAttachedException() {
            super("Please set progress view not null object");
        }
    }

}
