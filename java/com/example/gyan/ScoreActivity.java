package com.example.gyan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;

public class ScoreActivity extends AppCompatActivity {
    int result,tot;
    TextView text;
    Button view;
    ArrayList<String> ques,answ,option1,option2,option3,option4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Bundle bundle = getIntent().getExtras();
        if (bundle!= null) {
            result = bundle.getInt("Result");
            tot = bundle.getInt("Total");
            ques=bundle.getStringArrayList("Questions");
            answ=bundle.getStringArrayList("Answers");
            option1=bundle.getStringArrayList("Option1");
            option2=bundle.getStringArrayList("Option2");
            option3=bundle.getStringArrayList("Option3");
            option4=bundle.getStringArrayList("Option4");

        }

        text=findViewById(R.id.qui1);
        view=findViewById(R.id.btn_view);
        StringBuilder sd=new StringBuilder();
        sd.append(result).append("/").append(tot);
        text.setText(sd.toString());

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ir = new Intent(ScoreActivity.this, AnswerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Total",tot);
                bundle.putStringArrayList("Questions",ques);
                bundle.putStringArrayList("Answers",answ);
                bundle.putStringArrayList("Option1", option1);
                bundle.putStringArrayList("Option2", option2);
                bundle.putStringArrayList("Option3", option3);
                bundle.putStringArrayList("Option4", option4);
                ir.putExtras(bundle);
                startActivity(ir);

            }

        });




            }

    }

