package com.example.j7ars.fragmentstartproject.dataclasses;

/**
 * Created by j7ars on 18.01.2018.
 */

public class Pair<T> {

    private T mValue;

    public Pair(T value){
        this.mValue = value;
    }

    public Object getValue() {
        return mValue;
    }

    public void setValue(T value) {
        this.mValue = value;
    }

}
