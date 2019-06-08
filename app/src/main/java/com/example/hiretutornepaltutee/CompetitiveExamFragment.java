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

public class CompetitiveExamFragment extends Fragment {
    RadioGroup competitiveExamFaculty;
    RadioButton engineering,medicalScience,management;
    Button nextBTn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      final View view=inflater.inflate(R.layout.competitiveexam_fragment,container,false);
      competitiveExamFaculty=view.findViewById(R.id.CompetitiveExamlist);
      engineering=view.findViewById(R.id.Engineering);
      medicalScience=view.findViewById(R.id.MedicalScience);
      management=view.findViewById(R.id.Management);
      nextBTn=view.findViewById(R.id.NextBtn);
      nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(competitiveExamFaculty.getCheckedRadioButtonId());
                if (competitiveExamFaculty.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Faculty", Toast.LENGTH_SHORT).show();

                }


                else{

                    if(competitiveExamFaculty.getCheckedRadioButtonId()==R.id.Engineering) {
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new EngineeringExamList()).addToBackStack("Fragment").commit();
                    }
                    if(competitiveExamFaculty.getCheckedRadioButtonId()==R.id.MedicalScience) {
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new MedicalScience()).addToBackStack("Fragment").commit();
                    }
                    if(competitiveExamFaculty.getCheckedRadioButtonId()==R.id.Management) {
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new ManagementExamList()).addToBackStack("Fragment").commit();
                    }
                    String selectedcompetitiveExam=checkedBtn.getText().toString();
                    SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt = pref.edit();
                    edt.putString("SelectedCompetitiveExam",selectedcompetitiveExam);
                    edt.commit();


                }

            }
        });
      return  view;
    }
}
