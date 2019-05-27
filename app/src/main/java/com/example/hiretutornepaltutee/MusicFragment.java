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

public class MusicFragment extends Fragment {
    RadioGroup musicCategory;
    RadioButton guitar,vocal,flute;
    Button nextBTn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.music_fragment,container,false);
        musicCategory=view.findViewById(R.id.MusicCategorylist);
        guitar=view.findViewById(R.id.Guitar);
        vocal=view.findViewById(R.id.Vocals);
        flute=view.findViewById(R.id.Flute);
        nextBTn=view.findViewById(R.id.NextBtn);
        nextBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedBtn=view.findViewById(musicCategory.getCheckedRadioButtonId());
                if (musicCategory.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Choose Your Category", Toast.LENGTH_SHORT).show();

                }


                else{
                    String checkedValue=checkedBtn.getText().toString();
                    if(musicCategory.getCheckedRadioButtonId()==R.id.Guitar) {
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new InstrumentAvailability()).addToBackStack("Fragment").commit();
                    }
                    if(musicCategory.getCheckedRadioButtonId()==R.id.Vocals) {
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new VocalsCategory()).addToBackStack("Fragment").commit();
                    }
                    if(musicCategory.getCheckedRadioButtonId()==R.id.Flute) {
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new InstrumentAvailability()).addToBackStack("Fragment").commit();
                    }
                }
            }
        });
        return  view;
    }
}
