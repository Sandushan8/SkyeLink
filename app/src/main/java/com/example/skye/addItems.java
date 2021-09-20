package com.example.skye;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skye.database.DBHelper;

public class addItems extends AppCompatActivity {

    EditText pname, pdesc, pcategory,pPrice;
    public boolean isfieldsvalidated=false;
    private Button addItems;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        Log.d("workflow", "Add Items onCreate  method  Called");
        pname = findViewById(R.id.productNametxt);
        pdesc = findViewById(R.id.productDescriptiontxt);
        pcategory = findViewById(R.id.productCategorytxt);
        pPrice = findViewById(R.id.pricetxt);
        addItems = findViewById(R.id.edittembtn);




    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addItems(View view) {
        Log.d("workflow","Add Item addItem  method  Called");
        isfieldsvalidated = CheckAllFields();

        if (isfieldsvalidated) {
            DBHelper dbHelper = new DBHelper(this);
            //removedefault(issetasdefault);

            long val;
//            val = dbHelper.addItem(pname.getText().toString(),
//                    pcategory.getText().toString(),
//                    Double.parseDouble(pPrice.getText().toString()),
//                    pdesc.getText().toString());

            //Snackbar.make(view,"Record Added Succesfully", BaseTransientBottomBar.LENGTH_LONG).setAction("OK",null).show();

            Toast.makeText(this, "Record Added Succesfully", Toast.LENGTH_SHORT).show();


            Log.i("BTN Click", "Add Item Confirmation button clicked");
        }
    }



    private boolean CheckAllFields() {
        Log.d("workflow","Add Item CheckAllFields  method  Called");
        if (pname.length() == 0) {
            pname.setError("This field is required");
            return false;
        }

        if (pdesc.length() == 0) {
            pdesc.setError("This field is required");
            return false;
        }

        if (pcategory.length() == 0) {
            pcategory.setError("This field is required");
            return false;
        }
        if (pPrice.length() == 0) {
            pPrice.setError("This field is required");
            return false;
        }

        return true;

    }

}