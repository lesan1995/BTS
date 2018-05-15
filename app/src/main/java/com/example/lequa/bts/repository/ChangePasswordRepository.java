package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.PasswordService;
import com.example.lequa.bts.api.UserService;
import com.example.lequa.bts.db.LoginDAO;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.UserDAO;
import com.example.lequa.bts.model.Login;
import com.example.lequa.bts.model.Password;
import com.example.lequa.bts.model.UserBTS;
import com.example.lequa.bts.vo.Resource;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ChangePasswordRepository {
    private final AppExecutors appExecutors;
    private final LoginDAO loginDAO;
    private final PasswordService passwordService;
    private final MyDatabase myDatabase;
    @Inject
    ChangePasswordRepository(AppExecutors appExecutors,PasswordService passwordService,LoginDAO loginDAO,MyDatabase myDatabase){
        this.appExecutors=appExecutors;
        this.passwordService=passwordService;
        this.loginDAO=loginDAO;
        this.myDatabase=myDatabase;
    }
    public LiveData<Resource<Login>> changePassword(Password password, String token ){
        return new NetworkBoundResource<Login,Void>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Void item) {
            }

            @Override
            protected boolean shouldFetch(@Nullable Login data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Login> loadFromDb() {
                return loginDAO.load("bearer");
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Void>> createCall() {
                return passwordService.changePassword("bearer "+token,password);
            }
        }.asLiveData();
    }
}
