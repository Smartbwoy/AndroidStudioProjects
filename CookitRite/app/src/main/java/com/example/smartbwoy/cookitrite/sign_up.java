package com.example.smartbwoy.cookitrite;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class sign_up extends AppCompatActivity {
    /*View step1= (View) findViewById(R.id.setion1);
    View step2= (View) findViewById(R.id.setion2);
    View step3= (View) findViewById(R.id.setion3);
    View step4= (View) findViewById(R.id.setion4);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText et = (EditText) findViewById(R.id.input_name);
// to set text color using RGB code
        et.setTextColor(Color.parseColor("#00ff00"));

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

