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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        profile = (ImageButton)findViewById(R.id.profiles);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfiles();
            }
        });


    }

    private void openProfiles(){
        Intent intent = new Intent(this, Admin_Profile.class);
        startActivity(intent);
    }
}
