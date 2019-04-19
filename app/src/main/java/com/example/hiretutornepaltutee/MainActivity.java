package com.example.hiretutornepaltutee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button regbutton,loginbtn;
    TextInputLayout email_layout,pass_layout;
    TextInputEditText email,pass;
    TextView forgot_pass;
    private FirebaseAuth firebaseAuth;
    ProgressDialog  progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regbutton=findViewById(R.id.registerbtn);
        loginbtn=findViewById(R.id.loginbtn);
        forgot_pass=findViewById(R.id.forgotpass);
        email_layout=findViewById(R.id.Emaillayout);
        pass_layout=findViewById(R.id.Passlayout);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class));
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConfirmInput(v)) {
                    validate(email_layout.getEditText().getText().toString().trim(), pass_layout.getEditText().getText().toString().trim());
                }

            }
        });
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void validate(String username,String password){
        progressDialog.setMessage("Logging in");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,Home.class));
                    finish();
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Invalid Email/Password", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    private boolean validateEmail() {
        String Email = email_layout.getEditText().getText().toString().trim();
        if (Email.isEmpty()) {
            email_layout.setError("Enter the email address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email_layout.setError("Please enter a valid email address");
            return false;

        } else {
            email_layout.setError(null);
            return true;
        }

    }
    private boolean validatePass() {
        String pass = pass_layout.getEditText().getText().toString().trim();
        if (pass.isEmpty()) {
            pass_layout.setError("Set the password");
            return false;
        }



        else {
            pass_layout.setError(null);
            return true;
        }

    }
    public boolean ConfirmInput(View V) {
        if (!validateEmail() | !validatePass()) {
            return false;
        }
        return true;

    }





}
