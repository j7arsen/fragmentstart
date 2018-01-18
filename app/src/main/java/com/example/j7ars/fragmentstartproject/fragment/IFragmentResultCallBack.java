package com.example.j7ars.fragmentstartproject.fragment;

import com.example.j7ars.fragmentstartproject.dataclasses.Pair;

import java.io.Serializable;

/**
 * Created by j7ars on 18.01.2018.
 */

public interface IFragmentResultCallBack extends Serializable{

    void onFragmentResult(int requestCode, int resultCode, Pair data);

}
