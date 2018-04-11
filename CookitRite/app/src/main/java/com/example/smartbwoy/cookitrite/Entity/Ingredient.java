package com.example.smartbwoy.cookitrite.Entity;

public class Ingredient {
    String intName,mealid,intid;
    double quanity;

    public Ingredient(){}
    public Ingredient(String intName, String mealid, String intid, double quanity) {
        this.intName = intName;
        this.mealid = mealid;
        this.intid = intid;
        this.quanity = quanity;
    }

    public String getIntName() {
        return intName;
    }

    public void setIntName(String intName) {
        this.intName = intName;
    }

    public String getMealid() {
        return mealid;
    }

    public void setMealid(String mealid) {
        this.mealid = mealid;
    }

    public String getIntid() {
        return intid;
    }

    public void setIntid(String intid) {
        this.intid = intid;
    }

    public double getQuanity() {
        return quanity;
    }

    public void setQuanity(double quanity) {
        this.quanity = quanity;
    }
}
