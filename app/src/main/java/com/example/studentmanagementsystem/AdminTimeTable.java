package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class AdminTimeTable extends AppCompatActivity {

    EditText subId,subName,time,teacherName,venue,day;
    Button add_subject;
    //Button add_data3000;
    //FirebaseDatabase database;
    DatabaseReference ref;
    //DatabaseReference ref3001;
    //DatabaseReference ref3002;
    SubjectModel subjectModel;
    //TimeTableModel timeTableModel;
    Spinner spinner;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_timetable);

        subId = (EditText) findViewById(R.id.subId_3000);
        subName = (EditText) findViewById(R.id.sub_name3001);
        add_subject = (Button)findViewById(R.id.add_subject);

        subjectModel = new SubjectModel();

        //selectIdSpinner_3001 = (Spinner) findViewById(R.id.selectIdSpinner_3001);

        teacherName = (EditText) findViewById(R.id.teacher_3001);
        venue = (EditText) findViewById(R.id.venue_3001);
        day = (EditText) findViewById(R.id.day_3001);
        time = (EditText) findViewById(R.id.time_3001);

        spinner = (Spinner)findViewById(R.id.selectIdSpinner_3001);

        ref = FirebaseDatabase.getInstance().getReference().child("SubjectModel");

        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(AdminTimeTable.this,android.R.layout.simple_spinner_dropdown_item,spinnerDataList);

        spinner.setAdapter(adapter );
        retrieveData();

        add_subject.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                subjectModel.setSubId(subId.getText().toString());
                subjectModel.setSubName(subName.getText().toString());
                subjectModel.setTeacherName(teacherName.getText().toString());
                subjectModel.setVenue(venue.getText().toString());
                subjectModel.setDay(day.getText().toString());
                subjectModel.setTime(time.getText().toString());

                ref.child(subId.getText().toString()).setValue(subjectModel);
                Toast.makeText(getApplicationContext(),"Succesfully Data Inserted",Toast.LENGTH_SHORT).show();

            }

        });

        }

    public void retrieveData(){

        listener = ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot item:dataSnapshot.getChildren()){

                    String subId = item.child("subId").getValue(String.class);
                    spinnerDataList.add(subId);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}