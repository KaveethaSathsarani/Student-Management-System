package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;

public class AdminTimeTable extends AppCompatActivity {

    EditText subId,subName;
    Button add_subject;
    //FirebaseDatabase database;
    DatabaseReference ref;
    SubjectModel subjectModel;
    TimeTableModel timeTableModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_timetable);

        subId = (EditText) findViewById(R.id.subId_3000);
        subName = (EditText) findViewById(R.id.sub_name3001);
        add_subject = (Button)findViewById(R.id.add_subject);
        subjectModel = new SubjectModel();

        ref = FirebaseDatabase.getInstance().getReference().child("SubjectModel");
        add_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subjectModel.setSubId(subId.getText().toString());
                subjectModel.setSubName(subName.getText().toString());

                ref.push().setValue(subjectModel);

            }
        });
        //timeTableModel = new TimeTableModel();

    }


    /*private void getValues(){

            subjectModel.setSubName(subName.getText().toString());
            subjectModel.setSubId(subId.getText().toString());

    }*/


    /*public void add_subject(View view){

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                getValues();
                ref.child("SubjectModel01").setValue(subjectModel);
                Toast.makeText(AdminTimeTable.this,"Data Inserted",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }*/

}
