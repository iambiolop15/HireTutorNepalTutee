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

public class ReasonToLearnDance extends Fragment {
    RadioGroup hobbyorpassion;
    RadioButton hobby,stageperformance,competition;
    Button nextBTn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.reason_to_learn_dance,container,false);
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
                    String checkedValue=checkedBtn.getText().toString();
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new DanceClassSpaceAvailibility()).addToBackStack("Fragment").commit();
                }
            }
        });
        return view;
    }
}
