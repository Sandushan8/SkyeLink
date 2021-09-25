package com.example.skye;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skye.database.DBHelper;


public class CardDetails extends AppCompatActivity {

    Button buttonback,submit;
    EditText ed5,ed6,ed7,ed8,ed9;

    String name,phone,address,email,cardholdername,cardnumber,cvv,expirydate;
    Double amount;
    long rowid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        Intent intent2 = getIntent();
        name = intent2.getStringExtra("Name");
        phone = intent2.getStringExtra("PhoneNumber");
        address = intent2.getStringExtra("Address");
        email = intent2.getStringExtra("Email");
        amount = intent2.getDoubleExtra("Amount",0.0);

        ed5 = findViewById(R.id.cardholdername);
        ed6 = findViewById(R.id.cardno);
        ed7 = findViewById(R.id.cvv);
        ed8 = findViewById(R.id.mmyy);
        ed9 = findViewById(R.id.amount);
        ed9.setText(amount.toString());


        buttonback = findViewById(R.id.button2);
        submit = findViewById(R.id.button3);


        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardDetails.this, Paymentmain.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardholdername = ed5.getText().toString();
                cardnumber = ed6.getText().toString();
                cvv = ed7.getText().toString();
                expirydate = ed8.getText().toString();

                createPayment();

                Intent intent = new Intent(CardDetails.this,OrderDetails.class);
                intent.putExtra("rowId",rowid);
                startActivity(intent);
            }
        });
    }

    public void createPayment() {
        DBHelper db = new DBHelper(this);
        rowid = db.createPayment(name,phone, address, email, cardholdername, cardnumber, cvv, expirydate, amount);
    }
}