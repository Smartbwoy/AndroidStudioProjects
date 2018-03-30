package com.advancemoms.theserviceapp;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Belal on 10/18/2017.
 */


public class DisplayUsersAdapter extends RecyclerView.Adapter<DisplayUsersAdapter.DisplayViewHolder> {

    private static final String TAG = "DisplayUsersAdapter";

    //this context we will use to inflate the layout
    private Context mCtx;
    String sss;

    //we are storing all the products in a list
    private List<Worker> usersList;

    //getting the context and product list with constructor
    public DisplayUsersAdapter(Context mCtx, List<Worker> userstList) {
        this.mCtx = mCtx;
        this.usersList = userstList;
    }

    @Override
    public DisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.worker_layout_view, null);
        return new DisplayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DisplayViewHolder holder, int position) {
        Worker worker = usersList.get(position);
        Log.d(TAG, "onBindViewHolder: worker ratings  = " + worker.getRatings());

        Picasso.get().load(worker.getImgUrl()).into(holder.workerImg);
        holder.workersName.setText(worker.getUname());
        sss = getAddress(mCtx, worker.getLlocation().getLatitude(), worker.getLlocation().getLatitude());
        holder.workerLocation.setText(sss);
        holder.workerContact.setText(worker.getTelephone());
        holder.workersRatings.setRating(worker.getRatings());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }


    class DisplayViewHolder extends RecyclerView.ViewHolder {
        ImageView workerImg;
        TextView workersName, workerContact, workerLocation ;
        RatingBar workersRatings;

        public DisplayViewHolder(View itemView) {
            super(itemView);
            workerImg = (ImageView) itemView.findViewById(R.id.worker_search_image);
            workersName = (TextView) itemView.findViewById(R.id.worker_search_username);
            workerContact = (TextView) itemView.findViewById(R.id.worker_search_contact);
            workerLocation = (TextView) itemView.findViewById(R.id.worker_search_location);
            workersRatings = (RatingBar) itemView.findViewById(R.id.worker_search_ratingBar);

        }
    }



    public static String getAddress(Context context, double LATITUDE, double LONGITUDE) {
        String addr = "no address found";
        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {



                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                Log.d(TAG, "getAddress:  address" + address);
                Log.d(TAG, "getAddress:  city" + city);
                Log.d(TAG, "getAddress:  state" + state);
                Log.d(TAG, "getAddress:  postalCode" + postalCode);
                Log.d(TAG, "getAddress:  knownName" + knownName);


                addr = address + " / " + city + " " + country;


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addr;
    }
}