package com.example.studentmanagementsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;


public class TimeTable extends Fragment {

    Button b1;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_timetable, container,false);

        b1 = v.findViewById(R.id.timetablebutton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimeTable2 timeTable2 = new TimeTable2();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.timetableLayout, timeTable2);
                transaction.commit();


            }
        });


        return v;


    }
}

