package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.RegisterService;
import com.example.lequa.bts.api.UserService;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.UserDAO;
import com.example.lequa.bts.model.Register;
import com.example.lequa.bts.model.Tram;
import com.example.lequa.bts.model.UserBTS;
import com.example.lequa.bts.vo.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddTaiKhoanRepository {
    private final AppExecutors appExecutors;
    private final UserService userService;
    private final RegisterService registerService;
    private final UserDAO userDAO;
    private final MyDatabase myDatabase;
    @Inject
    AddTaiKhoanRepository(AppExecutors appExecutors, MyDatabase myDatabase, UserDAO userDAO, UserService userService,
                          RegisterService registerService){
        this.myDatabase=myDatabase;
        this.userDAO=userDAO;
        this.userService=userService;
        this.registerService=registerService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<UserBTS>> registerTaiKhoan(Register taiKhoan, String token){
        return new NetworkBoundResource<UserBTS,Void>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Void item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable UserBTS data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<UserBTS> loadFromDb() {
                return userDAO.loadWithEmail(taiKhoan.getEmail());
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Void>> createCall() {
                return registerService.registerTaiKhoan("bearer "+token,taiKhoan);
            }
        }.asLiveData();
    }
}
