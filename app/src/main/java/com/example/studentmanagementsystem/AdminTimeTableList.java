package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class AdminTimeTableList extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_timetable_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview_subjects);
        new TimeTableFirebase().viewTimeTable(new TimeTableFirebase.DataStatus(){

            @Override
            public void DataIsLoaded(List<SubjectModel> subjects, List<String> keys) {

                new RecycleView_TimeTable().setConfig(mRecyclerView, AdminTimeTableList.this, subjects, keys);

            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }

        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.timetable_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.new_timetable:

                startActivity(new Intent(this,AddTimeTable.class));
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
