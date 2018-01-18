package com.example.j7ars.fragmentstartproject.dataclasses;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.AnimatorRes;
import android.support.annotation.NonNull;

/**
 * Created by j7ars on 18.01.2018.
 */

public class Animation implements Parcelable{

    @AnimatorRes
    @NonNull
    private int enterAnimResId;
    @AnimatorRes
    @NonNull
    private int exitAnimResId;
    @AnimatorRes
    private int popEnterAnimResId;
    @AnimatorRes
    private int popExitAnimResId;

    public Animation(@NonNull int enterAnimResId, @NonNull int exitAnimResId) {
        this.enterAnimResId = enterAnimResId;
        this.exitAnimResId = exitAnimResId;
    }

    public Animation(@NonNull int enterAnimResId, @NonNull int exitAnimResId, int popEnterAnimResId, int popExitAnimResId) {
        this.enterAnimResId = enterAnimResId;
        this.exitAnimResId = exitAnimResId;
        this.popEnterAnimResId = popEnterAnimResId;
        this.popExitAnimResId = popExitAnimResId;
    }

    @NonNull
    public int getEnterAnimResId() {
        return enterAnimResId;
    }

    @NonNull
    public int getExitAnimResId() {
        return exitAnimResId;
    }

    public int getPopEnterAnimResId() {
        return popEnterAnimResId;
    }

    public int getPopExitAnimResId() {
        return popExitAnimResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.enterAnimResId);
        dest.writeInt(this.exitAnimResId);
        dest.writeInt(this.popEnterAnimResId);
        dest.writeInt(this.popExitAnimResId);
    }

    protected Animation(Parcel in) {
        this.enterAnimResId = in.readInt();
        this.exitAnimResId = in.readInt();
        this.popEnterAnimResId = in.readInt();
        this.popExitAnimResId = in.readInt();
    }

    public static final Creator<Animation> CREATOR = new Creator<Animation>() {
        @Override
        public Animation createFromParcel(Parcel source) {
            return new Animation(source);
        }

        @Override
        public Animation[] newArray(int size) {
            return new Animation[size];
        }
    };
}
