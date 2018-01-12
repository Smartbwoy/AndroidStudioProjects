package com.example.smartbwoy.cookitrite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Create_MealActivity extends AppCompatActivity {
    private List hr,min,sec;
    private ImageView imageView;//Image request code
    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btnCapturePicture = (Button) findViewById(R.id.btnCapturePicture);
        hr = new ArrayList<Integer>();
        min = new ArrayList<Integer>();
        sec = new ArrayList<Integer>();

        for (int i = 0; i <=60; i++) {
            min.add(Integer.toString(i));
            sec.add(Integer.toString(i));
        }
        for (int i = 0; i <=12; i++) {
            hr.add(Integer.toString(i));
        }
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, hr);
        ArrayAdapter<Integer> spinnerArrayAdapter1 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, min);
        ArrayAdapter<Integer> spinnerArrayAdapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, sec);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerArrayAdapter1.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerArrayAdapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        Spinner spinner = (Spinner)findViewById(R.id.hr);
        Spinner spinner1 = (Spinner)findViewById(R.id.min);
        Spinner spinner2 = (Spinner)findViewById(R.id.sec);

        spinner.setAdapter(spinnerArrayAdapter);
        spinner1.setAdapter(spinnerArrayAdapter1);
        spinner2.setAdapter(spinnerArrayAdapter2);


        Spinner mealcategory = (Spinner) findViewById(R.id.recipe_category);
        Spinner measurement=(Spinner)findViewById(R.id.measurements);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Cateogory_type, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> measure_type=ArrayAdapter.createFromResource(this,
                R.array.Measure_type,android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measure_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mealcategory.setAdapter(adapter);
        measurement.setAdapter(measure_type);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
