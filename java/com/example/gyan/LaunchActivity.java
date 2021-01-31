package com.example.gyan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class LaunchActivity extends AppCompatActivity {

    Button admin;
    Button user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        admin = findViewById(R.id.btn_admin);
        user = findViewById(R.id.btn_user);

        admin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent is=new Intent(LaunchActivity.this,AdminActivity.class);
                startActivity(is);
            }
        });

        user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View vi) {
                Intent i=new Intent(LaunchActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
