package com.example.lequa.bts.ui.dsmang;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.lequa.bts.model.NhaMang;
import com.example.lequa.bts.repository.DSMangRepository;
import com.example.lequa.bts.util.AbsentLiveData;
import com.example.lequa.bts.vo.Resource;

import java.util.List;

import javax.inject.Inject;

public class DSMangViewModel extends ViewModel {
    final MutableLiveData<String> token = new MutableLiveData<>();
    private final LiveData<Resource<List<NhaMang>>> listNhaMang;
    @Inject
    DSMangViewModel(DSMangRepository dsMangRepository){
        listNhaMang= Transformations.switchMap(token, token->{
            if(token==null){
                return AbsentLiveData.create();

            }else{
                return dsMangRepository.getListNhaMang(token);
            }
        });
    }
    public LiveData<Resource<List<NhaMang>>> getListNhaMang(){
        return this.listNhaMang;
    }
    public void setToken(String token){
        this.token.setValue(token);
    }
}
