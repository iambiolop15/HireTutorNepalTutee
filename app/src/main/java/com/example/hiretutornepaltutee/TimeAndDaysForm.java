package com.example.hiretutornepaltutee;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class TimeAndDaysForm extends Fragment {
    MaterialSpinner hourSpinner,daysSPinner;
    Button nxtButton;
    EditText preferTime;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.timeanddaysform,container,false);
        hourSpinner=view.findViewById(R.id.Hours);
        daysSPinner=view.findViewById(R.id.DaysaWeek);
        preferTime=(EditText) view.findViewById(R.id.PreferredTime);
        nxtButton=view.findViewById(R.id.NextBtn);
        String [] Hour = {"1 hour","1.5 hour","2 hours"};
        ArrayAdapter<String> houradapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Hour);
        houradapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        hourSpinner.setAdapter(houradapter);
        String [] Days ={"Twice a week","Alternate days","Everyday except saturday","Everyday except saturday and sunday"};
        ArrayAdapter<String> daysadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Days);
        daysadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        daysSPinner.setAdapter(daysadapter);
        preferTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar=Calendar.getInstance();
                currentHour=calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute=calendar.get(Calendar.MINUTE);
               timePickerDialog= new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       if(hourOfDay>=12){
                           amPm="PM";
                       }
                       else{
                           amPm="AM";
                       }


                       preferTime.setText(String.format("%02d:%02d",hourOfDay,minute) + amPm);

                   }
               },currentHour,currentMinute,false);
                timePickerDialog.show();
            }
        });
        nxtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new PreForm()).addToBackStack("Fragment").commit();

            }
        });


        return  view;
    }

}
