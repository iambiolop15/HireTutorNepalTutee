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

public class Hobbyorpassion extends Fragment {
    RadioGroup hobbyorpassion;
    RadioButton hobby,stageperformance,competition;
    Button nextBTn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.hobbyorpassion,container,false);
        hobbyorpassion=view.findViewById(R.id.Reasontolearnmusic);
        hobby=view.findViewById(R.id.Hobby);
        stageperformance=view.findViewById(R.id.StagePerformance);
        competition=view.findViewById(R.id.Competition);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(hobbyorpassion.getCheckedRadioButtonId());
                if (hobbyorpassion.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Faculty", Toast.LENGTH_SHORT).show();
                }
                else{
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new TimeAndDaysForm()).addToBackStack("Fragment").commit();
                }
                String whyToLearn=checkedBtn.getText().toString();
                SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.putString("whyToLearn",whyToLearn);
                edt.commit();
            }
        });
        return view;
    }
}
