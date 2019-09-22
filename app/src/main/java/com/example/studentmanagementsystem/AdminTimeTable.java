package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

    EditText subId,subName,time,teacherName,venue,day,viewSubId,viewSubName,viewTeacherName,viewVenue,viewDay,viewTime;
    Button add_subject,viewTimetable_2;
    //Button add_data3000;
    //FirebaseDatabase database;
    DatabaseReference ref,viewref;
    //DatabaseReference ref3001;
    //DatabaseReference ref3002;
    SubjectModel subjectModel;
    //TimeTableModel timeTableModel;
    Spinner spinner,spinner2;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    View selectedView = null;  //Declare it as a class level variable so that you don't need to make it final


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_timetable);

        subId = (EditText) findViewById(R.id.subId_3000);
        viewSubId = (EditText) findViewById(R.id.subId_3000);

        subName = (EditText) findViewById(R.id.sub_name3001);
        viewSubName = (EditText) findViewById(R.id.sub_name3001);

        add_subject = (Button)findViewById(R.id.add_subject);
        viewTimetable_2 = (Button) findViewById(R.id.viewTimetable_2);

        subjectModel = new SubjectModel();

        //selectIdSpinner_3001 = (Spinner) findViewById(R.id.selectIdSpinner_3001);

        teacherName = (EditText) findViewById(R.id.teacher_3001);
        viewTeacherName = (EditText) findViewById(R.id.teacher_3001);

        venue = (EditText) findViewById(R.id.venue_3001);
        viewVenue = (EditText) findViewById(R.id.venue_3001);

        day = (EditText) findViewById(R.id.day_3001);
        viewDay = (EditText) findViewById(R.id.day_3001);

        time = (EditText) findViewById(R.id.time_3001);
        viewTime = (EditText) findViewById(R.id.time_3001);


        spinner = (Spinner)findViewById(R.id.selectIdSpinner_3001);
        spinner2 = (Spinner)findViewById(R.id.selectIdSpinner_3001);

        ref = FirebaseDatabase.getInstance().getReference().child("SubjectModel");

        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(AdminTimeTable.this,android.R.layout.simple_spinner_dropdown_item,spinnerDataList);

        spinner.setAdapter(adapter );
        retrieveData();

        /*spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedView = view;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


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

        viewTimetable_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewref = FirebaseDatabase.getInstance().getReference().child("SubjectModel").child("Grade_08_English");

                viewref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String viewDay = dataSnapshot.child("day").getValue().toString();
                        String viewSubId = dataSnapshot.child("subId").getValue().toString();
                        String viewSubName = dataSnapshot.child("subName").getValue().toString();
                        String viewTeacherName = dataSnapshot.child("teacherName").getValue().toString();
                        String viewTime = dataSnapshot.child("time").getValue().toString();
                        String viewVenue = dataSnapshot.child("venue").getValue().toString();
                        day.setText(viewDay);
                        subId.setText(viewSubId);
                        subName.setText(viewSubName);
                        teacherName.setText(viewTeacherName);
                        time.setText(viewTime);
                        venue.setText(viewVenue);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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