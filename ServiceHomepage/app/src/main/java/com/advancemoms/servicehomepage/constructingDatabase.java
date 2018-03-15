package com.advancemoms.servicehomepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

/**
 * Created by alway on 3/7/2018.
 */

public class constructingDatabase extends AppCompatActivity

{

    //Declaring database variables

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;
    String userName;
    User user;
    private static final String TAG = "constructingDatabase";
    private String[] UT = {"Customer", "Worker"};
    private String[] boxName = {"Cooking Gas", "Electrician ", "Gardener", "Mechanic", "plumbing", "Removal", "Technician ", "Wrecker", "cesspool truck"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: access constructing database");
    }

}
/*
        Log.d(TAG, "onCreate: constructing database");
        for(int i = 1; i<2; i++) {
            String email = "serviceapp" + i +"@hotmail.com";
            String password = "qwerty123";
            userName = "romain" + i;

            mAuth=FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(constructingDatabase.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                // Sign in success, update UI with the signed-in user's information
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: sucessfull");
                                Toast.makeText(constructingDatabase.this, "Authenticated.",
                                        Toast.LENGTH_SHORT).show();

                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                int rnd = new Random().nextInt( UT.length);
                                int rnd2 = new Random().nextInt( boxName.length);
                                MapOperations mmap = new MapOperations();
                                if(rnd==1){
                                    user =(Worker) new Worker(userName, UT[rnd], mmap.getLlocation(), boxName[rnd2]);
                                    mDatabase.child("Worker").child(mAuth.getCurrentUser().getUid().toString()).setValue(boxName[rnd2]);
                                }else{
                                    user = new User(userName, UT[rnd], mmap.getLlocation());
                                }

                                mDatabase.child("User").child(mAuth.getCurrentUser().getUid().toString()).setValue(user );
                                //FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.d(TAG, "createUserWithEmail:failure", task.getException());
                                Log.d(TAG, "onComplete: unsucessfull");
                                Toast.makeText(constructingDatabase.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });

        }
    }


}*/
