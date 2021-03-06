package com.advancemoms.imageproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alway on 3/23/2018.
 */

public class HomeScreen extends AppCompatActivity {

    //Declaring database variables
    DatabaseReference myRef;
    FirebaseDatabase database;

    public interface OnGridImageSelectedListener{
        void onGridImageSelected(Upload upload);
    }
    OnGridImageSelectedListener mOnGridImageSelectedListener;

    private String mName;
    private String mImageUrl;
    Upload upload;
    private static final String TAG = "userHomeScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Industry");



        initImageLoader();
        databasecheck();
        //setupGrid();
    }



    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(HomeScreen.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    public void databasecheck(){

        final GridView gridView = (GridView) findViewById(R.id.gridView);
        final ArrayList<Upload> wNames = new ArrayList<>();  //names of all the jobs

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String type = (String) postSnapshot.child("Type").getValue();
                    String urlImg = (String) postSnapshot.child("ImgUrl").getValue();
                    wNames.add(new Upload(type, urlImg));
                }
                int gridwidth = getResources().getDisplayMetrics().heightPixels;
                int imagewidth = gridwidth/3;
                gridView.setColumnWidth(imagewidth);

                ArrayList<String> urlimg = new ArrayList<>();
                for(int i=0; i<wNames.size(); i++) {
                    urlimg.add(wNames.get(i).getImageUrl());
                }
                GridImageAdapter adapter = new GridImageAdapter(HomeScreen.this,R.layout.layout_grid_imageview, "", urlimg);
                //setupGrid();

                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(HomeScreen.this, "Service "+wNames.get(position).getmName(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public  void setupGrid(){
        Log.d(TAG, "setupGrid: Setting up grid");
        final GridView gridView = (GridView) findViewById(R.id.gridView);
        final ArrayList<Upload> wNames = new ArrayList<>();  //names of all the jobs
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Industry");




        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: 2");
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int count = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String type = (String) postSnapshot.child("Type").getValue();
                    String urlImg = (String) postSnapshot.child("ImgUrl").getValue();
                    wNames.add(new Upload(type, urlImg));
                    Log.d(TAG, "onDataChange: for");
                }


                int gridwidth = getResources().getDisplayMetrics().heightPixels;
                int imagewidth = gridwidth/3;
                gridView.setColumnWidth(imagewidth);

                ArrayList<String> urlimg = new ArrayList<>();
                for(int i=0; i<wNames.size(); i++) {
                    urlimg.add(wNames.get(i).getImageUrl());
                }
                Log.d(TAG, "onDataChange: b4 adapter");
                GridImageAdapter adapter = new GridImageAdapter(HomeScreen.this,R.layout.layout_grid_imageview, "", urlimg);
                setupGrid();
                gridView.setAdapter(adapter);
                Log.d(TAG, "onDataChange: after adaper");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }




}

