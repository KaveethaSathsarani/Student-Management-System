package com.example.studentmanagementsystem.adminProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
    ImageButton im;
    ScrollView scrollView;
    TextView tv1;

    DatabaseReference dbrf;
    StudentModel studentModel;
    LinearLayout linearLayout;
    //ListView listViewStudent;

   // List<StudentModel> studentModelList;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_profile1,container,false);

        studentid = (EditText)view.findViewById(R.id.studentId1);
        name= (EditText)view.findViewById(R.id.studentName);
        password=(EditText)view.findViewById(R.id.password1);
        scrollView = new ScrollView(getContext());
        create =(Button)view.findViewById(R.id.create);
        delete = (Button)view.findViewById(R.id.delete);
        im = view.findViewById(R.id.imageButton2);
        linearLayout = view.findViewById(R.id.listViewStudent);


        tv1 = new TextView(getContext());

        scrollView.addView(tv1);
        linearLayout.addView(scrollView);
        //listViewStudent = (ListView) view.findViewById(R.id.listViewStudent);
        //studentModelList = new ArrayList<>();

        studentModel= new StudentModel();

        dbrf= FirebaseDatabase.getInstance().getReference().child("StudentModel");



        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbrf.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(studentid.getText().toString())){
                            name.setText(dataSnapshot.child(studentid.getText().toString()).child("studentName").getValue().toString());
                            password.setText(dataSnapshot.child(studentid.getText().toString()).child("password").getValue().toString());
                        }else{
                            Toast.makeText(getContext(),"Invalid ID", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
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
                clear();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = studentid.getText().toString().trim();
                deleteStudent(id);

            }

            private boolean deleteStudent(String id) {

                dbrf.child(id).removeValue();
                Toast.makeText(getContext(),"Profile Deleted",Toast.LENGTH_SHORT).show();
                clear();
                return true;
            }
        });


        return view;
    }

    private void clear(){
        studentid.setText("");
        name.setText("");
        password.setText("");
    }


    @Override
    public void onStart() {
        super.onStart();

        dbrf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tv1.setText("");
                //studentModelList.clear();
                for(DataSnapshot studentSnapshot : dataSnapshot.getChildren()){
                   StudentModel studentModel = studentSnapshot.getValue(StudentModel.class);

                   if(studentModel.getStudentID() != null)
                   tv1.setText(tv1.getText() + studentModel.getStudentID() + "\n"+studentModel.getStudentName()+ "\n" + studentModel.getPassword() + "\n\n");


                    //studentModelList.add(studentModel);

                }

                //StudentList adapter = new StudentList(getActivity(), studentModelList);
                //listViewStudent.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
