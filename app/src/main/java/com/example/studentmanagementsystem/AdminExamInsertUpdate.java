package com.example.studentmanagementsystem;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanagementsystem.DBHandlers.ExamModel;
import com.example.studentmanagementsystem.DBHandlers.ExamSubjectStudentModel;
import com.example.studentmanagementsystem.DBHandlers.StudentModel;
import com.example.studentmanagementsystem.DBHandlers.SubjectModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class AdminExamInsertUpdate extends AppCompatActivity {
    String examName,subName;
    EditText etExamID,etExamName,etStudentId,etSubjectId,etMarks,etExamTime,etExamVenue,etExamDate;
    Button addExambtn,removeExambtn,btnAddMarks;
    DatabaseReference dref,dref1,dref2,dref3,dref4,dref5,dref6;
    Spinner spGrade,spVenue;
    EditText date;
    DatePickerDialog datePickerDialog;


    private String studentName;
    private String school;
    private int ContactNo;
    private String address;
    private String gName;
    private int gcNo;
    private String password;
    ExamModel examModel,examModel2;
    StudentModel studentModel;
    SubjectModel subjectModel;
    ExamSubjectStudentModel examSubjectStudentModel;
    boolean validation3 = false;
    boolean validation6 = false;
    boolean examUpdateValidation1= true;
    boolean examUpdateValidation2= true;
    boolean examUpdateValidation3= true;
    boolean examUpdateValidation4= true;
    boolean examUpdateValidation5= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_exam_insertupdate);
        etExamID = findViewById(R.id.examIDet);
        etExamName=findViewById(R.id.examNameet);
        addExambtn = findViewById(R.id.addExambtn);
        removeExambtn =findViewById(R.id.updateExambtn);
        etExamTime=findViewById(R.id.etExamTime);
        etExamDate=findViewById(R.id.etExamDate);
        //etExamVenue=findViewById(R.id.etExamVenue);
        spGrade=findViewById(R.id.spinner4);
        spVenue = findViewById(R.id.spinner5);

        //etStudentId = findViewById(R.id.etStudentId);
        //etSubjectId=findViewById(R.id.etSubjectId);
        //etMarks=findViewById(R.id.etMarks);
        //btnAddMarks =findViewById(R.id.btnAddMarks);
        examModel = new ExamModel();
        examModel2= new ExamModel();


        studentModel = new StudentModel();
        subjectModel = new SubjectModel();
        examSubjectStudentModel = new ExamSubjectStudentModel();
        dref =FirebaseDatabase.getInstance().getReference();
        Spinner spinner = findViewById(R.id.spinner4);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        Spinner spinner1 = findViewById(R.id.spinner5);
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("101");
        arrayList1.add("102");
        arrayList1.add("201");
        arrayList1.add("202");
        arrayList1.add("301");
        arrayList1.add("302");
        arrayList1.add("303");
        arrayList1.add("304");
        arrayList1.add("305");
        arrayList1.add("203");
        arrayList1.add("204");
        arrayList1.add("205");
        arrayList1.add("103");
        arrayList1.add("Main hall");

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        // initiate the date picker and a button
        date = (EditText) findViewById(R.id.etExamDate);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AdminExamInsertUpdate.this ,new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        date.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



    }

    public void createExamBtnClicked(View view){
        boolean validation1 = true;
        boolean validation2 = true;
        boolean validation31 = true;
        boolean validation4 = true;
        boolean validation5 = true;


        dref1 = dref.child("Exam");
        try{
            if(TextUtils.isEmpty(etExamID.getText().toString())){
                Toast.makeText(getApplicationContext(),"Please enter a value to Exam ID",Toast.LENGTH_SHORT).show();
                validation1 =false;
            }
            if(TextUtils.isEmpty((etExamName.getText().toString()))){
                Toast.makeText(getApplicationContext(),"Please enter a value to Exam Name",Toast.LENGTH_SHORT).show();
                validation2 =false;
            }
            try {
                if (TextUtils.isEmpty(etExamTime.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter a value to Exam Time", Toast.LENGTH_LONG).show();
                    validation31 = false;
                } else if (etExamTime.getText().toString().length() == 5) {
                    if (!((Integer.parseInt(etExamTime.getText().toString().substring(0, 2))) > 0 && (Integer.parseInt(etExamTime.getText().toString().substring(0, 2)) <= 12) && (etExamTime.getText().toString().substring(2, 3).equals("-")) && (Integer.parseInt(etExamTime.getText().toString().substring(3, 5)) > 0) && (Integer.parseInt(etExamTime.getText().toString().substring(3, 5)) <= 12))) {
                        Toast.makeText(getApplicationContext(), "Please enter valid exam Time", Toast.LENGTH_SHORT).show();
                        validation31 = false;
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please enter valid Exam Time", Toast.LENGTH_LONG).show();
                    validation31 = false;
                }
                }catch(NumberFormatException e){
                    validation31 = false;
                    Toast.makeText(getApplicationContext(), "Please enter valid exam Time", Toast.LENGTH_SHORT).show();
                }



          // if(TextUtils.isEmpty()){
                //Toast.makeText(getApplicationContext(),"Please enter a value to Exam Venue",Toast.LENGTH_SHORT).show();
               // validation4 =false;
           // }
            if(TextUtils.isEmpty(etExamDate.getText().toString())){
                Toast.makeText(getApplicationContext(),"Please enter a value to Exam Date",Toast.LENGTH_SHORT).show();
                validation5 =false;
            }


            if(validation1 && validation2 && validation31 && validation4 && validation5){
                //String  examId = etExamID.getText().toString();
               // DatabaseReference dref1 = dref.child(examId);
                dref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!(dataSnapshot.hasChild(etExamID.getText().toString()))){
                            examModel.setExamId(etExamID.getText().toString().trim());
                            examModel.setExamName(etExamName.getText().toString().trim());
                            examModel.setExamDate(etExamDate.getText().toString().trim());
                            examModel.setExamTime(etExamTime.getText().toString().trim());
                            examModel.setExamVenue(spVenue.getSelectedItem().toString().trim());
                            examModel.setGrade(spGrade.getSelectedItem().toString().trim());
                            //examModel.setExamDate();
                            dref1.child(etExamID.getText().toString()).setValue(examModel);
                            Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                        }
                        else{

                            Toast.makeText(getApplicationContext(),"Exam ID already exsists",Toast.LENGTH_SHORT).show();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
               


            }

        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Please enter data in valid format",Toast.LENGTH_SHORT).show();
        }

    }
    public void btnAddMarksClicked(View view) {
        boolean validation1 = true;
        boolean validation2 = true;
        boolean validation4 = true;
        boolean validation5= true;


        if (TextUtils.isEmpty(etStudentId.getText().toString())) {
            validation1 = false;
            Toast.makeText(getApplicationContext(), "Enter a value to studentID", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(etExamID.getText().toString())) {
            validation2 = false;
            Toast.makeText(getApplicationContext(), "Enter a value to Exam ID", Toast.LENGTH_SHORT).show();

        }
        if (TextUtils.isEmpty(etSubjectId.getText().toString())) {
            validation4 = false;
            Toast.makeText(getApplicationContext(), "Enter a value to Subject ID", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(etMarks.getText().toString())){
            validation5 = false;
            Toast.makeText(getApplicationContext(), "Enter a value to Marks", Toast.LENGTH_SHORT).show();

        }
        if (validation4) {
            dref2 = dref.child("SubjectModel");


            dref2.orderByChild("subId").equalTo(etSubjectId.getText().toString())
                    .addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                validation3 = true;
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Please enter a valid Subject ID",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


        }
        if(validation1){
            dref3 = dref.child("StudentModel");
             dref3.orderByChild("studentID").equalTo(etStudentId.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     if(dataSnapshot.exists()){

                         validation6 =true;
                     }
                     else{
                         Toast.makeText(getApplicationContext(),"Enter a valid Student ID",Toast.LENGTH_SHORT).show();
                     }
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             });

        }
        //if(validation1 && validation2 && validation3 && validation4 && validation5 && validation6){


            //Query query1 = dref.child("Exam").orderByChild("examId");
           // query1.get
            //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");

            dref.child("Exam").orderByChild("examId").equalTo(etExamID.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot datas: dataSnapshot.getChildren()){
                         examName=datas.child("examName").getValue().toString();
                         Log.i("Info","Exam Name1:" + examName);
                    }
                    examModel2.setExamId(etExamID.getText().toString());
                    Log.i("Info","Before Passing"+examName);
                    examModel2.setExamName(examName);
                    Log.i("Info","examID" +examModel2.getExamId());
                    Log.i("Info","ExamName"+examModel2.getExamName());
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            dref.child("StudentModel").orderByChild("studentID").equalTo(etStudentId.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot datas: dataSnapshot.getChildren()){
                        studentName=datas.child("studentName").getValue().toString();
                        //school = datas.child("school").getValue().toString();
                        ContactNo  = Integer.parseInt(datas.child("contactNo").getValue().toString());
                        //address = datas.child("address").getValue().toString();
                        //gName=datas.child("gName").getValue().toString();
                        gcNo=Integer.parseInt(datas.child("gcNo").getValue().toString());
                        password=datas.child("password").getValue().toString();



                    }
                    studentModel.setStudentID(etStudentId.getText().toString());
                    studentModel.setStudentName(studentName);
                    //studentModel.setAddress(address);
                    //studentModel.setContactNo(ContactNo);
                    //studentModel.setSchool(school);
                    //studentModel.setGcNo(gcNo);
                    //studentModel.setgName(gName);
                    studentModel.setPassword(password);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            dref.child("SubjectModel").orderByChild("subId").equalTo(etSubjectId.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot datas: dataSnapshot.getChildren()){
                        subName=datas.child("subName").getValue().toString();
                    }
                    subjectModel.setSubId(etSubjectId.getText().toString());
                    subjectModel.setSubName(subName);

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });




            /*examModel2.setExamId(etExamID.getText().toString());
            Log.i("Info","Before Passing"+examName);
            examModel2.setExamName(examName);
            Log.i("Info","examID" +examModel2.getExamId());
            Log.i("Info","ExamName"+examModel2.getExamName());*/
            dref4 = dref.child("ExamSubjectStudent");

            dref4.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    examSubjectStudentModel.setExamModel(examModel2);
                    examSubjectStudentModel.setStudentModel(studentModel);
                    examSubjectStudentModel.setSubjectModel(subjectModel);
                    examSubjectStudentModel.setMarks(Double.parseDouble(etMarks.getText().toString().trim()));
                    examSubjectStudentModel.setGrade("A");
                    dref4.push().setValue(examSubjectStudentModel);
                    Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        //}
    }
    public void updateExambtnClicked(View view){
        boolean validation1 = true;
        boolean validation2 = true;
        boolean validation31 = true;
        boolean validation4 = true;
        boolean validation5 = true;


        dref1 = dref.child("Exam");
        try{
            if(TextUtils.isEmpty(etExamID.getText().toString())){

               examUpdateValidation1=false;
            }
            if(TextUtils.isEmpty((etExamName.getText().toString()))){

                examUpdateValidation2 =false;
            }
            try {
                if (TextUtils.isEmpty(etExamTime.getText().toString())) {

                    examUpdateValidation3 = false;
                } else if (etExamTime.getText().toString().length() == 5) {
                    if (!((Integer.parseInt(etExamTime.getText().toString().substring(0, 2))) > 0 && (Integer.parseInt(etExamTime.getText().toString().substring(0, 2)) <= 12) && (etExamTime.getText().toString().substring(2, 3).equals("-")) && (Integer.parseInt(etExamTime.getText().toString().substring(3, 5)) > 0) && (Integer.parseInt(etExamTime.getText().toString().substring(3, 5)) <= 12))) {
                        Toast.makeText(getApplicationContext(), "Please enter valid exam Time", Toast.LENGTH_SHORT).show();
                        examUpdateValidation3 = false;
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please enter valid Exam Time", Toast.LENGTH_LONG).show();
                    examUpdateValidation3= false;
                }
            }catch(NumberFormatException e){
                examUpdateValidation3 = false;
                Toast.makeText(getApplicationContext(), "Please enter valid exam Time", Toast.LENGTH_SHORT).show();
            }



            // if(TextUtils.isEmpty()){
            //Toast.makeText(getApplicationContext(),"Please enter a value to Exam Venue",Toast.LENGTH_SHORT).show();
            // validation4 =false;
            // }
            if(TextUtils.isEmpty(etExamDate.getText().toString())){

               examUpdateValidation5 =false;
            }


            if(examUpdateValidation1){
                //String  examId = etExamID.getText().toString();
                // DatabaseReference dref1 = dref.child(examId);
                dref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if((dataSnapshot.hasChild(etExamID.getText().toString()))){
                            examModel.setExamId(etExamID.getText().toString().trim());
                            if(examUpdateValidation2) {
                                examModel.setExamName(etExamName.getText().toString().trim());
                            }
                            if(examUpdateValidation5) {
                                examModel.setExamDate(etExamDate.getText().toString().trim());
                            }
                            if(examUpdateValidation3) {
                                examModel.setExamTime(etExamTime.getText().toString().trim());
                            }
                            examModel.setExamVenue(spVenue.getSelectedItem().toString().trim());
                            examModel.setGrade(spGrade.getSelectedItem().toString().trim());
                            //examModel.setExamDate();
                            dref1.child(etExamID.getText().toString()).setValue(examModel);
                            Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_SHORT).show();
                        }
                        else{

                            Toast.makeText(getApplicationContext(),"Please enter an exsisting ExamID",Toast.LENGTH_SHORT).show();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }

        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Please enter data in valid format",Toast.LENGTH_SHORT).show();
        }


    }
}
