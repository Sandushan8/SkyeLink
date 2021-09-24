package com.example.skye;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class CardDetails extends AppCompatActivity {
    EditText editText_cardholdername,editText_cardno,editText_cvv,editText_mmyy,editText_amount;
    public Button button,submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);



        button = (Button) findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardDetails.this, Paymentmain.class);
                startActivity(intent);
            }
        });
    }

}