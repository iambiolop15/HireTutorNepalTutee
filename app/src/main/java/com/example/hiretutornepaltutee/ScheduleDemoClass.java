package com.example.hiretutornepaltutee;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ScheduleDemoClass  extends Fragment  {
    EditText scheduleDay;
    Button nxtBtn;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int cYear;
    int cMonth;
    int cDay;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.scheduledemoclass,container,false);
        scheduleDay=view.findViewById(R.id.ScheduleDay);
        nxtBtn=view.findViewById(R.id.NextBtn);
        scheduleDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                cYear=calendar.get(Calendar.YEAR);
                cMonth=calendar.get(Calendar.MONTH);
                cDay=calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { scheduleDay.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },cYear,cMonth,cDay);

                datePickerDialog.show();


            }
        });
        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scheduledDemoclass=scheduleDay.getText().toString();
                SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.putString("ScheduledDemoClass",scheduledDemoclass);
                edt.commit();
                SharedPreferences preff = getActivity().getSharedPreferences("course",Context.MODE_PRIVATE);
                int selectedcourse=preff.getInt("CourseId",0);
                Log.i("Biplop",""+selectedcourse);
                if(selectedcourse==0) {
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new AcademicsReviewPage()).addToBackStack("Fragment").commit();
                }
                if(selectedcourse==1){
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new CompetitiveReviewPage()).addToBackStack("Fragment").commit();
                }
                if(selectedcourse==2){
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new MusicReviewPage()).addToBackStack("Fragment").commit();
                }
                if(selectedcourse==3){
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new DanceReviewPage()).addToBackStack("Fragment").commit();
                }
                if(selectedcourse==4){
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new SportsReviewPage()).addToBackStack("Fragment").commit();
                }
            }
        });


        return  view;
    }
}
