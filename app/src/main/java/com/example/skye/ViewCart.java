package com.example.skye;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewCart extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DbCart dbCart= new DbCart(this);
        List<addCart> storecart = dbCart.getCartList();

        if(storecart.size()>0){
            CartAdapterClass cartAdapterClass = new CartAdapterClass(storecart,ViewCart.this);
            recyclerView.setAdapter(cartAdapterClass);

        }else{
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
        }






    }
}