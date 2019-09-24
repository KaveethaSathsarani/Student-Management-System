package com.example.studentmanagementsystem;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import static com.example.studentmanagementsystem.TimeTableUtils.searchString;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context c;
    private int mBackground;
    private int[] mMaterialColours;
    public List<SubjectModel> subjectModel;

    interface ItemClickListener{

        void onItemClick(int pos);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView subIdtxt, subNametxt;
        //private final MaterialLetterIcon mIcon;
        private ItemClickListener itemClickListener;

        ViewHolder(View itemView){

            super(itemView);

            subIdtxt = itemView.findViewById(R.id.adminView_subId);
            subNametxt = itemView.findViewById(R.id.adminView_subName);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            this.itemClickListener.onItemClick(this.getLayoutPosition());

        }

        void setItemClickListener(ItemClickListener itemClickListener){

            this.itemClickListener = itemClickListener;

        }

    }



    public MyAdapter(List<SubjectModel> subjectModel){

        this.subjectModel = subjectModel;

    }


    private void prepareLetteIcons(Context c){

        TypedValue mTypedValue = new TypedValue();

        c.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);

        mMaterialColours = c.getResources().getIntArray(R.array.colors);
        mBackground = mTypedValue.resourceId;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        this.c = parent.getContext();
        prepareLetteIcons(c);
        View view = LayoutInflater.from(c).inflate(R.layout.subject_list_item, parent, false);
        //view.setOnBackgroungResource(mBackground);
        return new ViewHolder(view);

    }

    public void onBindViewHolder(ViewHolder holder, int position){

        final SubjectModel s = subjectModel.get(position);

        holder.subIdtxt.setText(s.getSubId());
        holder.subNametxt.setText(s.getSubName());


        if(position%2 == 0 ){

            holder.itemView.setBackgroundColor(Color.parseColor("#4285F4"));

        }

        String subId = s.getSubId().toLowerCase(Locale.getDefault());

        if(subId.contains(searchString) && !(searchString.isEmpty())){

            int startPos = subId.indexOf(searchString);
            int endPos = startPos + searchString.length();

            Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.subIdtxt.getText());

            spanString.setSpan(new ForegroundColorSpan(Color.RED), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.subIdtxt.setText(spanString);

        }else{


        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
