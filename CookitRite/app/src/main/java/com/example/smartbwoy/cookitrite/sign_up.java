package com.example.smartbwoy.cookitrite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity {
    private FirebaseAuth userAuth;
    private FirebaseAuth.AuthStateListener firebaseListener;
    private EditText user_name;
    private EditText email;
    private EditText password;
    private EditText passwordConfirm;
    private TextView createErrorMessage;
    private FirebaseUser verifyuser;

    Button createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createErrorMessage=(TextView) findViewById(R.id.createErrorMessage);
        user_name  = (EditText) findViewById(R.id.user_name);
        email = (EditText) findViewById(R.id.user_email);
        password  = (EditText) findViewById(R.id.user_password);
        passwordConfirm  = (EditText) findViewById(R.id.user_password_confirm);
        createAccount = (Button) findViewById(R.id.createAccount);
        userAuth=FirebaseAuth.getInstance();
        firebaseListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null) {
                    Intent intent = new Intent(sign_up.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        verifyuser=userAuth.getCurrentUser();
        verifyuser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

       createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();
                String userpasswordconfirm = passwordConfirm.getText().toString();

                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(useremail);
                if (matcher.matches()) {

                    userAuth.createUserWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(sign_up.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "User Not Created", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getBaseContext(), "User Created", Toast.LENGTH_LONG).show();
                                String userID = userAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_dp = FirebaseDatabase.getInstance().getReference().child("User").child(userID);
                                String username = user_name.getText().toString();
                                Map newPost = new HashMap();
                                newPost.put("username", username);
                                DatabaseReference current_user_meal = FirebaseDatabase.getInstance().getReference().child("User").child(userID).child("Meals");
                                current_user_dp.setValue(newPost);


                            }
                        }
                    });
                }
                else{
                    createErrorMessage.setText("Ensure Email format is correct");
                    createErrorMessage.setVisibility(View.VISIBLE);

                }
            }
        });


// to set text color using RGB code


        Spinner spin_country = (Spinner)findViewById(R.id.country_list);
        Spinner spin_ques1=(Spinner)findViewById(R.id.question1);
        Spinner spin_ques2=(Spinner)findViewById(R.id.question2);
        Spinner spin_ques3=(Spinner)findViewById(R.id.question3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterques1 = ArrayAdapter.createFromResource(this,
                R.array.ques1, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterques2 = ArrayAdapter.createFromResource(this,
                R.array.ques2, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterques3 = ArrayAdapter.createFromResource(this,
                R.array.ques3, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_country.setAdapter(adapter);

        adapterques1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_ques1.setAdapter(adapterques1);
        adapterques2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_ques2.setAdapter(adapterques2);
        adapterques3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_ques3.setAdapter(adapterques3);

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
    public void run_personal_info(View v){
        View step1= (View) findViewById(R.id.setion1);
        step1.setVisibility(View.INVISIBLE);
        View step2= (View) findViewById(R.id.setion2);
        step2.setVisibility(View.VISIBLE);
    }
    public void  run_security(View v2){
        View step2= (View) findViewById(R.id.setion2);
        step2.setVisibility(View.INVISIBLE);
        View step3= (View) findViewById(R.id.setion3);
        step3.setVisibility(View.VISIBLE);

    }

    public void  run_back_personal_info(View v){
        View step3= (View) findViewById(R.id.setion3);
        step3.setVisibility(View.INVISIBLE);
        View step2= (View) findViewById(R.id.setion2);
        step2.setVisibility(View.VISIBLE);
    }
    public void run_meal_pref(View view){
        View step3= (View) findViewById(R.id.setion3);
        step3.setVisibility(View.INVISIBLE);
        View step4= (View) findViewById(R.id.setion4);
        step4.setVisibility(View.VISIBLE);

    }

    public void run_back_security(View view){
        View step4= (View) findViewById(R.id.setion4);
        step4.setVisibility(View.INVISIBLE);
        View step3= (View) findViewById(R.id.setion3);
        step3.setVisibility(View.VISIBLE);

    }

}

