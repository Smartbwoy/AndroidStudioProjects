package com.example.smartbwoy.cookitrite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class ListedRecipes extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listed_recipes);
        rv = (RecyclerView)findViewById(R.id.rv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //getSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //rv.setHasFixedSize(true);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        ArrayList<String[]> recipes= new ArrayList<>();
        recipes.add(new String[] {"ACKEE", "Miguel"});
        recipes.add(new String[] {"Mango", "Miguel"});
        recipes.add(new String[] {"Apple", "Miguel"});
        recipes.add(new String[] {"Rice and Peas with fry chicken and green beans", "Miguel"});
        recipes.add(new String[] {"meal", "Miguel"});
        recipes.add(new String[] {"ACKEE", "Miguel"});

        rv.setAdapter( new MealListAdapter(recipes));


    }
}
