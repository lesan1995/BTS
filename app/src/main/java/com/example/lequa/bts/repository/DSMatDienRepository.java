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
import com.example.lequa.bts.model.Tram;
import com.example.lequa.bts.vo.Resource;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DSMatDienRepository {
    private final AppExecutors appExecutors;
    private final MatDienService matDienService;
    private final MatDienDAO matDienDAO;
    private final MyDatabase myDatabase;
    @Inject
    DSMatDienRepository(AppExecutors appExecutors, MyDatabase myDatabase, TramDAO tramDAO, MatDienDAO matDienDAO,
                        TramService tramService,MatDienService matDienService){
        this.myDatabase=myDatabase;
        this.matDienDAO=matDienDAO;
        this.matDienService=matDienService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<List<MatDien>>> getListMatDien(int idTram,String token){
        return new NetworkBoundResource<List<MatDien>, List<MatDien>>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull List<MatDien> item) {
                matDienDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MatDien> data) {
                return data==null||data.size()==0;
            }

            @NonNull
            @Override
            protected LiveData<List<MatDien>> loadFromDb() {
                return matDienDAO.loadWithIDTram(idTram);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<MatDien>>> createCall() {
                return matDienService.getMatDienByIDTram("bearer "+token,idTram+"");
            }
        }.asLiveData();
    }
}
