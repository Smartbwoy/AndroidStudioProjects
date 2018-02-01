package com.advancemoms.theserviceapp;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    ///private static final in ERROR_DIOLOG_REQUEST = 9001; @Override
    MyRecyclerViewAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// data to populate the RecyclerView with
        String[] data = {"Machanic","Plumber","Doctor","Teacher"};
        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new MyRecyclerViewAdapter(this, data);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


       /* if(isServiceOk()){
            init();
        }*/
    }

    /*private void init(){
        Button btnMap = (Button) FindViewById(R.id.btnMap);
        btnMap.setOnClickListener(New View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }*/

    /*public boolean isServiceOk(){
        Log.d(TAG, "isServiceOk: checking google services version:");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS){
            //Everything is fine and user can make map request
            Log.d(TAG, "isServiceOk: Google Play service is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error accor but can be resolved
            Log.d(TAG, "isServiceOk: and error occur but can be resolved");
            Dialog diolog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIOLOG_REQUEST)
            dialog.show();
        }
        else{
            Toast.makeText(this, "you can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}*/
