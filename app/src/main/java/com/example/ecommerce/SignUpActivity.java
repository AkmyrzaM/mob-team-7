package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    EditText fullNameTXT;
    EditText userNameTXT;
    EditText passwordTXT;
    Button signUpButton;
    DPOperations database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        initiateEnvVariables();

        signUpButton.setOnClickListener(v -> {
            String fullName = fullNameTXT.getText().toString();
            String username = userNameTXT.getText().toString();
            String password = passwordTXT.getText().toString();

            if(!fullName.equals("") && !username.equals("") && !password.equals("")){
                User user = new User(fullName, username, password);
                database.userSignUp(user);
                Toast.makeText(getApplicationContext(),"Successfully registered, back to login!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                i.putExtra("comingFromSignUp", true);
                startActivity(i);
            }
            else
                Toast.makeText(getApplicationContext(),"Make sure you have entered all the data", Toast.LENGTH_LONG).show();
        });
    }

    private void initiateEnvVariables(){
        fullNameTXT = (EditText) findViewById(R.id.in_fullName);
        userNameTXT = (EditText) findViewById(R.id.in_usernameNew);
        passwordTXT = (EditText) findViewById(R.id.in_passwordNew);
        signUpButton = (Button) findViewById(R.id.signUp_bt);
        database = new DPOperations(this);
    }
}