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
import com.example.appmoblieqlct.entity.LoaiChi;
import com.example.appmoblieqlct.entity.LoaiThu2;

import java.util.ArrayList;

public class LoaiChiReyclerView extends RecyclerView.Adapter<LoaiChiReyclerView.loaiChiViewHolder> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<LoaiChi> mList;
    private IClickListener icl;


    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public interface IClickListener{
        void onClickDeleteItem(LoaiChi lc1);
    }

    public LoaiChiReyclerView(Context context) {
        mLayoutInflater =LayoutInflater.from(context);
    }

    public LoaiChiReyclerView( ArrayList<LoaiChi> mList, IClickListener iclick) {
        this.mList = mList;
        this.icl = iclick;
//        notifyDataSetChanged();
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        LoaiThuReyclerView.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiThuReyclerView.itemViewClickListener = itemViewClickListener;
    }


    @NonNull
    @Override
    public loaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.reyclerview_loai_thu_item,parent, false);
        return new loaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull loaiChiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LoaiChi lc = mList.get(position);
        if(mList != null)
        {
            holder.tvName.setText(lc.getName());
//            holder.position = position;
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    icl.onClickDeleteItem(lc);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
//        if (mList == null) {
//            return mList.size();
//        }
        return mList.size();
    }

    public LoaiChi getItem(int position)
    {
        if(mList == null | position >= mList.size())
        {
            return null;
        }
        return mList.get(position);
    }
//    public void setmList(ArrayList<LoaiThu> mList) {
//        this.mList = mList;
//        notifyDataSetChanged();
//    }

    public static class loaiChiViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvName;
        public ImageView imgDelete;
        public ImageView ivEdit,ivView, ivDetail;
        public CardView cv;
        public int position;

        public loaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameKT);
            ivView = itemView.findViewById(R.id.ivView);
//            ivEdit = itemView.findViewById(R.id.ivEdit);
            imgDelete = itemView.findViewById(R.id.ivdelete);

            cv = (CardView) itemView;
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemEditClickListener != null)
                    {
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
//
//            ivDetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(itemEditClickListener != null)
//                    {
//                        itemEditClickListener.onItemClick(position);
//                    }
//                }
//            });
        }
    }
}
