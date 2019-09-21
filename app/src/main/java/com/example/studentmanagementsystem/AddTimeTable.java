package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AddTimeTable extends AppCompatActivity {

    private EditText mSubId_editText;
    private EditText mSubName_editText;
    private EditText mTeacherName_editText;
    private EditText mVenue_editText;
    private Spinner mday_categories_spinner;
    private Spinner mtime_categories_spinner;
    DatabaseReference ref;

    private Button mAdd_btn;
    private Button mBack_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);

        mSubId_editText = (EditText) findViewById(R.id.subId_add);
        mSubName_editText = (EditText) findViewById(R.id.subName_add);
        mTeacherName_editText = (EditText) findViewById(R.id.teacher_add);
        mVenue_editText = (EditText) findViewById(R.id.venue_add);
        mday_categories_spinner = (Spinner) findViewById(R.id.day_add);
        mtime_categories_spinner = (Spinner) findViewById(R.id.time_add);

        mAdd_btn = (Button) findViewById(R.id.addtimetable);
        mBack_btn = (Button) findViewById(R.id.back);

        ref = FirebaseDatabase.getInstance().getReference().child("SubjectModel");

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SubjectModel subjectModel = new SubjectModel();
                subjectModel.setSubId(mSubId_editText.getText().toString());
                subjectModel.setSubName(mSubName_editText.getText().toString());
                subjectModel.setTeacherName( mTeacherName_editText.getText().toString());
                subjectModel.setVenue(mVenue_editText.getText().toString());
                subjectModel.setDay(mday_categories_spinner.getSelectedItem().toString());
                subjectModel.setTime(mtime_categories_spinner.getSelectedItem().toString());

                ref.child(mSubId_editText.getText().toString()).setValue(subjectModel);
                Toast.makeText(getApplicationContext(),"Succesfully Data Inserted",Toast.LENGTH_SHORT).show();

                /*new TimeTableFirebase().addTimeTable(subjectModel, new TimeTableFirebase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<SubjectModel> subjects, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {


                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });*/

            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); return;
            }
        });


    }
}
