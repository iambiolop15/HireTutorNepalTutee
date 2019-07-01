package com.example.hiretutornepaltutee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DanceReviewPage extends Fragment {
    TextView whatToLearn,spaceAvail,reasonToLearn,durationofclass,daysAweek,prefTime,prefTeacher,city,area,scheduledDemoclass;
    Button submitBtn;
    FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dance_review_page,container,false);
        whatToLearn=view.findViewById(R.id.Whatdoyouwanttolearn);
        spaceAvail=view.findViewById(R.id.SpaceAvail);
        reasonToLearn=view.findViewById(R.id.Whydoyouwanttolearn);
        durationofclass=view.findViewById(R.id.Durationoftime);
        daysAweek=view.findViewById(R.id.HowManydaysaWeek);
        prefTime=view.findViewById(R.id.PreferredTime);
        prefTeacher=view.findViewById(R.id.PreferenceofTeacher);
        city=view.findViewById(R.id.SelectedCity);
        area=view.findViewById(R.id.SelectedArea);
        scheduledDemoclass=view.findViewById(R.id.ScheduledDemoclass);
        submitBtn=view.findViewById(R.id.SubmitBtn);
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        final String pwhatToLearn=pref.getString("selectedDancecategory", "empty");
        final String pspaceAvail=pref.getString("danceSpacAvail", "empty");
        final String preasonToLearn=pref.getString("whyToLearn","empty");
        final String pdurationofclass=pref.getString("SelectedClassDuration","empty");
        final String pdaysAweek=pref.getString("SelectedDayAweek","empty");
        final String pprefTime=pref.getString("SelectedPrefTime","empty");
        final String pprefTeacher=pref.getString("PrefTeacher","empty");
        final String pcity=pref.getString("SelectedCity","empty");
        final String parea=pref.getString("SelectedArea","empty");
        final String pscheduleddeoclass=pref.getString("ScheduledDemoClass","empty");
        whatToLearn.setText(pwhatToLearn);
        spaceAvail.setText(pspaceAvail);
        reasonToLearn.setText(preasonToLearn);
        durationofclass.setText(pdurationofclass);
        daysAweek.setText(pdaysAweek);
        prefTime.setText(pprefTime);
        prefTeacher.setText(pprefTeacher);
        city.setText(pcity);
        area.setText(parea);
        scheduledDemoclass.setText(pscheduleddeoclass);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth= FirebaseAuth.getInstance();
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                String key=database.getReference().child(firebaseAuth.getUid()).push().getKey();
                DatabaseReference databaseReference=database.getReference().child("Tutee Courses").child("DanceCours").child(firebaseAuth.getUid()).child(key);
                DanceCourses courses=new DanceCourses(pwhatToLearn,pspaceAvail,preasonToLearn,pdurationofclass,pdaysAweek,pprefTime,pprefTeacher,pcity,parea,pscheduleddeoclass);
                databaseReference.setValue(courses);
            }
        });
        return view;
    }
}
