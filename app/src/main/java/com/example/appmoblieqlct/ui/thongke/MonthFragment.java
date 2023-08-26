package com.example.appmoblieqlct.ui.thongke;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.adapter.MonthAdapter;
import com.example.appmoblieqlct.entity.Month;
import com.example.appmoblieqlct.entity.ThongKeThang;
import com.example.appmoblieqlct.entity.Thu2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthFragment extends Fragment{
    private TextView txtKhoanThu,txtKhoanChi;
    private Spinner spl;
    private DatabaseReference data,data2;
    ArrayList<Month> lm;
    ArrayList<ThongKeThang> tkt,tkt2;

    public static MonthFragment newInstance() {
        return new MonthFragment();
    }

    private void Mont(View v){
        spl = v.findViewById(R.id.spType);
        lm = new ArrayList<>();
        for(int i = 0;i<12;i++)
        {
            lm.add(new Month(i,String.valueOf(i+1)));
        }
        MonthAdapter adapter = new MonthAdapter(getContext(),lm);
        spl.setAdapter(adapter);
    }

    private void ClickSpinner() {
        spl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(tkt != null){
                            tkt.clear();
                        }
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            ThongKeThang Thon = dataSnapshot.getValue(ThongKeThang.class);
                            tkt.add(Thon);
                        }
                        int sum = 0;
                        for(int k = 0; k<tkt.size();k++){
                                if (Integer.parseInt(tkt.get(k).getMonth()) == Integer.parseInt(lm.get(i).getMonth())) {
                                    sum += tkt.get(k).getMoney();
                                }
                        }
                        txtKhoanThu.setText("Khoản Thu: " + sum);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                data2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(tkt2 != null){
                            tkt2.clear();
                        }
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            ThongKeThang Thon = dataSnapshot.getValue(ThongKeThang.class);
                            tkt2.add(Thon);
                        }
                        int sum2 = 0;
                        for(int h = 0; h<tkt2.size();h++){
                            if (Integer.parseInt(tkt2.get(h).getMonth()) == Integer.parseInt(lm.get(i).getMonth())) {
                                sum2 += tkt2.get(h).getMoney();
                            }
                        }
                        txtKhoanChi.setText("Khoản Chi: " + sum2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.month_fragment, container, false);
        Mont(view);
        ClickSpinner();
        txtKhoanThu = view.findViewById(R.id.txtKhoanThu);
        txtKhoanChi = view.findViewById(R.id.txtKhoanChi);
        data = FirebaseDatabase.getInstance().getReference("Khoản Thu");
        data2 = FirebaseDatabase.getInstance().getReference("Khoản Chi");
        tkt = new ArrayList<>();
        tkt2 = new ArrayList<>();

        return view;
    }
}
