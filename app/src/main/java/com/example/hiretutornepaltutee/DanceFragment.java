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

public class DanceFragment  extends Fragment {
    RadioGroup danceCategory;
    RadioButton hiphop,salsa,contemporary,classical;
    Button nextBTn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.dance_fragment,container,false);
        danceCategory=view.findViewById(R.id.DanceFormList);
        hiphop=view.findViewById(R.id.Hiphop);
        salsa=view.findViewById(R.id.Salsa);
        contemporary=view.findViewById(R.id.Contemporary);
        classical=view.findViewById(R.id.Classical);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(danceCategory.getCheckedRadioButtonId());
                if (danceCategory.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Category", Toast.LENGTH_SHORT).show();

                }
                else {
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new ReasonToLearnDance()).addToBackStack("Fragment").commit();
                }
                String selectedDancecategory=checkedBtn.getText().toString();
                SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.putString("selectedDancecategory",selectedDancecategory);
                edt.commit();
            }
        });
        return  view;
    }
}
