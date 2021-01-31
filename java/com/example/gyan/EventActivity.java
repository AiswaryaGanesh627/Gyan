package com.example.gyan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.DocumentChange;

public class EventActivity extends AppCompatActivity {

    TextView title,event;
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String name,date,time,venue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        title=(TextView)findViewById(R.id.quiz);
        event=findViewById(R.id.quiz1);
        mAuth = FirebaseAuth.getInstance();
        DocumentReference docRef = db.collection("Events").document("Event");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields=new StringBuilder("Amazing ");
                    fields.append(doc.get("Name")).append(" on ");
                    fields.append(doc.get("Date")).append(" at ");
                    fields.append(doc.get("Venue")).append(" from ");
                    fields.append(doc.get("Time")).append("!");
                    event.setText(fields.toString());

                    }
            }
        });
    }
}
