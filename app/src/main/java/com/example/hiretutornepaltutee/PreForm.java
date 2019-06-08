package com.example.hiretutornepaltutee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PreForm  extends Fragment {
    RadioGroup gender;
    RadioButton pMale,pFemale,noPref;
    Button nextBtn;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.prefform,container,false);
        gender=view.findViewById(R.id.Gender);
        pFemale=view.findViewById(R.id.Female);
        pMale=view.findViewById(R.id.Male);
        noPref=view.findViewById(R.id.NoPreference);
        nextBtn=view.findViewById(R.id.NextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(gender.getCheckedRadioButtonId());

                if(gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Preference", Toast.LENGTH_SHORT).show();
                }


                else{
                    String checkedValue=checkedBtn.getText().toString();
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new LocationForm()).addToBackStack("Fragment").commit();
                    String prefTeacher=checkedBtn.getText().toString();
                    SharedPreferences pref =getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt = pref.edit();
                    edt.putString("PrefTeacher",prefTeacher);
                    edt.commit();


                }

            }
        });

        return view;
    }
}
