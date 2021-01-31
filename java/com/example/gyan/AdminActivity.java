package com.example.gyan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    TextView textView1;
    EditText email1;
    EditText password1;
    private FirebaseAuth mAuth;
    Button login;

    String pass,mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        email1 = findViewById(R.id.et_email);
        password1= findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        textView1=findViewById(R.id.text);

        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = password1.getText().toString().trim();
                mail = email1.getText().toString().trim();

                if (TextUtils.isEmpty(mail)) {
                    email1.setError("Mail id  is required");

                } else if (TextUtils.isEmpty(pass) || pass.length() < 9) {
                    password1.setError("Password of 9 char is required");
                } else {

                    if(mail.equals("aisuanand9847@gmail.com") && pass.equals("anand2001"))
                    {

                        Toast.makeText(AdminActivity.this, "Logged in.", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),permissions_act.class));
                        startActivity(new Intent(AdminActivity.this, AdpersonalActivity.class));

                    } else {
                        Toast.makeText(AdminActivity.this, "Invalid!!", Toast.LENGTH_LONG).show();
                    }




                }
            }
        });
    }
}

