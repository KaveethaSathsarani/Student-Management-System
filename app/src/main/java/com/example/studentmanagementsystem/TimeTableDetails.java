package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentmanagementsystem.DBHandlers.SubjectModel;

import java.util.List;

public class TimeTableDetails extends AppCompatActivity {

    private EditText mSubId_editText;
    private EditText mSubName_editText;
    private EditText mTeacherName_editText;
    private EditText mVenue_editText;
    private Spinner mday_categories_spinner;
    private Spinner mtime_categories_spinner;
    private Spinner mendtime_categories_spinner;

    private Button mUpdate_btn;
    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String subId;
    private String subName;
    private String teacherName;
    private String venue;
    private String day;
    private String time;
    private String endtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_details);

        key = getIntent().getStringExtra("key");
        subId = getIntent().getStringExtra("subId");
        subName = getIntent().getStringExtra("subName");
        teacherName = getIntent().getStringExtra("teacherName");
        venue = getIntent().getStringExtra("venue");
        day = getIntent().getStringExtra("day");
        time = getIntent().getStringExtra("time");
        endtime = getIntent().getStringExtra("endTime");

        mSubId_editText = (EditText) findViewById(R.id.subId_edit);
        mSubId_editText.setText(subId);
        mSubName_editText = (EditText) findViewById(R.id.subName_edit);
        mSubName_editText.setText(subName);
        mTeacherName_editText = (EditText) findViewById(R.id.teacher_edit);
        mTeacherName_editText.setText(teacherName);
        mVenue_editText = (EditText) findViewById(R.id.venue_edit);
        mVenue_editText.setText(venue);
        mday_categories_spinner = (Spinner) findViewById(R.id.day_edit);
        mtime_categories_spinner = (Spinner) findViewById(R.id.time_edit);
        mendtime_categories_spinner = (Spinner) findViewById(R.id.endtime_edit);

        mday_categories_spinner.setSelection(getIndex_SpinnerItem(mday_categories_spinner, day));
        mtime_categories_spinner.setSelection(getIndex_SpinnerItem(mtime_categories_spinner, time));
        mendtime_categories_spinner.setSelection(getIndex_SpinnerItem(mendtime_categories_spinner, endtime));

        mUpdate_btn = (Button) findViewById(R.id.updatetimetable);
        mDelete_btn = (Button) findViewById(R.id.deletetimetable);
        mBack_btn = (Button) findViewById(R.id.backtimetable);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
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

                //Validating The Text Fields

                String mSubId_editValid = mSubId_editText.getText().toString();

                if(TextUtils.isEmpty(mSubId_editValid)) {
                    mSubId_editText.setError("Field is Empty");
                    return;
                }

                String mSubName_editValid = mSubName_editText.getText().toString();

                if(TextUtils.isEmpty(mSubName_editValid)) {
                    mSubName_editText.setError("Field is Empty");
                    return;
                }

                String mTeacherName_editValid = mTeacherName_editText.getText().toString();

                if(TextUtils.isEmpty(mTeacherName_editValid)) {
                    mTeacherName_editText.setError("Field is Empty");
                    return;
                }

                String mVenue_editValid = mVenue_editText.getText().toString();

                if(TextUtils.isEmpty(mVenue_editValid)) {
                    mVenue_editText.setError("Field is Empty");
                    return;
                }


                //Validating the Spinners

                int dayeditSpinner = mday_categories_spinner.getSelectedItemPosition();

                if(dayeditSpinner!=0)
                {
                    String dayedit = mday_categories_spinner.getSelectedItem().toString();
                }
                else{
                    Toast.makeText(TimeTableDetails.this, "Please Select The Day!", Toast.LENGTH_LONG).show();
                    return;
                }


                int startTimeeditSpinner = mtime_categories_spinner.getSelectedItemPosition();

                if(startTimeeditSpinner!=0)
                {
                    String startTimeedit = mtime_categories_spinner.getSelectedItem().toString();
                }
                else{
                    Toast.makeText(TimeTableDetails.this, "Please Select The Start Time!", Toast.LENGTH_LONG).show();
                    return;
                }


                int endTimeeditSpinner = mendtime_categories_spinner.getSelectedItemPosition();

                if(endTimeeditSpinner!=0)
                {
                    String endTimeedit = mendtime_categories_spinner.getSelectedItem().toString();
                }
                else{
                    Toast.makeText(TimeTableDetails.this, "Please Select The End Time!", Toast.LENGTH_LONG).show();
                    return;
                }



                new TimeTableFirebase().updateTimeTable(key, subjectModel, new TimeTableFirebase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<SubjectModel> subjects, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                        Toast.makeText(TimeTableDetails.this,"Time Table Has Been Updated", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });

            }
        });

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new TimeTableFirebase().deleteTimeTable(key, new TimeTableFirebase.DataStatus() {
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

                        Toast.makeText(TimeTableDetails.this, "Time Table Has Been Deleted", Toast.LENGTH_LONG).show();

                        finish(); return;

                    }
                });

            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish(); return;

            }
        });

    }

    private int getIndex_SpinnerItem(Spinner spinner, String item){

        int index = 0;

        for(int i = 0; i<spinner.getCount(); i++){

            if(spinner.getItemAtPosition(i).equals(item)){

                index = i;
                break;
            }

        }

        return index;

    }

}
