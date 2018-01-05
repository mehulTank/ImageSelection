package com.ab.mehul.imageselectionnew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.mehul.mylibrary.ImageSelection;
import com.ab.mehul.mylibrary.interfaceImageResult.ImageResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class MainActivity extends AppCompatActivity implements ImageResult {

    TextView textView;
    ImageSelection imageSelection;
    ImageView imgDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.tv1);

        imgDisplay = (ImageView) findViewById(R.id.imgDisplay);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


    }


    public void selectImage() {

        imageSelection = new ImageSelection(MainActivity.this);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {


                if (items[item].equals("Take Photo")) {
                    boolean isComprase = true;
                    imageSelection.openCamera(isComprase);

                } else if (items[item].equals("Choose from Library")) {
                    boolean isComprase = true;
                    imageSelection.openGallery(isComprase);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("---- on activity result", "---- on activity result");

        imageSelection.imageProcessing(requestCode, resultCode, data);


    }


    @Override
    public void imageUri(Uri uri) {

        Log.d("---image uri", "" + uri);

        Glide.with(MainActivity.this).load(uri).asBitmap().centerCrop().into(new BitmapImageViewTarget(imgDisplay) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgDisplay.setImageDrawable(circularBitmapDrawable);
            }
        });


    }
}
