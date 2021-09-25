package com.example.skye;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skye.Adapter.CategoryAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.skye.Adapter.RecommendedAdapter;
import com.example.skye.login.loginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategotyList, recyclerViewPopularList;
    TextView Signouttxt, viewMobileCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategoty();
        recyclerViewPopular();
        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        Signouttxt = findViewById(R.id.signoutbtn);
        viewMobileCat = findViewById(R.id.ViewMobileCat);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home:
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

    private void recyclerViewCategoty() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategotyList = findViewById(R.id.view1);
        recyclerViewCategotyList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("MobilePhone", "cat_1"));
        categoryList.add(new CategoryDomain("Camera", "cat_1"));
        categoryList.add(new CategoryDomain("Headset", "cat_1"));
        categoryList.add(new CategoryDomain("Phone Caseses", "cat_1"));
        categoryList.add(new CategoryDomain("Headset", "cat_1"));
        categoryList.add(new CategoryDomain("Others", "cat_1"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategotyList.setAdapter(adapter);

    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<itemDomain>  itemlist = new ArrayList<>();
        itemlist.add(new itemDomain("Apple 12 Pro", "cat_1", "", 130000.00, 5, 20, 1000));
        itemlist.add(new itemDomain("Apple 11 Pro", "cat_1", "", 150000.00, 4, 18, 1500));
        itemlist.add(new itemDomain("Apple  Pro Max", "cat_1", "", 110000.00, 3, 16, 800));
        itemlist.add(new itemDomain("Apple 12 Mini", "cat_1", "", 180000.0, 5, 20, 1000));
        itemlist.add(new itemDomain("Iphone SE", "cat_1", "", 150000.00, 4, 18, 1500));
        itemlist.add(new itemDomain("Iphone X", "cat_1", " ", 250000.00, 3, 16, 800));

        adapter2 = new RecommendedAdapter(itemlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    public void signOut(View view) {
        Log.d("workflow", "goto EditItems activity");
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    public void viewMobileCat(View view) {
        Log.d("workflow", "goto viewItem activity");
        Intent intent = new Intent(this, ViewMobileHome.class);
        startActivity(intent);
    }



}