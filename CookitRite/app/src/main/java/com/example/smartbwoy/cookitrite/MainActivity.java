package com.example.smartbwoy.cookitrite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    /** Called when the user clicks the Send button */
    public void LogUser(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void Signup(View view){
        Intent intent= new Intent(this,sign_up.class);
        startActivity(intent);
    }

}
