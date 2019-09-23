package com.example.studentmanagementsystem.adminProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementsystem.DBHandlers.StudentModel;
import com.example.studentmanagementsystem.R;
import com.example.studentmanagementsystem.adapters.StudentList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentAdminProfile1 extends Fragment {

    EditText studentid,name,password;
    Button create,delete;

    DatabaseReference dbrf;
    StudentModel studentModel;

    ListView listViewStudent;

    List<StudentModel> studentModelList;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_profile1,container,false);

        studentid = (EditText)view.findViewById(R.id.studentId1);
        name= (EditText)view.findViewById(R.id.studentName);
        password=(EditText)view.findViewById(R.id.password1);

        create =(Button)view.findViewById(R.id.create);
        delete = (Button)view.findViewById(R.id.delete);

        listViewStudent = (ListView) view.findViewById(R.id.listViewStudent);
        studentModelList = new ArrayList<>();

        studentModel= new StudentModel();

        dbrf= FirebaseDatabase.getInstance().getReference().child("StudentModel");

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = studentid.getText().toString().trim();
                String n= name.getText().toString().trim();
                String pw = password.getText().toString().trim();

                studentModel.setStudentID(id);
                studentModel.setStudentName(n);
                studentModel.setPassword(pw);

                dbrf.child(studentid.getText().toString()).setValue(studentModel);
                Toast.makeText(getActivity(), "data insert", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        dbrf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentModelList.clear();

                for(DataSnapshot studentSnapshot : dataSnapshot.getChildren()){
                    StudentModel studentModel = studentSnapshot.getValue(StudentModel.class);

                    studentModelList.add(studentModel);

                }

                StudentList adapter = new StudentList(getActivity(), studentModelList);
                listViewStudent.setAdapter((adapter));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
