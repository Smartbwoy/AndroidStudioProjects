package com.advancemoms.theserviceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by alway on 3/22/2018.
 */

public class userHomeScreen extends AppCompatActivity {

    static String wCat;  //Selected category
    Upload upload;

    //Declaring database variables
    DatabaseReference myRef;
    FirebaseDatabase database;

    public interface OnGridImageSelectedListener{
        void onGridImageSelected(Upload upload);
    }
    OnGridImageSelectedListener mOnGridImageSelectedListener;

    private ArrayAdapter adapter;

    private static final String TAG = "userHomeScreen";
    databaseOperations dpop;

    ArrayList<String> workNames= new ArrayList<String>();

    private ListView listView;
    private SuggestionAdapter sugAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Industry");

        listView = (ListView) findViewById(R.id.workers_list);
        ArrayList<UserSample> workersList = new ArrayList<>();
        workersList.add(new UserSample("After Earth" , "2013"));
        workersList.add(new UserSample("Baby Driver" , "2017"));
        workersList.add(new UserSample("Deadpool" , "2016"));
        workersList.add(new UserSample("Divergent" , "2014"));

        sugAdapter = new SuggestionAdapter(this,workersList);
        //listView.setAdapter( sugAdapter);

        initImageLoader();         //Loagding grid images configeration
        setUpGrid();    //Loading images into grid

        //Declaring search variables
        EditText searchWorker = (EditText) findViewById(R.id.SearchEditView);
        AutoCompleteTextView findWorker=(AutoCompleteTextView) findViewById(R.id.findWorker);
        //ListView list = (ListView) findViewById(R.id.display_workers_list_view);

        final ArrayAdapter<String> WNames;   //names oh all the workers

        workNames.add("Peter");
        workNames.add("John");
        workNames.add("Steve");
        workNames.add("Lane");

        final ArrayAdapter<String> suggAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,workNames);
        //searchWorker.setAdapter(suggAdapter);
        //findWorker.setAdapter(sugAdapter);


        searchWorker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "At onchange");
                //sugAdapter.getFilter().filter(charSequence);
                //suggAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        findViewById(R.id.settingsPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(userHomeScreen.this, SettingsActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FirebaseAuth.getInstance().getCurrentUser()==null){
                    Toast.makeText(userHomeScreen.this, "No user Found", Toast.LENGTH_LONG).show();
                    //Log.d(TAG, "onClick: No user Found");
                }else {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(userHomeScreen.this, "user logout ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(userHomeScreen.this, MainActivity.class);
                    startActivity(i);

                }
            }
        });

    }



    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(userHomeScreen.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }


    public void setUpGrid(){

        final GridView gridView = (GridView) findViewById(R.id.gridView);
        final ArrayList<Upload> wNames = new ArrayList<>();  //names of all the jobs

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String type = (String) postSnapshot.child("Type").getValue();
                    String urlImg = (String) postSnapshot.child("ImgUrl").getValue();
                    wNames.add(new Upload(type, urlImg));

                }
                int gridwidth = getResources().getDisplayMetrics().heightPixels;
                int imagewidth = gridwidth/3;
                gridView.setColumnWidth(imagewidth);

                ArrayList<String> urlimg = new ArrayList<>();
                for(int i=0; i<wNames.size(); i++) {
                    urlimg.add(wNames.get(i).getImageUrl());
                }
                GridImageAdapter adapter = new GridImageAdapter(userHomeScreen.this,R.layout.layout_grid_imageview, "", urlimg);
                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(userHomeScreen.this, "Services "+wNames.get(position).getmName(), Toast.LENGTH_LONG).show();
                        home_button(wNames.get(position).getmName());
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    //Selecting sevice indutry
    private void home_button(String boxNum){
        setNumb(boxNum);
        Intent intent = new Intent(userHomeScreen.this, display_workers_industry.class);
        startActivity(intent);
    }

    private  void setNumb(String x){
        wCat = x;
    }

    static String getWorkerNum (){
        return wCat;
    }

}

