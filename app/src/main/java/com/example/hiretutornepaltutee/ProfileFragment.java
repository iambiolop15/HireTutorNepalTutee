package com.example.hiretutornepaltutee;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private Button logoutbtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile,null);
        firebaseAuth=FirebaseAuth.getInstance();
        logoutbtn=(Button) view.findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });

        return view;

    }
}