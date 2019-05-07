package com.example.hiretutornepaltutee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class TimeAndDaysForm extends Fragment {
    MaterialSpinner hourSpinner,daysSPinner;
    Button nxtButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.timeanddaysform,container,false);
        hourSpinner=view.findViewById(R.id.Hours);
        daysSPinner=view.findViewById(R.id.DaysaWeek);
        nxtButton=view.findViewById(R.id.NextBtn);
        String [] Hour = {"Time at Residence","Under 6 months","6-12 months","1-2 years","2-4 years","4-8 years","8-15 years","Over 15 years",};
        ArrayAdapter<String> houradapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Hour);
        houradapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        hourSpinner.setAdapter(houradapter);
        String [] Days ={"Time at Residence","Under 6 months","6-12 months","1-2 years","2-4 years","4-8 years","8-15 years","Over 15 years",};
        ArrayAdapter<String> daysadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Days);
        daysadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        daysSPinner.setAdapter(daysadapter);

        return  view;
    }

}
