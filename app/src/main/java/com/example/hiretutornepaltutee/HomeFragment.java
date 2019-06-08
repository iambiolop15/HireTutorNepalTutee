package com.example.hiretutornepaltutee;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;


public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private SliderLayout mDemoSlider;
    ListView listView;
    String category_list[]={"Academics Tutor","Competitive Exam Tutor","Music Teacher","Dance Teacher","Sports Coach"};
    String categoryDescription[]={"Tutor for all academic subjects. All classes.",
                                  "Tutor for all Competitive exam like IOE,MOE etc.",
                                  "Guitar,Flute,Vocals etc.",
                                  "Hiphop,Contemporary,Wedding Choreograpghy,Salsa etc.",
                                  "Football,Cricket,Tennis,Badminton,Futsal etc."};
    int images[]={R.drawable.academics,R.drawable.competitive,R.drawable.music,R.drawable.dance,R.drawable.football};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,null);
        mDemoSlider = view.findViewById(R.id.slider);
        listView=view.findViewById(R.id.CategorylistView);
        Myadapter myadapter=new Myadapter(getContext(),category_list,categoryDescription,images);
        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences preff = getActivity().getSharedPreferences("course",Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = preff.edit();
                edt.putInt("CourseId",position);
                Log.i("Position",""+position);
                edt.commit();
                if(position==0){
                    startActivity(new Intent(getActivity(),Academics.class));

                }
                if(position==1){
                    startActivity(new Intent(getActivity(),CompetitiveExam.class));


                }
                if(position==2){
                    startActivity(new Intent(getActivity(),Music.class));


                }
                if(position==3){
                    startActivity(new Intent(getActivity(),Dance.class));

                }
                if(position==4){
                    startActivity(new Intent(getActivity(),Sports.class));
                }


            }
        });
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Welcome",R.drawable.firstslide);
        file_maps.put("Academics and Competitive exams",R.drawable.secondslide);
        file_maps.put("Sports",R.drawable.thirdslide);
        file_maps.put("Dance",R.drawable.fourthslide);
        file_maps.put("Music",R.drawable.fifthslide);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    private PopupMenu makeText(HomeFragment homeFragment, String extra, int lengthShort) {
        return null;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    class Myadapter extends ArrayAdapter<String>{
        Context context;
        String rTitle[];
        String rDescription[];
        int rImagees[];
        Myadapter (Context c,String title[],String description[],int imgs[]){
            super(c,R.layout.row,R.id.CategoryTitle,title);
            this.context=c;
            this.rTitle=title;
            this.rDescription=description;
            this.rImagees=imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images=row.findViewById(R.id.CategoryImage);
            TextView title=row.findViewById(R.id.CategoryTitle);
            TextView description=row.findViewById(R.id.CategoryDescription);
            images.setImageResource(rImagees[position]);
            title.setText(rTitle[position]);
            description.setText(rDescription[position]);
            return row;
        }
    }
}

