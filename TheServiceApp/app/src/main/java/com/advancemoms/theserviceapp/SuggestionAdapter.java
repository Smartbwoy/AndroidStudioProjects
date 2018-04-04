package com.advancemoms.theserviceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SuggestionAdapter extends ArrayAdapter<UserSample> {

    private Context mContext;
    private List<UserSample> workerList = new ArrayList<>();

    public SuggestionAdapter(Context context, ArrayList<UserSample> list) {
        super(context, 0 , list);
        mContext = context;
        workerList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.suggested_searchitem,parent,false);

        UserSample currentWorker = workerList.get(position);

        //ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
        //image.setImageResource(currentMovie.getmImageDrawable());

        TextView name = (TextView) listItem.findViewById(R.id.category);
        name.setText(currentWorker.getmCategory());

        TextView release = (TextView) listItem.findViewById(R.id.servicer);
        release.setText(currentWorker.getmName());

        return listItem;
    }
}
