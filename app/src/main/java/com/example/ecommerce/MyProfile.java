package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        //My profile page shows details of current user

        TextView login = (TextView) findViewById(R.id.name_text_id);
        TextView pass = (TextView) findViewById(R.id.pass_text_id);

        login.setText(getIntent().getStringExtra("name"));
        pass.setText(getIntent().getStringExtra("pass"));
    }
}