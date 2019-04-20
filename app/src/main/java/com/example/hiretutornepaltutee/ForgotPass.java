package com.example.hiretutornepaltutee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {
    Button resetBtn;
    private TextInputLayout emailLayout;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        resetBtn=findViewById(R.id.Resetpassbtn);
        emailLayout=findViewById(R.id.Emaillayout);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Verifying Email");
                progressDialog.show();
                String email=emailLayout.getEditText().getText().toString().trim();
                if(ConfirmInput(v)){
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPass.this, "Password reset email sent", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ForgotPass.this,MainActivity.class));

                            }
                            else{
                                Toast.makeText(ForgotPass.this, "enter a valid email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

    }
    private boolean validateEmail() {
        String Email = emailLayout.getEditText().getText().toString().trim();
        if (Email.isEmpty()) {
            emailLayout.setError("Enter the email address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            emailLayout.setError("Please enter a valid email address");
            return false;

        } else {
            emailLayout.setError(null);
            return true;
        }
    }
    public boolean ConfirmInput(View V) {
        if (!validateEmail()) {
            return false;
        }
        return true;

    }
}
