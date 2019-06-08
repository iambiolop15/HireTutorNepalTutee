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

public class EngineeringExamList extends Fragment {
    RadioGroup engineeringExamList;
    RadioButton kU,tU;
    Button nextBTn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.engineeringexamlist,container,false);
        engineeringExamList=view.findViewById(R.id.EngineeringExamlist);
        kU=view.findViewById(R.id.KU);
        tU=view.findViewById(R.id.IOE);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(engineeringExamList.getCheckedRadioButtonId());
                if (engineeringExamList.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Faculty", Toast.LENGTH_SHORT).show();

                }


                else{

                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new EngineeringSubjects()).addToBackStack("Fragment").commit();
                    }
                String selectedEExam=checkedBtn.getText().toString();
                SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.putString("SelectedExam",selectedEExam);
                edt.commit();
            }


        });
        return view;
    }
}

