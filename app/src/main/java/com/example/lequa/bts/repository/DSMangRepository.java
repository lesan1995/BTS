package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.NhaMangService;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.NhaMangDAO;
import com.example.lequa.bts.model.NhaMang;
import com.example.lequa.bts.vo.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DSMangRepository {
    private final AppExecutors appExecutors;
    private final NhaMangService nhaMangService;
    private final NhaMangDAO nhaMangDAO;
    private final MyDatabase myDatabase;
    @Inject
    DSMangRepository(AppExecutors appExecutors, MyDatabase myDatabase, NhaMangDAO nhaMangDAO, NhaMangService nhaMangService){
        this.myDatabase=myDatabase;
        this.nhaMangDAO=nhaMangDAO;
        this.nhaMangService=nhaMangService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<List<NhaMang>>> getListNhaMang(String token){
        return new NetworkBoundResource<List<NhaMang>,List<NhaMang>>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull List<NhaMang> item) {
                nhaMangDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<NhaMang> data) {
                return data==null||data.size()==0;
            }

            @NonNull
            @Override
            protected LiveData<List<NhaMang>> loadFromDb() {
                return nhaMangDAO.load();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<NhaMang>>> createCall() {
                return nhaMangService.getNhaMangs("bearer "+token);
            }
        }.asLiveData();
    }
}
