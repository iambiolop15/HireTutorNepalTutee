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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button regbutton,loginbtn;
    TextView forgotPass;
    private TextInputLayout email_layout,pass_layout;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog  progressDialog;
    private FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        //if(firebaseUser!=null){
        //finish();
        //startActivity(new Intent(getApplicationContext(),Home.class));
        //}
        setContentView(R.layout.activity_main);
        regbutton=findViewById(R.id.registerbtn);
        loginbtn=findViewById(R.id.loginbtn);
        forgotPass=findViewById(R.id.forgotpass);
        email_layout=findViewById(R.id.Emaillayout);

        pass_layout=findViewById(R.id.Passlayout);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConfirmInput(v)) {
                    validate(email_layout.getEditText().getText().toString().trim(), pass_layout.getEditText().getText().toString().trim());
                }

            }
        });
        try{
            regbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),Register.class);
                    startActivity(intent);
                    finish();
                }
            });}
        catch(Exception ex){
            Log.i("Error",ex.getMessage());

        }
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgotPass.class));
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
                    firebaseAuth.getCurrentUser().getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                        @Override
                        public void onSuccess(GetTokenResult getTokenResult) {
                            String token_id=getTokenResult.getToken();
                            String current_id=firebaseAuth.getCurrentUser().getUid();
                            Map<String,Object> tokenMap=new HashMap<>();
                            tokenMap.put("token_id",token_id);
                            firebaseFirestore.collection("Users").document(current_id).update(tokenMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    String userId=firebaseAuth.getUid();
                                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                                    DatabaseReference myref=firebaseDatabase.getReference();
                                    myref.child("Tutee").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if(dataSnapshot.exists()){
                                                progressDialog.dismiss();
                                                checkEmailverification();
                                            }
                                            else {
                                                firebaseAuth.signOut();
                                                progressDialog.dismiss();
                                                Toast.makeText(MainActivity.this, "User is registered as Tutor", Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });


                                }
                            });
                        }
                    });


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
            pass_layout.setError("Enter the password");
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
    private  void checkEmailverification(){
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailFlag=firebaseUser.isEmailVerified();
        if(emailFlag){
            finish();
            startActivity(new Intent(MainActivity.this,Home.class));

        }
        else{
            Toast.makeText(this, "Verify Email First", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }




}