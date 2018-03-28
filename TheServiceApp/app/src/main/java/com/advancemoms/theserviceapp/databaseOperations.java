package com.advancemoms.theserviceapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by alway on 3/27/2018.
 */

public class databaseOperations extends AppCompatActivity {
    private static final String TAG = "databaseOperations";
    //Declaring database variables
    private FirebaseAuth mAuth;
    FirebaseUser current_user;

    public databaseOperations() {
        mAuth = FirebaseAuth.getInstance();


    }

    public void loginAuthUser(String password, String email){
        FirebaseAuth.getInstance().signOut();
        if(getUserNow() != null) {
            Log.w(TAG, "signInWithEmail:failure user found ");
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(databaseOperations.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                            }

                            // ...
                        }
                    });
        }

    }

    public FirebaseUser getUserNow(){
        return mAuth.getCurrentUser();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        current_user = mAuth.getCurrentUser();
    }

}



