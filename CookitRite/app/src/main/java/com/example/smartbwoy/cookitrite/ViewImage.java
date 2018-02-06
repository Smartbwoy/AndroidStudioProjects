package com.example.smartbwoy.cookitrite;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * Created by Smartbwoy on 3/2/2018.
 */

public class ViewImage extends Activity {
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilephotofullsize);
        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        final View profilePhoto= header.findViewById(R.id.profilephoto);
        profilePhoto.getContext();*/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (480 ), (int) (480));
        Toast.makeText(this,width+" ",Toast.LENGTH_LONG).show();
    }
}

