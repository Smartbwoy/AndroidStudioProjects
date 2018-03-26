package com.advancemoms.popdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by alway on 3/22/2018.
 */

public class CreateAccount extends AppCompatActivity {
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

/*
        final Button createbtn=(Button)findViewById(R.id.sign_up_button);
        createbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String email=((EditText)findViewById(R.id.emailAddress)).getText().toString();
                String password=((EditText)findViewById(R.id.userpassword)).getText().toString();
                final String userName=((EditText)findViewById(R.id.username)).getText().toString();
                mAuth=FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");

                                    mDatabase = FirebaseDatabase.getInstance().getReference();
                                    User user = new User(userName, mAuth.getCurrentUser().getEmail().toString());

                                    mDatabase.child("User").child(mAuth.getCurrentUser().getUid().toString()).setValue(user);
                                    Toast.makeText(CreateAccount.this, "Authentication Successful.",
                                            Toast.LENGTH_LONG).show();
                                    //Intent i = new Intent(CreateAccount.this, LoginActivity.class);
                                    //startActivity(i);
                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.d(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(CreateAccount.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });

                // ...


            }
        });*/

    }
}
