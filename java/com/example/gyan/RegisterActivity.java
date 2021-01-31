package com.example.gyan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText name;
    EditText con_pass;
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button register;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        name = (EditText)findViewById(R.id.et_name);
        email = (EditText)findViewById(R.id.et_email);
        password = (EditText)findViewById(R.id.et_password);
        con_pass = (EditText)findViewById(R.id.et_conpassword);

        register = (Button)findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();








        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name1 = name.getText().toString().trim();
                final String mail = email.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                final String cpass = con_pass.getText().toString().trim();



                if (TextUtils.isEmpty(name1)) {
                    name.setError("username is required");
                } else if (TextUtils.isEmpty(mail)) {
                    email.setError("Mail id  is required");
                } else if (TextUtils.isEmpty(pass) || pass.length() < 9) {
                    password.setError("Password of 9 char is required");

                } else if (TextUtils.isEmpty(cpass) || !(pass.equals(cpass))) {

                    con_pass.setError("Passwords must match");

                } else {

                    // Sign in success, update UI with the signed-in user's information

                    mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            final String userId = user.getUid();
                            if (task.isSuccessful()) {
                                Map<String, Object> users = new HashMap<>();
                                users.put("Name", name1);
                                users.put("Email",mail);
                                users.put("Password",pass);
                                users.put("User Id",userId);




// Add a new document with a generated ID
                                db.collection("users")
                                        .add(users)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {


                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {


                                            }
                                        });




                                Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            } else {

                                Toast.makeText(RegisterActivity.this, "User already exist", Toast.LENGTH_SHORT).show();

                            }
                        }


                    });

                }
            }

        });
    }

    }
