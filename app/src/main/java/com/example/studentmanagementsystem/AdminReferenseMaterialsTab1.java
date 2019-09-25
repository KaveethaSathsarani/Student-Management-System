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
import android.widget.EditText;
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

public class AdminReferenseMaterialsTab1 extends Fragment {

    ListView myPDFListView;
    DatabaseReference databaseReference;
    List<RefferenceMaterialsModel> models;
    ListViewAdapterReferenceMaterials adapter;

    EditText editTextSearch;

    ArrayList<RefferenceMaterialsModel> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_admin_reference_materials,container,false);

        myPDFListView=(ListView)view.findViewById(R.id.listView_papers);
        models=new ArrayList<>();
        arrayList=new ArrayList<RefferenceMaterialsModel>();

      /*  editTextSearch=(EditText)view.findViewById(R.id.search_text);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!editable.toString().isEmpty()){
                    search(editable.toString());
                }else {
                    search("");
                }

            }
        });*/

        viewAllFiles();

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



        return view;

    }

    /*private void search(String s) {

        String q=s.toLowerCase().trim();

        Query query=databaseReference.orderByChild("name").startAt(q).endAt(q + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()){

                    arrayList.clear();
                    for (DataSnapshot dss:dataSnapshot.getChildren()){

                        final RefferenceMaterialsModel refferenceMaterialsModel=dss.getValue(RefferenceMaterialsModel.class);
                        arrayList.add(refferenceMaterialsModel);

                    }

                    ListViewAdapterReferenceMaterials myadapter=new ListViewAdapterReferenceMaterials(getContext(),arrayList);
                    myPDFListView.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }*/


    private void viewAllFiles() {

        databaseReference=FirebaseDatabase.getInstance().getReference("ReferenceMaterialsModel");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                models.clear();
                for (DataSnapshot postsnapshot:dataSnapshot.getChildren()){

                    RefferenceMaterialsModel model=postsnapshot.getValue(RefferenceMaterialsModel.class);
                    models.add(model);

                }

                String[] uploads=new String[models.size()];

                for (int i=0;i<uploads.length;i++){
                    uploads[i]=models.get(i).getName();
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













