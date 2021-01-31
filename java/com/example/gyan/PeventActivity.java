package com.example.gyan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PeventActivity extends AppCompatActivity {

    EditText name,time,date,venue;
    Button next,cancel;
    Map<String,Object> event=new HashMap<>();
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pevent);
        name=findViewById(R.id.et_name);
        time=findViewById(R.id.et_time);
        date=findViewById(R.id.et_date);
        venue=findViewById(R.id.et_venue);
        mAuth = FirebaseAuth.getInstance();
        next=findViewById(R.id.btn_ne);
        cancel=findViewById(R.id.btn_can);


        next.setOnClickListener(new View.OnClickListener() {
             int i=1;
            @Override
            public void onClick(View v) {
                String names=name.getText().toString().trim();
                String dates=date.getText().toString().trim();
                String times=time.getText().toString().trim();
                String venues=venue.getText().toString().trim();

                event.put("Name",names);
                event.put("Date",dates);
                event.put("Time",times);
                event.put("Venue",venues);


                db.collection("Events").document("Event")
                        .set(event)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                            }
                        });
                Toast.makeText(getApplicationContext(),"Event successfully posted",Toast.LENGTH_LONG).show();
                startActivity(new Intent(PeventActivity.this,ThankActivity.class));

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                event.clear();
                startActivity(new Intent(PeventActivity.this,AdpersonalActivity.class));

            }
        });
    }
}
