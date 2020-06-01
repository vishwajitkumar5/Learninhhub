package com.example.learninghub.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learninghub.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;




public class LoginActivity extends AppCompatActivity {
    //facebook


    private EditText usermail,userpassword;
    private Button btnLogin;
    private ProgressBar loginprogress;
    private FirebaseAuth mAuth;
    private Intent Home;
    private ImageView loginPhoto;

    private TextView register;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usermail = findViewById(R.id.Login_user);
        userpassword = findViewById(R.id.Login_password);
        btnLogin = findViewById(R.id.Login);
        loginprogress = findViewById(R.id.Login_progress);
        register = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        Home = new Intent(this, com.example.learninghub.Activities.Home.class);




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(Register);
            }
        });

        loginprogress.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginprogress.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);
                final String mail = usermail.getText().toString();
                final String password = userpassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    showMessage("Please Verify all Fields");
                    btnLogin.setVisibility(View.VISIBLE);
                    loginprogress.setVisibility(View.INVISIBLE);
                } else {
                    signin(mail, password);
                }
            }
        });
    }


    private void signin(String mail, String password) {
        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    loginprogress.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else{
                    btnLogin.setVisibility(View.VISIBLE);
                    loginprogress.setVisibility(View.INVISIBLE);
                    showMessage(task.getException().getMessage());

                }

            }
        });

    }

    private void updateUI() {
        startActivity(Home);
        finish();


    }
    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();

        if(user !=null){
            //user is already connected so we need to redirect him to home page
            updateUI();

        }

    }
}
