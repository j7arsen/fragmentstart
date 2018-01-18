package com.example.j7ars.fragmentstartproject;

import android.os.Bundle;

import com.example.j7ars.fragmentstartproject.fragment.BaseContainerFragment;
import com.example.j7ars.fragmentstartproject.fragment.FragmentIntent;

/**
 * Created by maxmobiles on 18.01.2018.
 */

public class TestContainerFragment extends BaseContainerFragment {

    @Override
    public void openContent() {
        openContentScreen(TestFragment1.class, TestFragment1.class.getName());
    }

    public void openTest2Screen(String testData, FragmentIntent fragmentIntent){
        Bundle bundle = new Bundle();
        bundle.putString(TestFragment2.TEST_DATA, testData);
        openScreen(TestFragment2.class, TestFragment2.class.getName(), true, bundle, fragmentIntent);
    }

    public void openTest3Screen(FragmentIntent fragmentIntent){
        openScreen(TestFragment3.class, TestFragment3.class.getName(), true, fragmentIntent);
    }

    @Override
    public String getFragmentTag() {
        return TestContainerFragment.class.getName();
    }

}
