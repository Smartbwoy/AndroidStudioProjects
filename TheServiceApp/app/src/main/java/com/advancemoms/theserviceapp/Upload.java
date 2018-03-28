package com.advancemoms.theserviceapp;



import android.net.Uri;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by alway on 3/23/2018.
 */

public class Upload {
    private String mName;
    private String mImageUrl;


    public Upload() {
        //empty constructor needed
    }


    public Upload(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
    }


    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmName() {

        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
