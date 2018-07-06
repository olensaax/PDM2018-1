package com.moviles.examenfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText mEmail;
    private EditText mPassword;
    private Button loginBtn;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){//logged in
                    //go to home
                    Toast.makeText(LoginActivity.this, "Logueado", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        };

        mEmail = (EditText) findViewById(R.id.email_login_field);
        mPassword = (EditText) findViewById(R.id.pass_login_field);
        loginBtn = (Button) findViewById(R.id.login_btn);
        signupBtn = (Button) findViewById(R.id.sign_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String pass = mPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //go to home
                            Toast.makeText(LoginActivity.this, "Logueado", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "Error en credenciales", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    protected void onResume(){
        super.onResume();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

