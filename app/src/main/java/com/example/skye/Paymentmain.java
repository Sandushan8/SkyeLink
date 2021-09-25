package com.example.skye;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Paymentmain extends AppCompatActivity {
    Button button;
    EditText ed1,ed2,ed3,ed4;

    String name,phone,address,email;
    Double amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentmain);
        amount=0.0;
        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.phone);
        ed3 = findViewById(R.id.address);
        ed4 = findViewById(R.id.email);

        button = (Button) findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = ed1.getText().toString();
                phone = ed2.getText().toString();
                address = ed3.getText().toString();
                email = ed4.getText().toString();
                Intent intent = new Intent(Paymentmain.this, CardDetails.class);
                intent.putExtra("Name",name);
                intent.putExtra("PhoneNumber",phone);
                intent.putExtra("Address",address);
                intent.putExtra("Email",email);
                intent.putExtra("Amount",amount);
                startActivity(intent);
                clearFields();
            }
        });

        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.sales);

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
    public void clearFields(){
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
    }
}