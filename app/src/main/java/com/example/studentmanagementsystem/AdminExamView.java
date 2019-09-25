package com.example.studentmanagementsystem;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagementsystem.DBHandlers.ExamModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminExamView extends AppCompatActivity {
    DatabaseReference dref,dref1;
    RecyclerView recyclerView;
    ArrayList<ExamModel> list;
    ExamDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_exam_view);
        dref = FirebaseDatabase.getInstance().getReference().child("Exam");
        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ExamModel>();
        recyclerView.setHasFixedSize(true);
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    ExamModel e = dataSnapshot1.getValue(ExamModel.class);
                    list.add(e);
                }
                adapter = new ExamDetailsAdapter(AdminExamView.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something gone Wrong!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}