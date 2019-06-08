package com.example.hiretutornepaltutee;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mPager;
    private int[] layouts={R.layout.first_slide,R.layout.second_slide,R.layout.third_slide,R.layout.fourth_slide};
    private MpagerAdapter mpagerAdapter;
    private LinearLayout dots_layout;
    private ImageView[] dots;
    private Button btnNext,btnSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mPager=findViewById(R.id.viewpager);
        mpagerAdapter=new MpagerAdapter(layouts,this);
        mPager.setAdapter(mpagerAdapter);
        dots_layout=(LinearLayout)findViewById(R.id.dots_layout);
        btnNext=(Button)findViewById(R.id.btnnext);
        btnSkip=(Button)findViewById(R.id.btnskip);
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
        createDots(0);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if(position==layouts.length-1){
                    btnNext.setText("Login");
                    btnSkip.setVisibility(View.INVISIBLE);
                }
                else{
                    btnNext.setText("Next");
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void createDots(int current_position){
        if(dots_layout!=null)
            dots_layout.removeAllViews();
            dots=new ImageView[layouts.length];
        for (int i=0;i<layouts.length;i++){
            dots[i]=new ImageView(this);
            if(i==current_position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
            }
            else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_dots));
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            dots_layout.addView(dots[i],params);

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnnext:
                loadNextSlide();
                break;
            case R.id.btnskip:
                loadHome();
                new PreferenceManager(this).writePreference();
                break;
        }
    }
    private void loadHome(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void loadNextSlide(){
        int nextSlide=mPager.getCurrentItem()+1;
        if(nextSlide<layouts.length){
            mPager.setCurrentItem(nextSlide);
        }
        else{
            loadHome();
            new PreferenceManager(this).writePreference();
        }
    }

}