package com.example.studentmanagementsystem.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.studentmanagementsystem.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentNotices extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModelNotices pageViewModelNotices;

    public static PlaceholderFragmentNotices newInstance(int index) {
        PlaceholderFragmentNotices fragment = new PlaceholderFragmentNotices();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModelNotices = ViewModelProviders.of(this).get(PageViewModelNotices.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModelNotices.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_admin_notices, container, false);
        final TextView textView = root.findViewById(R.id.notices_admin);
        pageViewModelNotices.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}