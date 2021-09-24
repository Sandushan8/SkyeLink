package com.example.skye.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.skye.MainActivity;
import com.example.skye.R;
import com.example.skye.admin.AdminMainActivity;

public class loginActivity extends AppCompatActivity {
    Button button;
    TextView textViewemail,textViewemailpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        textViewemail = findViewById(R.id.usertxt);
        textViewemailpass = findViewById(R.id.useremail);


    }

    public void loginvalidation(View view) {

        if(textViewemail.getText().toString().equals("chanuth@gmail.com") && (textViewemailpass.getText().toString().equals("123"))) {
            Intent intent = new Intent(this, AdminMainActivity.class);
            startActivity(intent);
        }else{
            Log.d("workflow", "goto EditItems activity");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}