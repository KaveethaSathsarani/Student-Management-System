package com.example.studentmanagementsystem;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminExamDelete extends AppCompatActivity {

    DatabaseReference dref,dref1,dref2;
    EditText etExamIdDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_exam_delete);
        dref = FirebaseDatabase.getInstance().getReference();
        etExamIdDelete = findViewById(R.id.etExamIdDelete);
    }
    private void clearFields(){
        etExamIdDelete.setText("");
    }

    public void examDetailsRemoveBtnClicked(View view){
        boolean validation1 = true;
        dref1=dref.child("Exam");
        if(TextUtils.isEmpty(etExamIdDelete.getText().toString())){
            Toast.makeText(getApplicationContext(),"Please enter a value to Exam ID",Toast.LENGTH_SHORT).show();
            validation1 =false;
        }
        dref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if((dataSnapshot.hasChild(etExamIdDelete.getText().toString()))){
                    dref2 = dref1.child(etExamIdDelete.getText().toString());
                    dref2.removeValue();
                    clearFields();
                    Toast.makeText(getApplicationContext(),"Successfully Deleted",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"please enter an exsisting ExamID",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
