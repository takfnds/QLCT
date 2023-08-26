package com.example.appmoblieqlct.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appmoblieqlct.ui.chi.KhoanChiFragment;
import com.example.appmoblieqlct.ui.chi.LoaiChiFragment;
import com.example.appmoblieqlct.ui.thu.KhoanThuFragment;
import com.example.appmoblieqlct.ui.thu.LoaiThuFragment;

public class chiviewPager2Adapter extends FragmentStateAdapter {
    public chiviewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position == 0)
        {
            fragment = KhoanChiFragment.newInstance();
        }
        else
        {
            fragment = LoaiChiFragment.newInstance();
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
