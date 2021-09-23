package com.example.skye.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.skye.R;
import com.example.skye.database.DBHelper;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class itemRecordList extends AppCompatActivity {

    ListView mListView;
    ArrayList<itemModel> mList;
    itemRecordListAdapter mAdapter = null;
    DBHelper dbHelper ;
    ImageView imageViewIcon;
    final int REQUEST_CODE_GALLERY = 999;
    public boolean isfieldsvalidated=false;
    Button btnUpdate;
    EditText txtEdtItemName,txtEdtItemCategory,txtEdtItemDesc,txtEdtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_record_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Current Item List");

        dbHelper = new DBHelper(this);

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new itemRecordListAdapter(this,R.layout.row,mList);
        mListView.setAdapter(mAdapter);

        // get all data from sqlite
        Cursor cursor = dbHelper.getData("select * from Items");
        mList.clear();
        while (cursor.moveToNext()){
            int id  = cursor.getInt(0);
            String itemName = cursor.getString(1);
            String itemCategory = cursor.getString(2);
            String itemDescription = cursor.getString(3);
            double itemSellPrice = cursor.getDouble(4);
            byte[] image = cursor.getBlob(5);

            mList.add(new itemModel(id,itemName,itemCategory,itemSellPrice,itemDescription,image));
            Log.d("workflow",cursor.getString(4));
            Log.d("workflow",cursor.getString(3));
        }
        mAdapter.notifyDataSetChanged();

        if(mList.size()==0){
            Log.d("workflow","GGGGGGGGGGGGG");
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this,"No Item Found",Toast.LENGTH_SHORT).show();
        }

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                CharSequence[] items = {"Update","Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(itemRecordList.this);
                Log.d("worflow","Alert builder called");

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0){
                            Cursor c = dbHelper.getData("select ItemCode from Items");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while ((c.moveToNext())){
                                arrID.add(c.getInt(0));
                            }
                            //show update dialog
                            showDialogUpdate(itemRecordList.this,arrID.get(position));
                        }
                        if(i == 1){
                            Cursor c = dbHelper.getData("select ItemCode from Items");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while ((c.moveToNext())){
                                arrID.add(c.getInt(0));
                            }
                            //show update dialog
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }

    private void showDialogDelete(int idRecord) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(itemRecordList.this);
        dialogDelete.setTitle("Warning! ");
        dialogDelete.setMessage("Are You sure to delete?");
        dialogDelete.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    dbHelper.deleteItem(idRecord);
                    recreate();
                    Toast.makeText(itemRecordList.this,"Delete Successfully",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.d("error",e.getMessage());
                }
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }


    private  void showDialogUpdate(Activity activity, final  int position){
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.itemupdate);
        dialog.setTitle("Update");

        imageViewIcon = dialog.findViewById(R.id.imageViewRecord);
        txtEdtItemName = dialog.findViewById(R.id.itemName);
        txtEdtItemCategory = dialog.findViewById(R.id.itemCategory);
        txtEdtPrice = dialog.findViewById(R.id.itemSellPrice);
        txtEdtItemDesc = dialog.findViewById(R.id.itemDescription);
        btnUpdate = dialog.findViewById(R.id.btnUpdate);

        // get  data row click from sqlite
        Cursor cursor = dbHelper.getData("select * from Items where ItemCode = "+position);
        mList.clear();
        while (cursor.moveToNext()){
            int id  = cursor.getInt(0);
            String itemName = cursor.getString(1);
            txtEdtItemName.setText(itemName);
            String itemCategory = cursor.getString(2);
            txtEdtItemCategory.setText(itemCategory);
            String itemDescription = cursor.getString(3);
            txtEdtItemDesc.setText(itemDescription);
            double itemSellPrice = cursor.getDouble(4);
            txtEdtPrice.setText(String.valueOf(itemSellPrice));
            byte[] image = cursor.getBlob(5);
            imageViewIcon.setImageBitmap(BitmapFactory.decodeByteArray(image,0, image.length));
            

            mList.add(new itemModel(id,itemName,itemCategory,itemSellPrice,itemDescription,image));
            Log.d("workflow",cursor.getString(4));
            Log.d("workflow",cursor.getString(3));
        }


        //set width of dialog
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels* 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels* 0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        itemRecordList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }

        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Log.d("workflow", "Add Item addItem  method  Called");
                isfieldsvalidated = CheckAllFields();

                if (isfieldsvalidated) {
                    DBHelper dbHelper = new DBHelper(itemRecordList.this);
                    //removedefault(issetasdefault);

                    long val;
                    val = dbHelper.updateItem(position,txtEdtItemName.getText().toString(),
                            txtEdtItemCategory.getText().toString(),
                            Double.parseDouble(txtEdtPrice.getText().toString()),
                            txtEdtItemDesc.getText().toString(),
                            imageViewToByte(imageViewIcon));

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Update Successflly",Toast.LENGTH_SHORT).show();

                    updateRecordList();
                }


            }
        });




    }

    private void updateRecordList() {

        // get all data from sqlite
        Cursor cursor = dbHelper.getData("select * from Items");
        mList.clear();
        while (cursor.moveToNext()){
            int id  = cursor.getInt(0);
            String itemName = cursor.getString(1);
            String itemCategory = cursor.getString(2);
            String itemDescription = cursor.getString(3);
            double itemSellPrice = cursor.getDouble(4);
            byte[] image = cursor.getBlob(5);

            mList.add(new itemModel(id,itemName,itemCategory,itemSellPrice,itemDescription,image));
            Log.d("workflow",cursor.getString(4));
            Log.d("workflow",cursor.getString(3));
        }
        mAdapter.notifyDataSetChanged();

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

                imageViewIcon.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean CheckAllFields() {
        Log.d("workflow", "Add Item CheckAllFields  method  Called");
        if (txtEdtItemName.length() == 0) {
            txtEdtItemName.setError("This field is required");
            return false;
        }

        if (txtEdtItemCategory.length() == 0) {
            txtEdtItemCategory.setError("This field is required");
            return false;
        }

        if (txtEdtItemDesc.length() == 0) {
            txtEdtItemDesc.setError("This field is required");
            return false;
        }
        if (txtEdtPrice.length() == 0) {
            txtEdtPrice.setError("This field is required");
            return false;
        }

        return true;

    }

}

//design row for the listView
//create class model
//create custom adapter for listView