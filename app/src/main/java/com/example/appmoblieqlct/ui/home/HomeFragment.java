package com.example.appmoblieqlct.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.databinding.FragmentHomeBinding;
import com.example.appmoblieqlct.entity.Chi;
import com.example.appmoblieqlct.entity.NganSach;
import com.example.appmoblieqlct.entity.Thu2;
import com.example.appmoblieqlct.entity.TongChi;
import com.example.appmoblieqlct.entity.TongCon;
import com.example.appmoblieqlct.entity.TongThu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private TextView tvcalender,tvmon,tvTongchi,tvTongThu,tvTongcon;
    private Button add,save;
    private EditText edadd;


    public static int parseInt(String s) {
        return 0;
    }

    private DatabaseReference data,dataThu,dataChi,dataTong;
    ArrayList<NganSach> ns;
    ArrayList<TongCon> tongcon;
    ArrayList<TongChi> tongchi;
    ArrayList<TongThu> tongthu;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        tvcalender = view.findViewById(R.id.calender);
        tvTongchi = view.findViewById(R.id.tvTongChi);
        tvTongThu = view.findViewById(R.id.tvTongThu);
        tvTongcon = view.findViewById(R.id.tvCon);
        add = view.findViewById(R.id.add);
        tvmon = view.findViewById(R.id.tvmon);
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        tvcalender.setText(time.format(calendar.getTime()));
        dataTong = FirebaseDatabase.getInstance().getReference("Tổng");
        dataChi = FirebaseDatabase.getInstance().getReference("Khoản Chi");

        tongcon = new ArrayList<>();
        dataTong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(tongcon != null){
                    tongcon.clear();
                }
                int sumcon = 0;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    TongCon tc = dataSnapshot.getValue(TongCon.class);
                    tongcon.add(tc);
                }
                for (int i = 0;i<tongcon.size();i++)
                {
                      sumcon = tongcon.get(i).getNs()+tongcon.get(i).getTt()-tongcon.get(i).getTc();
                    tvTongcon.setText(String.valueOf(sumcon));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tongchi = new ArrayList<>();
        dataChi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(tongchi != null){
                    tongchi.clear();
                }
                int sumchi = 0;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    TongChi chi = dataSnapshot.getValue(TongChi.class);
                    tongchi.add(chi);
                }
                for (int i = 0;i<tongchi.size();i++)
                {
                    sumchi += tongchi.get(i).getMoney();
                    tvTongchi.setText(String.valueOf(sumchi));
                    dataTong.child("1").child("tc").setValue(sumchi);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tongthu = new ArrayList<>();
        dataThu = FirebaseDatabase.getInstance().getReference("Khoản Thu");

        dataThu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(tongthu != null){
                    tongthu.clear();
                }
                int sumthu = 0;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    TongThu thu = dataSnapshot.getValue(TongThu.class);
                    tongthu.add(thu);
                }
                for (int i = 0;i<tongthu.size();i++)
                {
                    sumthu += tongthu.get(i).getMoney();
                    tvTongThu.setText(String.valueOf(sumthu));
                    dataTong.child("1").child("tt").setValue(sumthu);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        data = FirebaseDatabase.getInstance().getReference("Ngân Sách");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NganSach set = snapshot.getValue(NganSach.class);
                 tvmon.setText(String.valueOf(set.getNs()));

              //   int x = set.getNs() + 51;
                // Toast.makeText(getActivity(),String.valueOf(x),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ns = new ArrayList<>();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add();
            }
        });

    }

    private void Add(){
        Dialog dialog  = new Dialog(getContext());
        dialog.setContentView(R.layout.addns);
        dialog.show();
        edadd = dialog.findViewById(R.id.edadd);
        save = dialog.findViewById(R.id.save);
        dialog.setTitle("Nhập Số Tiền");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int in = Integer.parseInt(edadd.getText().toString());
                tvmon.setText(String.valueOf(in));
                data.child("ns").setValue(in);
                dataTong.child("1").child("ns").setValue(in);
                dialog.dismiss();
            }
        });

    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}