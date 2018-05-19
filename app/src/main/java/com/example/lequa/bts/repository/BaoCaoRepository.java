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
public class BaoCaoRepository {
    private final AppExecutors appExecutors;
    private final NhatKyService nhatKyService;
    private final NhatKyDAO nhatKyDAO;
    private final MyDatabase myDatabase;
    @Inject
    BaoCaoRepository(AppExecutors appExecutors, MyDatabase myDatabase, NhatKyDAO nhatKyDAO,
                       NhatKyService nhatKyService){
        this.myDatabase=myDatabase;
        this.nhatKyDAO=nhatKyDAO;
        this.nhatKyService=nhatKyService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<List<NhatKy>>> getListNhatKy(String query, String token){
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
                    case "HoanThanh":return nhatKyDAO.loadSCHT();
                    case "ChuaHoanThanh":return nhatKyDAO.loadSCCHT();
                    default:return nhatKyDAO.loadSC();
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<NhatKy>>> createCall() {
                return nhatKyService.getNhatKys("bearer "+token);
            }
        }.asLiveData();
    }
}

