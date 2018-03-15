package com.advancemoms.servicehomepage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createAccount extends AppCompatActivity {
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        final Button createbtn=(Button)findViewById(R.id.sign_up_button);
        createbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String email=((EditText)findViewById(R.id.emailAddress)).getText().toString();
                String password=((EditText)findViewById(R.id.userpassword)).getText().toString();
                final String userName=((EditText)findViewById(R.id.username)).getText().toString();
                final String user_type = ((EditText)findViewById(R.id.userType)).getText().toString();
                mAuth=FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(createAccount.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");

                                    MapOperations mapp = new MapOperations();
                                    mDatabase = FirebaseDatabase.getInstance().getReference();
                                    user = new User(userName, user_type, mapp.getLlocation());

                                    mDatabase.child("User").child(mAuth.getCurrentUser().getUid().toString()).setValue(user);
                                    Toast.makeText(createAccount.this, "Authentication Successful.",
                                            Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(createAccount.this, LoginActivity.class);
                                    startActivity(i);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.d(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(createAccount.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });

                // ...


            }
        });

    }
}
