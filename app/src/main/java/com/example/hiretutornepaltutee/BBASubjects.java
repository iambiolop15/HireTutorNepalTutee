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

public class BBASubjects extends Fragment {
    RadioGroup bbaSubjectList;
    RadioButton financeAc,businessAc,businessMath,businessFinance,businessStat,bankLawPractice;
    Button nextBTn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.bbasubjects,container,false);
        bbaSubjectList=view.findViewById(R.id.BBAsubjectLists);
        financeAc=view.findViewById(R.id.FinancialAccounting);
        businessAc=view.findViewById(R.id.BusinessAccounting);
        businessFinance=view.findViewById(R.id.BusinessFinance);
        businessMath=view.findViewById(R.id.BusinessMath);
        businessStat=view.findViewById(R.id.Businesstat);
        bankLawPractice=view.findViewById(R.id.BankingLaw);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(bbaSubjectList.getCheckedRadioButtonId());
                if (bbaSubjectList.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Subject", Toast.LENGTH_SHORT).show();

                }


                else{
                    String checkedValue=checkedBtn.getText().toString();
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container,new TimeAndDaysForm()).addToBackStack("Fragment").commit();







                }
            }
        });
        return  view;

    }
}
