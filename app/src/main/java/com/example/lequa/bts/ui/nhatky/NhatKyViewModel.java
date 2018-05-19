package com.example.lequa.bts.ui.nhatky;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import com.example.lequa.bts.model.NhatKy;
import com.example.lequa.bts.model.Tram;
import com.example.lequa.bts.model.UserBTS;
import com.example.lequa.bts.repository.NhatKyRepository;
import com.example.lequa.bts.util.AbsentLiveData;
import com.example.lequa.bts.vo.Resource;
import javax.inject.Inject;

public class NhatKyViewModel extends ViewModel {
    final MutableLiveData<String> token = new MutableLiveData<>();
    final MutableLiveData<Integer> idNhatKy = new MutableLiveData<>();
    final MutableLiveData<NhatKy> nhatKy = new MutableLiveData<>();
    private final LiveData<Resource<NhatKy>> nhatKyResult;
    private final LiveData<Resource<UserBTS>> userResult;
    private final LiveData<Resource<Tram>> tramResult;
    @Inject
    public NhatKyViewModel(NhatKyRepository nhatKyRepository){
        nhatKyResult= Transformations.switchMap(idNhatKy, idNhatKy->{
            if(idNhatKy==null){
                return AbsentLiveData.create();

            }else{
                return nhatKyRepository.getNhatKy(idNhatKy,token.getValue());
            }
        });
        userResult= Transformations.switchMap(nhatKy, nhatKy->{
            if(nhatKy==null){
                return AbsentLiveData.create();

            }else{
                return nhatKyRepository.getUser(nhatKy.getIDQuanLy(),token.getValue());
            }
        });
        tramResult= Transformations.switchMap(nhatKy, nhatKy->{
            if(nhatKy==null){
                return AbsentLiveData.create();

            }else{
                return nhatKyRepository.getTram(nhatKy.getIDTram(),token.getValue());
            }
        });
    }
    public LiveData<Resource<NhatKy>> getNhatKyResult(){
        return this.nhatKyResult;
    }
    public LiveData<Resource<UserBTS>> getUserResult(){
        return this.userResult;
    }
    public LiveData<Resource<Tram>> getTramResult(){
        return this.tramResult;
    }
    public void setIDNhatKy(int idNhatKy,String token){
        this.token.setValue(token);
        this.idNhatKy.setValue(idNhatKy);
    }
    public void setNhatKy(NhatKy nhatKy){
        this.nhatKy.setValue(nhatKy);
    }
}
