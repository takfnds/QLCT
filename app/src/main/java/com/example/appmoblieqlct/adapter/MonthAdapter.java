package com.example.appmoblieqlct.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.entity.Month;

import java.util.ArrayList;
import java.util.List;

public class MonthAdapter extends ArrayAdapter<Month> {

    private Context context;
    private ArrayList<Month> data;
    public Resources res;
    private LayoutInflater inflater;

    public MonthAdapter(Context context, ArrayList<Month> objects) {
        super(context, R.layout.month_adapter, objects);

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

        View row = inflater.inflate(R.layout.month_adapter, parent, false);
        Month thangNam = data.get(position);
        TextView txtCustomSp = (TextView) row.findViewById(R.id.edtMon);
        txtCustomSp.setText("Tháng: "+ thangNam.getMonth());

        return row;
    }
}
