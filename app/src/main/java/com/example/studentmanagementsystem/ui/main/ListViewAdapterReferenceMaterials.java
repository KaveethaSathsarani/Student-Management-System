package com.example.studentmanagementsystem.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.studentmanagementsystem.R;
import com.example.studentmanagementsystem.DBHandlers.RefferenceMaterialsModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapterReferenceMaterials extends ArrayAdapter<RefferenceMaterialsModel> implements Filterable{

    private Context context;
    private List<RefferenceMaterialsModel> modelList;
    private List<RefferenceMaterialsModel> model;
    //ArrayList<RefferenceMaterialsModel> arrayList;


    public ListViewAdapterReferenceMaterials(Context context, List<RefferenceMaterialsModel> modelList) {

        super(context, R.layout.listview_referencematerials,modelList);
        this.context = context;
        this.modelList = modelList;

        model=new ArrayList<>(modelList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listViewItem=(LayoutInflater.from(context).inflate(R.layout.listview_referencematerials,null,true));

        TextView name=(TextView)listViewItem.findViewById(R.id.list_reference);

        RefferenceMaterialsModel model1=modelList.get(position);
        name.setText(model1.getName());

        return listViewItem;


    }

   @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<RefferenceMaterialsModel> filteredList=new ArrayList<>();

            if(charSequence==null || charSequence.length()==0){
                filteredList.addAll(model);
            }else {
                String filterPattern=charSequence.toString().toLowerCase().trim();

                for (RefferenceMaterialsModel papers : model){

                    if (papers.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(papers);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {


            modelList.clear();
            modelList.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };




}
