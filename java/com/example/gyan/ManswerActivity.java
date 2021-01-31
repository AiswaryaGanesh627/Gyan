package com.example.gyan;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

public class ManswerActivity extends AppCompatActivity {


    Button nex;
    TextView qn,text;
    RadioGroup rad;
    RadioButton r1, r2, r3, r4;

    final  ArrayList<String> quests=new ArrayList<>();
    final  ArrayList<String> ans=new ArrayList<>();
    final  ArrayList<String> opt1=new ArrayList<>();
    final  ArrayList<String> opt2=new ArrayList<>();
    final  ArrayList<String> opt3=new ArrayList<>();
    final  ArrayList<String> opt4=new ArrayList<>();
    int i = 2;
    int result = 0;
    String quest, op1, op2, op3, op4, corr;

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manswer);
        qn = (TextView) findViewById(R.id.quest1);
        text = (TextView) findViewById(R.id.num1);
        rad = (RadioGroup) findViewById(R.id.radioGroup1);
        r1 = (RadioButton) findViewById(R.id.radio11);
        r2 = (RadioButton) findViewById(R.id.radio21);
        r3 = (RadioButton) findViewById(R.id.radio31);
        r4 = (RadioButton) findViewById(R.id.radio41);
        nex = (Button) findViewById(R.id.btn_nexts1);
        mAuth = FirebaseAuth.getInstance();


        final DocumentReference docRef = db.collection("MonthlyQuest").document(String.valueOf(java.time.ZonedDateTime.now().getMonth()));
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
                    opt1.add(forms.get("Opt1").toString());
                    opt2.add(forms.get("Opt2").toString());
                    opt3.add(forms.get("Opt3").toString());
                    opt4.add(forms.get("Opt4").toString());
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
                                    opt1.add(form.get("Opt1").toString());
                                    opt2.add(form.get("Opt2").toString());
                                    opt3.add(form.get("Opt3").toString());
                                    opt4.add(form.get("Opt4").toString());

                                }
                                if (i == val) {
                                    nex.setText("DONE");
                                } else if (i > val) {
                                    Log.d("question", "Qns" + quests);
                                    Log.d("Answer", "Ans" + ans);
                                    Intent ir = new Intent(ManswerActivity.this, ScoreActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("Result", result);
                                    bundle.putInt("Total", val);
                                    bundle.putStringArrayList("Questions", quests);
                                    bundle.putStringArrayList("Answers", ans);
                                    bundle.putStringArrayList("Option1", opt1);
                                    bundle.putStringArrayList("Option2", opt2);
                                    bundle.putStringArrayList("Option3", opt3);
                                    bundle.putStringArrayList("Option4", opt4);
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

