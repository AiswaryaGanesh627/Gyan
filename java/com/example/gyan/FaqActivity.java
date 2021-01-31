package com.example.gyan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FaqActivity extends AppCompatActivity {

    EditText sug;
    RatingBar bar1,bar2,bar3,bar4,bar5;
    Button sub;
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        sub = (Button)findViewById(R.id.btn_sub);
        sug = (EditText)findViewById(R.id.et_sug);
        bar1 = (RatingBar)findViewById(R.id.bar1);
        bar2 = (RatingBar)findViewById(R.id.bar2);
        bar3 = (RatingBar)findViewById(R.id.bar3);
        bar4 = (RatingBar)findViewById(R.id.bar4);
        bar5 = (RatingBar)findViewById(R.id.bar5);
        mAuth = FirebaseAuth.getInstance();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String rate1=String.valueOf(bar1.getRating());
                final String rate2=String.valueOf(bar2.getRating());
                final String rate3=String.valueOf(bar3.getRating());
                final String rate4=String.valueOf(bar4.getRating());
                final String rate5=String.valueOf(bar5.getRating());
                final String suqq = sug.getText().toString().trim();
                if(bar1.getRating() == 0.0 || bar2.getRating() == 0.0 || bar3.getRating() == 0.0 || bar4.getRating() == 0.0 || bar5.getRating() == 0.0)
                {
                    Toast.makeText(FaqActivity.this, "Kindly rate all the 5 questions", Toast.LENGTH_LONG).show();
                }
                else
                {


                    Map<String, Object> feed = new HashMap<>();
                    feed.put("Usefulness", rate1);
                    feed.put("Rating",rate2);
                    feed.put("Daily Quiz",rate3);
                    feed.put("User-Friendliness",rate4);
                    feed.put("Difficulty Level",rate5);
                    feed.put("Suggestion",suqq);




// Add a new document with a generated ID
                    db.collection("Feedback")
                            .add(feed)
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

                    Toast.makeText(getApplicationContext(),"Thank You for your valuable feedback!!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(FaqActivity.this, ThankActivity.class));

                }


            }
        });

    }

}

