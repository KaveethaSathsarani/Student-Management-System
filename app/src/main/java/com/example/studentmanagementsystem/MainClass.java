package com.example.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import com.example.studentmanagementsystem.DBHandlers.StudentModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainClass extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    DatabaseReference dbrf;
    StudentModel studentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentModel = new StudentModel();

        setContentView(R.layout.student);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);



        loadFragment(new Profile());

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new Profile());
        fragmentTransaction.commit();


    }


    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId())
        {
            case R.id.navigation_profile:
                fragment = new Profile();
                break;

            case R.id.navigation_exam:
                fragment = new Exam();
                break;

            case R.id.navigation_papers:
                fragment = new Papers();
                break;

            case R.id.navigation_timeTable:
                fragment = new TimeTable();
                break;


        }
        return loadFragment(fragment);
    }

    public void callNotices(View view) {


        Fragment fragment;

        if (view == findViewById(R.id.notification_button)) {
           // Log.i("Info","notification button clicked");
            fragment = new Notifications();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

        }
    }

}
