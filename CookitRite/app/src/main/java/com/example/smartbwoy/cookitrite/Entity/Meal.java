package com.example.smartbwoy.cookitrite.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Meal {
    String mealName,mealid,useriD;
    //Map<Integer,String> step=new HashMap<>();
    //ArrayList<Ingredient> ingredients;


    Meal(){}
    public Meal(String mealName, String mealid, String useriD,ArrayList<Ingredient> ingredients, Map<Integer, String> step) {
        this.mealName = mealName;
        this.mealid = mealid;
        this.useriD = useriD;
        //this.step = step;
        //this.ingredients = ingredients;

    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealid() {
        return mealid;
    }

    public void setMealid(String mealid) {
        this.mealid = mealid;
    }

    public String getUseriD() {
        return useriD;
    }

    public void setUseriD(String useriD) {
        this.useriD = useriD;
    }
}
