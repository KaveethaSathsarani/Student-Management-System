package com.example.studentmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExamDetailsAdapter extends RecyclerView.Adapter<ExamDetailsAdapter.MyViewHolder> {
   Context context;
   ArrayList<ExamModel> exams;

   public ExamDetailsAdapter(Context c,ArrayList<ExamModel> e){
       this.context = c;
       this.exams = e;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.exam_details_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.etExamName1.setText(exams.get(position).getExamName());
            holder.etExamId1.setText("Exam ID:   "+exams.get(position).getExamId());
            holder.etExamGrade1.setText("Exam Grade:   "+exams.get(position).getGrade());
            holder.etExamVenue1.setText("Exam Venue:  "+exams.get(position).getExamVenue());
            holder.etExamDate1.setText("Exam Date:   "+exams.get(position).getExamDate());
            holder.etExamTime1.setText("Exam TIme:   "+exams.get(position).getExamTime());
            ExamModel examModel = exams.get(position);
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView etExamName1,etExamId1,etExamDate1,etExamVenue1,etExamTime1,etExamGrade1;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            etExamName1=(TextView) itemView.findViewById(R.id.etExamName1);
            etExamId1=(TextView)itemView.findViewById(R.id.etExamId1);
            etExamDate1=(TextView)itemView.findViewById(R.id.etExamDate1);
            etExamGrade1=(TextView)itemView.findViewById(R.id.etExamGrade1);
            etExamVenue1=(TextView)itemView.findViewById(R.id.etExamVenue1);
            etExamTime1=(TextView)itemView.findViewById(R.id.etExamTime1);
        }
    }
}
