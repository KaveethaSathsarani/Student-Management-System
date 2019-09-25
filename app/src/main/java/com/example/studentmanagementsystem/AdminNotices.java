package com.example.studentmanagementsystem;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanagementsystem.ui.main.SectionsPagerAdapterNotices;

public class AdminNotices extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notices);

        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.view_pager);
        SectionsPagerAdapterNotices sectionsPagerAdapterNotices = new SectionsPagerAdapterNotices(this, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapterNotices);
        tabs.setupWithViewPager(viewPager);


    }





}