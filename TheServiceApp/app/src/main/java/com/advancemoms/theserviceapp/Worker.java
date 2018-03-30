package com.advancemoms.theserviceapp;

import android.location.Location;

/**
 * Created by alway on 3/22/2018.
 */

public class Worker extends User{
    //initializing variables

    String  Qualification, Status, Industry;
    int Experience, Ratings;

    public Worker(){
        super();
    }

    public Worker(int experience, String qualification, int ratings, String status, String industry) {
        super();
        this.Experience = experience;
        this.Qualification = qualification;
        this.Status = status;
        this.Industry = industry;
        this.Ratings = ratings;
    }

    public Worker(User user, int experience, String qualification, int ratings, String status, String industry){
        super(user.getUname(), user.getEmail(), user.getType(), user.getTelephone(), user.getImgUrl(), user.getAge());
        super.setLocation(user.getLlocation());

        this.Experience = experience;
        this.Qualification = qualification;
        this.Status = status;
        this.Industry = industry;
        this.Ratings = ratings;
    }



    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public void setIndustry(String industry){
        this.Industry= industry;
    }

    public String getIndustry(){
        return Industry;
    }

    public int getRatings() {
        return Ratings;
    }
}
