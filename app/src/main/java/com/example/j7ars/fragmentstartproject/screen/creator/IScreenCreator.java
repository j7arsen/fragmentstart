package com.example.j7ars.fragmentstartproject.screen.creator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.j7ars.fragmentstartproject.fragment.FragmentIntent;

/**
 * Created by j7ars on 18.01.2018.
 */

public interface IScreenCreator {

    void startActivity(Activity activity, Class initClass);

    void startActivity(Fragment fragment, Activity activity, Class initClass);

    void startActivity(Activity activity, Class initClass, int requestCode);

    void startActivity(Fragment fragment, Activity activity, Class initClass, int requestCode);

    void startActivity(Activity activity, Class initClass, Bundle bundle);

    void startActivity(Fragment fragment, Activity activity, Class initClass, Bundle bundle);

    void startActivity(Activity activity, Class initClass, Bundle bundle, int requestCode);

    void startActivity(Fragment fragment, Activity activity, Class initClass, Bundle bundle, int requestCode);

    <T extends Fragment>T newInstance(Class<T> mClass);

    <T extends Fragment> T newInstance(Class<T> mClass, Bundle bundle);

    <T extends Fragment> T newInstance(Class<T> mClass, FragmentIntent fragmentIntent);

    <T extends Fragment> T newInstance(Class<T> mClass, Bundle bundle, FragmentIntent fragmentIntent);

}
