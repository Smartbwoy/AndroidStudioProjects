package theserviceapp.advancemoms.com.theserviceapp10;

/**
 * Created by Smartbwoy on 1/2/2018.
 */

import android.Manifest;
        import android.content.pm.PackageManager;
        import android.location.Location;
        import android.os.Bundle;

        import com.google.android.gms.maps.CameraUpdate;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.widget.Toast;
        import com.google.android.gms.location.FusedLocationProviderClient;
        import com.google.android.gms.location.LocationServices;

        import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;

        import java.util.Set;

/*
 * Created by alway on 1/31/2018.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        Log.d(TAG, "OnMapReady: map is reeady here");
        if(mLocationPermissionGranted){

            getDeviceLocation();
        }
    }

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String TAG = "MapActivity";
    private static final float DEFAULT_ZOOM = 15f;
    //var
    private Boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mfusedLocationProviderClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getLocationPermission();
    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting device current location");
        mfusedLocationProviderClient =  LocationServices.getFusedLocationProviderClient(this);
        Log.d(TAG, "before try");
        try{
            Log.d(TAG, "try error");
            if(mLocationPermissionGranted){
                Log.d(TAG, "if work");
                Task location = mfusedLocationProviderClient.getLastLocation();

                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: Found location");
                            Location currentLocation = (Location)task.getResult();
                            Log.d(TAG , "OnComplete: Latitube dir " + currentLocation.toString() );
                            LatLng ltlg = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            Log.d(TAG,ltlg.toString());
                            //moveCamera(ltlg,15F);
                        }
                        else{
                            Log.d(TAG, "OnComplete: current location not found");
                            Toast.makeText(MapActivity.this, "unable to get current location",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch(SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityExecution: " + e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, Float zoom){
        //Log.d(TAG, "moveCamera: moving camera to lat: " + latLng.latitude + ", lng " + latLng.longitude);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        //Log.d(TAG, "MoveCamera: moving");
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocation: getting location");
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted = true;
                initMap();
            }
        }
        else{
            ActivityCompat.requestPermissions(this,
                    permission,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestLocation: getting request");
        mLocationPermissionGranted = false;
        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if(grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    //initialize on map
                    initMap();
                }
            }
        }
    }


}

