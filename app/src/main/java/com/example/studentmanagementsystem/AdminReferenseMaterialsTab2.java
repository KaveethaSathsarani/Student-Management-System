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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementsystem.DBHandlers.RefferenceMaterialsModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class AdminReferenseMaterialsTab2 extends Fragment {


    EditText papersId;
    EditText papersName;
    TextView path;

    Button papersAdd;

    DatabaseReference databasePapers;
    StorageReference storageReference;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_admin_reference_materials1,container,false);

        storageReference= FirebaseStorage.getInstance().getReference();
        databasePapers= FirebaseDatabase.getInstance().getReference().child("ReferenceMaterialsModel");

        papersAdd= root.findViewById(R.id.papers_add_btn);


        papersId= root.findViewById(R.id.id_ans);
        papersName = root.findViewById(R.id.title_ans);


        papersAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(papersId.getText().toString())) {
                    Toast.makeText(getActivity(), "You should enter ID", Toast.LENGTH_LONG).show();
                }   else if (TextUtils.isEmpty(papersName.getText().toString())) {
                    Toast.makeText(getActivity(), "You should enter Title", Toast.LENGTH_LONG).show();

                } else {

                    selectPDFFile();
                }
            }
        });

        return root;


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
                    Uri url = uri.getResult();

                    String id = papersId.getText().toString().trim();
                    String name = papersName.getText().toString().trim();


                    RefferenceMaterialsModel model = new RefferenceMaterialsModel(id, name, url.toString());
                    databasePapers.child(id).setValue(model);
                    Toast.makeText(getActivity(), "Reference Material Added Successfully", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    clearControls();
                }
                //Toast.makeText(getContext(),"File Uploaded",Toast.LENGTH_LONG).show();


            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded: " + (int) progress + "%");

                }
            });


    }


    private void clearControls(){

        papersId.setText("");
        papersName.setText("");

    }

}













