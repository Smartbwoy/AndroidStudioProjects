package theserviceapp.advancemoms.com.theserviceapp10;
/*
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIOLOG_REQUEST = 9001;


    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Main activity: start here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //if(isServiceOk()){
            Log.d(TAG, "Service is ok");
           // init();
        //}
    }
/*
    private void init(){
        //Switches and buttons from main xml
        Switch wswitch = (Switch) findViewById(R.id.worker_switch);
        Button btnMap = (Button) findViewById(R.id.btnMap);
        Button wReview = (Button) findViewById(R.id.read_review_btn);
        Button wStats = (Button) findViewById(R.id.Analysis_btn);
        Button wFinance = (Button) findViewById(R.id.Finances_btn);
        Button wJobs = (Button) findViewById(R.id.btn_job);
        TextView wName = (TextView) findViewById(R.id.user_namer_text_view);
        TextView wDetail = (TextView) findViewById(R.id.worker_details_text_view);

        //Var
        final String wmane ="Romain Small";
        String wdeatils ="Age: 23 \nExperience : 23 years \n Qualifications : Phd \nJobs Completed : 23";

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        wReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sswName("Review ");
            }
        });

        wStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sswName("Analysis");
            }
        });

        wFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sswName("Finances");
            }
        });

        wJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sswName("Jobs");
            }
        });

        wName.setText(wmane);
        wDetail.setText(wdeatils);

    }

    //Only for testing
    private void sswName(String ss)
    {
        Toast.makeText(this, ss, Toast.LENGTH_SHORT).show();
    }

    public boolean isServiceOk(){
        Log.d(TAG, "isServiceOk:Checking service");
        Log.d(TAG, "isServiceOk: checking google services version:");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS){
            //Everything is fine and user can make map request
            Log.d(TAG, "isServiceOk: Google Play service is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error accor but can be resolved
            Log.d(TAG, "isServiceOk: and error occur but can be resolved");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIOLOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this, "you can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
*/
}
