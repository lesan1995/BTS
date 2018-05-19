package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.NhatKyService;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.NhatKyDAO;
import com.example.lequa.bts.model.NhatKy;
import com.example.lequa.bts.vo.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DSNhatKyRepository {
    private final AppExecutors appExecutors;
    private final NhatKyService nhatKyService;
    private final NhatKyDAO nhatKyDAO;
    private final MyDatabase myDatabase;
    @Inject
    DSNhatKyRepository(AppExecutors appExecutors, MyDatabase myDatabase, NhatKyDAO nhatKyDAO,
                       NhatKyService nhatKyService){
        this.myDatabase=myDatabase;
        this.nhatKyDAO=nhatKyDAO;
        this.nhatKyService=nhatKyService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<List<NhatKy>>> getListNhatKy(String query,int idTram, String token){
        return new NetworkBoundResource<List<NhatKy>, List<NhatKy>>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull List<NhatKy> item) {
                nhatKyDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<NhatKy> data) {
                return data==null||data.size()==0;
            }

            @NonNull
            @Override
            protected LiveData<List<NhatKy>> loadFromDb() {
                switch (query){
                    case "NhatKy":return nhatKyDAO.loadNKWithIDTram(idTram);
                    case "SuCo":return nhatKyDAO.loadSCWithIDTram(idTram);
                    default:return nhatKyDAO.loadWithIDTram(idTram);
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<NhatKy>>> createCall() {
                return nhatKyService.getNhatKyByIDTram("bearer "+token,idTram+"");
            }
        }.asLiveData();
    }
    public LiveData<Resource<NhatKy>> putNhatKy(NhatKy nhatKy, String token){
        return new NetworkBoundResource<NhatKy, Void>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Void item) {
                nhatKyDAO.save(nhatKy);
            }

            @Override
            protected boolean shouldFetch(@Nullable NhatKy data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<NhatKy> loadFromDb() {
                return nhatKyDAO.load(nhatKy.getIDNhatKy());
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Void>> createCall() {
                return nhatKyService.changeTrangThai("bearer "+token,nhatKy.getIDNhatKy()+"",nhatKy.getDaGiaiQuyet());
            }
        }.asLiveData();
    }
}
