package com.example.studentmanagementsystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementsystem.DBHandlers.RefferenceMaterialsModel;
import com.example.studentmanagementsystem.ui.main.ListViewAdapterReferenceMaterials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Papers extends Fragment {

    private ListView myPDFListView;
    private DatabaseReference databaseReference;
    private List<RefferenceMaterialsModel> models;
    private ListViewAdapterReferenceMaterials adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_papers,null);
    }
        //return inflater.inflate(R.layout.fragment_papers,null);

        View v =inflater.inflate(R.layout.fragment_papers, container, false);
        myPDFListView=v.findViewById(R.id.papers_student_list);
        models=new ArrayList<>();

        viewAllFiles();



        Button adminbtn =  v.findViewById(R.id.papers_btn);
        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(getContext(), AdminReferenceMaterials.class);
                startActivity(intent);
            }
        });

        myPDFListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                RefferenceMaterialsModel model=models.get(i);

                Intent intent=new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(model.getUrl()));
                startActivity(intent);

            }
        });

        return v;


        }

    private void viewAllFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference("ReferenceMaterialsModel");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                models.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                    RefferenceMaterialsModel model = postsnapshot.getValue(RefferenceMaterialsModel.class);
                    models.add(model);

                }

                String[] uploads = new String[models.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = models.get(i).getName();
                }

                adapter=new ListViewAdapterReferenceMaterials(getContext(),models);
                myPDFListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.papers_menu,menu);
        MenuItem searchItem=menu.findItem(R.id.search_papers);
        SearchView searchView=(SearchView)searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                return false;
            }
        });

    }
}

