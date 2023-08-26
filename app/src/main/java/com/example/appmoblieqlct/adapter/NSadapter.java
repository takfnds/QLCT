package com.example.appmoblieqlct.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.entity.NganSach;

import java.util.Arrays;
import java.util.List;

public class NSadapter extends ArrayAdapter<NganSach> {
    public NSadapter(@NonNull Context context, int resource, @NonNull List<NganSach> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_thong_ke, parent, false);
        TextView textView = convertView.findViewById(R.id.tvmon);
        NganSach ns = this.getItem(position);
        if (ns != null) {
            textView.setText(ns.getNs());
        }
        return convertView;
    }

//    @Override
//    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
////        convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_thong_ke,parent,false);
////        TextView textView = convertView.findViewById(R.id.tvmon);
////        NganSach ns = this.getItem(position);
////        if(ns !=  null)
////        {
////            textView.setText(ns.getNs());
////        }
////        return convertView;
//    }

}
