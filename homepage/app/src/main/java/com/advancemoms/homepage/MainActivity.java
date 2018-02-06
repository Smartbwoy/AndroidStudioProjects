package com.advancemoms.homepage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    //Hompage buttons
    ImageButton home_btn1;
    ImageButton home_btn2;
    ImageButton home_btn3;
    ImageButton home_btn4;
    ImageButton home_btn5;
    ImageButton home_btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String TAG = "MainActivity";
        home_btn1 = (ImageButton) findViewById(R.id.home_button_1);
        home_btn2 = (ImageButton) findViewById(R.id.home_button_2);
        home_btn3 = (ImageButton) findViewById(R.id.home_button_3);
        home_btn4 = (ImageButton) findViewById(R.id.home_button_4);
        home_btn5 = (ImageButton) findViewById(R.id.home_button_5);
        home_btn6 = (ImageButton) findViewById(R.id.home_button_6);
        Toast.makeText(this,"Button 1", Toast.LENGTH_SHORT).show();
    }

    private void loadingButtons (){
        home_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_on_screen("home_btn1");
            }
        });

        home_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_on_screen("home_btn2");
            }
        });

        home_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_on_screen("home_btn3");
            }
        });
        home_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_on_screen("home_btn4");
            }
        }); home_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_on_screen("home_btn5");
            }
        });

        home_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_on_screen("home_btn6");

            }
        });

    }




    private void display_on_screen(String ss){
        Toast.makeText(this,"Button 1", Toast.LENGTH_SHORT).show();
    }

}
