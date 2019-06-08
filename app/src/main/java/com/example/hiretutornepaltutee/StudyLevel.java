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

public class StudyLevel extends Fragment {
    private RadioGroup academicList;
    private RadioButton aSchool,aPlusTwo,aBachelor;
    private Button nextBtn;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View view=inflater.inflate(R.layout.studylevel,container,false);
       academicList= view.findViewById(R.id.studylevel);
       aSchool=view.findViewById(R.id.School);
       aPlusTwo=view.findViewById(R.id.Plustwo);
       aBachelor=view.findViewById(R.id.Bachelor);
       nextBtn=view.findViewById(R.id.NextBtn);

       nextBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RadioButton checkedBtn=view.findViewById(academicList.getCheckedRadioButtonId());
               if (academicList.getCheckedRadioButtonId() == -1) {
                   Toast.makeText(getActivity(), "Choose Your School Level", Toast.LENGTH_SHORT).show();

                   }


                else{

                       String checkedValue=checkedBtn.getText().toString();

                                     if(academicList.getCheckedRadioButtonId()==R.id.Bachelor){
                                         FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                                         fr.replace(R.id.fragment_container,new AcademicsFaculty()).addToBackStack("Fragment").commit();

                                     }
                                     if(academicList.getCheckedRadioButtonId()==R.id.Plustwo){
                                         FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                                         fr.replace(R.id.fragment_container,new PlusTwoClass()).addToBackStack("Fragment").commit();

                                     }
                                     if(academicList.getCheckedRadioButtonId()==R.id.School){
                                         FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                                         fr.replace(R.id.fragment_container,new SchoolClass()).addToBackStack("Fragment").commit();

                                     }
                   String selectedLevel=checkedBtn.getText().toString();
                   SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                   SharedPreferences.Editor edt = pref.edit();
                   edt.putString("SelectedLevel",selectedLevel);
                   edt.commit();






                   }




           }
       });
       return  view;
    }
}
