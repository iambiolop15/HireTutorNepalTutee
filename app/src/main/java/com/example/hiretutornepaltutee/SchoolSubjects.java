package com.example.hiretutornepaltutee;

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

public class SchoolSubjects extends Fragment {
    RadioGroup schoolSubjectList;
    RadioButton science,math,optMath,account,economics;
    Button nextBTn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.schoolsubjects,container,false);
        schoolSubjectList=view.findViewById(R.id.SchoolSubjectlist);
        science=view.findViewById(R.id.Science);
        math=view.findViewById(R.id.Math);
        optMath=view.findViewById(R.id.OMath);
        account=view.findViewById(R.id.Account);
        economics=view.findViewById(R.id.Economics);
        nextBTn=view.findViewById(R.id.SubmitBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(schoolSubjectList.getCheckedRadioButtonId());
                if (schoolSubjectList.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Subject", Toast.LENGTH_SHORT).show();

                }


                else{
                    String checkedValue=checkedBtn.getText().toString();
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container,new TimeAndDaysForm()).addToBackStack("Fragment").commit();







                }
            }
        });

        return view;
    }
}
