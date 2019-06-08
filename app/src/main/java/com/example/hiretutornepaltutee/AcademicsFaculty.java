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

public class AcademicsFaculty extends Fragment {
    RadioGroup facultylist;
    RadioButton bba,bbs,it;
    Button nextBtn;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.academics_faculty,container,false);
        facultylist=view.findViewById(R.id.Facultylist);
        bba=view.findViewById(R.id.BBA);
        bbs=view.findViewById(R.id.BBS);
        it=view.findViewById(R.id.IT);
        nextBtn=view.findViewById(R.id.NextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(facultylist.getCheckedRadioButtonId());
                String selectedfaculty=checkedBtn.getText().toString();
                if (facultylist.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Faculty", Toast.LENGTH_SHORT).show();

                }


                else{
                    if(facultylist.getCheckedRadioButtonId()==R.id.BBA) {
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new BBASubjects()).addToBackStack("Fragment").commit();
                    }
                    if(facultylist.getCheckedRadioButtonId()==R.id.BBS) {
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new BBSsubjects()).addToBackStack("Fragment").commit();
                    }
                    if(facultylist.getCheckedRadioButtonId()==R.id.IT) {
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new ITsubjects()).addToBackStack("Fragment").commit();
                    }
                    SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt = pref.edit();
                    edt.putString("SelectedClass",selectedfaculty);
                    edt.commit();


                    }

            }
        });
        return view;

    }
}
