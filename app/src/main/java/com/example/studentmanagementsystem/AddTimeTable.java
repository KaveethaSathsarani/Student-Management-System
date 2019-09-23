package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTimeTable extends AppCompatActivity {

    private EditText mSubId_editText;
    private EditText mSubName_editText;
    private EditText mTeacherName_editText;
    private EditText mVenue_editText;
    private Spinner mday_categories_spinner;
    private Spinner mtime_categories_spinner;
    private Spinner mendtime_categories_spinner;
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
        mendtime_categories_spinner = (Spinner) findViewById(R.id.endtime_add);

        //EditText etUserName = (EditText) findViewById(R.id.txtUsername);


        mAdd_btn = (Button) findViewById(R.id.addtimetable);
        mBack_btn = (Button) findViewById(R.id.back_timeTable);

        ref = FirebaseDatabase.getInstance().getReference().child("SubjectModel");



        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SubjectModel subjectModel = new SubjectModel();

                subjectModel.setSubId(mSubId_editText.getText().toString());
                subjectModel.setSubName(mSubName_editText.getText().toString());
                subjectModel.setTeacherName(mTeacherName_editText.getText().toString());
                subjectModel.setVenue(mVenue_editText.getText().toString());
                subjectModel.setDay(mday_categories_spinner.getSelectedItem().toString());
                subjectModel.setTime(mtime_categories_spinner.getSelectedItem().toString());
                subjectModel.setEndtime(mendtime_categories_spinner.getSelectedItem().toString());

                String mSubId_addValid = mSubId_editText.getText().toString();

                if(TextUtils.isEmpty(mSubId_addValid)) {
                    mSubId_editText.setError("Field is Empty");
                    return;
                }

                String mSubName_addValid = mSubName_editText.getText().toString();

                if(TextUtils.isEmpty(mSubName_addValid)) {
                    mSubName_editText.setError("Field is Empty");
                    return;
                }

                String  mTeacherName_addValid = mTeacherName_editText.getText().toString();

                if(TextUtils.isEmpty(mTeacherName_addValid)) {
                    mTeacherName_editText.setError("Field is Empty");
                    return;
                }

                String   mVenue_addValid = mVenue_editText.getText().toString();

                if(TextUtils.isEmpty(mVenue_addValid)) {
                    mVenue_editText.setError("Field is Empty");
                    return;
                }


                int daySpinner = mday_categories_spinner.getSelectedItemPosition();

                if(daySpinner!=0)
                {
                    String day = mday_categories_spinner.getSelectedItem().toString();
                }
                else{
                    Toast.makeText(AddTimeTable.this,
                            "Please Select The Day!", Toast.LENGTH_LONG).show();
                    return;
                }

                int startTimeSpinner = mtime_categories_spinner.getSelectedItemPosition();

                if(startTimeSpinner!=0)
                {
                    String startTime = mtime_categories_spinner.getSelectedItem().toString();
                }
                else{
                    Toast.makeText(AddTimeTable.this,
                            "Please Select The Start Time!", Toast.LENGTH_LONG).show();
                    return;
                }

                int endTimeSpinner = mendtime_categories_spinner.getSelectedItemPosition();

                if(endTimeSpinner!=0)
                {
                    String endTime = mendtime_categories_spinner.getSelectedItem().toString();
                }
                else{
                    Toast.makeText(AddTimeTable.this,
                            "Please Select The End Time!", Toast.LENGTH_LONG).show();
                    return;
                }

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
