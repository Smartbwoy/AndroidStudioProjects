package com.advancemoms.servicehomepage;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declearing service buttons
        ImageButton box1 = (ImageButton) findViewById(R.id.home_box_1);
        ImageButton box2 = (ImageButton) findViewById(R.id.home_box_2);
        ImageButton box3 = (ImageButton) findViewById(R.id.home_box_3);
        ImageButton box4 = (ImageButton) findViewById(R.id.home_box_4);
        ImageButton box5 = (ImageButton) findViewById(R.id.home_box_5);
        ImageButton box6 = (ImageButton) findViewById(R.id.home_box_6);
        ImageButton box7 = (ImageButton) findViewById(R.id.home_box_7);
        ImageButton box8 = (ImageButton) findViewById(R.id.home_box_8);
        ImageButton box9 = (ImageButton) findViewById(R.id.home_box_9);

        //Declaring search variables
        EditText searchWorker = (EditText) findViewById(R.id.SearchEditView);
        ListView list = (ListView) findViewById(R.id.workers_listView);

        final ArrayAdapter<String> WNames;   //names oh all the workers
        ArrayList<String> wNames = new ArrayList<>();  //names of all the jobs

        for(int i=1; i<=9; i++){
            wNames.add("Worker " + i);
            Log.d(TAG, "print workers names");
        }

        adapter = new ArrayAdapter(MainActivity.this, R.layout.workers_layout, wNames);

       //WNames = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, wNames);
         //list.setAdapter(adapter);
         Log.d(TAG, "List added to list View");
        searchWorker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "At onchange");
                (MainActivity.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //On click listener for all the buttons

        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BOX 1", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, display_workers_name.class);
                startActivity(intent);
            }
        });

        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BOX 2", Toast.LENGTH_SHORT).show();
            }
        });

        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BOX 3", Toast.LENGTH_SHORT).show();
            }
        });

        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BOX 4", Toast.LENGTH_SHORT).show();
            }
        });

        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BOX 5", Toast.LENGTH_SHORT).show();
            }
        });

        box6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BOX 6", Toast.LENGTH_SHORT).show();
            }
        });

        box7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BOX 7", Toast.LENGTH_SHORT).show();
            }
        });

        box8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BOX 8", Toast.LENGTH_SHORT).show();
            }
        });

        box9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BOX 9", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
