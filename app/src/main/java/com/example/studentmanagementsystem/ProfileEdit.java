package com.example.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.studentmanagementsystem.DBHandlers.StudentModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileEdit extends Fragment  {

   // TextView t1,t2;
    DatabaseReference dbrf;
    StudentModel studentModel;
    EditText e1,e2,e3,e4,e5,e6;
    String pass;
    Button save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view1 = inflater.inflate(R.layout.fragment_profile2,container,false);

        save = view1.findViewById(R.id.Save);
        TextView t1 = (TextView) view1.findViewById(R.id.stdId);
        TextView t2 = (TextView) view1.findViewById(R.id.stdName);
        e1 = view1.findViewById(R.id.editText1);
        e2 = view1.findViewById(R.id.editText2);
        e3 = view1.findViewById(R.id.editText3);
        e4 = view1.findViewById(R.id.editText4);
        e5 = view1.findViewById(R.id.editText5);
        e6 = view1.findViewById(R.id.editText6);

        Bundle bundle = getArguments();
       final String id = bundle.getString("id");
       final String name = bundle.getString("name");
       final String password= bundle.getString("password");
       final String dob = bundle.getString("dob");
       final String school = bundle.getString("school");
       final String address = bundle.getString("address");
       final String cNum = bundle.getString("cNum");
       final String gName = bundle.getString("gName");
       final String gcNum = bundle.getString("gcNum");

        t1.setText(id);
        t2.setText(name);
        e1.setText(dob);
        e2.setText(school);
        e3.setText(address);
        e4.setText(cNum);
        e5.setText(gName);
        e6.setText(gcNum);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dob = e1.getText().toString().trim();
                String school = e2.getText().toString().trim();
                String address = e3.getText().toString().trim();
                String coNum = e4.getText().toString().trim();
                String gname = e5.getText().toString().trim();
                String gcno = e6.getText().toString().trim();

                if (dob.isEmpty() || school.isEmpty() || address.isEmpty() || coNum.isEmpty() || gname.isEmpty() || gcno.isEmpty()) {

                    Toast.makeText(getContext(), "All Fields should be filled", Toast.LENGTH_LONG).show();
                } else {



                    update(id, name, school, dob, coNum, address, gname, gcno, password);

                    Toast.makeText(getContext(), "Updated successful !", Toast.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fr = fragmentManager.beginTransaction();

                    Profile profile = new Profile();
                    fr.replace(R.id.fragment_container, profile);
                    fr.commit();
                }
            }
        });



        return view1;
    }

    private boolean update(String id,String name,String school, String dob, String coNum, String address, String gName, String gcno, String password){
        dbrf = FirebaseDatabase.getInstance().getReference("StudentModel").child(id);

        studentModel = new StudentModel(id,name,school,dob,coNum,address,gName,gcno,password);

        dbrf.setValue(studentModel);

        return true;

    }

}

