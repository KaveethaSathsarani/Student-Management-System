package com.example.studentmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TimeTableUtils {

    public static List<SubjectModel> DataCache = new ArrayList<>();

    public static String searchString = "";

    public static void show(Context c,String message){

        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();

    }

    public static void openActivity(Context c,Class clazz){

        Intent intent = new Intent(c, clazz);
        c.startActivity(intent);

    }

    public static void sendSubjectToActivity(Context c, SubjectModel subjectModel, Class clazz){

        Intent i = new Intent(c,clazz);
        i.putExtra("subId",subjectModel);
        c.startActivity(i);

    }

    public static SubjectModel receiveSubject(Intent intent, Context c){

        try{

            return (SubjectModel) intent.getSerializableExtra("subId");

        }catch (Exception e){

            e.printStackTrace();
            show(c,"RECEIVE ERROR: "+e.getMessage());

        }

        return null;

    }

    public static void showProgressBar(ProgressBar pb){

        pb.setVisibility(View.VISIBLE);
    }

    public static void hideProgressBar(ProgressBar pb){

        //pb.setVisibility(View.GONE);
    }

    public static DatabaseReference getDatabaseReference(){

        return FirebaseDatabase.getInstance().getReference();
    }

    public static void search(final AppCompatActivity a, DatabaseReference db, final ProgressBar pb, final MyAdapter adapter, String searchTerm){

        if(searchTerm != null && searchTerm.length()>0){

            char[] letters = searchTerm.toCharArray();
            String firstletter = String.valueOf(letters[0]).toUpperCase();
            String remainingletters = searchTerm.substring(1);
            searchTerm = firstletter+remainingletters;

        }

        //TimeTableUtils.showProgressBar(pb);

        Query firebaseSearchQuery = db.child("SubjectModel").orderByChild("subId").startAt(searchTerm).endAt(searchTerm+"\uf8ff");

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



                } else{

                    TimeTableUtils.show(a, "No Item Found");

                }

                TimeTableUtils.hideProgressBar(pb);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.d("FIREBASE CRUD", databaseError.getMessage());
                TimeTableUtils.hideProgressBar(pb);
                TimeTableUtils.show(a,databaseError.getMessage());

            }
        });

    }

    public static void select(final AppCompatActivity a, DatabaseReference db, final ProgressBar pb, final RecyclerView rv, final MyAdapter adapter){

        TimeTableUtils.showProgressBar(pb);

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

                    TimeTableUtils.show(a, "Not Found");

                }

                TimeTableUtils.hideProgressBar(pb);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.d("FIREBASE CRUD", databaseError.getMessage());
                TimeTableUtils.hideProgressBar(pb);
                TimeTableUtils.show(a,databaseError.getMessage());

            }
        });

    }


}
