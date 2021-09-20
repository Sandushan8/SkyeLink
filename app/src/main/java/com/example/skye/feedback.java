package com.example.skye;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skye.admin.AdminMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.routes);

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
    public void openEditItemActivity(View view) {
        Log.d("workflow", "goto EditItems activity");
        Intent intent = new Intent(this, AdminMainActivity.class);
        startActivity(intent);
    }

    public void openaddItemActivity(View view) {
        Log.d("workflow", "goto additem activity");
        Intent intent = new Intent(this,addItems.class);
        startActivity(intent);
    }

}