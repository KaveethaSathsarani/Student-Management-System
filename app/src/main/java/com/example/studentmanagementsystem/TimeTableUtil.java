package com.example.studentmanagementsystem;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagementsystem.DBHandlers.SubjectModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TimeTableUtil {


    public static List<SubjectModel> DataCache = new ArrayList<>();
    public static String searchString = "";



    public static void show(Context c,String message){

        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();

    }


    public static DatabaseReference getDatabaseReference(){

        return FirebaseDatabase.getInstance().getReference();

    }

    public static void search(final AppCompatActivity a, DatabaseReference db, final MyAdapter adapter, String searchText){

        if(searchText != null && searchText.length()>0){

            char[] letters = searchText.toCharArray();
            String firstletter = String.valueOf(letters[0]).toUpperCase();
            String remainingletters = searchText.substring(1);
            searchText = firstletter+remainingletters;

        }

        Query firebaseSearchQuery = db.child("SubjectModel").orderByChild("subId").startAt(searchText).endAt(searchText+"\uf8ff");

        firebaseSearchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataCache.clear();

                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){

                    for(DataSnapshot ds: dataSnapshot.getChildren()){

                        SubjectModel subjectModel = ds.getValue(SubjectModel.class);
                        subjectModel.setSubId(ds.getKey());
                        DataCache.add(subjectModel);

                    }

                    adapter.notifyDataSetChanged();

                } else{

                    TimeTableUtil.show(a, "No Item Found");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.d("FIREBASE CRUD", databaseError.getMessage());
                TimeTableUtil.show(a,databaseError.getMessage());

            }
        });

    }

    public static void select(final AppCompatActivity a, DatabaseReference db, final RecyclerView mRecyclerView, final MyAdapter adapter){


        db.child("SubjectModel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataCache.clear();

                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){

                    for(DataSnapshot ds: dataSnapshot.getChildren()){

                        SubjectModel subjectModel = ds.getValue(SubjectModel.class);
                        subjectModel.setSubId(ds.getKey());
                        DataCache.add(subjectModel);

                    }

                    adapter.notifyDataSetChanged();

                } else{

                    TimeTableUtil.show(a, "Not Found");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.d("FIREBASE CRUD", databaseError.getMessage());
                TimeTableUtil.show(a,databaseError.getMessage());

            }
        });

    }

}
