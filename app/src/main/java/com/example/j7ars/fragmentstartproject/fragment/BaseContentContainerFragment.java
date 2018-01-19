package com.example.j7ars.fragmentstartproject.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.j7ars.fragmentstartproject.R;
import com.example.j7ars.fragmentstartproject.dataclasses.Animation;

/**
 * Created by j7ars on 18.01.2018.
 */

public abstract class BaseContentContainerFragment extends BaseContentFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        openContent();
    }

    public abstract void openContent();

    protected void openContentScreen(@IdRes int resId, Class<? extends Fragment> cls, String tag) {
        openContentScreen(resId, cls, tag, null, null, 0);
    }

    protected void openContentScreen(@IdRes int resId, Class<? extends Fragment> cls, String tag, Bundle bundle) {
        openContentScreen(resId, cls, tag, bundle, null, 0);
    }

    protected void openContentScreen(@IdRes int resId, Class<? extends Fragment> cls, String tag, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        openContentScreen(resId, cls, tag, null, iFragmentResultCallBack, requestCode);
    }

    protected void openContentScreen(@IdRes int resId, Class<? extends Fragment> cls, String tag, Bundle bundle, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        createAndOpenScreen(resId, cls, tag, false, bundle, iFragmentResultCallBack, requestCode, true);
    }

    protected void openScreen(@IdRes int resId, Class<? extends Fragment> cls, String tag, boolean addToBackStack) {
        openScreen(resId, cls, tag, addToBackStack, null, null, 0);
    }

    protected void openScreen(@IdRes int resId, Class<? extends Fragment> cls, String tag, boolean addToBackStack, Bundle bundle) {
        openScreen(resId, cls, tag, addToBackStack,  bundle, null, 0);
    }

    protected void openScreen(@IdRes int resId, Class<? extends Fragment> cls, String tag, boolean addToBackStack, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        openScreen(resId, cls, tag, addToBackStack, null, iFragmentResultCallBack, requestCode);
    }

    protected void openScreen(@IdRes int resId, Class<? extends Fragment> cls, String tag, boolean addToBackStack, Bundle bundle, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        createAndOpenScreen(resId, cls, tag, addToBackStack, bundle, iFragmentResultCallBack, requestCode, false);
    }

    private void createAndOpenScreen(@IdRes int resId, Class<? extends Fragment> cls, String tag, boolean addToBackStack, Bundle bundle, IFragmentResultCallBack iFragmentResultCallBack, int requestCode, boolean isContentScreen){
        Fragment frag = getCurrentFragment(tag);
        if (frag == null) {
            if(bundle != null){
                if(iFragmentResultCallBack != null){
                    frag = getScreenCreator().newInstance(cls, bundle, iFragmentResultCallBack, requestCode);
                } else{
                    frag = getScreenCreator().newInstance(cls, bundle);
                }
            } else{
                if(iFragmentResultCallBack != null){
                    frag = getScreenCreator().newInstance(cls, iFragmentResultCallBack, requestCode);
                } else{
                    frag = getScreenCreator().newInstance(cls);
                }
            }
            if(isContentScreen){
                replaceFragmentWithAllowStateLoss(resId, frag, tag, getCurrentFragmentTransaction());
            } else {
                FragmentTransaction transaction = getAnimationFragmentTransaction(frag);
                replaceFragmentWithAllowStateLoss(resId, frag, tag, transaction, addToBackStack);
            }
        }
    }

    protected Fragment getCurrentFragment(String tag) {
        Fragment fragment = getCurrentFragmentManager().findFragmentByTag(tag);
        return fragment;
    }

    protected Fragment getCurrentFragment() {
        Fragment fragment = getCurrentFragmentManager().findFragmentById(R.id.container);
        return fragment;
    }

    protected FragmentTransaction getAnimationFragmentTransaction(Fragment fragment) {
        if (fragment instanceof BaseContentFragment) {
            Animation animation = ((BaseContentFragment) fragment).getUIAnimation();
            if(animation != null) {
                if (animation.getPopEnterAnimResId() != 0 && animation.getPopExitAnimResId() != 0) {
                    return getCurrentFragmentTransaction().setCustomAnimations(animation.getEnterAnimResId(), animation.getExitAnimResId(), animation.getPopEnterAnimResId(), animation.getPopExitAnimResId());
                } else {
                    return getCurrentFragmentTransaction().setCustomAnimations(animation.getEnterAnimResId(), animation.getExitAnimResId());
                }
            } else{
                return getCurrentFragmentTransaction();
            }
        } else {
            return getCurrentFragmentTransaction();
        }
    }


}
