package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagementsystem.DBHandlers.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login1 extends AppCompatActivity {

    Button button1,button2;
    StudentModel studentModel;
    EditText username;
    EditText password;
    DatabaseReference dbrf1;

    String user;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);


        button1 = findViewById(R.id.loginStudent1);
        button2 = findViewById(R.id.loginAdmin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                dbrf1 = FirebaseDatabase.getInstance().getReference().child("StudentModel");
                dbrf1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String userName = "";
                        for (DataSnapshot ds : dataSnapshot.getChildren() ){
                            if(ds.getKey().toString().matches(user)){
                                userName = user;
                                break;
                            }
                        }
                        if(userName.matches(user)){
                            if(dataSnapshot.child(user).child("password").getValue().toString().matches(pass)){
                                Toast.makeText(login1.this, "Login successful !", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(login1.this,MainClass.class);
                                it.putExtra("username", user);
                                startActivity(it);
                            }else{
                                Toast.makeText(login1.this, "Wrong password !", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(login1.this, "User not found !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = username.getText().toString();
                    pass = password.getText().toString();
                    dbrf1 = FirebaseDatabase.getInstance().getReference().child("AdminModel");
                    dbrf1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userName = "";
                            for (DataSnapshot ds : dataSnapshot.getChildren() ){
                                if(ds.getKey().toString().matches(user)){
                                    userName = user;
                                    break;
                                }
                            }
                            if(userName.matches(user)){
                                if(dataSnapshot.child(user).child("password").getValue().toString().matches(pass)){
                                    Toast.makeText(login1.this, "Login successful !", Toast.LENGTH_SHORT).show();
                                    Intent it = new Intent(login1.this,AdminMenu.class);
                                    it.putExtra("username", user);
                                    startActivity(it);
                                }else{
                                    Toast.makeText(login1.this, "Wrong password !", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(login1.this, "User not found !", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });

    }

}
