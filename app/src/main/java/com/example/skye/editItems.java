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

public class editItems extends AppCompatActivity {
    public boolean isfieldsvalidated = false;
    EditText updateID, update_pname, update_pdesc, update_pcategory,update_pPrice;
    private Button editItems;
    private Context context;
    String issetasdefault = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

          Log.d("workflow", "Update Items onCreate  method  Called");
        updateID = findViewById(R.id.productID);
        update_pname = findViewById(R.id.productNametxt);
        update_pdesc = findViewById(R.id.productDescriptiontxt);
        update_pcategory = findViewById(R.id.productCategorytxt);
        update_pPrice = findViewById(R.id.pricetxt);
        editItems = findViewById(R.id.edittembtn);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateItem(View view) {

        isfieldsvalidated = CheckAllFields();
        Log.d("workflow","Edit_Item_Activity updateItem  method  Called");

        Log.d("workflow",issetasdefault);
        if (isfieldsvalidated) {

            DBHelper dbHelper = new DBHelper(this);

            //   int val1=dbHelper.update_def_route();

//            int val = dbHelper.updateItem(
//                    updateID.getText().toString(),
//                    update_pname.getText().toString(),
//                    update_pcategory.getText().toString(),
//                    Double.parseDouble(update_pPrice.getText().toString()),
//                    update_pdesc.getText().toString());

            Toast.makeText(this, "Record Updated Succesfully ", Toast.LENGTH_SHORT).show();

         //   Intent intent = new Intent(this, Items.class);
        //    startActivity(intent);
         //   Log.i("BTN Click", "Update item Confirmation button clicked");
        }
    }

    private boolean CheckAllFields() {
        Log.d("workflow","Add Item CheckAllFields  method  Called");
        if (update_pname.length() == 0) {
            update_pname.setError("This field is required");
            return false;
        }

        if (update_pdesc.length() == 0) {
            update_pdesc.setError("This field is required");
            return false;
        }

        if (update_pcategory.length() == 0) {
            update_pcategory.setError("This field is required");
            return false;
        }
        if (update_pPrice.length() == 0) {
            update_pPrice.setError("This field is required");
            return false;
        }

        return true;

    }

    public void deleteItem(View view) {


        Log.d("workflow","Edit_Item_Activity updateItem  method  Called");




            DBHelper dbHelper = new DBHelper(this);

            //   int val1=dbHelper.update_def_route();

          //  dbHelper.deleteItem(updateID.getText().toString() );

            Toast.makeText(this, "Record Updated Succesfully ", Toast.LENGTH_SHORT).show();

            //   Intent intent = new Intent(this, Items.class);
            //    startActivity(intent);
            //   Log.i("BTN Click", "Update item Confirmation button clicked");

    }


}