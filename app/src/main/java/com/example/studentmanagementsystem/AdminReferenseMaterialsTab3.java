package com.example.studentmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementsystem.DBHandlers.RefferenceMaterialsModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class AdminReferenseMaterialsTab3 extends Fragment {

    EditText papersId;
    EditText papersName;
    EditText papersUrl;

    ImageButton papersSearch;
    Button delete;
    ImageButton choose;
    Button update;


    DatabaseReference databasePapers;
    StorageReference storageReference;

    Uri url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_admin_reference_materials2,container,false);

        storageReference= FirebaseStorage.getInstance().getReference();
        databasePapers= FirebaseDatabase.getInstance().getReference().child("ReferenceMaterialsModel");

        papersSearch=(ImageButton) root.findViewById(R.id.search_btn_papers);
        delete=(Button)root.findViewById(R.id.papers_delete_btn);
        choose=(ImageButton) root.findViewById(R.id.choose_btn2);
        update=(Button)root.findViewById(R.id.papers_update_btn);

        papersId=(EditText)root.findViewById(R.id.id_ans2);
        papersName =(EditText)root.findViewById(R.id.title_ans2);
        papersUrl =(EditText)root.findViewById(R.id.url_ans);


        papersSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseReference databaseSearch = FirebaseDatabase.getInstance().getReference().child("ReferenceMaterialsModel");

                databaseSearch.child(papersId.getText().toString()).addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.hasChildren()){

                                papersName.setText(dataSnapshot.child("name").getValue().toString());
                                papersUrl.setText(dataSnapshot.child("url").getValue().toString());

                            }else{

                                Toast.makeText(getContext(),"Invalid Reference Material ID",Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id=papersId.getText().toString().trim();

                    deletePapers(id);
                    clearControls();

            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=papersId.getText().toString().trim();
                String name=papersName.getText().toString().trim();
                //String url=papersUrl.getText().toString().trim();

                if (!TextUtils.isEmpty(id)){
                    updatePapers(id,name,url.toString());

                }
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectPDFFile();

            }
        });


        return root;


    }

    private boolean updatePapers(String id, String t, String c){

        DatabaseReference updatedatabase=FirebaseDatabase.getInstance().getReference("ReferenceMaterialsModel").child(id);

        RefferenceMaterialsModel model=new RefferenceMaterialsModel(id,t,c);
        updatedatabase.setValue(model);
        Toast.makeText(getContext(),"Reference Material Updated",Toast.LENGTH_LONG).show();
        clearControls();

        return true;
    }

    public  boolean deletePapers(String id){

        DatabaseReference deletedatabase=FirebaseDatabase.getInstance().getReference("ReferenceMaterialsModel").child(id);


        deletedatabase.removeValue();

        Toast.makeText(getContext(),"Reference Material Deleted",Toast.LENGTH_LONG).show();
        clearControls();

        return true;
    }


    private void clearControls(){


        papersName.setText("");
        papersUrl.setText("");
        papersId.setText("");

    }

    private void selectPDFFile() {

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File"),1);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uploadPDFFile(data.getData());
        }else {
            Toast.makeText(getActivity(), "No Permission to upload pdf", Toast.LENGTH_LONG).show();
        }


    }

    private void uploadPDFFile(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Uploading....");
        progressDialog.show();

        StorageReference reference = storageReference.child("uploads/" + System.currentTimeMillis() + ".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while (!uri.isComplete()) ;
                url = uri.getResult();

                progressDialog.dismiss();

                Toast.makeText(getContext(),"File Uploaded",Toast.LENGTH_LONG).show();


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressDialog.setMessage("Uploaded: " + (int) progress + "%");

            }
        });

    }

    }













