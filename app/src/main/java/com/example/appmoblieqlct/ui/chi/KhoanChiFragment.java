package com.example.appmoblieqlct.ui.chi;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmoblieqlct.R;
import com.example.appmoblieqlct.adapter.ChiReyclerView;
import com.example.appmoblieqlct.adapter.DropDownKhoanChiA;
import com.example.appmoblieqlct.adapter.DropDownKhoanThuA;
import com.example.appmoblieqlct.adapter.ItemClickListener;
import com.example.appmoblieqlct.adapter.ThuReyclerView;
import com.example.appmoblieqlct.entity.Category;
import com.example.appmoblieqlct.entity.Chi;
import com.example.appmoblieqlct.entity.Thu2;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;


public class KhoanChiFragment extends Fragment {

    private RecyclerView mRv;
    private ChiReyclerView mAdapter;
    private DropDownKhoanChiA drop;
    private TextInputEditText etName,etAmount,etNote;
    private Button btnSavem1,btnAdd1,btnHuy1,btnChose;
    private TextView txtDay,txtMonth,txtYear,textView;
    private EditText edDate;
    private int Year;
    private int Month;
    private int DayOfMonth;
    private Spinner splKhoanThu;
    private DatabaseReference data,data2;
    ArrayList<Chi> c;
    ArrayList<Category> list;


    public static KhoanChiFragment newInstance() {
        return new KhoanChiFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv =view.findViewById(R.id.recyclerView);
        btnAdd1 = view.findViewById(R.id.btnAdd1);
        mAdapter = new ChiReyclerView(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setHasFixedSize(true);
        data = FirebaseDatabase.getInstance().getReference("Khoản Chi");


        c = new ArrayList<>();
        mAdapter = new ChiReyclerView(c, new ChiReyclerView.IClickListener() {
            @Override
            public void onClickDeleteItem(Chi chi) {
                deleteItem(chi);
            }
        });
        mRv.setAdapter(mAdapter);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(c != null){
                    c.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Chi chi = dataSnapshot.getValue(Chi.class);
                    c.add(0,chi);
                    Log.d("oo",c.toString());
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

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Chi chi = snapshot.getValue(Chi.class);
                if(chi == null|| c == null || c.isEmpty()){
                    return;
                }
                for(int i = 0; i< c.size();i++){
                    if(chi.getLc() == c.get(i).getLc()){
                        c.remove(c.get(i));
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

        KhoanChiFragment currentFrament = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int posotion) {
                Chi chi = mAdapter.getItem(posotion);
            }
        });


        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add1();
            }
        });

    }
    private void deleteItem(Chi chi) {
        data.child(String.valueOf(chi.getTkc())).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(getActivity(), "Xóa Thành Công!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void Add1(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_chi);
        dialog.setTitle("Thêm Khoản Chi");
        textView = dialog.findViewById(R.id.txtLt);
        btnSavem1 = dialog.findViewById(R.id.btnSavem1);
        splKhoanThu  = dialog.findViewById(R.id.spType);
        txtDay = dialog.findViewById(R.id.dateDay);
        txtMonth = dialog.findViewById(R.id.dateMonth);
        txtYear = dialog.findViewById(R.id.dateYear);
        btnHuy1 = dialog.findViewById(R.id.btnHuy1);
        etName = dialog.findViewById(R.id.etName1);
        etAmount = dialog.findViewById(R.id.etAmount1);
        etNote = dialog.findViewById(R.id.etNote1);

        drop = new DropDownKhoanChiA(getActivity(),R.layout.spinner_dropdown,getList());
        splKhoanThu.setAdapter(drop);
        splKhoanThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText(drop.getItem(i).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectDay();
            }
        });

        txtMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectMonth();
            }
        });

        txtYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectYear();
            }
        });

        Calendar c = Calendar.getInstance();
        this.Year = c.get(Calendar.YEAR);
        this.Month = c.get(Calendar.MONTH);
        this.DayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        dialog.show();
        btnHuy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSavem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String note = etNote.getText().toString();
                String lc = textView.getText().toString();
                String day = txtDay.getText().toString();
                String month = txtMonth.getText().toString();
                String year = txtYear.getText().toString();
                int money = Integer.parseInt(etAmount.getText().toString());
//              Toast.makeText(getActivity(),lt,Toast.LENGTH_SHORT).show();
                Chi lc1 = new Chi(name,note,lc,money,day,month,year);
                data.child(name).setValue(lc1);
                dialog.dismiss();
            }
        });
    }
    private void SelectYear(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int Month, int Day) {

                txtYear.setText(String.valueOf(year));
                Year = year;
            }
        };
        DatePickerDialog pickerDialog = null;
        pickerDialog =  new DatePickerDialog(getContext(),dateSetListener,Year,Month,DayOfMonth);
        // pickerDialog =  new DatePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog_NoActionBar,dateSetListener,Year,Month,DayOfMonth);
        pickerDialog.show();
    }

    private void SelectMonth(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                txtMonth.setText(String.valueOf(month + 1));
                Month = month;
            }
        };
        DatePickerDialog pickerDialog = null;
        pickerDialog =  new DatePickerDialog(getContext(),dateSetListener,Year,Month,DayOfMonth);
        // pickerDialog =  new DatePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog_NoActionBar,dateSetListener,Year,Month,DayOfMonth);
        pickerDialog.show();
    }

    private void SelectDay(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                txtDay.setText(String.valueOf(dayOfMonth));
                DayOfMonth = dayOfMonth;
            }
        };
        DatePickerDialog pickerDialog = null;
        pickerDialog =  new DatePickerDialog(getContext(),dateSetListener,Year,Month,DayOfMonth);
        // pickerDialog =  new DatePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog_NoActionBar,dateSetListener,Year,Month,DayOfMonth);
        pickerDialog.show();
    }

    private ArrayList<Category> getList()
    {

        list = new ArrayList<>();
        data2 = FirebaseDatabase.getInstance().getReference("Loại Chi");
        data2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(list != null){
                    list.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Category Chi = dataSnapshot.getValue(Category.class);
                    list.add(new Category(Chi.getName()));
                }
                drop.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return list;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoan_chi, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(KhoanThuViewModel.class);
//        // TODO: Use the ViewModel
//        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
//            @Override
//            public void onChanged(List<Thu> thus) {
//                mAdapter.setmList(thus);
//            }
//        });
//    }

}