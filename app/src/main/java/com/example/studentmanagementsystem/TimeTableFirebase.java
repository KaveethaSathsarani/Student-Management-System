package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TimeTableFirebase {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<SubjectModel> subjects = new ArrayList<>();

    public interface DataStatus{

        void DataIsLoaded(List<SubjectModel> subjects, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public TimeTableFirebase() {

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("SubjectModel");

    }

    public void viewTimeTable(final DataStatus dataStatus){

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                subjects.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){

                    keys.add(keyNode.getKey());
                    SubjectModel subjectModel = keyNode.getValue(SubjectModel.class);
                    subjects.add(subjectModel);

                }

                dataStatus.DataIsLoaded(subjects,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addTimeTable(SubjectModel subjectModel, final DataStatus dataStatus){

        String key = mReference.push().getKey();
        mReference.child(key).setValue(subjectModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                dataStatus.DataIsInserted();
            }
        });

    }

}
