package com.example.lequa.bts.ui.addmatdien;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.lequa.bts.R;
import com.example.lequa.bts.databinding.FragmentAddMatDienBinding;
import com.example.lequa.bts.di.Injectable;
import com.example.lequa.bts.util.AutoClearedValue;
import com.example.lequa.bts.vo.Status;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMatDienFragment extends Fragment implements Injectable {
    private static final String ADD_MAT_DIEN_TOKEN_KEY = "add_mat_dien_token";
    private static final String ADD_MAT_DIEN_ID_TRAM_KEY = "add_id_tram_token";
    AutoClearedValue<FragmentAddMatDienBinding> addMatDienBinding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private AddMatDienViewModel addMatDienViewModel;
    private String ngayMatDien="";
    private String ngayMayNo="";
    private String ngayNgung="";
    private int ngayCurrent,thangCurrent,namCurrent;
    private int ngayMD,thangMD,namMD;
    private int ngayMN,thangMN,namMN;
    private int ngayNG,thangNG,namNG;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addMatDienViewModel= ViewModelProviders.of(this,viewModelFactory).get(AddMatDienViewModel.class);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setBack(toolbar);
        addMatDienViewModel.getResultInsertMatDien().observe(this,result->{
            if(result.status== Status.SUCCESS){
                int i=0;
                if(result.status==Status.SUCCESS){
                    i++;
                    if(i==1){
                        Toast.makeText(getActivity().getApplicationContext(),"Lưu Thành Công",Toast.LENGTH_LONG).show();
                        thoat();
                    }
                }
                else if(result.status==Status.ERROR){
                    i++;
                    if(i==1)
                        Toast.makeText(getActivity().getApplicationContext(),"Báo cáo lỗi",Toast.LENGTH_LONG).show();
                }
            }
        });
        getTimeCurrent();
    }
    public void setBack(Toolbar toolbar){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thoat();
            }
        });
    }
    @OnClick(R.id.btnThoat)
    public void thoat(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            getActivity().onBackPressed();
        }
    }
    @OnClick(R.id.btnLuu)
    public void luu(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentAddMatDienBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_add_mat_dien, container, false);
        addMatDienBinding = new AutoClearedValue<>(this, dataBinding);
        ButterKnife.bind(this,dataBinding.getRoot());
        return dataBinding.getRoot();
    }
    public static AddMatDienFragment create(String idTram,String token) {
        AddMatDienFragment addMatDienFragment = new AddMatDienFragment();
        Bundle args = new Bundle();
        args.putString(ADD_MAT_DIEN_TOKEN_KEY, token);
        args.putString(ADD_MAT_DIEN_ID_TRAM_KEY, idTram);
        addMatDienFragment.setArguments(args);
        return addMatDienFragment;
    }
    @OnClick(R.id.btnNgayMatDien)
    public void ChonNgayMatDien(){
        getDate(addMatDienBinding.get().tvNgayMatDien.getText().toString(),1);
        final DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            }
        };

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), mDateSetListener, namMD, thangMD, ngayMD);

        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Chọn",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            DatePicker datePicker = datePickerDialog.getDatePicker();
                            addMatDienBinding.get().tvNgayMatDien.
                                    setText(datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear());
                            ngayMatDien=datePicker.getYear() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth();
                        }
                    }
                });
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        datePickerDialog.show();
    }
    @OnClick(R.id.btnNgayMayNo)
    public void ChonNgayMayNo(){
        getDate(addMatDienBinding.get().tvNgayMayNo.getText().toString(),2);
        final DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            }
        };

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), mDateSetListener, namMN, thangMN, ngayMN);

        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Chọn",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            DatePicker datePicker = datePickerDialog.getDatePicker();
                            addMatDienBinding.get().tvNgayMayNo.
                                    setText(datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear());
                            ngayMayNo=datePicker.getYear() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth();
                        }
                    }
                });
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        datePickerDialog.show();
    }
    @OnClick(R.id.btnNgayNgung)
    public void ChonNgayNgung(){
        getDate(addMatDienBinding.get().tvNgayNgung.getText().toString(),3);
        final DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            }
        };

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), mDateSetListener, namNG, thangNG, ngayNG);

        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Chọn",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            DatePicker datePicker = datePickerDialog.getDatePicker();
                            addMatDienBinding.get().tvNgayNgung.
                                    setText(datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear());
                            ngayNgung=datePicker.getYear() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth();
                        }
                    }
                });
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        datePickerDialog.show();
    }
    public void getTimeCurrent(){
        Date c = Calendar.getInstance().getTime();
        Calendar cc=Calendar.getInstance();
        cc.setTime(c);
        ngayCurrent=cc.get(Calendar.DAY_OF_MONTH);
        thangCurrent=cc.get(Calendar.MONTH)+1;
        namCurrent=cc.get(Calendar.YEAR);
        ngayMatDien=namCurrent+"/"+thangCurrent+"/"+ngayCurrent;
        ngayMayNo=namCurrent+"/"+thangCurrent+"/"+ngayCurrent;
        ngayNgung=namCurrent+"/"+thangCurrent+"/"+ngayCurrent;
        addMatDienBinding.get().tvNgayMatDien.setText(ngayCurrent+"/"+thangCurrent+"/"+namCurrent);
        addMatDienBinding.get().tvNgayMayNo.setText(ngayCurrent+"/"+thangCurrent+"/"+namCurrent);
        addMatDienBinding.get().tvNgayNgung.setText(ngayCurrent+"/"+thangCurrent+"/"+namCurrent);
    }
    public void getDate(String dateStr,int i){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        try {
            date = format.parse(dateStr);
            c.setTime(date);
            switch (i){
                case 1:
                    ngayMD=c.get(Calendar.DAY_OF_MONTH);
                    thangMD=(c.get(Calendar.MONTH));
                    namMD=c.get(Calendar.YEAR);
                    break;
                case 2:
                    ngayMN=c.get(Calendar.DAY_OF_MONTH);
                    thangMN=(c.get(Calendar.MONTH));
                    namMN=c.get(Calendar.YEAR);
                    break;
                case 3:
                    ngayNG=c.get(Calendar.DAY_OF_MONTH);
                    thangNG=(c.get(Calendar.MONTH));
                    namNG=c.get(Calendar.YEAR);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
