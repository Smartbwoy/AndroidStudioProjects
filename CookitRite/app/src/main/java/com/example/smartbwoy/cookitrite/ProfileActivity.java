package com.example.smartbwoy.cookitrite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.smartbwoy.cookitrite.CameraPackage.CameraActivity;
import com.example.smartbwoy.cookitrite.CameraPackage.CameraActivity2;
import com.example.smartbwoy.cookitrite.CameraPackage.CameraPreview;
import com.example.smartbwoy.cookitrite.Entity.User;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

public class ProfileActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private FirebaseAuth userAuth;
    private FirebaseAuth.AuthStateListener firebaseListener;
    View header;
    StorageReference storageReference;
    static CircleImageView profilePhoto;
    public static class myMenu{
        static Menu name;
    }
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private Camera mCamera;
    private CameraPreview mPreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();

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
        header = navigationView.getHeaderView(0);

        profilePhoto = (CircleImageView) header.findViewById(R.id.profilephoto);


        userAuth = FirebaseAuth.getInstance();

        // Load the image using Glide
        storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference ref = storageReference.child("images/usersprofilephotoes/" + userAuth.getCurrentUser().getUid());
            Glide.with(this)
                    .using(new FirebaseImageLoader())
                    .load(ref)
                    .dontAnimate()
                    .error(R.drawable.user_default)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(profilePhoto);
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Set a Profile Photo");
                builder.setItems(R.array.phototype, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                if (checkCameraHardware(ProfileActivity.this)) {
                                    Intent intent = new Intent(ProfileActivity.this, CameraActivity.class);
                                    // Create an instance of Camera
                                    startActivity(intent);


                                } else {
                                    Toast.makeText(getApplicationContext(), "No Camera FOUND", Toast.LENGTH_SHORT).show();
                                }
                        }
                        //Toast.makeText(getApplicationContext(),""+which,Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                //Intent additem = new Intent(view.getContext(), ViewImage.class);
                //startActivity(additem);
            }
        });


        ImageView done = (ImageView) findViewById(R.id.doneRemoving);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.topbarRemover).setVisibility(View.GONE);
            }
        });

        userAuth = FirebaseAuth.getInstance();
        String userID = userAuth.getCurrentUser().getUid();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (!user.isAnonymous()) {
            final DatabaseReference current_user_dp = FirebaseDatabase.getInstance().getReference("Users").child(userID);
            if (user != null && !user.isAnonymous()) {
                header = navigationView.getHeaderView(0);
                TextView email = (TextView) header.findViewById(R.id.userEmailAddress);
                final TextView uname = (TextView) header.findViewById(R.id.userName);
                email.setText(user.getEmail().toString());
                current_user_dp.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User currentUser=dataSnapshot.getValue(User.class);
                        uname.setText(currentUser.getUsername());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        }

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
        userAuth=FirebaseAuth.getInstance();
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
        if (id==R.id.action_logout){
            userAuth.signOut();
        }

        return super.onOptionsItemSelected(item);
    }


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
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}
