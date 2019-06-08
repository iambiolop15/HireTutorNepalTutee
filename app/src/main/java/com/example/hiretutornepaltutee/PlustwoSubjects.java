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

public class PlustwoSubjects  extends Fragment {
    RadioGroup plustwoSubjectList;
    RadioButton phy,chem,bio,eng,mathScience,mathComm,account,economics,finance,businessStd;
    Button nextBTn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.plustwo_subjects,container,false);
        plustwoSubjectList=view.findViewById(R.id.PlustwoSubjectlist);
        phy =view.findViewById(R.id.Physics);
        chem=view.findViewById(R.id.Chemistry);
        bio=view.findViewById(R.id.Biology);
        eng=view.findViewById(R.id.English);
        mathScience=view.findViewById(R.id.SMath);
        mathComm=view.findViewById(R.id.MMath);
        account=view.findViewById(R.id.Account);
        economics=view.findViewById(R.id.Economics);
        finance=view.findViewById(R.id.Finance);
        businessStd=view.findViewById(R.id.BusinessStudies);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(plustwoSubjectList.getCheckedRadioButtonId());
                if (plustwoSubjectList.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Subject", Toast.LENGTH_SHORT).show();

                }
                else{
                    String checkedValue=checkedBtn.getText().toString();
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container,new TimeAndDaysForm()).addToBackStack("Fragment").commit();
                    String selectedSubject=checkedBtn.getText().toString();
                    SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt = pref.edit();
                    edt.putString("SelectedSubject",selectedSubject);
                    edt.commit();







                }
            }
        });


        return view;
    }
}
