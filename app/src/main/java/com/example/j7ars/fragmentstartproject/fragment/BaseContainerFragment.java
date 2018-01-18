package com.example.j7ars.fragmentstartproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.j7ars.fragmentstartproject.R;

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
        openContentScreen(getContent());
    }

    protected void openContentScreen(Class<? extends Fragment> cls) {
        String tag = cls.getName();
        Fragment frag = getScreenCreator().newInstance(cls);
        getChildFragmentManager().beginTransaction().replace(R.id.container, frag, tag).commitAllowingStateLoss();
    }

    public abstract Class<? extends Fragment> getContent();

}
