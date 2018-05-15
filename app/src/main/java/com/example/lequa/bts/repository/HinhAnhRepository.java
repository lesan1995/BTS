package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.HinhAnhTramService;
import com.example.lequa.bts.db.HinhAnhTramDAO;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.model.HinhAnhTram;
import com.example.lequa.bts.vo.Resource;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Singleton
public class HinhAnhRepository {
    private final AppExecutors appExecutors;
    private final HinhAnhTramDAO hinhAnhTramDAO;
    private final HinhAnhTramService hinhAnhTramService;
    private final MyDatabase myDatabase;
    @Inject
    HinhAnhRepository(AppExecutors appExecutors, MyDatabase myDatabase,
                      HinhAnhTramDAO hinhAnhTramDAO,HinhAnhTramService hinhAnhTramService){
        this.appExecutors=appExecutors;
        this.hinhAnhTramDAO=hinhAnhTramDAO;
        this.hinhAnhTramService=hinhAnhTramService;
        this.myDatabase=myDatabase;
    }
    public LiveData<Resource<List<HinhAnhTram>>> getHinhAnhTramByIDTram(String token, int idTram){
        return new NetworkBoundResource<List<HinhAnhTram>,List<HinhAnhTram>>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull List<HinhAnhTram> item) {
                hinhAnhTramDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<HinhAnhTram> data) {
                //return data==null||data.size()==0;
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<HinhAnhTram>> loadFromDb() {
                return hinhAnhTramDAO.loadByIDTram(idTram);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<HinhAnhTram>>> createCall() {
                return hinhAnhTramService.getHinhAnhTramByIDTram("bearer "+token,idTram+"");
            }
        }.asLiveData();
    }
    public LiveData<Resource<HinhAnhTram>> addHinhAnh(int idTram,MultipartBody.Part file, String token){
        return new NetworkBoundResource<HinhAnhTram,HinhAnhTram>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull HinhAnhTram item) {
                hinhAnhTramDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable HinhAnhTram data) {
                return data==null;
            }

            @NonNull
            @Override
            protected LiveData<HinhAnhTram> loadFromDb() {
                return hinhAnhTramDAO.load(0);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<HinhAnhTram>> createCall() {
                return hinhAnhTramService.postHinhAnh("bearer "+token,idTram+"",file);
            }
        }.asLiveData();

    }
    public LiveData<Resource<HinhAnhTram>> deleteHinhAnh(HinhAnhTram hinhAnhTram, String token){
        return new NetworkBoundResource<HinhAnhTram,HinhAnhTram>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull HinhAnhTram item) {
                hinhAnhTramDAO.delete(item.getIDHinhAnh());
            }

            @Override
            protected boolean shouldFetch(@Nullable HinhAnhTram data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<HinhAnhTram> loadFromDb() {
                return hinhAnhTramDAO.load(hinhAnhTram.getIDHinhAnh());
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<HinhAnhTram>> createCall() {
                return hinhAnhTramService.deleteHinhAnhTram("bearer "+token,hinhAnhTram.getIDHinhAnh()+"");
            }
        }.asLiveData();
    }
}