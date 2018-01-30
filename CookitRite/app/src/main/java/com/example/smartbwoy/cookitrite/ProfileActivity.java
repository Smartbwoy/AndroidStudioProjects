package com.example.smartbwoy.cookitrite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

public class ProfileActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private FirebaseAuth userAuth;
    private FirebaseAuth.AuthStateListener firebaseListener;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    //private FusedLocationProviderClient mFusedLocationClient;
    public static class myMenu{
        static Menu name;
    }
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

        //TextView txt_username = (TextView) toolbar.findViewById(R.id.txt_username);
        //txt_email.setText("abc@stackoverflow.com");
        //txt_username.setText("Username");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView done= (ImageView) findViewById(R.id.doneRemoving);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.topbarRemover).setVisibility(View.GONE);
            }
        });
        userAuth=FirebaseAuth.getInstance();
        View header1=navigationView.getHeaderView(0);
        String userID=userAuth.getCurrentUser().getUid();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String[] userName = new String[1];
        DocumentReference docRef = db.collection("Users").document(userID);
        final TextView uname=(TextView) header1.findViewById(R.id.userName);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append(doc.get("Userame"));
                    uname.setText(fields.toString());

                   /* if (doc != null) {
                        userName[0] =doc.get("Username").toString();

                        //Toast.makeText(getApplicationContext(),task.getResult().getData().toString(),Toast.LENGTH_SHORT);
                        //Log.d(TAG, "DocumentSnapshot data: " + task.getResult().getData());
                    } else {
                        Toast.makeText(getApplicationContext(),"No such document",Toast.LENGTH_SHORT);

                    }*/
                } else {
                    Toast.makeText(getApplicationContext(), "error",Toast.LENGTH_SHORT);

                }
            }
        });

        //DatabaseReference current_user_dp= FirebaseDatabase.getInstance().getReference().child("User").child(userID);

        if (user != null && !user.isAnonymous()) {
            View header=navigationView.getHeaderView(0);
            TextView email = (TextView)header.findViewById(R.id.userEmailAddress);
            final TextView name=(TextView)header.findViewById(R.id.userName);
            email.setText(user.getEmail().toString());
            //name.setText(userName[0]);

           /* current_user_dp.child("username").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    name.setText(value);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });*/


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
}
