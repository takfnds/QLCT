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
import com.example.appmoblieqlct.entity.Category;

import java.util.List;

public class DropDownKhoanChiA extends ArrayAdapter<Category> {

    public DropDownKhoanChiA(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_dropdown,parent,false);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_khoan_thu,parent,false);

        TextView textView = convertView.findViewById(R.id.tvNameKT);

        Category lt = this.getItem(position);
        if(lt !=  null)
        {
            textView.setText(lt.getName());
        }
        return convertView;
    }
}
