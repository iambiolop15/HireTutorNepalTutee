package com.example.hiretutornepaltutee;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    RelativeLayout editProfile,myCourses,faq,sendFeedback,about,privacy,logout;
    Fragment fragment=null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile,null);
        firebaseAuth=FirebaseAuth.getInstance();

        editProfile=view.findViewById(R.id.editprofileLayout);
        myCourses=view.findViewById(R.id.CoursesLayout);
        faq=view.findViewById(R.id.FAQLayout);
        sendFeedback=view.findViewById(R.id.SendFeedbackLayout);
        about=view.findViewById(R.id.AboutLayout);
        privacy=view.findViewById(R.id.PrivacyLayout);
        logout=view.findViewById(R.id.LogoutLayout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(),MainActivity.class));

            }
        });

        myCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new CoursesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Framelayout, fragment);
                fragmentTransaction.commit();

            }


        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Privacy.class));
            }
        });
        return view;

    }
}