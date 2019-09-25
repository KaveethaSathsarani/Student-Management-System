package com.example.studentmanagementsystem;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Exam extends Fragment {
    DatabaseReference dref,dref1;
    RecyclerView recyclerView;
    ArrayList<ExamModel> list;
    StudentExamDetailsAdapter adapter;
    EditText etStudentExamId;
    Button searchBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       Log.i("Info","Entered");
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_exam,null);
        dref = FirebaseDatabase.getInstance().getReference().child("Exam");
        recyclerView = view.findViewById(R.id.myRecycler1);
        etStudentExamId = view.findViewById(R.id.etStudentExamId);
        searchBtn = view.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searcbBtnClicked(view);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<ExamModel>();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    ExamModel e = dataSnapshot1.getValue(ExamModel.class);
                    list.add(e);
                }
                adapter = new StudentExamDetailsAdapter(getContext(),list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Something gone Wrong!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
public void searcbBtnClicked(View view){

    dref = FirebaseDatabase.getInstance().getReference().child("Exam");
   // recyclerView = view.findViewById(R.id.myRecycler1);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    list = new ArrayList<ExamModel>();
    dref.orderByChild("examId").equalTo(etStudentExamId.getText().toString()).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ExamModel e = dataSnapshot1.getValue(ExamModel.class);
                    list.add(e);
                }
                adapter = new StudentExamDetailsAdapter(getContext(), list);
                recyclerView.setAdapter(adapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(getContext(),"Something gone Wrong!",Toast.LENGTH_SHORT).show();
        }
    });

}


}

