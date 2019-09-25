package com.example.studentmanagementsystem;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementsystem.DBHandlers.NoticesModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminNoticesTab2 extends Fragment {

    EditText noticeId;
    EditText noticeTitle;
    EditText noticeContent;

    Button noticeAdd;

    DatabaseReference databaseNotices;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_admin_notices1, container, false);


        databaseNotices= FirebaseDatabase.getInstance().getReference().child("NoticesModel");

        noticeAdd=(Button)root.findViewById(R.id.notice_add_btn);

        noticeId=(EditText)root.findViewById(R.id.noticeID_ans);
        noticeTitle =(EditText)root.findViewById(R.id.notices_title_ans);
        noticeContent =(EditText)root.findViewById(R.id.notices_content1_ans);

        noticeAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                addNotice();

            }
        });

        return root;

    }


    private void clearControls(){


        noticeTitle.setText("");
        noticeContent.setText("");
        noticeId.setText("");

    }

    private void addNotice() {

        String id=noticeId.getText().toString().trim();
        String title = noticeTitle.getText().toString().trim();
        String content = noticeContent.getText().toString().trim();

        if (TextUtils.isEmpty(noticeId.getText().toString())) {
            Toast.makeText(getActivity(), "You should enter Notice ID", Toast.LENGTH_LONG).show();
        }   else if (TextUtils.isEmpty(noticeTitle.getText().toString())) {
            Toast.makeText(getActivity(), "You should enter Title", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(noticeContent.getText().toString())) {
            Toast.makeText(getActivity(), "You should enter Content", Toast.LENGTH_LONG).show();
        } else {

            NoticesModel noticesModel=new NoticesModel(id,title,content);
            databaseNotices.child(id).setValue(noticesModel);
            Toast.makeText(getActivity(), "Notice Added Successfully", Toast.LENGTH_LONG).show();
            clearControls();

        }
    }

}