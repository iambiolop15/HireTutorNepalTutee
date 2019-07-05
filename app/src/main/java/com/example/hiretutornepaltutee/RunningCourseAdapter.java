package com.example.hiretutornepaltutee;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RunningCourseAdapter extends RecyclerView.Adapter<RunningCourseAdapter.UserListViewHolder> {
    private ClickListener clickListener;
    private List<AcademicCourses> usersList = new ArrayList<>();
    public Context  context;

    public RunningCourseAdapter(){}

    @Override
    public UserListViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.courseslist_layout, viewGroup, false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder( UserListViewHolder holder, int position) {
        AcademicCourses currentUser = usersList.get(position);
        holder.title1.setText(currentUser.getUpselectedclass());
        holder.title2.setText(currentUser.getUpsubjects());

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void addUserList(List<AcademicCourses> users, Context context) {
        usersList.addAll(users);
        this.context = context;
        notifyDataSetChanged();
    }

    public void setStudentClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class UserListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title1, title2;



        UserListViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title1 = itemView.findViewById(R.id.CourseTopic1);
            title2 = itemView.findViewById(R.id.CourseTopic2);


        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, usersList.get(getPosition()), getPosition());
            }
        }
    }

    public interface ClickListener {
        void itemClicked(View v,AcademicCourses users, int position);
    }
}