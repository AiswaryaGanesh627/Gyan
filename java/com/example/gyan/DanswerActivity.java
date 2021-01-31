package com.example.gyan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

public class DanswerActivity extends AppCompatActivity {


    Button nex;
    TextView qn,text;
    RadioGroup rad;
    RadioButton r1, r2, r3, r4;

  final  ArrayList<String> quests=new ArrayList<>();
  final  ArrayList<String> ans=new ArrayList<>();
    int i = 2;
    int result = 0;
    String quest, op1, op2, op3, op4, corr;


    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danswer);
        qn = (TextView) findViewById(R.id.quest);
        rad = (RadioGroup) findViewById(R.id.radioGroup);
        r1 = (RadioButton) findViewById(R.id.radio1);
        r2 = (RadioButton) findViewById(R.id.radio2);
        r3 = (RadioButton) findViewById(R.id.radio3);
        r4 = (RadioButton) findViewById(R.id.radio4);
        text=findViewById(R.id.num);
        nex = (Button) findViewById(R.id.btn_nexts);
        mAuth = FirebaseAuth.getInstance();


        final DocumentReference docRef = db.collection("DailyQuest").document(String.valueOf(java.time.LocalDate.now()));
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(final DocumentSnapshot documentSnapshot) {

                if (!documentSnapshot.exists()) {
                    Log.d("Error", "onSuccess: LIST EMPTY");
                    Toast.makeText(getApplicationContext(), "Please wait..Questions not posted!", Toast.LENGTH_LONG).show();
                }
                else {


                    StringBuilder sb = new StringBuilder();
                    sb.append(documentSnapshot.get("Number"));
                    final int val = Integer.parseInt(sb.toString());

                    if (val == 1) {
                        nex.setText("DONE");
                    }

                    Map<String, Object> forms = (Map<String, Object>) documentSnapshot.get("Question1");
                    quests.add(forms.get("Question").toString());
                    ans.add(forms.get("Correct").toString());
                    qn.setText(forms.get("Question").toString());
                    String st="Question 1/"+val;
                    text.setText(st);
                    r1.setText(forms.get("Opt1").toString());
                    r2.setText(forms.get("Opt2").toString());
                    r3.setText(forms.get("Opt3").toString());
                    r4.setText(forms.get("Opt4").toString());
                    corr = forms.get("Correct").toString();
                    nex.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            int id=rad.getCheckedRadioButtonId();
                            if(id==-1){
                                Toast.makeText(getApplicationContext(), "Please choose an answer to proceed", Toast.LENGTH_LONG).show();
                            }
                            else {
                                RadioButton answer = (RadioButton) findViewById(id);
                                if (corr.equals(answer.getText())) {
                                    result++;
                                    Toast.makeText(getApplicationContext(), "Hurray!! You got it right!!", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Oops!! You were wrong!!", Toast.LENGTH_LONG).show();
                                }
                                if (i <= val) {
                                    Map<String, Object> form = (Map<String, Object>) documentSnapshot.get("Question" + i);
                                    String st1="Question "+i+ "/"+val;
                                    text.setText(st1);
                                    qn.setText(form.get("Question").toString());
                                    r1.setText(form.get("Opt1").toString());
                                    r2.setText(form.get("Opt2").toString());
                                    r3.setText(form.get("Opt3").toString());
                                    r4.setText(form.get("Opt4").toString());
                                    corr = form.get("Correct").toString();
                                    quests.add(form.get("Question").toString());
                                    ans.add(form.get("Correct").toString());

                                }
                                if (i == val) {
                                    nex.setText("DONE");
                                } else if (i > val) {
                                    Log.d("question", "Qns" + quests);
                                    Log.d("Answer", "Ans" + ans);
                                    Intent ir = new Intent(DanswerActivity.this, ScoreActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("Result", result);
                                    bundle.putInt("Total", val);
                                    bundle.putStringArrayList("Questions", quests);
                                    bundle.putStringArrayList("Answers", ans);
                                    ir.putExtras(bundle);
                                    startActivity(ir);

                                }
                                i++;
                            }

                        }

                    });

                }

            }
        });
    }
}

