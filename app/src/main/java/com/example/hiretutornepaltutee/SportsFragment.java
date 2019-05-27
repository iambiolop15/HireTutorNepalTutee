package com.example.hiretutornepaltutee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SportsFragment extends Fragment {
    RadioGroup sportsCategory;
    RadioButton cricket,football,tabletennis,badminton;
    Button nextBTn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.sports_fragment,container,false);
        sportsCategory=view.findViewById(R.id.SportsCategorylist);
        cricket=view.findViewById(R.id.Cricket);
        football=view.findViewById(R.id.Football);
        tabletennis=view.findViewById(R.id.Tablet);
        badminton=view.findViewById(R.id.Badminton);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(sportsCategory.getCheckedRadioButtonId());
                if (sportsCategory.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Category", Toast.LENGTH_SHORT).show();
                }
                else {
                    String selectedSports=checkedBtn.getText().toString();
                    Bundle bundle=new Bundle();
                    bundle.putString("SelectedSports",selectedSports);
                    new EquipmentAvailibility().setArguments(bundle);
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new EquipmentAvailibility()).addToBackStack("Fragment").commit();
                }
            }
        });
        return  view;
    }
}
