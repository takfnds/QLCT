package com.example.appmoblieqlct.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appmoblieqlct.ui.chi.KhoanChiFragment;
import com.example.appmoblieqlct.ui.chi.LoaiChiFragment;
import com.example.appmoblieqlct.ui.thongke.MonthFragment;
import com.example.appmoblieqlct.ui.thongke.YearFragment;

import java.time.Year;

public class thongkePager extends FragmentStateAdapter {
    public thongkePager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position == 0)
        {
            fragment = MonthFragment.newInstance();
        }
        else
        {
            fragment = YearFragment.newInstance();
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
