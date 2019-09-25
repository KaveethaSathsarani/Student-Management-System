package com.example.studentmanagementsystem.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagementsystem.DBHandlers.NoticesModel;
import com.example.studentmanagementsystem.R;

import java.util.ArrayList;

public class RecyclerViewAdapterNotices extends RecyclerView.Adapter<RecyclerViewAdapterNotices.MyViewHolder> {

        Context context;
        ArrayList<NoticesModel> models;


    public RecyclerViewAdapterNotices(Context context, ArrayList<NoticesModel> models) {
         this.context=context;
        this.models = models;

    }



    @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_card_view_notices,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.content.setText(models.get(position).getContent());
            holder.title.setText(models.get(position).getTitle());
            NoticesModel noticesModel=models.get(position);


        }

    @Override
    public int getItemCount()
    {
        return models.size();
    }





      public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView title,content;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);


                content=(TextView) itemView.findViewById(R.id.content_notice);
                title=(TextView)itemView.findViewById(R.id.title_notice);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }


      }

    }
