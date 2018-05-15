package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.MatDienService;
import com.example.lequa.bts.db.MatDienDAO;
import com.example.lequa.bts.model.MatDien;
import com.example.lequa.bts.vo.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddMatDienRepository {
    private final AppExecutors appExecutors;
    private final MatDienService matDienService;
    private final MatDienDAO matDienDAO;
    @Inject
    AddMatDienRepository(AppExecutors appExecutors,MatDienService matDienService,MatDienDAO matDienDAO){
        this.appExecutors=appExecutors;
        this.matDienDAO=matDienDAO;
        this.matDienService=matDienService;
    }
    public LiveData<Resource<MatDien>> addMatDien(MatDien matDien, String token){
        return new NetworkBoundResource<MatDien,MatDien>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull MatDien item) {
                matDienDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable MatDien data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<MatDien> loadFromDb() {
                return matDienDAO.load(matDien.getIDMatDien());
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MatDien>> createCall() {
                return matDienService.postMatDiens("bearer "+token,matDien);
            }
        }.asLiveData();
    }
}
