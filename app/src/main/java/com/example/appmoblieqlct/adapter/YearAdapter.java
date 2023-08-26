package com.example.appmoblieqlct.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.entity.Month;
import com.example.appmoblieqlct.entity.Year;

import java.util.ArrayList;
import java.util.List;

public class YearAdapter extends ArrayAdapter<Year> {
    private Context context;
    private ArrayList<Year> data;
    public Resources res;
    private LayoutInflater inflater;

    public YearAdapter(Context context, ArrayList<Year> objects) {
        super(context, R.layout.year_adapter, objects);

        this.context = context;
        data = objects;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.year_adapter, parent, false);
        Year Nam = data.get(position);
        TextView txtCustomSp = (TextView) row.findViewById(R.id.edtYear);
        txtCustomSp.setText("Năm: "+ Nam.getYear());

        return row;
    }
}
