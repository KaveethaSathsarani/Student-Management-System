package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.studentmanagementsystem.DBHandlers.SubjectModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static com.example.studentmanagementsystem.TimeTableUtil.DataCache;

public class AdminTimeTableList extends AppCompatActivity implements SearchView.OnQueryTextListener,MenuItem.OnActionExpandListener {


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

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview_subjects);


        mDatabase = FirebaseDatabase.getInstance();

        //mReference = mDatabase.getReference("SubjectModel");

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
        adapter = new MyAdapter(DataCache);
        mRecyclerView.setAdapter(adapter);

    }

    private void bindData(){

        TimeTableUtil.select(this, TimeTableUtil.getDatabaseReference(),mRecyclerView,adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.timetable_activity_menu, menu);

        getMenuInflater().inflate(R.menu.timetable_toolbar_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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

        TimeTableUtil.searchString = query;
        TimeTableUtil.search(this, TimeTableUtil.getDatabaseReference(),adapter, query);
        return false;

    }


}
