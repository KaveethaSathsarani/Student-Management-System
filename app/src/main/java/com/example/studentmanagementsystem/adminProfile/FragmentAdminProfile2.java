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

import com.example.studentmanagementsystem.DBHandlers.AdminModel;
import com.example.studentmanagementsystem.R;
import com.example.studentmanagementsystem.adapters.AdminList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdminProfile2 extends Fragment {

    EditText adminid,name,password;
    Button create, delete;

    DatabaseReference dbrf;
    AdminModel adminModel;

    ListView listViewAdmin;
    List<AdminModel> adminModelList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_profile2,container,false);

        adminid =(EditText)view.findViewById(R.id.adminId1);
        name = (EditText)view.findViewById(R.id.adminName);
        password=(EditText)view.findViewById(R.id.password1);

        create=(Button)view.findViewById(R.id.create);
        delete=(Button)view.findViewById(R.id.delete);


        listViewAdmin = (ListView) view.findViewById(R.id.listViewStudent);

        adminModelList = new ArrayList<>();

        adminModel= new AdminModel();

        dbrf= FirebaseDatabase.getInstance().getReference().child("AdminModel");

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = adminid.getText().toString().trim();
                String n= name.getText().toString().trim();
                String pw = password.getText().toString().trim();

                adminModel.setAdminID(id);
                adminModel.setName(n);
                adminModel.setPassword(pw);

                dbrf.child(adminid.getText().toString()).setValue(adminModel);
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

                for(DataSnapshot adminsnapshot : dataSnapshot.getChildren() ){
                    AdminModel adminModel = adminsnapshot.getValue(AdminModel.class);

                    adminModelList.add(adminModel);
                }

                AdminList adapter = new AdminList(getActivity(), adminModelList);
                listViewAdmin .setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
