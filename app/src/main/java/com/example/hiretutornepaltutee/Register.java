package com.example.hiretutornepaltutee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private AppCompatSpinner appCompatSpinner;
    private Button regbutton,backbutton;
    private TextInputEditText uName, uEmail, uAge, uPhone, uPassword, uCpassword;
    private TextInputLayout namelayout, emaillyout, agelayout, phonelayout, passlayout, cPasslayout;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private ImageView profilePic;
    String name,email,phone,age,gender,cPass,pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        appCompatSpinner = findViewById(R.id.spinner);
        regbutton = findViewById(R.id.Regbtn);
        backbutton=findViewById(R.id.Backbtn);
        emaillyout = findViewById(R.id.Emaillayout);
        namelayout = findViewById(R.id.Namelayout);
        agelayout = findViewById(R.id.Agelayout);
        phonelayout = findViewById(R.id.Phonelayout);
        passlayout = findViewById(R.id.Passlayout);
        profilePic=(ImageView) findViewById(R.id.profilepic);
        cPasslayout = findViewById(R.id.CPasslayout);
        List<String> categories = new ArrayList<>();
        categories.add(0, "Gender");
        categories.add("Male");
        categories.add("Female");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<>(this, R.layout.spinnerview, categories);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        appCompatSpinner.setAdapter(arrayAdapter);
        //spinner//

        appCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Gender")) {

                } else {
                     gender= parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //register button//

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ConfirmInput(v)) {
                    registerUser();
                }




            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }

    private void registerUser(){
        String email=emaillyout.getEditText().getText().toString().trim();
        String pass=passlayout.getEditText().getText().toString().trim();
        progressDialog.setMessage("Registering");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    sendEmailVerification();

                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validateName() {
         name = namelayout.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            namelayout.setError("Enter your name");
            return false;
        } else {
            namelayout.setError(null);
            return true;
        }

    }

    private boolean validateEmail() {
         email= emaillyout.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            emaillyout.setError("Enter the email address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emaillyout.setError("Please enter a valid email address");
            return false;

        } else {
            emaillyout.setError(null);
            return true;
        }

    }

    private boolean validatePhone() {
         phone = phonelayout.getEditText().getText().toString().trim();
        if (phone.isEmpty()) {
            phonelayout.setError("Enter your phone number");
            return false;
        } else {
            phonelayout.setError(null);
            return true;
        }

    }

    private boolean validateAge() {
        age = agelayout.getEditText().getText().toString().trim();
        if (age.isEmpty()) {
            agelayout.setError("Enter your age");
            return false;
        } else {
            agelayout.setError(null);
            return true;
        }

    }

    private boolean validatePass() {
       pass = passlayout.getEditText().getText().toString().trim();
        Log.i("variable",pass);
        if (pass.isEmpty()) {
            passlayout.setError("Set the password");
            return false;
        }



        else {
            passlayout.setError(null);
            return true;
        }

    }

    private boolean validateCPass() {
         cPass = cPasslayout.getEditText().getText().toString().trim();
        if (cPass.isEmpty()) {
            cPasslayout.setError("Re type password");
            return false;
        }
        else if(!cPasslayout.getEditText().getText().toString().equals(passlayout.getEditText().getText().toString())){
            cPasslayout.setError("Password do not match");
            return false;

        }
        else {
            cPasslayout.setError(null);
            return true;
        }

    }


    public boolean ConfirmInput(View V) {
        if (!validateAge() | !validateCPass() | !validateEmail() | !validateName() | !validatePass() | !validatePhone()) {
            return false;
        }
        return true;

    }
    private void sendEmailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserdata();
                        Toast.makeText(Register.this, "User Registered, please verify your email", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Register.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(Register.this, "Verification email not sent", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendUserdata(){
        gender=appCompatSpinner.getSelectedItem().toString();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile=new UserProfile(name,email,age,phone,gender);
        myRef.setValue(userProfile);
    }



}
