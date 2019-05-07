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

public class ITsubjects extends Fragment {
    RadioGroup itSubjectList;
    RadioButton calculus,numericalMethod,linearAlgebra,cProgram,computerArch,dataAnalysis;
    Button nextBTn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.itsubjects,container,false);
        itSubjectList=view.findViewById(R.id.ITsubjectLists);
        calculus=view.findViewById(R.id.Calculus);
        numericalMethod=view.findViewById(R.id.NumericalMethod);
        linearAlgebra=view.findViewById(R.id.LinearAlgebra);
        cProgram=view.findViewById(R.id.CProgramming);
        computerArch=view.findViewById(R.id.ComputerArchitecture);
        dataAnalysis=view.findViewById(R.id.DAA);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn = view.findViewById(itSubjectList.getCheckedRadioButtonId());
                if (itSubjectList.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Subject", Toast.LENGTH_SHORT).show();

                } else {
                    String checkedValue = checkedBtn.getText().toString();
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new TimeAndDaysForm()).addToBackStack("Fragment").commit();

                }
            }
        });

        return  view;
    }
}
