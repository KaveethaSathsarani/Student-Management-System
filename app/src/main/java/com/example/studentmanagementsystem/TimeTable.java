package com.example.studentmanagementsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class TimeTable extends Fragment {

    private View TimeTableView;
    private RecyclerView SubjectTimeTableList;
    private DatabaseReference Subjectref, tref;
    private String currentsubId;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        TimeTableView = inflater.inflate(R.layout.fragment_timetable, container,false);

        SubjectTimeTableList = (RecyclerView) TimeTableView.findViewById(R.id.studentrecycleview);
        SubjectTimeTableList.setLayoutManager(new LinearLayoutManager(getContext()));

        Subjectref = FirebaseDatabase.getInstance().getReference().child("SubjectModel");

    return TimeTableView;

    }


    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<SubjectModel>().setQuery(Subjectref, SubjectModel.class).build();

        FirebaseRecyclerAdapter<SubjectModel,SubjectsViewHolder> adapter = new FirebaseRecyclerAdapter<SubjectModel, SubjectsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final SubjectsViewHolder holder, int position, @NonNull SubjectModel subjectModel) {

                String ids = getRef(position).getKey();
                Subjectref.child(ids).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String ssubId = dataSnapshot.child("subId").getValue().toString();
                        String ssubName = dataSnapshot.child("subName").getValue().toString();
                        String steacherName = dataSnapshot.child("teacherName").getValue().toString();
                        String svenue = dataSnapshot.child("venue").getValue().toString();
                        String sday = dataSnapshot.child("day").getValue().toString();
                        String stime = dataSnapshot.child("time").getValue().toString();

                        holder.subId.setText(ssubId);
                        holder.subName.setText(ssubName);
                        holder.teacherName.setText(steacherName);
                        holder.venue.setText(svenue);
                        holder.day.setText(sday);
                        holder.time.setText(stime);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @NonNull
            @Override
            public SubjectsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_timetable_list_item, viewGroup, false);

                SubjectsViewHolder viewHolder = new SubjectsViewHolder(view);

                return viewHolder;

            }

        };

        SubjectTimeTableList.setAdapter(adapter);
        adapter.startListening();

    }

    public static class SubjectsViewHolder extends RecyclerView.ViewHolder{

        TextView subId,subName,teacherName,venue,day,time;

        public SubjectsViewHolder(@NonNull View itemView) {
            super(itemView);

            subId = itemView.findViewById(R.id.studentView_subId);
            subName = itemView.findViewById(R.id.studentView_subName);
            teacherName = itemView.findViewById(R.id.studentView_teacherName);
            venue = itemView.findViewById(R.id.studentView_Venue);
            day = itemView.findViewById(R.id.studentView_day);
            time = itemView.findViewById(R.id.studentView_startTime);

        }
    }


}

