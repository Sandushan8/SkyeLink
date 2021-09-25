package com.example.skye.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skye.MainActivity;
import com.example.skye.R;
import com.example.skye.admin.AdminMainActivity;

public class loginActivity extends AppCompatActivity {
    Button button;
    TextView textViewemail,textViewemailpass;
    public boolean isfieldsvalidated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        textViewemail = findViewById(R.id.usertxt);
        textViewemailpass = findViewById(R.id.useremail);


    }


    public void loginvalidation(View view) {

        isfieldsvalidated = CheckAllFields();
        if(isfieldsvalidated) {

            if (textViewemail.getText().toString().equals("admin@gmail.com") && (textViewemailpass.getText().toString().equals("123"))) {
                Intent intent = new Intent(this, AdminMainActivity.class);
                startActivity(intent);
            } else {
                Log.d("workflow", "goto EditItems activity");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    private boolean CheckAllFields() {
        Log.d("workflow", "Add Item CheckAllFields  method  Called");
        if (textViewemail.length() == 0) {
            textViewemail.setError("This field is required");
            return false;
        }
        if (textViewemailpass.length() == 0) {
            textViewemailpass.setError("This field is required");
            return false;
        }
        return true;

    }





}