package com.example.skye;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartMain extends AppCompatActivity {

    EditText editText_ID;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_main);

        editText_ID = findViewById(R.id.editText_ID);

        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strID = editText_ID.getText().toString();

                if(strID.length() <=0 ){
                    Toast.makeText(CartMain.this, "ID is empty", Toast.LENGTH_SHORT).show();
                }else if(strID.length()<2){
                    Toast.makeText(CartMain.this, "ID not valid", Toast.LENGTH_SHORT).show();
                }else{
                    DbCart dbCart = new DbCart(CartMain.this);
                    addCart addCart = new addCart(strID);
                    dbCart.addID(addCart);
                    Toast.makeText(CartMain.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });




        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.items);

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