package com.example.j7ars.fragmentstartproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.j7ars.fragmentstartproject.R;

/**
 * Created by j7ars on 19.01.2018.
 */

public abstract class BaseOneContentContainerFragment extends BaseContentContainerFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_container, container, false);
    }

    protected void openContentScreen(Class<? extends Fragment> cls, String tag) {
        super.openContentScreen(R.id.container, cls, tag);
    }

    protected void openContentScreen(Class<? extends Fragment> cls, String tag, Bundle bundle) {
        super.openContentScreen(R.id.container, cls, tag, bundle);
    }

    protected void openContentScreen(Class<? extends Fragment> cls, String tag, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        super.openContentScreen(R.id.container, cls, tag, iFragmentResultCallBack, requestCode);
    }

    protected void openContentScreen(Class<? extends Fragment> cls, String tag, Bundle bundle, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        super.openContentScreen(R.id.container, cls, tag, bundle, iFragmentResultCallBack, requestCode);
    }

    protected void openScreen(Class<? extends Fragment> cls, String tag, boolean addToBackStack) {
        super.openScreen(R.id.container, cls, tag, addToBackStack);
    }

    protected void openScreen(Class<? extends Fragment> cls, String tag, boolean addToBackStack, Bundle bundle) {
        super.openScreen(R.id.container, cls, tag, addToBackStack, bundle);
    }

    protected void openScreen(Class<? extends Fragment> cls, String tag, boolean addToBackStack, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        super.openScreen(R.id.container, cls, tag, addToBackStack, iFragmentResultCallBack, requestCode);
    }

    protected void openScreen(Class<? extends Fragment> cls, String tag, boolean addToBackStack, Bundle bundle, IFragmentResultCallBack iFragmentResultCallBack, int requestCode) {
        super.openScreen(R.id.container, cls, tag, addToBackStack, bundle, iFragmentResultCallBack, requestCode);
    }

}
