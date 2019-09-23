package com.example.studentmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleView_Student_TimeTable{

    private Context mContext;
    private SubjectModelAdapter mSubjectModelAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<SubjectModel> subjects, List<String> keys){

        mContext = context;
        mSubjectModelAdapter = new SubjectModelAdapter(subjects, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mSubjectModelAdapter);

    }

    class SubjectModelItemView extends RecyclerView.ViewHolder{

        private TextView mSubId;
        private TextView mSubName;
        private TextView mTeacherName;
        private TextView mVenue;
        private TextView mDay;
        private TextView mTime;

        private String key;

        public SubjectModelItemView(ViewGroup parent){

            super(LayoutInflater.from(mContext).inflate(R.layout.student_timetable_list_item, parent,false));

            mSubId = (TextView) itemView.findViewById(R.id.studentView_subId);
            mSubName = (TextView) itemView.findViewById(R.id.studentView_subName);
            mTeacherName = (TextView) itemView.findViewById(R.id.studentView_teacherName);
            mVenue = (TextView) itemView.findViewById(R.id.studentView_Venue);
            mDay = (TextView) itemView.findViewById(R.id.studentView_day);
            mTime = (TextView) itemView.findViewById(R.id.studentView_startTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, TimeTableDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("subId",mSubId.getText().toString());
                    intent.putExtra("subName",mSubName.getText().toString());
                    intent.putExtra("teacherName",mTeacherName.getText().toString());
                    intent.putExtra("venue",mVenue.getText().toString());
                    intent.putExtra("day",mDay.getText().toString());
                    intent.putExtra("time",mTime.getText().toString());

                    mContext.startActivity(intent);

                }
            });

        }

        public void bind(SubjectModel subjectModel, String key){

            mSubId.setText(subjectModel.getSubId());
            mSubName.setText(subjectModel.getSubName());
            mTeacherName.setText(subjectModel.getTeacherName());
            mVenue.setText(subjectModel.getVenue());
            mDay.setText(subjectModel.getDay());
            mTime.setText(subjectModel.getTime());
            this.key = key;

        }

    }

    class SubjectModelAdapter extends RecyclerView.Adapter<RecycleView_Student_TimeTable.SubjectModelItemView>{

        private List<SubjectModel> mSubjectList;
        private List<String> mKeys;

        public SubjectModelAdapter(List<SubjectModel> mSubjectList, List<String> mKeys) {
            this.mSubjectList = mSubjectList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RecycleView_Student_TimeTable.SubjectModelItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecycleView_Student_TimeTable.SubjectModelItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecycleView_Student_TimeTable.SubjectModelItemView holder, int position) {

            holder.bind(mSubjectList.get(position), mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mSubjectList.size();
        }
    }


}
