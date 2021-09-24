package com.example.skye;
import com.example.skye.Adapter.MobileListAdapter;
import com.example.skye.admin.*;
import com.example.skye.database.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class   ViewMobileHome extends AppCompatActivity {

    GridView gridView;
    ArrayList<itemModel> list;
    MobileListAdapter adapter = null;
    DBHelper dbHelper ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mobile_home);

        dbHelper = new DBHelper(this);
        Log.d("workflow", "onCreate: method call 1111111111 ");
        gridView = findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new MobileListAdapter(this,R.layout.mobile_items,list);
        gridView.setAdapter(adapter);
        Log.d("workflow", "onCreate: method call  3333333333 ");
        Cursor cursor = dbHelper.getData("select * from Items");
        Log.d("workflow", "onCreate: method call  3333333333 44444444444444444  555555555555555 ");
        list.clear();
        Log.d("workflow", "onCreate: method call  3333333333 44444444444444444 ");

        while (cursor.moveToNext()){
            int id  = cursor.getInt(0);
            String itemName = cursor.getString(1);
            String itemCategory = cursor.getString(2);
            String itemDescription = cursor.getString(3);
            double itemSellPrice = cursor.getDouble(4);
            byte[] image = cursor.getBlob(5);

            list.add(new itemModel(id,itemName,itemCategory,itemSellPrice,itemDescription,image));
            Log.d("workflow",cursor.getString(4));
            Log.d("workflow",cursor.getString(3));
        }
        adapter.notifyDataSetChanged();

        if(list.size()==0){
            Log.d("workflow","GGGGGGGGGGGGG");
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this,"No Item Foundzzzzzzzz",Toast.LENGTH_SHORT).show();

        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.routes:
                        startActivity(new Intent(getApplicationContext()
                                , feedback.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.items:
                        startActivity(new Intent(getApplicationContext()
                                , CartMain.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.sales:
                        startActivity(new Intent(getApplicationContext()
                                , Paymentmain.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.customers:
                        startActivity(new Intent(getApplicationContext()
                                , ProfileMain.class));
                        overridePendingTransition(0,0);
                        return true;




                }

                return false;
            }
        });




    }
}