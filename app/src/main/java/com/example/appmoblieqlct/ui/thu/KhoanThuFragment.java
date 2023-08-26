package com.example.appmoblieqlct.ui.thu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.appmoblieqlct.adapter.DropDownKhoanThuA;
import com.example.appmoblieqlct.adapter.ItemClickListener;
import com.example.appmoblieqlct.adapter.ThuReyclerView;
import com.example.appmoblieqlct.adapter.ViewKTA;
import com.example.appmoblieqlct.entity.Category;
import com.example.appmoblieqlct.entity.KhoanThuView;
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


public class KhoanThuFragment extends Fragment {

    private RecyclerView mRv;
    private ThuReyclerView mAdapter;
    private DropDownKhoanThuA drop;
    private ViewKTA show;
    private TextInputEditText etName;
    private TextInputEditText etAmount;
    private TextInputEditText etNote;
    private Button btnSavem1,btnAdd1,btnHuy1,btnChose;
    private EditText edDate;
    private int Year;
    private int Month;
    private int DayOfMonth;
    private TextView txtDay,txtMonth,txtYear,textView,ghichu;
    private Spinner splKhoanThu,showkt;
    private DatabaseReference data,data2,data3,dtv;
    ArrayList<Thu2> ltt,tv;
    ArrayList<Category> list;
    ArrayList<KhoanThuView> ktv;


    public static KhoanThuFragment newInstance() {
        return new KhoanThuFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv =view.findViewById(R.id.recyclerView);
        btnAdd1 = view.findViewById(R.id.btnAdd1);
        mAdapter = new ThuReyclerView(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setHasFixedSize(true);
        data = FirebaseDatabase.getInstance().getReference("Khoản Thu");


        ltt = new ArrayList<>();
        mAdapter = new ThuReyclerView(ltt, new ThuReyclerView.IClickListener() {
            @Override
            public void onClickDeleteItem(Thu2 lt) {
                deleteItem(lt);
            }

            @Override
            public void onClickViewItem(Thu2 lt) {
                viewItem(lt);
            }

        });
        mRv.setAdapter(mAdapter);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(ltt != null){
                    ltt.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                   Thu2 Thu = dataSnapshot.getValue(Thu2.class);
                    ltt.add(0,Thu);
                    Log.d("oo",ltt.toString());
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
                Thu2 Thu2 = snapshot.getValue(Thu2.class);
                if(Thu2 == null|| ltt == null || ltt.isEmpty()){
                    return;
                }
                for(int i = 0; i< ltt.size();i++){
                    if(Thu2.getLt()== ltt.get(i).getLt()){
                        ltt.remove(ltt.get(i));
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

        KhoanThuFragment currentFrament = this;
        mAdapter.setOnItemDeleteClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int posotion) {
                Thu2 Thu = mAdapter.getItem(posotion);
            }
        });

        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int posotion) {
                Thu2 Thu = mAdapter.getItem(posotion);
            }
        });


        btnAdd1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Add1();
          }
      });
    }
    private void deleteItem(Thu2 Thu2) {
        data.child(String.valueOf(Thu2.getTkt())).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(getActivity(), "Xóa Thành Công!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void viewItem(Thu2 Thu2) {
        ThuView();
    }


    private void ThuView(){
      Dialog dialog = new Dialog(getContext());
      dialog.setContentView(R.layout.viewkt_fragment);
      dialog.setTitle("Xem Khoản Thu");
      ghichu = dialog.findViewById(R.id.tvgc);
        dtv = FirebaseDatabase.getInstance().getReference("Khoản Thu");
        tv = new ArrayList<>();
        dtv.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(tv != null){
                    tv.clear();
                }
               for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Thu2 thu = dataSnapshot.getValue(Thu2.class);
                    tv.add(thu);
                    for (int i = 0;i<tv.size();i++)
                    {
                        ghichu.setText(String.valueOf(thu.getNote()));
                    }
                   // ghichu.setText(String.valueOf(thu.getNote()));
                }
//                Thu2 Thu2 = snapshot.getValue(Thu2.class);
//                if(Thu2 == null|| ltt == null || ltt.isEmpty()){
//                    return;
//                }
//                for(int i = 0; i< tv.size();i++){
//                    if(Thu2.getNote() == tv.get(i).getNote()){
//                        //ltt.remove(ltt.get(i));
//                        tv.add(tv.get(i));
//                        ghichu.setText(String.valueOf(Thu2.getNote()));
//                        break;
//                    }
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
      dialog.show();
    }

    private void Add1(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_thu);
        dialog.setTitle("Thêm Khoản Thu");
        textView = dialog.findViewById(R.id.txtLt);
        btnSavem1 = dialog.findViewById(R.id.btnSavem1);
        btnHuy1 = dialog.findViewById(R.id.btnHuy1);
        etName = dialog.findViewById(R.id.etName1);
        etAmount = dialog.findViewById(R.id.etAmount1);
        etNote = dialog.findViewById(R.id.etNote1);
//        edDate = dialog.findViewById(R.id.date);
        txtDay = dialog.findViewById(R.id.dateDay);
        txtMonth = dialog.findViewById(R.id.dateMonth);
        txtYear = dialog.findViewById(R.id.dateYear);

        splKhoanThu  = dialog.findViewById(R.id.spType);
        drop = new DropDownKhoanThuA(getActivity(),R.layout.spinner_dropdown,getList());
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
                String lt = textView.getText().toString();
                String day = txtDay.getText().toString();
                String month = txtMonth.getText().toString();
                String year = txtYear.getText().toString();
                int money = Integer.parseInt(etAmount.getText().toString());
                Thu2 lt1 = new Thu2(name,note,lt,money,day,month,year);
                data.child(name).setValue(lt1);
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

    private ArrayList<KhoanThuView> getListkt()
    {
        ktv = new ArrayList<>();
        data3 = FirebaseDatabase.getInstance().getReference("Khoản Thu");
        data3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (ktv != null){
                    ktv.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    KhoanThuView thuView = dataSnapshot.getValue(KhoanThuView.class);
                    ktv.add(new KhoanThuView(thuView.getGhi()));
                }
                show.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return ktv;
    }

    private ArrayList<Category> getList()
    {
        list = new ArrayList<>();
        data2 = FirebaseDatabase.getInstance().getReference("Loại Thu");
        data2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(list != null){
                    list.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Category Thu = dataSnapshot.getValue(Category.class);
                    list.add(new Category(Thu.getName()));
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
        return inflater.inflate(R.layout.fragment_khoan_thu, container, false);
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