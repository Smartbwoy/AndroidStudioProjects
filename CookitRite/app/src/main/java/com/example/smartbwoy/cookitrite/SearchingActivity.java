package com.example.smartbwoy.cookitrite;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arlib.floatingsearchview.FloatingSearchView;

import layout.search;

public class SearchingActivity extends Activity {

    FloatingSearchView searchView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
        searchView = findViewById(R.id.search_reccipe);
        searchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            //@Override
            public void onHomeClicked() {
                SearchingActivity.super.onBackPressed();
            }
       });
        //mFragmentManager = getSupportFragmentManager();
        //mFragmentTransaction = mFragmentManager.beginTransaction();
        //mFragmentTransaction.replace(R.id.searchContainerView,new search()).commit();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar(-
        // ).setDisplayHomeAsUpEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void ReturnHome(View view){
        super.onBackPressed();
    }

}
