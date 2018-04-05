package com.advancemoms.theserviceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class workers_details_activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workers_detail_layout);

        Intent intent = getIntent();
        Worker worker = (Worker) intent. getSerializableExtra("worker");

        Toast.makeText(this, "Display workerd deatils " + worker.getUname(), Toast.LENGTH_LONG).show();

    }
}
