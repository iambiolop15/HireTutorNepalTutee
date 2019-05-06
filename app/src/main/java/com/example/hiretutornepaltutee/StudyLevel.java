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

public class StudyLevel extends Fragment {
    private RadioGroup academicList;
    private RadioButton aSchool,aPlusTwo,aBachelor,aMasters;
    private Button submitBtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View view=inflater.inflate(R.layout.studylevel,container,false);
       academicList= view.findViewById(R.id.studylevel);
       aSchool=view.findViewById(R.id.School);
       aPlusTwo=view.findViewById(R.id.Plustwo);
       aBachelor=view.findViewById(R.id.Bachelor);
       aMasters=view.findViewById(R.id.Master);
       submitBtn=view.findViewById(R.id.SubmitBtn);

       submitBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RadioButton checkedBtn=view.findViewById(academicList.getCheckedRadioButtonId());
               String checkedValue=checkedBtn.getText().toString();
                if(academicList.isSelected()){
                    Toast.makeText(getActivity(), "Choose Your School Level", Toast.LENGTH_SHORT).show();

                }
                else{
                    FragmentTransaction fr=getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container,new AcademicsDetailForm1()).addToBackStack("Fragment").commit();


                }



           }
       });
       return  view;
    }
}
