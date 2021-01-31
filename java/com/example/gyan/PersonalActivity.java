package com.example.gyan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView userName,userEmail,userId;
    ImageView profileImage;
    String UserId;

    FirebaseAuth auth;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userName=(TextView)findViewById(R.id.name);
        userEmail=(TextView)findViewById(R.id.email);
        profileImage=(ImageView)findViewById(R.id.profileImage);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();

        db.collection("users")
                .whereEqualTo("User Id",current)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                //Toast.makeText(getApplicationContext(),document.getId() +"==>" + document.getData(),Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(),""+ document.get("Email") ,Toast.LENGTH_LONG).show();

                                userName.setText((CharSequence) document.get("Name"));
                                userEmail.setText((CharSequence) document.get("Email"));



                            }
                        } else {

                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.personal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();
            Intent irwq = new Intent(PersonalActivity.this, MainActivity.class);
            irwq.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(irwq);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent h = new Intent(PersonalActivity.this, PersonalActivity.class);
            startActivity(h);
        } else if (id == R.id.nav_Score) {
            Intent w = new Intent(PersonalActivity.this, EventActivity.class);
            startActivity(w);

        } else if (id == R.id.nav_dquiz) {

            Intent ei = new Intent(PersonalActivity.this, DanswerActivity.class);
            startActivity(ei);

        } else if (id == R.id.nav_mquiz) {
            Intent iry = new Intent(PersonalActivity.this,ManswerActivity.class);
            startActivity(iry);

        } else if (id == R.id.nav_log) {

            Intent i = new Intent(PersonalActivity.this, AboutActivity.class);
            startActivity(i);

        }  else if (id == R.id.nav_faq) {

            Intent irw = new Intent(PersonalActivity.this, FaqActivity.class);
            startActivity(irw);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
