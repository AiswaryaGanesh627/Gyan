package com.example.gyan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener  {
    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    TextView textView;
    TextView pasq;
    private static final int RC_SIGN_IN = 1;
    EditText email1;
    EditText password1;
    private FirebaseAuth mAuth;

    Button login;
    Button regi;
    String mail,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        signInButton=(SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });


        setupUI();
        setupListeners();


    }

private void setupUI() {
    email1 = findViewById(R.id.et_email);
    password1= findViewById(R.id.et_password);
    login = findViewById(R.id.btn_login);
    regi = findViewById(R.id.btn_regi);
    pasq=findViewById(R.id.pas);
    mAuth = FirebaseAuth.getInstance();

}

private void setupListeners()
{

    pasq.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intw=new Intent(MainActivity.this,PassActivity.class);
            startActivity(intw);

        }
    });

    login.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            pass = password1.getText().toString().trim();
            mail = email1.getText().toString().trim();

            if (TextUtils.isEmpty(mail)) {
                email1.setError("Mail id  is required");

            }
            else if (TextUtils.isEmpty(pass) || pass.length() < 9) {
                password1.setError("Invalid password");
            }

            else {


                mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged in.", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),permissions_act.class));
                            startActivity(new Intent(MainActivity.this, PersonalActivity.class));

                        } else {
                            Toast.makeText(MainActivity.this, "Invalid!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

    }
    });

    regi.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View vi) {
            Intent i=new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(i);
        }
    });


}




    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            gotoProfile();
        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }
    private void gotoProfile(){
        Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(intent);
    }


}


