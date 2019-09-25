package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.studentmanagementsystem.Admin_Profile;

public class AdminMenu extends AppCompatActivity {

    ImageButton profile;
    ImageButton notices;
    ImageButton papers;
    ImageButton exam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        profile = findViewById(R.id.profiles);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfiles();
            }
        });
        exam = (ImageButton) findViewById(R.id.exam);
        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExams();
            }
        });

        notices=findViewById(R.id.notifications);
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNotices();
            }
        });

        papers=findViewById(R.id.papers);
        papers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPapers();
            }
        });


    }

    private void openPapers() {
        Intent intent = new Intent(this, AdminReferenceMaterials.class);
        startActivity(intent);
    }

    private void openNotices() {
        Intent intent = new Intent(this, AdminNotices.class);
        startActivity(intent);
    }

    private void openProfiles(){
        Intent intent = new Intent(this, Admin_Profile.class);
        startActivity(intent);
    }
    private void openExams(){
        Intent intent = new Intent(this,AdminExam.class);
        startActivity(intent);
    }
}
