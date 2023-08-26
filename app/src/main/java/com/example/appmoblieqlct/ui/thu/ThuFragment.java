package com.example.appmoblieqlct.ui.thu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.adapter.chiviewPager2Adapter;
import com.example.appmoblieqlct.adapter.thuviewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThuFragment extends Fragment {

    private ViewPager2 mVp;
    private TabLayout mTl;

    public ThuFragment() {
        // Required empty public constructor
    }


    public static ThuFragment newInstance(String param1, String param2) {
        ThuFragment fragment = new ThuFragment();

        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVp = view.findViewById(R.id.viewPager2);
        mTl = view.findViewById(R.id.tabLayout);
        thuviewPager2Adapter adapter = new thuviewPager2Adapter(getActivity());
        mVp.setAdapter(adapter);

        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0)
                {
                    tab.setText("Khoản Thu");
                   // tab.setIcon(R.drawable.ic_menu_camera);
                }
                else
                {
                    tab.setText("Loại Khoản Thu");
                   // tab.setIcon(R.drawable.ic_menu_camera);
                }
            }
        }).attach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thu, container, false);
    }
}