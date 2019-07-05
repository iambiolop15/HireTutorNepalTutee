package com.example.hiretutornepaltutee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
public class Register extends AppCompatActivity {
    TextInputLayout email,name,age,phone,pass,cPass;
    Button regBTn,loginBtn;
    private AppCompatSpinner spinner;
    ImageView profilePic;
    String uEmail,uName,uAge,uPhone,uPass,ucPass,ugender;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private static  int PICK_IMAGE=123;
    FirebaseDatabase firebaseDatabase;
    String  imageurl;
    Uri imagepath;
    CheckBox checkBox;
    TextView termsandconditions;
    private StorageReference storageReference;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data.getData()!=null) {
            imagepath= data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
                profilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.Emaillayout);
        name=findViewById(R.id.Namelayout);
        age=findViewById(R.id.Agelayout);
        phone=findViewById(R.id.Phonelayout);
        pass=findViewById(R.id.Passlayout);
        cPass=findViewById(R.id.CPasslayout);
        regBTn=findViewById(R.id.Regbtn);
        loginBtn=findViewById(R.id.Backbtn);
        spinner=(AppCompatSpinner)findViewById(R.id.spinner);
        profilePic=findViewById(R.id.profilepic);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseStorage=firebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        termsandconditions=findViewById(R.id.termsandconditions);
        checkBox=findViewById(R.id.checkbox);
        final List<String> gender=new ArrayList<>();
        gender.add(0,"Gender");
        gender.add("Male");
        gender.add("Female");
        gender.add("Others");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter=new ArrayAdapter<>(getApplication(),R.layout.spinnerview,gender);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Gender")){

                }
                else {
                    ugender=parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        regBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){

                    if (ConfirmInput(v)) {
                        registerUser();
                    }}
                else{
                    Toast.makeText(Register.this, "U must agree to all the terms and conditions", Toast.LENGTH_SHORT).show();

                }
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }
        });
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
            }
        });
        termsandconditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),TermsAndconditions.class);
                startActivity(intent);
            }
        });

    }
    private void registerUser(){
        progressDialog.setMessage("Registering");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(uEmail,uPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    sendEmailVerification();
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    private boolean validateName() {
        uName=name.getEditText().getText().toString().trim();
        if (uName.isEmpty()) {
            name.setError("Enter your name");
            return false;
        } else {
            name.setError(null);
            return true;
        }

    }

    private boolean validateEmail() {
        uEmail=email.getEditText().getText().toString().trim();
        if (uEmail.isEmpty()) {
            email.setError("Enter the email address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(uEmail).matches()) {
            email.setError("Please enter a valid email address");
            return false;

        } else {
            email.setError(null);
            return true;
        }

    }

    private boolean validatePhone() {
        uPhone=phone.getEditText().getText().toString().trim();
        if (uPhone.isEmpty()) {
            phone.setError("Enter your phone number");
            return false;
        } else {
            phone.setError(null);
            return true;
        }

    }

    private boolean validateAge() {
        uAge=age.getEditText().getText().toString().trim();
        if (uAge.isEmpty()) {
            age.setError("Enter your age");
            return false;
        } else {
            age.setError(null);
            return true;
        }

    }

    private boolean validatePass() {
        uPass=pass.getEditText().getText().toString().trim();
        if (uPass.isEmpty()) {
            pass.setError("Set the password");
            return false;
        }



        else {
            pass.setError(null);
            return true;
        }

    }

    private boolean validateCPass() {

        if (cPass.getEditText().getText().toString().trim().isEmpty()) {
            cPass.setError("Re type password");
            return false;
        }
        else if(!pass.getEditText().getText().toString().trim().equals(cPass.getEditText().getText().toString().trim())){
            cPass.setError("Password do not match");
            return false;

        }
        else {
            cPass.setError(null);
            return true;
        }
    }
    private boolean validateProfilePic (){
        if(imagepath==null){
            Toast.makeText(this, "Image must be selected", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;


    }

    public boolean ConfirmInput(View V) {
        if (!validateAge() | !validateCPass() | !validateEmail() | !validateName() | !validatePass() | !validatePhone()| !validateProfilePic()) {
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
        ugender=spinner.getSelectedItem().toString();
        firebaseDatabase= FirebaseDatabase.getInstance();
        final DatabaseReference myRef=firebaseDatabase.getReference().child("Tutee").child(firebaseAuth.getUid());
        final StorageReference imageReference=storageReference.child(firebaseAuth.getUid()).child("Tutee").child("Images").child("ProfilePic");
        UploadTask uploadTask=imageReference.putFile(imagepath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("biplop",e.getMessage());

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageurl = uri.toString();
                        UserProfile userProfile=new UserProfile(uName,uEmail,uAge,uPhone,ugender,imageurl);
                        myRef.setValue(userProfile);
                        Log.i("image", "" + imageurl);
                        //Do what you want with the url
                    }

                });


            }
        });


    }
}