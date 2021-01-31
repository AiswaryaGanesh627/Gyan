package com.example.gyan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AnswerActivity extends AppCompatActivity {
    TextView question,thankyou,text,quesnum;
    Button nexts,prevs;
    TextView r1, r2, r3, r4,corr;
    ArrayList<String> ques,answ,option1,option2,option3,option4;
    int tot,i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Bundle bundle = getIntent().getExtras();
        if (bundle!= null) {
            tot = bundle.getInt("Total");
            ques=bundle.getStringArrayList("Questions");
            answ=bundle.getStringArrayList("Answers");
            option1=bundle.getStringArrayList("Option1");
            option2=bundle.getStringArrayList("Option2");
            option3=bundle.getStringArrayList("Option3");
            option4=bundle.getStringArrayList("Option4");
        }
        question=findViewById(R.id.questionq);
        quesnum=findViewById(R.id.number);
        text=findViewById(R.id.quiq);
        r1 = findViewById(R.id.rad1);
        r2 =  findViewById(R.id.rad2);
        r3 = findViewById(R.id.rad3);
        r4 =  findViewById(R.id.rad4);
        thankyou=findViewById(R.id.tv_thanks);
        thankyou.setVisibility(View.GONE);
        nexts=findViewById(R.id.btn_nexts);
        prevs=findViewById(R.id.btn_prev);
        prevs.setVisibility(View.GONE);
        if(answ.get(0).equals(option1.get(0))) {
            corr=r1;
        }
        else {
            Log.d("question", "Wrong answer 1" );
        }
         if(answ.get(0).equals(option2.get(0))) {
             corr=r2;
        }
         else {
             Log.d("question", "Wrong answer 2" );
         }
         if(answ.get(0).equals(option3.get(0))){
             corr=r3;
        }
         else {
             Log.d("question", "Wrong answer 3" );
         }
         if(answ.get(0).equals(option4.get(0))){
             corr=r4;
        }
         else {
             Log.d("question", "Wrong answer 4" );
         }
        String st="Question 1/"+tot;
        quesnum.setText(st);
        question.setText(ques.get(0));
        r1.setText(option1.get(0));
        r2.setText(option2.get(0));
        r3.setText(option3.get(0));
        r4.setText(option4.get(0));
        corr.setBackgroundDrawable( getResources().getDrawable(R.drawable.answer) );



        nexts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               prevs.setVisibility(View.VISIBLE);
               if(i<tot){
                   if(answ.get(i).equals(option1.get(i))) {
                       corr=r1;
                   }
                   else {
                       r1.setBackgroundDrawable( getResources().getDrawable(R.drawable.btn_state) );
                   }
                   if(answ.get(i).equals(option2.get(i))) {
                       corr=r2;
                   }
                   else {
                       r2.setBackgroundDrawable( getResources().getDrawable(R.drawable.btn_state) );
                   }
                   if(answ.get(i).equals(option3.get(i))){
                       corr=r3;
                   }
                   else {
                       r3.setBackgroundDrawable( getResources().getDrawable(R.drawable.btn_state) );
                   }
                   if(answ.get(i).equals(option4.get(i))){
                       corr=r4;
                   }
                   else {
                       r4.setBackgroundDrawable( getResources().getDrawable(R.drawable.btn_state) );
                   }
                   String st1="Question "+(i+1)+ "/"+tot;
                   quesnum.setText(st1);
                    question.setText(ques.get(i));
                   r1.setText(option1.get(i));
                   r2.setText(option2.get(i));
                   r3.setText(option3.get(i));
                   r4.setText(option4.get(i));
                   corr.setBackgroundDrawable( getResources().getDrawable(R.drawable.answer) );

                }
                if(i==(tot-1)){
                    nexts.setText("DONE");
                }
                else if(i>=tot){
                    question.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    quesnum.setVisibility(View.GONE);
                    nexts.setVisibility(View.GONE);
                    r1.setVisibility(View.GONE);
                    r2.setVisibility(View.GONE);
                    r3.setVisibility(View.GONE);
                    r4.setVisibility(View.GONE);
                    prevs.setVisibility(View.GONE);
                    thankyou.setVisibility(View.VISIBLE);

                }
                i++;
            }
        });
        prevs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                if(answ.get(i).equals(option1.get(i))) {
                    corr=r1;
                }
                else {
                    r1.setBackgroundDrawable( getResources().getDrawable(R.drawable.btn_state) );
                }
                if(answ.get(i).equals(option2.get(i))) {
                    corr=r2;
                }
                else {
                    r2.setBackgroundDrawable( getResources().getDrawable(R.drawable.btn_state) );
                }
                if(answ.get(i).equals(option3.get(i))){
                    corr=r3;
                }
                else {
                    r3.setBackgroundDrawable( getResources().getDrawable(R.drawable.btn_state) );
                }
                if(answ.get(i).equals(option4.get(i))){
                    corr=r4;
                }
                else {
                    r4.setBackgroundDrawable( getResources().getDrawable(R.drawable.btn_state) );
                }
                String st1="Question "+(i+1)+ "/"+tot;
                quesnum.setText(st1);
                question.setText(ques.get(i));
                r1.setText(option1.get(i));
                r2.setText(option2.get(i));
                r3.setText(option3.get(i));
                r4.setText(option4.get(i));
                corr.setBackgroundDrawable( getResources().getDrawable(R.drawable.answer) );

                if(i<tot){
                    nexts.setText("NEXT");
                }
                if(i==0){
                    prevs.setVisibility(View.GONE);
                }


            }
        });

    }
}
