package com.advancemoms.imageproject;

/**
 * Created by alway on 3/23/2018.
 */

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private static final String TAG = "ImagesActivity";
    static int wNumb;
    private ImageAdapter mAdapter;
    ImageButton[] box;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    //private Upload[] mUploads;

    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        //setContentView(R.layout.home_screen);

        //Declearing service buttons
        //box = new ImageButton[9];
        /*box[0] = (ImageButton) findViewById(R.id.home_box_1);
        box[1] = (ImageButton) findViewById(R.id.home_box_2);
        box[2] = (ImageButton) findViewById(R.id.home_box_3);
        box[3] = (ImageButton) findViewById(R.id.home_box_4);
        box[4] = (ImageButton) findViewById(R.id.home_box_5);
        box[5] = (ImageButton) findViewById(R.id.home_box_6);
        box[6] = (ImageButton) findViewById(R.id.home_box_7);
        box[7] = (ImageButton) findViewById(R.id.home_box_8);
        box[8] = (ImageButton) findViewById(R.id.home_box_9);*/

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);
        EditText searchWorker = (EditText) findViewById(R.id.SearchEditView);

        mUploads = new ArrayList<>();
        //mUploads = new Upload[9];

        //mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int ii = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    //mUploads[ii] = upload;
                    mUploads.add(upload);

                    Toast.makeText(ImagesActivity.this, "count = " + ii, Toast.LENGTH_LONG).show();
                    //Toast.makeText(ImagesActivity.this, mUploads.get(ii).getImageUrl(), Toast.LENGTH_LONG).show();
                    //Log.d(TAG, "onDataChange: values "+mUploads.get(ii).getImageUrl());
                    //box[ii].setImageURI(mUploads[ii].getImageUrl());
                    //box[ii].setImageURI(Uri.parse(upload.getImageUrl()));
                    //mUploads.add(upload);
                    ii++;
                }


                mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }
}


