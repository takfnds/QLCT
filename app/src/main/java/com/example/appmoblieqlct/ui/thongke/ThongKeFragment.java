package com.example.appmoblieqlct.ui.thongke;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.adapter.thongkePager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThongKeFragment extends Fragment {
    private ViewPager2 mVp;
    private TabLayout mTl;

    public ThongKeFragment() {
    }

    public static ThongKeFragment newInstance() {
        return new ThongKeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVp = view.findViewById(R.id.viewPager2);
        mTl = view.findViewById(R.id.tabLayout);
        thongkePager pager = new thongkePager(getActivity());
        mVp.setAdapter(pager);
        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0)
                {
                    tab.setText("Theo Tháng");
                  //  tab.setIcon(R.drawable.ic_menu_camera);
                }
                else
                {
                    tab.setText("Theo Năm");
                   // tab.setIcon(R.drawable.ic_menu_camera);
                }
            }
        }).attach();


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }
}