package com.example.gyan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class DquestActivity extends AppCompatActivity {
    String qnum;
    int q_number,val;
    EditText question,opt1,opt2,opt3,opt4,correct;
    Button next,cancel;
    Map<String,Object> map=new HashMap<>();
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dquest);
        Intent i = getIntent();
        qnum = i.getStringExtra("Questnum");
         q_number=Integer.parseInt(qnum);
        question=findViewById(R.id.et_question);
        opt1=findViewById(R.id.et_opt1);
        opt2=findViewById(R.id.et_opt2);
        opt3=findViewById(R.id.et_opt3);
        opt4=findViewById(R.id.et_opt4);
        correct=findViewById(R.id.et_correct);
        next=findViewById(R.id.btn_next);
        cancel=findViewById(R.id.btn_cancel);
        mAuth = FirebaseAuth.getInstance();
        map.put("Number",q_number);
        val=q_number;


        if(val!=0)
        {
            if(val==1){
                next.setText("DONE");
            }

            next.setOnClickListener(new View.OnClickListener() {
                                        int i = 0;

                                        @RequiresApi(api = Build.VERSION_CODES.O)
                                        @Override
                                        public void onClick(View v) {


                                            String questions = question.getText().toString().trim();
                                            String option1 = opt1.getText().toString().trim();
                                            String option2 = opt2.getText().toString().trim();
                                            String option3 = opt3.getText().toString().trim();
                                            String option4 = opt4.getText().toString().trim();
                                            String correctans = correct.getText().toString().trim();
                                            Map<String, Object> questi = new HashMap<>();
                                            questi.put("Question", questions);
                                            questi.put("Opt1",option1);
                                            questi.put("Opt2",option2);
                                            questi.put("Opt3",option3);
                                            questi.put("Opt4",option4);
                                            questi.put("Correct",correctans);
                                            map.put("Question"+(i+1),questi);

                                            if(i<(val-1)) {
                                                question.setText("");
                                                opt1.setText("");
                                                opt2.setText("");
                                                opt3.setText("");
                                                opt4.setText("");
                                                correct.setText("");
                                                i++;
                                                if (i == val - 1) {
                                                    next.setText("DONE");
                                                }

                                            }
                                            else
                                            {
                                                db.collection("DailyQuest").document(String.valueOf(java.time.LocalDate.now()))
                                                        .set(map)
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

                                                Toast.makeText(getApplicationContext(),"Questions successfully posted",Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(DquestActivity.this, ThankActivity.class));


                                            }

                                        }
                                    });
            cancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    map.clear();
                    startActivity(new Intent(DquestActivity.this,AdpersonalActivity.class));

                }
            });

        }
    }
}
