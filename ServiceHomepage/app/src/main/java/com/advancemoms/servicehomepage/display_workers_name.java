package com.advancemoms.servicehomepage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alway on 2/25/2018.
 */

public class display_workers_name extends AppCompatActivity {
    String[] workers = {"Romain", "small", "alwayne", "bob", "wayne", "kim", "marvin", "steph", "alisa", "monique", "migs"};

    String[] contact = {"(876)785-3923", "(876)565-7783", "(876)894-3498", "(876)565-3433", "(876)565-3555", "(876)558-3493",
                      "(876)908-2433", "(876)455-1233", "(876)545-3784", "(876)987-5633", "(876)595-783"};

    String[] location = {"20 Paula avenue kingston 11", "9 zinnia avenue kingston 11", "9 zinnia avenue st andrew",
                         "23 walkers lane St james", "9 zinnia drive kingston 02", "9 bob lane kingston 11", "9 zinnia avenue kingston 11",
                         "123 park road portlant", "kristal blv kingston 11", "9 zinnia avenue kingston 11", "34 owen road, kngs 12"};

    int[] rating = {2,4,6,3,7,4,9,1,3,6};
    int[] IMAGES = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3, R.drawable.profile4,
            R.drawable.profile5, R.drawable.profile6, R.drawable.profile7, R.drawable.profile8,
            R.drawable.profile9, R.drawable.profile10, R.drawable.profile11};

    private static final String TAG = "display_workers_name";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_workers_by_service);
        Log.d(TAG, "REACH");
        Toast.makeText(display_workers_name.this,"Workers", Toast.LENGTH_LONG).show();
        ListView workersView = (ListView) findViewById(R.id.display_workers_list_view);

        CustomAdapter customAdapter = new CustomAdapter();
        Log.d(TAG, "CustomAdapter");
        workersView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter{

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
            view = getLayoutInflater().inflate(R.layout.workerlayoutview, null);

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
}