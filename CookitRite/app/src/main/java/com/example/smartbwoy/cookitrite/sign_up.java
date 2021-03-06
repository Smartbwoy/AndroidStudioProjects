package com.example.smartbwoy.cookitrite;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbwoy.cookitrite.Entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import net.rimoto.intlphoneinput.IntlPhoneInput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity {
    private FirebaseAuth userAuth, verifyuser;
    private FirebaseAuth.AuthStateListener firebaseListener;
    private EditText user_name, email, password, passwordConfirm;
    private IntlPhoneInput phoneNumber;
    private TextView createErrorMessage;

    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final boolean[] userNamError = new boolean[1];
        final boolean[] pwdvalid = new boolean[1];
        createErrorMessage = (TextView) findViewById(R.id.createErrorMessage);
        user_name = (EditText) findViewById(R.id.user_name);
        email = (EditText) findViewById(R.id.user_email);
        //phoneNumber = (IntlPhoneInput) findViewById(R.id.user_phonenumber);
        password = (EditText) findViewById(R.id.user_password);
        //passwordConfirm = (EditText) findViewById(R.id.user_password_confirm);
        createAccount = (Button) findViewById(R.id.createAccount);
        userAuth = FirebaseAuth.getInstance();
        firebaseListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(sign_up.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        user_name.addTextChangedListener(new TextWatcher() {
            DatabaseReference usersRef;
            TextView userNameError=(TextView) findViewById(R.id.userNameError);
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                usersRef = FirebaseDatabase.getInstance().getReference("Users");
                usersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                String foundUser = (String) user.child("username").getValue();
                               // Toast.makeText(sign_up.this,foundUser,Toast.LENGTH_LONG).show();
                                if(foundUser.equals(charSequence.toString().toLowerCase())){
                                    userNameError.setText("Username Already Exist");
                                    userNameError.setVisibility(View.VISIBLE);
                                    userNamError[0] =true;
                                    break;
                                }else{
                                    userNameError.setVisibility(View.INVISIBLE);
                                    userNamError[0]=false;
                                }
                            }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                //usersRef.orderByChild("userName").equalTo()
                //Query searchQuery=usersRef.orderByChild("userName").startAt(charSequence.toString()).endAt(charSequence.toString()+"\uf8ff");
                //Firebase<User> options = new FirebaseRecyclerOptions.Builder<User>().setQuery(searchQuery, User.class).build();
               // System.out.println(options.getOwner());

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            TextView pwdError= (TextView) findViewById(R.id.passwordError);
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 8) {
                    pwdError.setText("Use 8 or more Characters");
                    pwdError.setVisibility(View.VISIBLE);
                    pwdvalid[0] = false;
                } else if (!charSequence.toString().matches(".*\\d.*")) {
                    pwdError.setText("Use a number (e.g. 1234)");
                    pwdError.setVisibility(View.VISIBLE);
                    pwdvalid[0] = false;
                } else if (!charSequence.toString().matches(".*[A-Z].*") || !charSequence.toString().matches(".*[a-z].*")) {
                    pwdError.setText("Use Uppper and Lower case Letters (e.g. Aa)");
                    pwdError.setVisibility(View.VISIBLE);
                    pwdvalid[0] = false;
                } else if (!charSequence.toString().matches(".*[^a-zA-Z0-9].*")) {
                    pwdError.setText("Use a symbol (e.g. !@#$))");
                    pwdError.setVisibility(View.VISIBLE);
                    pwdvalid[0] = false;

                } else {
                    pwdvalid[0] = true;
                    pwdError.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(sign_up.this);
                AlertDialog dialog;
                final String username = user_name.getText().toString();
                String useremail = email.getText().toString();
                //String userphoneNo = phoneNumber.getNumber();
                String userpassword = password.getText().toString();
                //String userpasswordconfirm = passwordConfirm.getText().toString();

                String emailExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                //String phoneNoExpression = "^[+]?[0-9]{10,13}$";
                Matcher emailMatcher = Pattern.compile(emailExpression, Pattern.CASE_INSENSITIVE).matcher(useremail);
                //Matcher phoneNoMatcher = Pattern.compile(phoneNoExpression, Pattern.CASE_INSENSITIVE).matcher(userphoneNo);
                if (username.isEmpty()) {
                    builder.setMessage("Enter username").setTitle("ERROR");
                    dialog = builder.create();
                    dialog.show();

                }else if(userNamError[0]){
                    builder.setMessage("Username already taken").setTitle("ERROR");
                    dialog = builder.create();
                    dialog.show();
                }
                else if (useremail.isEmpty()) {
                    builder.setMessage("Enter Email Address").setTitle("ERROR");
                    dialog = builder.create();
                    dialog.show();

                }
                else if(emailExist(useremail)==1){
                    builder.setMessage("Email already as an Account").setTitle("Error");
                    dialog = builder.create();
                    dialog.show();


                }else if (!emailMatcher.matches()) {
                    builder.setMessage("Ensure email is in the format johndoe@email.com").setTitle(" Error");
                    dialog = builder.create();
                    dialog.show();

                }/*else if (userphoneNo.isEmpty()) {
                    builder.setMessage("Enter Phoone Number").setTitle("ERROR");
                    dialog = builder.create();
                    dialog.show();

                }else if (!phoneNumber.isValid()) {
                    builder.setMessage("Re-enter phone number in the correct format").setTitle("ERROR");
                    dialog = builder.create();
                    dialog.show();

                }*/ else if (userpassword.isEmpty()) {
                    builder.setMessage("Create Password").setTitle("ERROR");
                    dialog = builder.create();
                    dialog.show();
                } else if (userpassword.toLowerCase().equals(username.toLowerCase())) {
                    builder.setMessage("Password Can't be the same as Username ").setTitle("ERROR");
                    dialog = builder.create();
                    dialog.show();
                } else if (!pwdvalid[0]) {
                    builder.setMessage("*Use 8 or more Characters\n" +
                            "*Use a number (e.g. 1234)\n" +
                            "*Use upper and lower case letters (e.g. Aa)\n" +
                            "*Use a symbol (e.g. !@#$)").setTitle("PASSWORD ERROR");
                    dialog = builder.create();
                    dialog.show();

                }
                else {

                    final User newUser = new User(userpassword,useremail,username);
                    userAuth.createUserWithEmailAndPassword(newUser.getEmail(), newUser.getPassword())
                            .addOnCompleteListener(sign_up.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "User Not Created", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getBaseContext(), "User Created", Toast.LENGTH_LONG).show();
                                //FirebaseUser user = userAuth.getCurrentUser();
                                //UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                                //user.updateProfile(profileUpdates);
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                final String userID = userAuth.getCurrentUser().getUid();
                                mDatabase.child("Users").child(userID).setValue(newUser);



                            }
                        }
                    });
                }


            }
        });


