package com.advancemoms.imageproject;

import android.util.Log;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alway on 3/24/2018.
 */



public class getImagesActivity {
    private String mName;
    private String mImageUrl;
    private static final String TAG = "getImagesActivity";
    public List<Upload> upl;
    private List<Upload> work;

    public getImagesActivity() {

        upl = new ArrayList<>();
        Log.d(TAG, "onDataChange: ");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Industry");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "onDataChange: " + dataSnapshot.getChildrenCount());
                Upload upload;
                work=new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String type = (String) postSnapshot.child("Type").getValue();
                    String urlImg = (String) postSnapshot.child("ImgUrl").getValue();
                    upload = new Upload(type, urlImg);
                    work.add(upload);
                    //getimages();
                    Log.d(TAG, "onDataChange: " + type);
                    Log.d(TAG, "onDataChange: " + urlImg);
                }
                Log.d(TAG, "Damn work " + getimages());
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public List<Upload> getWork(){
        return work;
    }
    public List<Upload> getimages(){
        //Log.d(TAG, "getimages: upl " + upl.toString());
        return work;
    }
}