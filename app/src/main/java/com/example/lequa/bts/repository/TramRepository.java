package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.NhaMangService;
import com.example.lequa.bts.api.NhaTramService;
import com.example.lequa.bts.api.TramService;
import com.example.lequa.bts.api.UserService;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.NhaMangDAO;
import com.example.lequa.bts.db.NhaTramDAO;
import com.example.lequa.bts.db.TramDAO;
import com.example.lequa.bts.db.UserDAO;
import com.example.lequa.bts.model.NhaMang;
import com.example.lequa.bts.model.NhaTram;
import com.example.lequa.bts.model.Tram;
import com.example.lequa.bts.model.UserBTS;
import com.example.lequa.bts.tinh.Tinh;
import com.example.lequa.bts.tinh.TinhUtil;
import com.example.lequa.bts.vo.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TramRepository {
    private final AppExecutors appExecutors;
    private final UserService userService;
    private final TramService tramService;
    private final NhaTramService nhaTramService;
    private final NhaMangService nhaMangService;
    private final UserDAO userDAO;
    private final TramDAO tramDAO;
    private final NhaTramDAO nhaTramDAO;
    private final NhaMangDAO nhaMangDAO;
    private final MyDatabase myDatabase;
    @Inject
    TramRepository(AppExecutors appExecutors, MyDatabase myDatabase, UserDAO userDAO, TramDAO tramDAO,
                   NhaTramDAO nhaTramDAO, NhaMangDAO nhaMangDAO,
                   UserService userService, TramService tramService, NhaTramService nhaTramService,
                   NhaMangService nhaMangService){
        this.myDatabase=myDatabase;
        this.userDAO=userDAO;
        this.tramDAO=tramDAO;
        this.nhaTramDAO=nhaTramDAO;
        this.nhaMangDAO=nhaMangDAO;
        this.userService=userService;
        this.tramService=tramService;
        this.nhaTramService=nhaTramService;
        this.nhaMangService=nhaMangService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<Tram>> getTram(int idTram, String token){
        return new NetworkBoundResource<Tram, Tram>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Tram item) {
                tramDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Tram data) {
                return data==null;
            }

            @NonNull
            @Override
            protected LiveData<Tram> loadFromDb() {
                return tramDAO.load(idTram);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Tram>> createCall() {
                return tramService.getTrams("bearer "+token,idTram+"");
            }
        }.asLiveData();
    }
    public LiveData<Resource<UserBTS>> getUser(String idUser, String token){
        return new NetworkBoundResource<UserBTS, UserBTS>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull UserBTS item) {
                userDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable UserBTS data) {
                return data==null;
            }

            @NonNull
            @Override
            protected LiveData<UserBTS> loadFromDb() {
                return userDAO.load(idUser);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserBTS>> createCall() {
                return userService.getUser("bearer "+token,idUser);
            }
        }.asLiveData();
    }
    public LiveData<Resource<List<NhaTram>>> getListNhaTram(int idTram, String token){
        return new NetworkBoundResource<List<NhaTram>, List<NhaTram>>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull List<NhaTram> item) {
                nhaTramDAO.save(item);
            }
            @Override
            protected boolean shouldFetch(@Nullable List<NhaTram> data) {
                return data==null||data.size()==0;
            }
            @NonNull
            @Override
            protected LiveData<List<NhaTram>> loadFromDb() {
                return nhaTramDAO.loadWithIDTram(idTram);
            }
            @NonNull
            @Override
            protected LiveData<ApiResponse<List<NhaTram>>> createCall() {
                return nhaTramService.getNhaTramByIDTram("bearer "+token,idTram+"");

            }
        }.asLiveData();
    }
    public LiveData<Resource<NhaTram>> getNhaTram(int idNhaTram, String token){
        return new NetworkBoundResource<NhaTram, NhaTram>(appExecutors){
            @Override
            protected void saveCallResult(@NonNull NhaTram item) {
                nhaTramDAO.save(item);
            }
            @Override
            protected boolean shouldFetch(@Nullable NhaTram data) {
                return data==null;
            }
            @NonNull
            @Override
            protected LiveData<NhaTram> loadFromDb() {
                return nhaTramDAO.load(idNhaTram);
            }
            @NonNull
            @Override
            protected LiveData<ApiResponse<NhaTram>> createCall() {
                return nhaTramService.getNhaTrams("bearer "+token,idNhaTram+"");
            }
        }.asLiveData();
    }
    
    public LiveData<Resource<NhaMang>> getNhaMang(int idNhaMang, String token){
        return new NetworkBoundResource<NhaMang, NhaMang>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull NhaMang item) {
                nhaMangDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable NhaMang data) {
                return data==null;
            }

            @NonNull
            @Override
            protected LiveData<NhaMang> loadFromDb() {
                return nhaMangDAO.load(idNhaMang);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<NhaMang>> createCall() {
                return nhaMangService.getNhaMangs("bearer "+token,idNhaMang+"");
            }
        }.asLiveData();
    }
    public LiveData<Resource<Tram>> updateTram(Tram tram, String token){
        return new NetworkBoundResource<Tram,Integer>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Integer item) {
                tramDAO.save(tram);
            }

            @Override
            protected boolean shouldFetch(@Nullable Tram data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Tram> loadFromDb() {
                return tramDAO.load(tram.getIDTram());
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Integer>> createCall() {
                return tramService.putTram("bearer "+token,tram.getIDTram()+"",tram);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<UserBTS>>> getAllUser(String token){
        return new NetworkBoundResource<List<UserBTS>,List<UserBTS>>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull List<UserBTS> item) {
                Log.d("TramRepository","saveCallResult");
                userDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<UserBTS> data) {
                Log.d("TramRepository","shouldFetch");
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<UserBTS>> loadFromDb() {
                Log.d("TramRepository","loadFromDb");
                return userDAO.loadAllQuanLy();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<UserBTS>>> createCall() {
                Log.d("TramRepository","createCall");
                return userService.getAllUser("bearer "+token);
            }
        }.asLiveData();
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
    public LiveData<Resource<NhaTram>> deleteNhaTram(NhaTram nhaTram, String token){
        return new NetworkBoundResource<NhaTram,NhaTram>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull NhaTram item) {
                nhaTramDAO.delete(nhaTram.getIDNhaTram());
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
                return nhaTramService.deleteNhaTram("bearer "+token,nhaTram.getIDNhaTram()+"");
            }
        }.asLiveData();
    }


}
