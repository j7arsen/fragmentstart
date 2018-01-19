package com.example.j7ars.fragmentstartproject.screen.creator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.j7ars.fragmentstartproject.fragment.BaseFragment;
import com.example.j7ars.fragmentstartproject.fragment.IFragmentResultCallBack;

/**
 * Created by j7ars on 18.01.2018.
 */

public class ScreenCreator implements IScreenCreator {

    private static ScreenCreator mInstance;

    private ScreenCreator(){

    }

    public static ScreenCreator getInstance(){
        if(mInstance == null){
            mInstance = new ScreenCreator();
        }
        return mInstance;
    }

    @Override
    public void startActivity(Activity activity, Class initClass) {
        startActivity(activity, initClass, 0, null);
    }

    @Override
    public void startActivity(Fragment fragment, Activity activity, Class initClass) {
        startActivity(fragment, activity, initClass, 0, null);
    }

    @Override
    public void startActivity(Activity activity, Class initClass, int requestCode) {
        startActivity(activity, initClass, requestCode, null);
    }

    @Override
    public void startActivity(Fragment fragment, Activity activity, Class initClass, int requestCode) {
        startActivity(fragment, activity, initClass, requestCode, null);
    }

    @Override
    public void startActivity(Activity activity, Class initClass, Bundle bundle) {
        startActivity(activity, initClass, 0, bundle);
    }

    @Override
    public void startActivity(Fragment fragment, Activity activity, Class initClass, Bundle bundle) {
        startActivity(fragment, activity, initClass, 0, bundle);
    }

    @Override
    public void startActivity(Activity activity, Class initClass, Bundle bundle, int requestCode) {
        startActivity(activity, initClass, requestCode, bundle);
    }

    @Override
    public void startActivity(Fragment fragment, Activity activity, Class initClass, Bundle bundle, int requestCode) {
        startActivity(fragment, activity, initClass, requestCode, bundle);
    }

    private void startActivity(Activity activity, Class initClass, int requestCode, Bundle bundle){
        Intent intent = new Intent(activity, initClass);
        if(bundle != null) {
            intent.putExtras(bundle);
        }
        if(requestCode != 0){
            activity.startActivityForResult(intent, requestCode);
        } else{
            activity.startActivity(intent);
        }
        //UI.animationOpenActivity(activity);

    }

    private void startActivity(Fragment fragment, Activity activity , Class initClass, int requestCode, Bundle bundle){
        Intent intent = new Intent(activity, initClass);
        if(bundle != null) {
            intent.putExtras(bundle);
        }
        if(requestCode != 0){
            fragment.startActivityForResult(intent, requestCode);
        } else{
            fragment.startActivity(intent);
        }
        //UI.animationOpenActivity(activity);

    }

    public <T extends Fragment> T newInstance(Class<T> mClass){
        return newInstance(mClass, (Bundle) null);
    }

    public <T extends Fragment> T newInstance(Class<T> mClass, Bundle bundle){
        try {
            T instance = mClass.newInstance();
            if(bundle != null) {
                instance.setArguments(bundle);
            }
            return instance;
        } catch (java.lang.InstantiationException e) {
            throw new FragmentNotCreateException();
        } catch (IllegalAccessException e) {
            throw new FragmentNotCreateException();
        }
    }

    @Override
    public <T extends Fragment> T newInstance(Class<T> mClass, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        return newInstance(mClass, null, iFragmentResultCallBack, requestCode);
    }

    @Override
    public <T extends Fragment> T newInstance(Class<T> mClass, Bundle bundle, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        try {
            T instance = mClass.newInstance();
            if(iFragmentResultCallBack != null) {
                if (instance instanceof BaseFragment) {
                    ((BaseFragment) instance).setFragmentResultCallback(iFragmentResultCallBack, requestCode);
                }
            }
            if(bundle != null) {
                instance.setArguments(bundle);
            }
            return instance;
        } catch (java.lang.InstantiationException e) {
            throw new FragmentNotCreateException();
        } catch (IllegalAccessException e) {
            throw new FragmentNotCreateException();
        }
    }

    private static class FragmentNotCreateException extends RuntimeException {
        FragmentNotCreateException() {
            super("Error when created fragment");
        }
    }

}
