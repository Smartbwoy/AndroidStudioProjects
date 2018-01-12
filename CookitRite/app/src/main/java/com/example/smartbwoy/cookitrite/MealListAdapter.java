package com.example.smartbwoy.cookitrite;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smartbwoy on 11/3/2017.
 */

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.ViewHolder>{
    ArrayList<String[]> recipes;
    public MealListAdapter(ArrayList<String[]> recipes) {

        this.recipes=recipes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        TextView creator;

        ViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView)itemView.findViewById(R.id.meal_name);
            creator = (TextView)itemView.findViewById(R.id.creator);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewmeal,parent,false));
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewmeal,parent,false);
        MealListAdapter.ViewHolder vh = new MealListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.recipeName.setText(recipes.get(position)[0]);
        holder.creator.setText(recipes.get(position)[1]);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


}
