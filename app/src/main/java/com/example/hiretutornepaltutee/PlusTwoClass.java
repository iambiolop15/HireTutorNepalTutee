package com.example.hiretutornepaltutee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PlusTwoClass extends Fragment {
    RadioGroup plustwoclass;
    RadioButton class11,class12;
    Button nxtBtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.plustwo_class,container,false);
        plustwoclass=view.findViewById(R.id.PlustwoClassLevel);
        class11=view.findViewById(R.id.Eleven);
        class12=view.findViewById(R.id.Twelve);
        nxtBtn=view.findViewById(R.id.NextBtn);
        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(plustwoclass.getCheckedRadioButtonId());
                if (plustwoclass.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Class", Toast.LENGTH_SHORT).show();

                }


                else{

                    String checkedValue=checkedBtn.getText().toString();
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container,new PlustwoSubjects()).addToBackStack("Fragment").commit();







                }

            }
        });

        return view;
    }
}
