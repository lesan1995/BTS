package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.MatDienService;
import com.example.lequa.bts.api.TramService;
import com.example.lequa.bts.db.MatDienDAO;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.TramDAO;
import com.example.lequa.bts.model.MatDien;
import com.example.lequa.bts.vo.Resource;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MatDienRepository {
    private final AppExecutors appExecutors;
    private final MatDienService matDienService;
    private final MatDienDAO matDienDAO;
    private final MyDatabase myDatabase;
    @Inject
    MatDienRepository(AppExecutors appExecutors, MyDatabase myDatabase, TramDAO tramDAO, MatDienDAO matDienDAO,
                        TramService tramService, MatDienService matDienService){
        this.myDatabase=myDatabase;
        this.matDienDAO=matDienDAO;
        this.matDienService=matDienService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<MatDien>> getMatDien(int idMatDien, String token){
        return new NetworkBoundResource<MatDien, MatDien>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull MatDien item) {
                matDienDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable MatDien data) {
                return data==null;
            }

            @NonNull
            @Override
            protected LiveData<MatDien> loadFromDb() {
                return matDienDAO.load(idMatDien);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MatDien>> createCall() {
                return matDienService.getMatDiens("bearer "+token,idMatDien+"");
            }
        }.asLiveData();
    }
}
