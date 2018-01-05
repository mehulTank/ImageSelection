package com.ab.mehul.mylibrary;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.ab.mehul.mylibrary.imagecrop.CropHandler;
import com.ab.mehul.mylibrary.imagecrop.CropHelper;
import com.ab.mehul.mylibrary.imagecrop.CropParams;
import com.ab.mehul.mylibrary.interfaceImageResult.ImageResult;

public class ImageSelection extends AppCompatActivity implements CropHandler {


    private Activity activity;

    private CropParams mCropParams;

    private String imagePath = "";


    public ImageSelection(Activity applicationContext) {
        activity = applicationContext;

        if (Build.VERSION.SDK_INT < 23) {


            mCropParams = new CropParams(activity);

        } else {
            if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                mCropParams = new CropParams(activity);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1);

            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void openCamera(boolean isComprase) {


        if (Build.VERSION.SDK_INT < 23) {

            mCropParams.refreshUri();
            mCropParams.enable = false;
            mCropParams.compress = isComprase;

            Intent intent = CropHelper.buildCameraIntent(mCropParams, activity);

            ((Activity) (activity)).startActivityForResult(intent, CropHelper.REQUEST_CAMERA);


        } else {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


                mCropParams.refreshUri();
                mCropParams.enable = false;
                mCropParams.compress = isComprase;

                Intent intent = CropHelper.buildCameraIntent(mCropParams, activity);

                ((Activity) (activity)).startActivityForResult(intent, CropHelper.REQUEST_CAMERA);

            } else {

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1);

            }
        }


    }


    public void openGallery( boolean isComprase) {


        if (Build.VERSION.SDK_INT < 23) {


            mCropParams.refreshUri();
            mCropParams.enable = false;
            mCropParams.compress = isComprase;
            Intent intent = CropHelper.buildGalleryIntent(mCropParams, activity);
            ((Activity) (activity)).startActivityForResult(intent, CropHelper.REQUEST_CROP);


        } else {
            if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                mCropParams.refreshUri();
                mCropParams.enable = false;
                mCropParams.compress = isComprase;
                Intent intent = CropHelper.buildGalleryIntent(mCropParams, activity);
                ((Activity) (activity)).startActivityForResult(intent, CropHelper.REQUEST_CROP);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1);

            }
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CropHelper.REQUEST_CROP) {
            CropHelper.handleResult(this, requestCode, resultCode, data);
        } else if (requestCode == CropHelper.REQUEST_CAMERA) {
            CropHelper.handleResult(this, requestCode, resultCode, data);
        }

    }

    @Override
    public void onPhotoCropped(Uri uri) {
        if (!mCropParams.compress) {
            returnImageResult(uri);
        }
    }


    @Override
    public void onCompressed(Uri uri) {
        imagePath = uri.getPath();
        returnImageResult(uri);
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public void handleIntent(Intent intent, int requestCode) {

        startActivityForResult(intent, requestCode);
    }

    @Override
    public CropParams getCropParams() {
        return mCropParams;
    }

    public void imageProcessing(int requestCode, int resultCode, Intent data) {


        if (requestCode == CropHelper.REQUEST_CROP) {
            CropHelper.handleResult(this, requestCode, resultCode, data);
        } else if (requestCode == CropHelper.REQUEST_CAMERA) {
            CropHelper.handleResult(this, requestCode, resultCode, data);
        }
    }

    public void returnImageResult(Uri path) {
        ImageResult imageResult = (ImageResult) activity;
        imageResult.imageUri(path);
    }


}
