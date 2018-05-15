package com.example.lequa.bts.ui.addmatdien;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.lequa.bts.model.MatDien;
import com.example.lequa.bts.repository.AddMatDienRepository;
import com.example.lequa.bts.util.AbsentLiveData;
import com.example.lequa.bts.vo.Resource;

import javax.inject.Inject;

public class AddMatDienViewModel extends ViewModel {
    final MutableLiveData<String> token = new MutableLiveData<>();
    final MutableLiveData<MatDien> matDien= new MutableLiveData<>();
    private final LiveData<Resource<MatDien>> resultInsertMatDien;
    @Inject
    public AddMatDienViewModel(AddMatDienRepository addMatDienRepository){
        resultInsertMatDien= Transformations.switchMap(matDien, matDien->{
            if(matDien==null){
                return AbsentLiveData.create();

            }else{
                return addMatDienRepository.addMatDien(matDien,token.getValue());
            }
        });
    }
    public LiveData<Resource<MatDien>> getResultInsertMatDien(){
        return this.resultInsertMatDien;
    }
    public void setInsertMatDien(MatDien matDien){
        this.matDien.setValue(matDien);
    }
}
