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

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

        import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String[] boxName = {"Gardener", "Mechanic", "Plumber", "Worker 4", "Worker 5", "Worker 6", "Worker 7", "Worker 8" , "Worker 1"};
    static int wNumb;

    private ArrayAdapter adapter;
    String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        Log.d(TAG, "check user");
        if(currentUser==null){
            Log.d(TAG, "no user");
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);

        }else{
            Intent intent = new Intent(MainActivity.this, constructingDatabase.class);
            startActivity(intent);


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
                home_button(0);
            }
        });

        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_button(1);
            }
        });

        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_button(2);
            }
        });

        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_button(3);
            }
        });

        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_button(4);
            }
        });

        box6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_button(5);
            }
        });

        box7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_button(6);
            }
        });

        box8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_button(7);
            }
        });

        box9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_button(8);
            }
        });

    }

    private void home_button(int boxNum){
        setNumb(boxNum);
        Toast.makeText(MainActivity.this, "box number" + wNumb, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, display_workers_name.class);
        startActivity(intent);
    }

    private  void setNumb(int x){
        wNumb = x;
    }

    static int getWorkerNum (){
       return wNumb;
    }

}
