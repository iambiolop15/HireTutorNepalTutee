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

public class SchoolClass extends Fragment {
    RadioGroup schoolsubjectlist;
    RadioButton class1,class2,class3,class4,class5,class6,class7,class8,class9,class10;
    Button nextBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.school_class,container,false);
        schoolsubjectlist=view.findViewById(R.id.SchoolClassList);
        class1=view.findViewById(R.id.Class1);
        class2=view.findViewById(R.id.Class2);
        class3=view.findViewById(R.id.Class3);
        class4=view.findViewById(R.id.Class4);
        class5=view.findViewById(R.id.Class5);
        class6=view.findViewById(R.id.Class6);
        class7=view.findViewById(R.id.Class7);
        class8=view.findViewById(R.id.Class8);
        class9=view.findViewById(R.id.Class9);
        class10=view.findViewById(R.id.Class10);
        nextBtn=view.findViewById(R.id.NextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(schoolsubjectlist.getCheckedRadioButtonId());
                if (schoolsubjectlist.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Class", Toast.LENGTH_SHORT).show();

                }


                else{
                    String selectedfaculty=checkedBtn.getText().toString();
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container,new SchoolSubjects()).addToBackStack("Fragment").commit();
                    SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt = pref.edit();
                    edt.putString("SelectedClass",selectedfaculty);
                    edt.commit();

                }


            }
        });

        return  view;
    }
}
