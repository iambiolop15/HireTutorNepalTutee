package com.example.hiretutornepaltutee;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class EditProfile extends AppCompatActivity {

    private AppCompatEditText editemail,editname,editphone,editage,editgender;
    private TextInputLayout editemaillayout,editnamelayout,editphonelayout,editagelayout,editgenderlayout;
    private Button updateBtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    Fragment fragment=null;
    private ImageView pPic;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    Uri imagepath;
    private static  int PICK_IMAGE=123;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data.getData()!=null) {
            imagepath= data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
                pPic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        updateBtn=findViewById(R.id.updatebtn);
        editemaillayout=findViewById(R.id.Emaillayout);
        editnamelayout=findViewById(R.id.Namelayout);
        editphonelayout=findViewById(R.id.phonelayout);
        editagelayout=findViewById(R.id.agelayout);
        editgenderlayout=findViewById(R.id.genderlayout);
        editemail=(AppCompatEditText) findViewById(R.id.email);
        editname=(AppCompatEditText)findViewById(R.id.name);
        editphone=(AppCompatEditText)findViewById(R.id.phone);
        editage=(AppCompatEditText)findViewById(R.id.age);
        editgender=(AppCompatEditText)findViewById(R.id.gender);
        pPic=findViewById(R.id.profilePic);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        final DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        storageReference=firebaseStorage.getReference();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                editname.setText(userProfile.getUname());
                editage.setText(userProfile.getUage());
                editemail.setText(userProfile.getUemail());
                editgender.setText(userProfile.getUgender());
                editphone.setText(userProfile.getUphone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }

        });
        storageReference.child(firebaseAuth.getUid()).child("Images").child("ProfilePic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).fit().centerCrop().into(pPic);
                Log.i("ABCDE",""+uri);

            }
        });

            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = editnamelayout.getEditText().getText().toString().trim();
                    String age = editagelayout.getEditText().getText().toString().trim();
                    String email = editemaillayout.getEditText().getText().toString().trim();
                    String gender = editgenderlayout.getEditText().getText().toString().trim();
                    String phone = editphonelayout.getEditText().getText().toString().trim();
                    UserProfile userProfile = new UserProfile(name,email,age,phone,gender);
                    databaseReference.setValue(userProfile);
                    StorageReference imageReference=storageReference.child(firebaseAuth.getUid()).child("Images").child("ProfilePic");
                    UploadTask uploadTask=imageReference.putFile(imagepath);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });
                    finish();
                }
            });
            pPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
                }
            });










    }
}
