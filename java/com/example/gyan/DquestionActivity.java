package com.example.gyan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DquestionActivity extends AppCompatActivity {
    Button start;
    String qnum;
    EditText num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dquestion);
        start=findViewById(R.id.btn_start);
        num=findViewById(R.id.et_qnum);

        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View vi) {
                qnum = num.getText().toString().trim();
                Intent ir = new Intent(DquestionActivity.this, DquestActivity.class);
                ir.putExtra("Questnum",qnum);
                startActivity(ir);
            }
        });
    }
}
