package com.advancemoms.servicehomepage;

import android.location.Location;

/**
 * Created by alway on 3/12/2018.
 */

public class User {
    private String Email, LName, FName, Uname, Telephone, DOB, Gender, Type;
    private Location llocation ;
    private int Age;

    //First Constructor
    public User (){
        this.Uname = "anonymous";
    }

    //First Constructor
    public User (String uname, String type, Location location){
        this.Uname = uname;
        this.Type = type;
        setLocation(location);
    }

    //Second Constructor
    public  User (String uname, String lname, String fname, String email, String type){
        this.Email = email;
        this.Uname = uname;
        this.FName = fname;
        this.LName = lname;
        this.Type = type;
    }

    //Third Constructor
    public User(String uname, String lname, String fname, String email, String dob, String location, String telephone, String gender, String type){
        this.Email = email;
        this.Uname = uname;
        this.DOB = dob;
        this.FName = fname;
        this.LName = lname;
        this.Telephone = telephone;
        this.Gender = gender;
        setLocation(location);
        this.Type = type;
    }


    //BEGIN SETTERS

    //Set type of user, customer or service provider
    public  void setType(String type){
        this.Type = type;
    }

    public void setEmail(String email){
        this.Email = email;
    }

    public void setLName(String lname){
        this.LName = lname;
    }

    public void setUname(String uname) {
        this.Uname = uname;
    }

    public void setDOB(String dob) {
        this.DOB = dob;
    }

    public void setFName(String fname) {
        this.FName = fname;
    }

    public void setTelephone(String telephone) {
        this.Telephone = telephone;
    }

    public void setLocation(String location) {
        this.llocation.setLatitude(Double.parseDouble(location.split("-")[0]));
        this.llocation.setLongitude(Double.parseDouble(location.split("-")[1]));
    }

    public void setLocation(Location location) {
        this.llocation.set(location);
    }
    
    public  void setGender(String gender){
        this.Gender = gender;
    }

    //END SETTERS


    //BEGIN MUTATORS
    
    public String getEmail(){
        return Email;
    }
    
    public  String getLName(){
        return LName;
    }
    
    public String getFName(){
        return FName;
    }
    
    public String getUname(){
        return Uname;
    }
    
    public String getTelephone(){
        return Telephone;
    }
    
    public String getDOB(){
        return DOB;
    }
    
    public Location getLlocation(){
        return llocation;
    }
    
    public String getGender(){
        return Gender;
    }

    //END MUYATORS


    //overtide tostring function
    public String toString(){
        return LName + "  \n"+ FName +"  \n"+ Uname +"\n"+ DOB +"\n"+ Email +"\n"+ Telephone; 
    }
}
