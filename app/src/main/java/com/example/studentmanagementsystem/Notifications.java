package com.example.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagementsystem.DBHandlers.NoticesModel;
import com.example.studentmanagementsystem.ui.main.RecyclerViewAdapterNotices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Notifications extends Fragment {

    private RecyclerView noticeList;
    private DatabaseReference reference;
    private ArrayList<NoticesModel> list;
    private RecyclerViewAdapterNotices adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notifiction, container,false);

        noticeList=rootView.findViewById(R.id.noticeRecyclerView2);
        //noticeList.setHasFixedSize(true);
        noticeList.setHasFixedSize(true);
        noticeList.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference("NoticesModel");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        NoticesModel model = dataSnapshot1.getValue(NoticesModel.class);
                        list.add(model);
                    }

                    adapter=new RecyclerViewAdapterNotices(getContext(),list);
                    noticeList.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Something went wrong....",Toast.LENGTH_LONG).show();
            }
        });


        return rootView;

    }
}

