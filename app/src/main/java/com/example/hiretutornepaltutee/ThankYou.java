package com.example.hiretutornepaltutee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ThankYou extends Fragment {
    Button homeBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.thankyou,container,false);
        homeBtn=view.findViewById(R.id.GoHomeBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent i = new Intent(getActivity(),Home.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
                getActivity().finish();


            }
        });

        return  view;

    }
}
