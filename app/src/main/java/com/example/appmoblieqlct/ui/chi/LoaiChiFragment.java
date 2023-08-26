package com.example.appmoblieqlct.ui.chi;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.adapter.ItemClickListener;
import com.example.appmoblieqlct.adapter.LoaiChiReyclerView;
import com.example.appmoblieqlct.adapter.LoaiThuReyclerView;
import com.example.appmoblieqlct.entity.LoaiChi;
import com.example.appmoblieqlct.entity.LoaiThu2;
import com.example.appmoblieqlct.ui.thu.LoaiThuFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoaiChiFragment extends Fragment {

    private RecyclerView mRv;
    private LoaiChiReyclerView mAdapter;
    private Button btnAdd;
    private Button btnSavem,btnCancel;
    private TextInputEditText etName,etId;
    private DatabaseReference data;
    ArrayList<LoaiChi> lc1;


    public static LoaiChiFragment newInstance() {
        return new LoaiChiFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_chi, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        btnAdd = view.findViewById(R.id.btnAdd);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setHasFixedSize(true);
        data = FirebaseDatabase.getInstance().getReference("Loại Chi");


        lc1 = new ArrayList<>();
        mAdapter = new LoaiChiReyclerView(lc1, new LoaiChiReyclerView.IClickListener() {
            @Override
            public void onClickDeleteItem(LoaiChi lc) {
                deleteItem(lc);
            }
        });
        mRv.setAdapter(mAdapter);

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(lc1 != null){
                    lc1.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    LoaiChi loaiChi = dataSnapshot.getValue(LoaiChi.class);
                    lc1.add(loaiChi);
                    Log.d("oo",lc1.toString());
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                LoaiThu2 loaiThu2 = snapshot.getValue(LoaiThu2.class);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                LoaiChi loaiChi = snapshot.getValue(LoaiChi.class);
                if(loaiChi == null|| lc1 == null || lc1.isEmpty()){
                    return;
                }
                for(int i = 0; i< lc1.size();i++){
                    if(loaiChi.getId()== lc1.get(i).getId()){
                        lc1.remove(lc1.get(i));
                        break;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LoaiChiFragment currentFrament = this;

        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int posotion) {
                LoaiChi loaiChi = mAdapter.getItem(posotion);
               // LoaiChi loaiChi = mAdapter.getItem(posotion);
                lc1.remove(loaiChi);
//                LoaiThuDialog dialog = new LoaiThuDialog(getActivity(),currentFrament,loaiThu);
//                dialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogLt();

            }
        });
//        ItemTouchHelper helper = new ItemTouchHelper(
//                new ItemTouchHelper.SimpleCallback( 0,
//                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
//                ) {
//                    @Override
//                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                        int position = viewHolder.getAdapterPosition();
//                        LoaiThu2 lthu2 = new LoaiThu2();
//                        String vt = position.
//                        data.child(String.valueOf(lthu2.getId())).removeValue(new DatabaseReference.CompletionListener() {
//                            @Override
//                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                                Toast.makeText(getActivity(), "Successfully", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        data.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                LoaiThu2 lthu2 = new LoaiThu2();
//                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
//
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                Log.e("TAG", "onCancelled", databaseError.toException());
//                            }
//                        });
//                        Toast.makeText(getActivity(), "Loại thu đã được xóa", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//        );
//        helper.attachToRecyclerView(mRv);
    }

    private void deleteItem(LoaiChi loaiChi) {
        data.child(String.valueOf(loaiChi.getId())).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(getActivity(), "Remove success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void DialogLt()
    {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_loai_chi);
        dialog.setTitle("Thêm Loại Chi");
        btnSavem = dialog.findViewById(R.id.btnSavem);
        btnCancel = dialog.findViewById(R.id.btnHuy);
        etName = dialog.findViewById(R.id.etName);
        etId = dialog.findViewById(R.id.etId);
        dialog.show();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSavem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LoaiThu2 lt = new LoaiThu2();
                String id = etId.getText().toString();
                String nem = etName.getText().toString();
                LoaiChi lc11 = new LoaiChi(nem,id);
                data.child(id).setValue(lc11);
                dialog.dismiss();
            }
        });
    }






//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(LoaiThuViewModel.class);
//        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<LoaiThu>>() {
//            @Override
//            public void onChanged(List<LoaiThu> loaiThus) {
//                mAdapter.setmList(loaiThus);
//            }
//        });

//    }

}