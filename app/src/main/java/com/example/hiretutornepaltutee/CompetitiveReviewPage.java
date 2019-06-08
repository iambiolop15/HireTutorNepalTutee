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

public class CompetitiveReviewPage extends Fragment {
    TextView competitiveExamField,whichCompetitiveExam,subjects,durationofclass,daysAweek,prefTime,prefTeacher,city,area,scheduledDemoclass;
    Button submitBtn;
    FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.competitive_review_page,container,false);
        competitiveExamField=view.findViewById(R.id.CompetitiveExamField);
        whichCompetitiveExam=view.findViewById(R.id.WhichExam);
        subjects=view.findViewById(R.id.Subject);
        durationofclass=view.findViewById(R.id.Durationoftime);
        daysAweek=view.findViewById(R.id.HowManydaysaWeek);
        prefTime=view.findViewById(R.id.PreferredTime);
        prefTeacher=view.findViewById(R.id.PreferenceofTeacher);
        city=view.findViewById(R.id.SelectedCity);
        area=view.findViewById(R.id.SelectedArea);
        scheduledDemoclass=view.findViewById(R.id.ScheduledDemoclass);
        submitBtn=view.findViewById(R.id.SubmitBtn);
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        final String pcompetitiveExamField=pref.getString("SelectedCompetitiveExam", "empty");
        final String pwhichCompetitiveExam=pref.getString("SelectedExam","empty");
        final String psubjects=pref.getString("SelectedSubject","empty");
        final String pdurationofclass=pref.getString("SelectedClassDuration","empty");
        final String pdaysAweek=pref.getString("SelectedDayAweek","empty");
        final String pprefTime=pref.getString("SelectedPrefTime","empty");
        final String pprefTeacher=pref.getString("PrefTeacher","empty");
        final String pcity=pref.getString("SelectedCity","empty");
        final String parea=pref.getString("SelectedArea","empty");
        final String pscheduleddeoclass=pref.getString("ScheduledDemoClass","empty");
        competitiveExamField.setText(pcompetitiveExamField);
        whichCompetitiveExam.setText(pwhichCompetitiveExam);
        subjects.setText(psubjects);
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
                DatabaseReference databaseReference=database.getReference().child("Courses").child("CompetitiveCours").child(firebaseAuth.getUid()).child(key);
                CompetitiveCourses courses=new CompetitiveCourses(pcompetitiveExamField,pwhichCompetitiveExam,psubjects,pdurationofclass,pdaysAweek,pprefTime,pprefTeacher,pcity,parea,pscheduleddeoclass);
                databaseReference.setValue(courses);
            }
        });
        return view;
    }
}
