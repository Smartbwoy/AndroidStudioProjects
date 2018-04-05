package com.advancemoms.theserviceapp;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final databaseOperations dpopp = new databaseOperations();
        if(dpopp.getUserNow()==null){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }else {
            Intent i = new Intent(this, userHomeScreen.class);
            startActivity(i);
        }
    }
}
