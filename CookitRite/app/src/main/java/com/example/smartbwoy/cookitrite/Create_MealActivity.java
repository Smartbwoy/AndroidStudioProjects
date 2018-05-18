package com.example.smartbwoy.cookitrite;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

//Class to create a Meal
public class Create_MealActivity extends AppCompatActivity {
    private List hr,min,sec;
    private ImageView imageView;//Image request code
    private int PICK_IMAGE_REQUEST = 1;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*Button btnCapturePicture = (Button) findViewById(R.id.btnCapturePicture);
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
        measurement.setAdapter(measure_type);*/


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final LinearLayout rootView = (LinearLayout) findViewById(R.id.ingr_list);
        final LinearLayout rootView1=(LinearLayout) findViewById(R.id.method_list);

        final int[] idNumber = {0};
        final int[] method_counter={0};
        findViewById(R.id.add_ingr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myEditText.setLayoutParams(new LinearLayoutCompat.LayoutParams(MATCH_PARENT,WRAP_CONTENT));

                final EditText myEditText = new EditText(rootView.getContext());
                idNumber[0]=idNumber[0]+1;
                String idName="Ingr"+ idNumber[0];

                myEditText.setHint("1 Pound Flour");
                myEditText.setTag(idName);
                myEditText.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_clear_black_24dp,0);
                myEditText.setBackgroundResource(R.drawable.shape2borders);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,7, 0, 5);
                myEditText.setLayoutParams(lp);
                rootView.addView(myEditText);
                myEditText.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        //final int DRAWABLE_LEFT = 0;
                        //final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        //final int DRAWABLE_BOTTOM = 3;

                        if(event.getAction() == MotionEvent.ACTION_UP) {
                            if(event.getRawX() >= (myEditText.getRight() - myEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                rootView.removeView(myEditText);

                                return true;
                            }
                        }
                        return false;
                    }
                });

            }
        });

findViewById(R.id.add_method).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final EditText myEditText = new EditText(rootView1.getContext());
        //final LinearLayout hlayout=new LinearLayout(rootView1.getContext());
        //final TextView numbbr=new TextView(rootView1.getContext());
        method_counter[0]=method_counter[0]+1;
        //numbbr.setText(method_counter[0]);

        String idName="method"+ method_counter[0];

        myEditText.setHint(idName);
        myEditText.setTag(idName);
        myEditText.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_clear_black_24dp,0);
        myEditText.setBackgroundResource(R.drawable.shape2borders);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,7, 0, 5);
        myEditText.setLayoutParams(lp);
        //rootView1.addView(numbbr);
        rootView1.addView(myEditText);
       myEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //final int DRAWABLE_LEFT = 0;
                //final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                //final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (myEditText.getRight() - myEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        //rootView1.removeView(numbbr);
                        rootView1.removeView(myEditText);

                        return true;
                    }
                }
                return false;
            }
        });
    }
});


    }

}