// to set text color using RGB code


        Spinner spin_country = (Spinner) findViewById(R.id.country_list);
        Spinner spin_ques1 = (Spinner) findViewById(R.id.question1);
        Spinner spin_ques2 = (Spinner) findViewById(R.id.question2);
        Spinner spin_ques3 = (Spinner) findViewById(R.id.question3);

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
    protected int emailExist(String email){
        userAuth=FirebaseAuth.getInstance();
        final int[] result = new int[1];
        userAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
            @Override
            public void onComplete(Task<ProviderQueryResult> task) {
                if(!task.getResult().getProviders().isEmpty()){
                    result[0] =1;
                }
                else{
                    result[0]=0;
                }
            }
        });
        return result[0];
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

    public void run_personal_info(View v) {
        View step1 = findViewById(R.id.setion1);
        step1.setVisibility(View.INVISIBLE);
        View step2 = findViewById(R.id.setion2);
        step2.setVisibility(View.VISIBLE);
    }

    public void run_security(View v2) {
        View step2 = findViewById(R.id.setion2);
        step2.setVisibility(View.INVISIBLE);
        View step3 = findViewById(R.id.setion3);
        step3.setVisibility(View.VISIBLE);

    }

    public void run_back_personal_info(View v) {
        View step3 = findViewById(R.id.setion3);
        step3.setVisibility(View.INVISIBLE);
        View step2 = findViewById(R.id.setion2);
        step2.setVisibility(View.VISIBLE);
    }

    public void run_meal_pref(View view) {
        View step3 = findViewById(R.id.setion3);
        step3.setVisibility(View.INVISIBLE);
        View step4 = findViewById(R.id.setion4);
        step4.setVisibility(View.VISIBLE);

    }

    public void run_back_security(View view) {
        View step4 = findViewById(R.id.setion4);
        step4.setVisibility(View.INVISIBLE);
        View step3 = findViewById(R.id.setion3);
        step3.setVisibility(View.VISIBLE);

    }

}

