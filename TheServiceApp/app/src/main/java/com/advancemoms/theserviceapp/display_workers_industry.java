package com.advancemoms.theserviceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.advancemoms.theserviceapp.userHomeScreen.wCat;

/**
 * Created by alway on 3/22/2018.
 */

public class display_workers_industry extends AppCompatActivity {

    RecyclerView recyclerView;
    DisplayUsersAdapter adapter;


    //Declaring database variables
    DatabaseReference myRef;
    FirebaseDatabase database;

    // Global Variables
    String WORKER_DATABASE;

    Worker worker;

    String[] workers = {"Romain", "small", "alwayne", "bob", "wayne", "kim", "marvin", "steph", "alisa", "monique", "migs"};

    String[] contact = {"(876)785-3923", "(876)565-7783", "(876)894-3498", "(876)565-3433", "(876)565-3555", "(876)558-3493",
            "(876)908-2433", "(876)455-1233", "(876)545-3784", "(876)987-5633", "(876)595-783"};

    String[] location = {"20 Paula avenue kingston 11", "9 zinnia avenue kingston 11", "9 zinnia avenue st andrew",
            "23 walkers lane St james", "9 zinnia drive kingston 02", "9 bob lane kingston 11", "9 zinnia avenue kingston 11",
            "123 park road portlant", "kristal blv kingston 11", "9 zinnia avenue kingston 11", "34 owen road, kngs 12"};

    int[] rating = {2,4,5,3,2,4,4,1,3, 3,1};
    int[] IMAGES = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3, R.drawable.profile4,
            R.drawable.profile5, R.drawable.profile6, R.drawable.profile7, R.drawable.profile8,
            R.drawable.profile9, R.drawable.profile10, R.drawable.profile11};

    private static final String TAG = "display_workers_name";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.setTitle(wCat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_workers_by_industry);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        createrWorkerUser();
    }



    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.worker_layout_view, null);

            ImageView workerImg = (ImageView) view.findViewById(R.id.worker_search_image);
            TextView workersName = (TextView) view.findViewById(R.id.worker_search_username);
            TextView workerContact = (TextView) view.findViewById(R.id.worker_search_contact);
            TextView workerLocation = (TextView) view.findViewById(R.id.worker_search_location);
            RatingBar workersRatings = (RatingBar) view.findViewById(R.id.worker_search_ratingBar);


            workerImg.setImageResource(IMAGES[i]);
            workersName.setText(workers[i]);
            workerLocation.setText(location[i]);
            workerContact.setText(contact[i]);
            workersRatings.setRating(rating[i]);
            return view;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            //startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void createrWorkerUser() {
        // Read from the database

        myRef = database.getReference("User");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<User> data_list = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String wType = (String)postSnapshot.child("Type").getValue();
                    if (wType.equalsIgnoreCase("work")) {
                        data_list.add(new User((String)postSnapshot.child("uname").getValue(),
                                "", (String) postSnapshot.child("Type").getValue(),
                                    (String) postSnapshot.child("Location").getValue(),
                                (String) postSnapshot.child("Telephone").getValue(),
                                (String) postSnapshot.child("ImgUrl").getValue(),
                                Integer.parseInt("33"), postSnapshot.getKey().toString()));
                    }
                }
                getData(data_list);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


    //getting data for database
    public void getData(final ArrayList<User> uUser){

        // Read from the database


        myRef = database.getReference("Worker");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<Worker> data_list = new ArrayList<>();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String industry = (String)postSnapshot.child("Industry").getValue() ;
                    User user_in_list = (User)FindUser(postSnapshot.getKey().toString(), uUser);
                    if(user_in_list.getUname().equalsIgnoreCase("anonymous")){
                        //nothing happens
                    }
                    else{
                        if(industry.trim().equalsIgnoreCase(wCat.trim())) {
                            Log.d(TAG, "onDataChange: found someone");
                            int experience = Integer.parseInt((String)postSnapshot.child("Experience").getValue());
                            String qualification = (String) postSnapshot.child("Qualification").getValue();
                            String status = (String) postSnapshot.child("Status").toString();
                            int rating = Integer.parseInt((String) postSnapshot.child("Ratings").getValue());

                            data_list.add( new Worker(user_in_list, experience, qualification, rating,  status, industry));
                        }
                    }
                }
                //ArrayList<String> urlimg = new ArrayList<>();
               // for(int i=0; i< data_list.size(); i++) {
                //    urlimg.add( data_list.get(i).getImgUrl());
                //}
                //DisplayServiceAdapter adapter = new DisplayServiceAdapter(display_workers_industry.this,R.layout.layout_grid_imageview, "", data_list);


                recyclerView = (RecyclerView) findViewById(R.id.display_workers_recycler_view);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(display_workers_industry.this));

                adapter = new DisplayUsersAdapter(display_workers_industry.this, data_list);
                recyclerView.setAdapter(adapter);
                //ustomAdapter customAdapter = new CustomAdapter();
                //DisplayServiceAdapter adapter = new DisplayServiceAdapter(display_workers_industry.this,R.layout.layout_grid_imageview,  workersView, data_list);
                //workersView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public User FindUser(String key, ArrayList<User> uuser){
        for(User u :uuser){
            if (u.getUID().equalsIgnoreCase(key)) {
                return u;
            }
        }
        return new User();
    }
}