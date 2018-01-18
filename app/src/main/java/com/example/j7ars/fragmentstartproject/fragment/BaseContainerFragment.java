package com.example.j7ars.fragmentstartproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.j7ars.fragmentstartproject.R;
import com.example.j7ars.fragmentstartproject.dataclasses.Animation;

/**
 * Created by j7ars on 18.01.2018.
 */

public abstract class BaseContainerFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_container, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        openContent();
    }

    public abstract void openContent();

    protected void openContentScreen(Class<? extends Fragment> cls, String tag) {
        openContentScreen(cls, tag, null, null);
    }

    protected void openContentScreen(Class<? extends Fragment> cls, String tag, Bundle bundle) {
        openContentScreen(cls, tag, bundle, null);
    }

    protected void openContentScreen(Class<? extends Fragment> cls, String tag, FragmentIntent fragmentIntent) {
        openContentScreen(cls, tag, null, fragmentIntent);
    }

    protected void openContentScreen(Class<? extends Fragment> cls, String tag, Bundle bundle, FragmentIntent fragmentIntent) {
        Fragment frag = getCurrentFragment(tag);
        if (frag == null) {
            if(bundle != null){
                if(fragmentIntent != null){
                    frag = getScreenCreator().newInstance(cls, bundle, fragmentIntent);
                } else{
                    frag = getScreenCreator().newInstance(cls, bundle);
                }
            } else{
                if(fragmentIntent != null){
                    frag = getScreenCreator().newInstance(cls, fragmentIntent);
                } else{
                    frag = getScreenCreator().newInstance(cls);
                }
            }
            replaceFragmentWithAllowStateLoss(R.id.container, frag, tag, getCurrentFragmentTransaction());
        }
    }

    protected void openScreen(Class<? extends Fragment> cls, String tag, boolean addToBackStack) {
        openScreen(cls, tag, addToBackStack, null, null);
    }

    protected void openScreen(Class<? extends Fragment> cls, String tag, boolean addToBackStack, Bundle bundle) {
        openScreen(cls, tag, addToBackStack,  bundle, null);
    }

    protected void openScreen(Class<? extends Fragment> cls, String tag, boolean addToBackStack, FragmentIntent fragmentIntent) {
        openScreen(cls, tag, addToBackStack, null, fragmentIntent);
    }

    protected void openScreen(Class<? extends Fragment> cls, String tag, boolean addToBackStack, Bundle bundle, FragmentIntent fragmentIntent) {
        Fragment frag = getCurrentFragment(tag);
        if (frag == null) {
            if(bundle != null){
                if(fragmentIntent != null){
                    frag = getScreenCreator().newInstance(cls, bundle, fragmentIntent);
                } else{
                    frag = getScreenCreator().newInstance(cls, bundle);
                }
            } else{
                if(fragmentIntent != null){
                    frag = getScreenCreator().newInstance(cls, fragmentIntent);
                } else{
                    frag = getScreenCreator().newInstance(cls);
                }
            }
            FragmentTransaction transaction = getAnimationFragmentTransaction(frag);
            replaceFragmentWithAllowStateLoss(R.id.container, frag, tag, transaction, addToBackStack);
        }
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

    protected Fragment getCurrentFragment(String tag) {
        Fragment fragment = getCurrentFragmentManager().findFragmentByTag(tag);
        return fragment;
    }

    protected Fragment getCurrentFragment() {
        Fragment fragment = getCurrentFragmentManager().findFragmentById(R.id.container);
        return fragment;
    }

}
