package com.example.lequa.bts.ui.baocao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.lequa.bts.model.NhatKy;
import com.example.lequa.bts.repository.BaoCaoRepository;
import com.example.lequa.bts.repository.DSNhatKyRepository;
import com.example.lequa.bts.util.AbsentLiveData;
import com.example.lequa.bts.vo.Resource;

import java.util.List;

import javax.inject.Inject;

public class BaoCaoViewModel extends ViewModel {
    final MutableLiveData<String> token = new MutableLiveData<>();
    final MutableLiveData<String> query = new MutableLiveData<>();
    private final LiveData<Resource<List<NhatKy>>> listNhatKy;
    @Inject
    public BaoCaoViewModel(BaoCaoRepository baoCaoRepository){
        listNhatKy= Transformations.switchMap(query, query->{
            if(query==null){
                return AbsentLiveData.create();

            }else{
                return baoCaoRepository.getListNhatKy(query,token.getValue());
            }
        });
    }
    public LiveData<Resource<List<NhatKy>>> getListNhatKy(){
        return this.listNhatKy;
    }
    public void setNhatKy(String token){
        this.token.setValue(token);
    }
    public void setQuery(String query){
        this.query.setValue(query);
    }
}

