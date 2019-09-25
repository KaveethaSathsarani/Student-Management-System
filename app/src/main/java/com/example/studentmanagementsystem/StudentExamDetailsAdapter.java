package com.example.studentmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StudentExamDetailsAdapter extends  RecyclerView.Adapter<StudentExamDetailsAdapter.MyViewHolderStudent> {
        Context context;
        ArrayList<ExamModel> exams;

public StudentExamDetailsAdapter(Context c, ArrayList<ExamModel> e){
        this.context = c;
        this.exams = e;
        }

@NonNull



@Override
public void onBindViewHolder(@NonNull MyViewHolderStudent holder, int position) {
        holder.etExamName2.setText(exams.get(position).getExamName());
        holder.etExamId2.setText("Exam ID:   "+exams.get(position).getExamId());
        holder.etExamGrade2.setText("Exam Grade:   "+exams.get(position).getGrade());
        holder.etExamVenue2.setText("Exam Venue:  "+exams.get(position).getExamVenue());
        holder.etExamDate2.setText("Exam Date:   "+exams.get(position).getExamDate());
        holder.etExamTime2.setText("Exam TIme:   "+exams.get(position).getExamTime());
       ExamModel examModel = exams.get(position);
        }

    @NonNull
    @Override
    public MyViewHolderStudent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new MyViewHolderStudent(LayoutInflater.from(context).inflate(R.layout.exam_details_student_card_view,parent,false));
    }



    @Override
public int getItemCount() {
        return exams.size();
        }

class MyViewHolderStudent extends RecyclerView.ViewHolder{
    TextView etExamName2,etExamId2,etExamDate2,etExamVenue2,etExamTime2,etExamGrade2;


    public MyViewHolderStudent(@NonNull View itemView) {
        super(itemView);
        etExamName2=(TextView) itemView.findViewById(R.id.etExamName2);
        etExamId2=(TextView)itemView.findViewById(R.id.etExamId2);
        etExamDate2=(TextView)itemView.findViewById(R.id.etExamDate2);
        etExamGrade2=(TextView)itemView.findViewById(R.id.etExamGrade2);
        etExamVenue2=(TextView)itemView.findViewById(R.id.etExamVenue2);
        etExamTime2=(TextView)itemView.findViewById(R.id.etExamTime2);
    }
}
}
