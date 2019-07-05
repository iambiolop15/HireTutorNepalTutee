package com.example.hiretutornepaltutee;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RunningCourseFragment extends Fragment {
    public static final String USER_ID = "com.keshar.anujasproject.userId";
    public static final String USER_FULLNAME = "com.keshar.anujasproject.fullname";
    public static final String USER_EMAIl = "com.keshar.anujasproject.email";
    public static final String USER_AGE = "com.keshar.anujasproject.age";
    public static final String USER_PHONE = "com.keshar.anujasproject.phone";
    public static final String USER_GENDER = "com.keshar.anujasproject.gender";
    public static final String USER_IMAGEURL = "com.keshar.anujasproject.imageurl";
    TextView nolist, bookcourse;
    Button gotoHome;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    RunningCourseAdapter runningCourseAdapter;
    List<AcademicCourses> runningcourselist = new ArrayList<>();
    private RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.runningcoursefragment, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        runningCourseAdapter = new RunningCourseAdapter();
        recyclerView = view.findViewById(R.id.RunningCourseView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(runningCourseAdapter);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        nolist = view.findViewById(R.id.nolist);
        bookcourse = view.findViewById(R.id.bookcourse);
        gotoHome = view.findViewById(R.id.gotohome);
        imageView = view.findViewById(R.id.imagebook);
        getAllUser();

        gotoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent i = new Intent(getActivity(), Home.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
                getActivity().finish();
            }
        });
        return view;
    }

    public void getAllUser() {
        runningcourselist.clear();
        runningCourseAdapter.notifyDataSetChanged();
        String key = firebaseDatabase.getReference().child(firebaseAuth.getUid()).push().getKey();
        databaseReference.child("Tutee Courses").child("AcademicCours").child(firebaseAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("my", "" + dataSnapshot);
//                ProfileModel profileModel = dataSnapshot.getValue()
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    String pselectedstudylevel = (String) messageSnapshot.child("upselectedstudylevel").getValue();
                    String pselectedclass = (String) messageSnapshot.child("upselectedclass").getValue();
                    String psubjects = (String) messageSnapshot.child("upsubjects").getValue();
                    String pdurationofclass = (String) messageSnapshot.child("updurationofclass").getValue();
                    String pdaysAweek = (String) messageSnapshot.child("updaysAweek").getValue();
                    String pprefTime = (String) messageSnapshot.child("upprefTime").getValue();
                    String pprefTeacher = (String) messageSnapshot.child("upprefTeacher").getValue();
                    String pcity = (String) messageSnapshot.child("upcity").getValue();
                    String parea = (String) messageSnapshot.child("uparea").getValue();
                    String pscheduledeoclass = (String) messageSnapshot.child("upscheduleddeoclass").getValue();

                    AcademicCourses academicCourses = new AcademicCourses(pselectedstudylevel, pselectedclass, psubjects, pdurationofclass, pdaysAweek, pprefTime, pprefTeacher, pcity, parea, pscheduledeoclass);
                    runningcourselist.add(academicCourses);
                    if (runningcourselist.isEmpty()) {
                        recyclerView.setVisibility(View.GONE);
                        nolist.setVisibility(View.VISIBLE);
                        bookcourse.setVisibility(View.VISIBLE);
                        gotoHome.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.VISIBLE);
                    } else {
                        nolist.setVisibility(View.GONE);
                        bookcourse.setVisibility(View.GONE);
                        gotoHome.setVisibility(View.GONE);
                        imageView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }


                    Log.i("Course", "" + academicCourses);
                }
                runningCourseAdapter.addUserList(runningcourselist, getActivity());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}