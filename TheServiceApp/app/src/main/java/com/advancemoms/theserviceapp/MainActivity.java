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
        //dpopp.loginAuthUser("qwerty", "alwaynesmall@hotmail.com");
        Button ldBtn = (Button) findViewById(R.id.loading_btn);
        dpopp.loginAuthUser("Password100", "rrennie66@gmail.com");
        ldBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(dpopp.getUserNow()==null){
                    Toast.makeText(MainActivity.this, "No user Found", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onClick: No user Found");
                }else {
                    Toast.makeText(MainActivity.this, "user " + dpopp.getUserNow().getUid(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, userHomeScreen.class);
                    startActivity(i);

                }
            }
        });
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


}
