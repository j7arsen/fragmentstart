package com.example.j7ars.fragmentstartproject;

import android.os.Bundle;

import com.example.j7ars.fragmentstartproject.activity.BaseOneContentContainerActivity;
import com.example.j7ars.fragmentstartproject.fragment.dataclasses.FragmentIntent;

public class MainActivity extends BaseOneContentContainerActivity {

    @Override
    public void openContent() {
        openContentScreen(TestContainerFragment.class, TestContainerFragment.class.getName());
    }

    public void openTest2Screen(String testData, FragmentIntent fragmentIntent){
        Bundle bundle = new Bundle();
        bundle.putString(TestFragment2.TEST_DATA, testData);
        openScreen(TestFragment2.class, TestFragment2.class.getName(), true, bundle, fragmentIntent.getFragmentResultCallBack(), fragmentIntent.getRequestCode());
    }

    public void openTest3Screen(FragmentIntent fragmentIntent){
        openScreen(TestFragment3.class, TestFragment3.class.getName(), true, fragmentIntent.getFragmentResultCallBack(), fragmentIntent.getRequestCode());
    }

}
