package com.example.smartbwoy.cookitrite;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

/**
 * Created by Smartbwoy on 3/2/2018.
 */

public class ViewImage extends Activity {
    private FirebaseAuth userAuth=FirebaseAuth.getInstance();
    Button uploadphoto,saveImage;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;
    //Firebase
    FirebaseStorage storage=FirebaseStorage.getInstance();;
    StorageReference storageReference= storage.getReference();
    final StorageReference ref = storageReference.child("images/usersprofilephotoes/"+ userAuth.getCurrentUser().getUid());

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilephotofullsize);
        uploadphoto=(Button) findViewById(R.id.uploadphoto);
        saveImage=(Button) findViewById(R.id.savephoto);

        final ImageView imageView = (ImageView) findViewById(R.id.imageViewphoto);
        // Load the image using Glide

        Glide.with(this/* context */)
                .using(new FirebaseImageLoader())
                .load(ref)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .dontAnimate()
                .into(imageView);

       uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();


            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width/1.2), (int) (height/1.2));
        //Toast.makeText(this,width+" ",Toast.LENGTH_LONG).show();

    }
    @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        filePath = data.getData();
        ImageView imageView = (ImageView) findViewById(R.id.imageViewphoto);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            //imageView.refreshDrawableState();
            //imageView.setImageBitmap(bitmap);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void uploadImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            //final StorageReference ref = storageReference.child("images/usersprofilephotoes/"+ userAuth.getCurrentUser().getUid());

            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Glide.with(getApplicationContext() /* context */)
                                    .using(new FirebaseImageLoader())
                                    .load(ref)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .error(R.drawable.user_default)
                                    .dontAnimate()
                                    .into(ProfileActivity.profilePhoto);

                            Toast.makeText(ViewImage.this, " Image Uploaded", Toast.LENGTH_SHORT).show();


                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            progressDialog.dismiss();
                            Toast.makeText(ViewImage.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });

        }

    }


}

