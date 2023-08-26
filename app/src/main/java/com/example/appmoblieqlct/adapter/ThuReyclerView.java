package com.example.appmoblieqlct.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.entity.Thu2;

import java.util.ArrayList;

public class ThuReyclerView extends RecyclerView.Adapter<ThuReyclerView.ThuViewHolder> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<Thu2> mList;
    private IClickListener icl;


    public static ItemClickListener itemDeleteClickListener;
    public static ItemClickListener itemViewClickListener;

    public ThuReyclerView(Context context) {
        mLayoutInflater =LayoutInflater.from(context);
    }

    public interface IClickListener{
        void onClickDeleteItem(Thu2 lt);
        void onClickViewItem(Thu2 lt);
    }

    public ThuReyclerView( ArrayList<Thu2> mList, ThuReyclerView.IClickListener iclick) {
        this.mList = mList;
        this.icl = iclick;
//        notifyDataSetChanged();
    }

    public void setOnItemDeleteClickListener(ItemClickListener itemDeleteClickListener) {
        ThuReyclerView.itemDeleteClickListener = itemDeleteClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        ThuReyclerView.itemViewClickListener = itemViewClickListener;
    }


    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.reyclerview_thu_item,parent,false);
        return new ThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Thu2 t = mList.get(position);
        if(mList != null)
        {
            holder.tvName.setText(t.getTkt());
            holder.tvNote.setText(t.getNote());
            holder.tvLt.setText(t.getLt());
            holder.date.setText(t.getDay()+"/"+t.getMonth()+"/"+t.getYear());
            holder.tvAmount.setText(t.getMoney()+" Đồng");


//            holder.position = position;
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    icl.onClickDeleteItem(t);
                }
            });

            holder.ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    icl.onClickViewItem(t);
                }
            });


        }

    }

    @Override
    public int getItemCount() {
//        if(mList == null)
//        return 0;
        return mList.size();
    }

    public Thu2 getItem(int position)
    {
        if(mList == null | position >= mList.size())
        {
            return null;
        }
        return mList.get(position);
    }
//    public void setmList(List<Thu> mList) {
//        this.mList = mList;
//        notifyDataSetChanged();
//    }

    public static class ThuViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvName, tvAmount, tvNote,tvLt,date;
        public ImageView ivDelete;
        public ImageView ivView;
        public CardView cv;
        public int position;

        public ThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameKT2);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvLt = itemView.findViewById(R.id.tvLoaithu);
            date = itemView.findViewById(R.id.tvdate);
            tvNote = itemView.findViewById(R.id.tvNote);
            ivView = itemView.findViewById(R.id.ivView1);
            ivDelete = itemView.findViewById(R.id.ivDelete);

            cv = (CardView) itemView;
//            ivView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(itemViewClickListener != null)
//                    {
//                        itemViewClickListener.onItemClick(position);
//                    }
//                }
//            });

//            ivEdit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(itemEditClickListener != null)
//                    {
//                        itemEditClickListener.onItemClick(position);
//                    }
//                }
//            });/

            ivView.setOnClickListener((view) -> {
                if(itemViewClickListener != null)
                {
                    itemViewClickListener.onItemClick(position);
                }
            });
            ivDelete.setOnClickListener((view)-> {
                if(itemDeleteClickListener != null)
                    {
                        itemDeleteClickListener.onItemClick(position);
                    }
            });
        }
    }
}
