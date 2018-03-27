package com.advancemoms.servicehomepage;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by alway on 3/7/2018.
 */

public class constructingDatabase extends AppCompatActivity implements LocationListener

{
    //declare permissions
    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;


    //Declaring database variables
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;
    String userName;
    private String password;
    private String email;
    User user;
    static String LLocation;
    private static final String TAG = "constructingDatabase";
    static public final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate: access constructing database");


<<<<<<< HEAD
        Log.d(TAG, "onCreate: constructing database");
        for(int i = 0; i<2; i++) {
            String email = "serviceapp" + i +"@hotmail.com";
            String password = "qwerty123";
            userName = "romain" + i;
            mAuth=FirebaseAuth.getInstance();
=======
        Toast.makeText(constructingDatabase.this, "getting location1", Toast.LENGTH_LONG).show();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
        }
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            onLocationChanged(location);



        Log.d(TAG, "onCreate: constructing database");
        for(int i = 1; i<2; i++) {
            email = "serviceapp" + i +"@hotmail.com";
            password = "qwerty123";



=======
        Toast.makeText(constructingDatabase.this, "getting location1", Toast.LENGTH_LONG).show();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
        }
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            onLocationChanged(location);


        for(int i = 10; i<50; i++) {
            mAuth=FirebaseAuth.getInstance();
            String email = "serviceapp" + i +"@hotmail.com";
            String password = "qwerty123";
            userName = "romain" + i;

>>>>>>> 38f48f95c006a4f23ccaf484aeb529e24dd16db2
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(constructingDatabase.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                // Sign in success, update UI with the signed-in user's information
                            mAuth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(constructingDatabase.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(constructingDatabase.this, "Welcome",
                                                        Toast.LENGTH_SHORT).show();


                                            } else {
                                                Toast.makeText(constructingDatabase.this, "not welcome",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            if (task.isSuccessful()) {
<<<<<<< HEAD
                                Log.d(TAG, "onComplete: sucessfull");

                                Toast.makeText(constructingDatabase.this, "Authenticated.",
                                        Toast.LENGTH_SHORT).show();

                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                Log.d(TAG, "I am here");
                                int rnd = new Random().nextInt( UT.length);
                                int rnd2 = new Random().nextInt( boxName.length);
                                MapOperations mmap = new MapOperations();
                                if(rnd==1){
                                    user =(Worker) new Worker(userName, UT[rnd], mmap.getLlocation(), boxName[rnd2]);
                                    mDatabase.child("Worker").child(mAuth.getCurrentUser().getUid().toString()).setValue(boxName[rnd2]);
                                }else{
                                    user = new User(userName, UT[rnd], mmap.getLlocation());
                                }

                                mDatabase.child("User").child(mAuth.getCurrentUser().getUid().toString()).setValue(user);
=======
                                // Sign in success, update UI with the signed-in user's information

                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                User user = new User(userName, mAuth.getCurrentUser().getEmail().toString());
                                mDatabase.child("User").child(mAuth.getCurrentUser().getUid().toString()).setValue("userType", "Regular");
>>>>>>> 38f48f95c006a4f23ccaf484aeb529e24dd16db2
                                //FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.d(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(constructingDatabase.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });

        }*/
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        LLocation = longitude + "--" + latitude;
    }

<<<<<<< HEAD
}
=======
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
=======
=======

>>>>>>> parent of 39e3266... setting up objects and populating server
    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
<<<<<<< HEAD
=======

    @Override
    public void onProviderDisabled(String s) {

<<<<<<< HEAD
    }

    @Override
>>>>>>> parent of 39e3266... setting up objects and populating server
=======
>>>>>>> parent of 39e3266... setting up objects and populating server
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(constructingDatabase.this, "permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(constructingDatabase.this, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of 39e3266... setting up objects and populating server
=======
>>>>>>> parent of 39e3266... setting up objects and populating server
}
=======
}*/
>>>>>>> parent of 28b41db... still not creating users
=======
}
>>>>>>> parent of 39e3266... setting up objects and populating server
>>>>>>> 38f48f95c006a4f23ccaf484aeb529e24dd16db2
