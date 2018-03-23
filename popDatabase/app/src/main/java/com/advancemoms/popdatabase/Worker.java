package com.advancemoms.popdatabase;

import android.location.Location;

/**
 * Created by alway on 3/22/2018.
 */

public class Worker extends User{
    //initializing variables

    String Industry;

    public Worker(String userN, String type, Location location, String industry){
        super(userN,type,location);
        this.Industry=industry;
    }

    public void setIndustry(String industry){
        this.Industry= industry;
    }

    public String getIndustry(){
        return Industry;
    }
}
