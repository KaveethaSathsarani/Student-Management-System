package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminTimeTableList extends AppCompatActivity implements SearchView.OnQueryTextListener,MenuItem.OnActionExpandListener{

    private RecyclerView mRecyclerView;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private LinearLayoutManager layoutManager;
    MyAdapter adapter;

    EditText search_edit_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_timetable_list);

        initializeViews();
        bindData();

        search_edit_text = (EditText) findViewById(R.id.subId_add);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview_subjects);

        new TimeTableFirebase().viewTimeTable(new TimeTableFirebase.DataStatus(){

            @Override
            public void DataIsLoaded(List<SubjectModel> subjects, List<String> keys) {

                new RecycleView_TimeTable().setConfig(mRecyclerView, AdminTimeTableList.this, subjects, keys);

            }

            @Override
            public void DataIsInserted() { }

            @Override
            public void DataIsUpdated() { }

            @Override
            public void DataIsDeleted() { }

        });

    }

    private void initializeViews(){

        mRecyclerView = findViewById(R.id.recycleview_subjects);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new MyAdapter(TimeTableUtils.DataCache);
        mRecyclerView.setAdapter(adapter);

    }

    private void bindData(){

        TimeTableUtils.select(this,TimeTableUtils.getDatabaseReference(),mRecyclerView,adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.timetable_activity_menu, menu);

        getMenuInflater().inflate(R.menu.timetable_toolbar_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        //searchView.setIconified(true);
        searchView.setQueryHint("Search");

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

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false; }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem imenuItem) {
        return false; }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false; }

    @Override
    public boolean onQueryTextChange(String query) {

        TimeTableUtils.searchString = query;
        TimeTableUtils.search(this, TimeTableUtils.getDatabaseReference(),adapter, query);
        return false;

    }

}
