package com.example.skye.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.skye.CartMain;
import com.example.skye.MainActivity;
import com.example.skye.Paymentmain;
import com.example.skye.R;
import com.example.skye.database.DBHelper;
import com.example.skye.feedback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class AdminMainActivity extends AppCompatActivity {

    EditText itemName, itemCategory, itemDesc, itemPrice;
    Button mBtnAdd, mBtnList;
    public boolean isfieldsvalidated=false;
    ImageView mImageView;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Item Records");

        itemName = findViewById(R.id.itemName);
        itemCategory = findViewById(R.id.itemCategory);
        itemDesc = findViewById(R.id.itemDescription);
        itemPrice = findViewById(R.id.itemSellPrice);
        mBtnList = findViewById(R.id.btnList);
        mImageView = findViewById(R.id.imageView);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions(
                        AdminMainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        // show record list
        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start item record
                startActivity(new Intent(AdminMainActivity.this, itemRecordList.class));
            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.admin_bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.customers);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.items:
                        startActivity(new Intent(getApplicationContext()
                                , itemRecordList.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.customers:

                        return true;



                }

                return false;
            }
        });


    }


    private static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return  byteArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
                Log.d("Yes", "Don't have permission to access file location");
            } else {
                Toast.makeText(this, "Don't have permission to access file location ", Toast.LENGTH_SHORT).show();
                Log.d("Yes", "Don't have permission to access file location");

            }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                mImageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }






    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItems(View view) {
        Log.d("workflow", "Add Item addItem  method  Called");
        isfieldsvalidated = CheckAllFields();

        if (isfieldsvalidated) {
            DBHelper dbHelper = new DBHelper(this);
            //removedefault(issetasdefault);

            long val;
            val = dbHelper.addItem(itemName.getText().toString(),
                    itemCategory.getText().toString(),
                    Double.parseDouble(itemPrice.getText().toString()),
                    itemDesc.getText().toString(),
                    imageViewToByte(mImageView));


            Toast.makeText(this, "Record Added Succesfully", Toast.LENGTH_SHORT).show();
            itemName.setText("");itemDesc.setText("");itemPrice.setText("");itemCategory.setText("");
            mImageView.setImageResource(R.drawable.addphoto);
            Log.i("BTN Click", "Add Item Confirmation button clicked");
        }
    }


    private boolean CheckAllFields() {
        Log.d("workflow", "Add Item CheckAllFields  method  Called");
        if (itemName.length() == 0) {
            itemName.setError("This field is required");
            return false;
        }

        if (itemCategory.length() == 0) {
            itemCategory.setError("This field is required");
            return false;
        }

        if (itemDesc.length() == 0) {
            itemDesc.setError("This field is required");
            return false;
        }

        if (itemPrice.length() == 0) {
            itemPrice.setError("This field is required");
            return false;
        }

        return true;

    }
}