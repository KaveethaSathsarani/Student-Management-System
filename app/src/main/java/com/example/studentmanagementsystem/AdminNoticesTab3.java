package com.example.studentmanagementsystem;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementsystem.DBHandlers.NoticesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminNoticesTab3 extends Fragment  {

    EditText noticeId;
    EditText noticeTitle;
    EditText noticeContent;

    ImageButton noticeSearch;
    Button update;
    Button delete;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_admin_notices2,container,false);


        noticeSearch=(ImageButton) root.findViewById(R.id.notice_search_button);
        update=(Button)root.findViewById(R.id.notice_update_btn);
        delete=(Button)root.findViewById(R.id.notice_delete_btn);

        noticeId=(EditText)root.findViewById(R.id.noticeID_ans2);
        noticeTitle =(EditText)root.findViewById(R.id.notices_title_ans2);
        noticeContent =(EditText)root.findViewById(R.id.notices_content2_ans);



            noticeSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  DatabaseReference databaseSearch= FirebaseDatabase.getInstance().getReference().child("NoticesModel");

                    databaseSearch.child(noticeId.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.hasChildren()){

                                noticeTitle.setText(dataSnapshot.child("title").getValue().toString());
                                noticeContent.setText(dataSnapshot.child("content").getValue().toString());

                            }else{

                                Toast.makeText(getContext(),"Invalid Notice ID",Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String id=noticeId.getText().toString().trim();
                    String title=noticeTitle.getText().toString().trim();
                    String content=noticeContent.getText().toString().trim();

                    if (!TextUtils.isEmpty(id)){
                        updateNotices(id,title,content);

                    }


                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String id=noticeId.getText().toString().trim();

                    deleteNotices(id);
                    clearControls();
                }
            });



       return root;

    }

    private boolean updateNotices(String id, String t, String c){

        DatabaseReference updatedatabase=FirebaseDatabase.getInstance().getReference("NoticesModel").child(id);

        NoticesModel model=new NoticesModel(id,t,c);
        updatedatabase.setValue(model);
        Toast.makeText(getContext(),"Notice Updated",Toast.LENGTH_LONG).show();
        clearControls();

        return true;
    }

    public  boolean deleteNotices(String id){

        DatabaseReference deletedatabase=FirebaseDatabase.getInstance().getReference("NoticesModel").child(id);


        deletedatabase.removeValue();

        Toast.makeText(getContext(),"Notice Deleted",Toast.LENGTH_LONG).show();
        clearControls();

        return true;
    }


    private void clearControls(){


        noticeTitle.setText("");
        noticeContent.setText("");
        noticeId.setText("");

    }



}
