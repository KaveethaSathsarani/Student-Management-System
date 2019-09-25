package com.example.studentmanagementsystem.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.studentmanagementsystem.DBHandlers.AdminModel;
import com.example.studentmanagementsystem.R;

import java.util.List;

public class AdminList extends ArrayAdapter<AdminModel> {

    private Activity context;
    private List<AdminModel> adminList;

    public AdminList(Activity context, List<AdminModel> adminList){
        super(context, R.layout.admin_list, adminList);
        this.context = context;
        this.adminList = adminList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listItem = inflater.inflate(R.layout.admin_list,null,true);

        TextView adminId = (TextView) listItem.findViewById(R.id.adminId1);
        TextView adminName = (TextView) listItem.findViewById(R.id.adminName1);
        TextView password = (TextView) listItem.findViewById(R.id.adminPassword);

        AdminModel adminModel = adminList.get(position);

        adminId.setText(adminModel.getAdminID());
        adminName.setText(adminModel.getName());
        password.setText(adminModel.getPassword());

        return listItem;
    }
}
