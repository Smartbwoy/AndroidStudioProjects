package com.advancemoms.popdatabase;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements LocationListener{

    //Declaring database variables
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;
    //declare permissions
    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private static Location llocation;
    static public final int REQUEST_LOCATION = 1;

    private static final String TAG = "MainActivity";
    User users;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        LocationManager locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, COURSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            ActivityCompat.requestPermissions(this, new String[]{FINE_LOCATION}, REQUEST_LOCATION);
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);

        Log.d(TAG, "onCreate: mappp" + location.getLatitude() + " " + location.getLongitude());



        setContentView(R.layout.activity_main);

        //Intent in = new Intent(MainActivity.this, CreateAccount.class);
        //startActivity(in);
        //setContentView(R.layout.activity_create_account);

        for(i = 5; i<=10; i++) {
            //Log.d(TAG, "onCreate: Accessing for loop");
            String eml = "serviceapp" + i +"@hotmail.com";
            String pword = "qwerty123";
            String uname = "romain" + i;

            //createAuthUser(uname, pword, eml);
            //loginAuthUser(uname, pword, eml);
            //Log.d(TAG, "onCreate: "+ eml +" "+pword + " " + uname);

            //super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            //FirebaseUser currentUser = mAuth.getCurrentUser();
            //Log.d(TAG, "onCreate: current user " + currentUser.getUid().toString());
            //FirebaseAuth.getInstance().signOut();

        }

    }

    public void createWorker(){
        int ind;
        String[] service = {"Cooking Gas","Electrician ", "Gardener","Mechanic", "plumbing", "Removal","Technician ", "Wrecker", "cesspool truck"};
        Random r = new Random();
        int ii = r.nextInt(service.length);

        String sss = service[ii];
        Log.d(TAG, "createWorker:  " + sss);
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Worker").child(mAuth.getCurrentUser().getUid()).child("Industry").setValue(sss);
    }

   /* public void createIndustry(){
        String[] service = {"Cooking Gas","Electrician ", "Gardener","Mechanic", "plumbing", "Removal","Technician ", "Wrecker", "cesspool truck"};
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        for(int i =0; i< service.length;i++){
            int sid = 10000 +i;

            mDatabase.child("worker").child(""+sid).setValue(user);
            mDatabase.child("User").child(mAuth.getCurrentUser().getUid()).child("Type").setValue(uType);

            mDatabase.child("Worker").child(mAuth.getCurrentUser().getUid()).child("Industry").setValue();
        }
    }*/

    public void loginAuthUser(String userName, String password, String email){
        final String userName1 = userName;
        Log.d(TAG, "loginAuthUser: "+ email +" "+password + " " + userName);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //Toast.makeText(MainActivity.this, userName+ " "+ mAuth.getCurrentUser().getUid(),Toast.LENGTH_SHORT).show();
                            //mDatabase = FirebaseDatabase.getInstance().getReference();
                            //String uType= "Work";
                            //users = new User(userName1,uType);
                           // mDatabase.child("User").child(mAuth.getCurrentUser().getUid().toString()).setValue(users);
                           // mDatabase.child("User").child(mAuth.getCurrentUser().getUid().toString()).setValue(new User(userName1, uType));
                            //mDatabase.child("User").child(mAuth.getCurrentUser().getUid()).child("Type").setValue(uType);
                            mapOperation mmpp = new mapOperation();
                            String mp = ""+mmpp.getLlocation().getLatitude() + " " + mmpp.getLlocation().getLongitude();
                            //mDatabase.child("User").child(mAuth.getCurrentUser().getUid()).child("Location").setValue(mp);

                            createWorker();
                            FirebaseAuth.getInstance().signOut();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                    }

                        // ...
                    }
                });
    }

    public void createAuthUser(String userName, String password, String email){
        Log.d(TAG, "createAuthUser: acessing");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "onComplete: 1.1"+i);
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //updateUI(null);
                        }

                        // ...
                    }
                });
        loginAuthUser(userName, password, email);

    }




    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        llocation = location;
        //llocation.set(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "permission granted ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }



}
