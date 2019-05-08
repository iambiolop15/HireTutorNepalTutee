package com.example.hiretutornepaltutee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import fr.ganfra.materialspinner.MaterialSpinner;

public class LocationForm extends Fragment {
    MaterialSpinner citySpinner;
    TextInputLayout areaLayout,descriptionLayout;
    AppCompatEditText area,description;
    Button nextBtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.locationform,container,false);
        citySpinner=view.findViewById(R.id.City);
        areaLayout=view.findViewById(R.id.Arealay);
        descriptionLayout=view.findViewById(R.id.Descriptionlay);
        area=view.findViewById(R.id.Area);
        description=view.findViewById(R.id.Description);
        nextBtn=view.findViewById(R.id.NextBtn);
        String [] citylist = {"Kathmandu","Bhaktapur","Lalitpur"};
        ArrayAdapter<String> houradapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, citylist);
        houradapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        citySpinner.setAdapter(houradapter);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ScheduleDemoClass()).addToBackStack("Fragment").commit();

            }
        });

        return view;
    }
}
