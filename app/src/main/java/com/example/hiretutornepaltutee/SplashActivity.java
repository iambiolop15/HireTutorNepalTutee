package com.example.hiretutornepaltutee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            try {
                if (new PreferenceManager(this).checkPreference()) {
                    if (firebaseUser != null) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), Home.class));
                    } else {
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }


                } else {
                    finish();
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));


                }
            }
            catch (Exception e){
                Log.i("List",e.getMessage());
            }



    }
}
