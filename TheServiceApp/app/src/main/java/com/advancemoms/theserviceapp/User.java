package com.advancemoms.theserviceapp;

import android.location.Location;
import android.util.Log;

/**
 * Created by alway on 3/22/2018.
 */

public class User {
    private static final String TAG = "User";
    private String Email, LName, FName, Uname, Telephone, DOB, Gender, Type, ImgUrl, UID;
    private Location llocation =  new Location("");;
    private int Age;

    //First Constructor
    public User (){
        this.Uname = "anonymous";
    }

    //Second Constructor
    public User(String username, String email) {
        this.Uname = username;
        this.Email = email;
    }

    //Third Constructor
    public User (String uname, String type, Location location){
        this.Uname = uname;
        this.Type = type;
        setLocation(location);
    }


    //Fourth Constructor
    public User(String uname, String email, String type, String telephone, String imgurl, int age) {
        this.Email = email;
        this.Uname = uname;
        this.Type = type;
        this.Telephone = telephone;
        this.ImgUrl = imgurl;
        this.Age = age;
    }

    public User(String uname, String email, String type, String ulocation, String telephone, String imgurl, int age, String uid){
        this.Email = email;
        this.Uname = uname;
        this.Type = type;
        this.Telephone = telephone;
        this.ImgUrl = imgurl;
        this.Age = age;
        this.UID = uid;
        Log.d(TAG, "User: loc" + ulocation);
        Log.d(TAG, "User: nam " + uname);
        setLocation(ulocation);

    }

    public String getUID() {
        return UID;
    }

    //Fifth Constructor
    public User(String uname, String lname, String fname, String email, String dob, String telephone, String gender, String type){
        this.Email = email;
        this.Uname = uname;
        this.DOB = dob;
        this.FName = fname;
        this.LName = lname;
        this.Telephone = telephone;
        this.Gender = gender;
        this.Type = type;
    }

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

    public String getType() {
        return Type;
    }

    public void setLocation(String uLocation) {
            
        this.llocation.setLatitude(Double.parseDouble(uLocation.split(" ")[0].trim()));
        this.llocation.setLongitude(Double.parseDouble(uLocation.split(" ")[1].trim()));
    }

    public void setLocation(Location location) {
        this.llocation.set(location);
    }

    public  void setGender(String gender){
        this.Gender = gender;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public void setAge(int age) {
        Age = age;
    }

    //END SETTERS


    //BEGIN MUTATORS

    public int getAge() {
        return Age;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

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



    //overtide tostring function
    public String toString(){
        return LName + "  \n"+ FName +"  \n"+ Uname +"\n"+ DOB +"\n"+ Email +"\n"+ Telephone;
    }
}