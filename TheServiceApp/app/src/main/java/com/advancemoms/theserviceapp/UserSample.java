package com.advancemoms.theserviceapp;

public class UserSample {
        // Store the name of the movie
        private String mName;
        // Store the release date of the movie
        private String mCategory;

        // Constructor that is used to create an instance of the Movie object
        public UserSample (String mName, String mCategory) {
            this.mName = mName;
            this.mCategory = mCategory;
        }

    @Override
    public String toString() {
        return "UserSample{" +
                "mName='" + mName + '\'' +
                ", mCategory='" + mCategory + '\'' +
                '}';
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmName() {

        return mName;
    }

    public String getmCategory() {
        return mCategory;
    }
}
