package com.example.smartbwoy.cookitrite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

public class ProfileActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    public static class myMenu{
        static Menu name;
    }
    public TabLayout tabLayout;
    ViewFlipper simpleViewFlipper;
    Animation in,out;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        ImageView done= (ImageView) findViewById(R.id.doneRemoving);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.topbarRemover).setVisibility(View.GONE);
            }
        });

    }

    public void nextpic(View v){
        //simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper);
        simpleViewFlipper.setFlipInterval(2000);
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        simpleViewFlipper.startFlipping();

        //simpleViewFlipper.showNext();
    }
    public void backpic(View v){
        //simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper);
        simpleViewFlipper.stopFlipping();
        //simpleViewFlipper.showPrevious();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        myMenu.name=menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.action_logout){
            Intent in= new Intent(this,MainActivity.class);
            startActivity(in);
        }
        if(id==R.id.action_search){
            Intent in= new Intent(this, SearchingActivity.class);
            startActivity(in);
        }
        if(id==R.id.action_edit){
           findViewById(R.id.topbarRemover).setVisibility(View.VISIBLE);

        }
        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createmeal(View view) {
    Intent intent = new Intent(this, Create_MealActivity.class);
    startActivity(intent);
    }

    public void viewmeal(View view){
        Intent meal=new Intent(this, ViewRecipe.class);
        startActivity(meal);
    }

    public void viewcreatedRecipe(View view){
        Intent allmeal=new Intent(this, ListedRecipes.class);
        startActivity(allmeal);
    }
    public void addGroceryitem(View view){
        Intent additem=new Intent(this, CreateGroceryItem.class);
        startActivity(additem);

    }
}
