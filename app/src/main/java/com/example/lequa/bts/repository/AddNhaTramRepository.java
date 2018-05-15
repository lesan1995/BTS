package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.NhaMangService;
import com.example.lequa.bts.api.NhaTramService;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.NhaMangDAO;
import com.example.lequa.bts.db.NhaTramDAO;
import com.example.lequa.bts.model.NhaMang;
import com.example.lequa.bts.model.NhaTram;
import com.example.lequa.bts.vo.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddNhaTramRepository {
    private final AppExecutors appExecutors;
    private final NhaTramService nhaTramService;
    private final NhaMangService nhaMangService;
    private final NhaTramDAO nhaTramDAO;
    private final NhaMangDAO nhaMangDAO;
    private final MyDatabase myDatabase;
    @Inject
    AddNhaTramRepository(AppExecutors appExecutors, MyDatabase myDatabase,NhaTramDAO nhaTramDAO, NhaMangDAO nhaMangDAO,
                    NhaTramService nhaTramService, NhaMangService nhaMangService){
        this.myDatabase=myDatabase;
        this.nhaTramDAO=nhaTramDAO;
        this.nhaMangDAO=nhaMangDAO;
        this.nhaTramService=nhaTramService;
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
    public LiveData<Resource<NhaTram>> addNhaTram(NhaTram nhaTram, String token){
        return new NetworkBoundResource<NhaTram,NhaTram>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull NhaTram item) {
                nhaTramDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable NhaTram data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<NhaTram> loadFromDb() {
                return nhaTramDAO.load(nhaTram.getIDNhaTram());
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<NhaTram>> createCall() {
                return nhaTramService.postNhaTram("bearer "+token,nhaTram);
            }
        }.asLiveData();
    }
}
