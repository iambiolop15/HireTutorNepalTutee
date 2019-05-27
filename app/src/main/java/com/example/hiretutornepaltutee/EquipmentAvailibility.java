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

public class EquipmentAvailibility extends Fragment {
    RadioGroup yesorno;
    RadioButton yes,no;
    Button nextBTn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.equipment_availibilty,container,false);
        yes=view.findViewById(R.id.Yes);
        no=view.findViewById(R.id.No);
        yesorno=view.findViewById(R.id.yesOrNo);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(yesorno.getCheckedRadioButtonId());
                if (yesorno.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Faculty", Toast.LENGTH_SHORT).show();
                }
                else{
                    String checkedValue=checkedBtn.getText().toString();
                    Bundle bundle=getArguments();
                    String selectedSports=bundle.getString("SelectedSports");
                    Log.i("Bhai",""+selectedSports);
                    FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new SportsSpaceAvailibilty()).addToBackStack("Fragment").commit();
                }
            }
        });

        return view;
    }
}