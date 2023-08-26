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
import com.example.appmoblieqlct.entity.KhoanThuView;

import java.util.List;

public class ViewKTA extends ArrayAdapter<KhoanThuView> {

    public ViewKTA(@NonNull Context context, int resource, @NonNull List<KhoanThuView> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.viewlistkt,parent,false);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_thu_view,parent,false);

        TextView textView3 = convertView.findViewById(R.id.tvgc1);

        KhoanThuView lt = this.getItem(position);
        if(lt !=  null)
        {
            textView3.setText(lt.getGhi());
        }
        return convertView;
    }
}
