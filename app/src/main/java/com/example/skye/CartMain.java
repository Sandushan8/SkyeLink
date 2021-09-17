package com.example.skye;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_main);

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