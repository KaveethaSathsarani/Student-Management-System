package com.example.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studentmanagementsystem.DBHandlers.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends Fragment  {

    DatabaseReference dbrf;
    StudentModel studentModel;

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    ImageButton b1;
    String pass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        dbrf = FirebaseDatabase.getInstance().getReference().child("StudentModel");


        t1 = view.findViewById(R.id.id);
        t2 = view.findViewById(R.id.textView32);
        t3 = view.findViewById(R.id.textView33);
       // t4 = view.findViewById(R.id.textView34);
        t5 = view.findViewById(R.id.textView35);
        t6 = view.findViewById(R.id.textView36);
        t7 = view.findViewById(R.id.textView37);
        t8 = view.findViewById(R.id.textView38);
        t9 = view.findViewById(R.id.textView39);

        b1=view.findViewById(R.id.profileEdit);
        studentModel= new StudentModel();


       Intent it = getActivity().getIntent();
        final String username = it.getStringExtra("username");

        dbrf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentModel = dataSnapshot.child(username).getValue(StudentModel.class);

                pass = studentModel.getPassword();

                t1.setText(studentModel.getStudentID());
                t2.setText(studentModel.getStudentName());
                t3.setText(studentModel.getDob());
                t5.setText(studentModel.getSchool());
                t6.setText(studentModel.getAddress());
                t7.setText(studentModel.getContactNo());
                t8.setText(studentModel.getgName());
                t9.setText(studentModel.getGcNo());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = t1.getText().toString();
                String name = t2.getText().toString();
                String dob = t3.getText().toString();
                String school = t5.getText().toString();
                String address = t6.getText().toString();
                String cNum = t7.getText().toString();
                String gName = t8.getText().toString();
                String gcNum = t9.getText().toString();
                String pass1 = pass;

                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                bundle.putString("name",name);
                bundle.putString("dob",dob);
                bundle.putString("school",school);
                bundle.putString("address",address);
                bundle.putString("cNum",cNum);
                bundle.putString("gName",gName);
                bundle.putString("gcNum",gcNum);
                bundle.putString("password",pass1);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fr=fragmentManager.beginTransaction();

                ProfileEdit profileEdit = new ProfileEdit();
                profileEdit.setArguments(bundle);

                fr.replace(R.id.fragment_container,profileEdit);
                fr.commit();
            }
        });

        return view;
    }
}

