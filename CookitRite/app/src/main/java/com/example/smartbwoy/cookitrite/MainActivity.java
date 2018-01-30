package com.example.smartbwoy.cookitrite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth userAuth;
    private FirebaseAuth.AuthStateListener firebaseListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userAuth=FirebaseAuth.getInstance();
        firebaseListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null) {
                    Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                else{
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        userAuth.addAuthStateListener(firebaseListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        userAuth.removeAuthStateListener(firebaseListener);
    }


}
