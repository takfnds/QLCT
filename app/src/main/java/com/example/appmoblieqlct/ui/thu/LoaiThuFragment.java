package com.example.appmoblieqlct.ui.thu;

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
import com.example.appmoblieqlct.adapter.LoaiThuReyclerView;
import com.example.appmoblieqlct.adapter.ThuReyclerView;
import com.example.appmoblieqlct.entity.LoaiThu2;
import com.example.appmoblieqlct.entity.Thu2;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoaiThuFragment extends Fragment {

    private RecyclerView mRv;
    private LoaiThuReyclerView mAdapter;
    private Button btnAdd;
    private Button btnSavem,btnCancel;
    private TextInputEditText etName,etId;
    private DatabaseReference data;
    ArrayList<LoaiThu2> lt2;


    public static LoaiThuFragment newInstance() {
        return new LoaiThuFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_thu, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        btnAdd = view.findViewById(R.id.btnAdd);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setHasFixedSize(true);
        data = FirebaseDatabase.getInstance().getReference("Loại Thu");


        lt2 = new ArrayList<>();
        mAdapter = new LoaiThuReyclerView(lt2, new LoaiThuReyclerView.IClickListener() {
            @Override
            public void onClickDeleteItem(LoaiThu2 lt) {
                deleteItem(lt);
            }
        });
        mRv.setAdapter(mAdapter);

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(lt2 != null){
                    lt2.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    LoaiThu2 loaiThu = dataSnapshot.getValue(LoaiThu2.class);
                    lt2.add(loaiThu);
                    Log.d("oo",lt2.toString());
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
                LoaiThu2 loaiThu2 = snapshot.getValue(LoaiThu2.class);
                if(loaiThu2 == null|| lt2 == null || lt2.isEmpty()){
                    return;
                }
                for(int i = 0; i< lt2.size();i++){
                    if(loaiThu2.getId()== lt2.get(i).getId()){
                        lt2.remove(lt2.get(i));
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

        LoaiThuFragment currentFrament = this;

        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int posotion) {
                LoaiThu2 loaiThu = mAdapter.getItem(posotion);
                lt2.remove(loaiThu);
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
    }

    private void deleteItem(LoaiThu2 loaiThu2) {
        data.child(String.valueOf(loaiThu2.getId())).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(getActivity(), "Remove success", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void DialogLt()
    {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_loai_thu);
        dialog.setTitle("Thêm Loại Thu");
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
                LoaiThu2 lt22 = new LoaiThu2(nem,id);
                data.child(id).setValue(lt22);
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