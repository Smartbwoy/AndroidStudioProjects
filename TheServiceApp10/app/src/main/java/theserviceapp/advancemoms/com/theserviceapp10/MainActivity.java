package theserviceapp.advancemoms.com.theserviceapp10;

import android.app.Dialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIOLOG_REQUEST = 9001; @Override


    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Main activity: start here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServiceOk()){
            Log.d(TAG, "Service is ok");
            init();
        }
    }

    private void init(){
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
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
}
