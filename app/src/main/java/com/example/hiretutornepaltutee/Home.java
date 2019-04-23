package com.example.hiretutornepaltutee;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Home extends AppCompatActivity {
    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavigation);

        fragment = new HomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Framelayout, fragment);
        fragmentTransaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(item.getItemId()==R.id.home) {
                    fragment = new HomeFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.Framelayout, fragment);
                    fragmentTransaction.commit();
                }
                    else if(item.getItemId()==R.id.course) {
                    fragment = new CoursesFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.Framelayout, fragment);
                    fragmentTransaction.commit();
                }
                else if(item.getItemId()==R.id.notification) {
                    fragment = new NotificationFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.Framelayout, fragment);
                    fragmentTransaction.commit();
                }
                else if(item.getItemId()==R.id.chat) {
                    fragment = new ChatFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.Framelayout, fragment);
                    fragmentTransaction.commit();
                }
                else if(item.getItemId()==R.id.profile) {
                    fragment = new ProfileFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.Framelayout, fragment);
                    fragmentTransaction.commit();
                }

                return true;
            }
        });

    }
}
