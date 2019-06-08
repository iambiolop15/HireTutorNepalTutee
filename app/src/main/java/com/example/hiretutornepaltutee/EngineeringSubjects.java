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

public class EngineeringSubjects  extends Fragment {
    RadioGroup engineeringsubjectList;
    RadioButton phy,chem,maths;
    Button nextBTn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.engineeringsubjects,container,false);
        engineeringsubjectList=view.findViewById(R.id.EngineeringSubjectlist);
        maths=view.findViewById(R.id.Maths);
        phy =view.findViewById(R.id.Phy);
        chem =view.findViewById(R.id.Chem);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(engineeringsubjectList.getCheckedRadioButtonId());
                if (engineeringsubjectList.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Subject", Toast.LENGTH_SHORT).show();
                }
                else{

                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new TimeAndDaysForm()).addToBackStack("Fragment").commit();
                }
                String selectedSubject=checkedBtn.getText().toString();
                SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.putString("SelectedSubject",selectedSubject);
                edt.commit();

            }
        });
        return view;
    }
}
