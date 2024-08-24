package com.example.assignment6;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSIONS = 2;
    private String currentPhotoPath;
    private ActivityResultLauncher<Intent> takePictureLauncher;


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCapture = findViewById(R.id.btnCapture);
        btnCapture.setOnClickListener(v -> {
            if (checkPermissions()) {
                dispatchTakePictureIntent();
            }
        });
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        File file = new File(currentPhotoPath);
                        if (file.exists()) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 800, 800, false);
                            saveImage(resizedBitmap);
                        } else {
                            Toast.makeText(this, "Image file not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS);
            return false;
        }
        return true;
    }
    @SuppressLint("QueryPermissionsNeeded")
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
                Log.e("CameraDemo", "Error occurred while creating the File", ex);
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.assignment6.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                takePictureLauncher.launch(takePictureIntent);
            }else {
                Toast.makeText(this, "Error creating file", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void saveImage(Bitmap bitmap) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(currentPhotoPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out);
            Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show();
            }
        }
    }




}