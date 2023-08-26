package com.example.appmoblieqlct.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.entity.Chi;

import java.util.ArrayList;

public class ChiReyclerView extends RecyclerView.Adapter<ChiReyclerView.ChiViewHolder> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<Chi> mList;
    private IClickListener icl;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;

    public ChiReyclerView(Context context) {
        mLayoutInflater =LayoutInflater.from(context);
    }

    public interface IClickListener{
        void onClickDeleteItem(Chi lc);
    }
    public ChiReyclerView( ArrayList<Chi> mList, ChiReyclerView.IClickListener iclick) {
        this.mList = mList;
        this.icl = iclick;
//        notifyDataSetChanged();
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        ChiReyclerView.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        ChiReyclerView.itemViewClickListener = itemViewClickListener;
    }


    @NonNull
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.reyclerview_chi_item,parent,false);
        return new ChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Chi t = mList.get(position);
        if(mList != null)
        {
            holder.tvName.setText(t.getTkc());
            holder.tvNote.setText(t.getNote());
            holder.tvLt.setText(t.getLc());
            holder.tvDate.setText(t.getDay()+"/"+t.getMonth()+"/"+t.getYear());
            holder.tvAmount.setText(t.getMoney()+" Đồng");

//            holder.position = position;
            holder.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    icl.onClickDeleteItem(t);
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

    public Chi getItem(int position)
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

    public static class ChiViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvName, tvAmount, tvNote,tvLt,tvDate;
        public ImageView ivEdit,ivView;
        public CardView cv;
        public int position;

        public ChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvdate);
            tvName = itemView.findViewById(R.id.tvNameKT2);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvLt = itemView.findViewById(R.id.tvLoaithu);
            tvNote = itemView.findViewById(R.id.tvNote);
            ivView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);

            cv = (CardView) itemView;

            ivView.setOnClickListener((view) -> {
                if(itemViewClickListener != null)
                {
                    itemViewClickListener.onItemClick(position);
                }
            });
            ivEdit.setOnClickListener((view)-> {
                if(itemEditClickListener != null)
                {
                    itemEditClickListener.onItemClick(position);
                }
            });
        }
    }
}
