package com.example.tranvanmanh.twamp;

import org.parceler.Parcel;

/**
 * Created by tranvanmanh on 4/4/2018.
 */
@Parcel
public class TransferParcel {

    private Profile profile;

    public TransferParcel() {
    }

    public TransferParcel(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }
}
