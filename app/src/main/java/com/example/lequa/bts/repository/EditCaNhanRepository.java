package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.UserService;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.UserDAO;
import com.example.lequa.bts.model.UserBTS;
import com.example.lequa.bts.vo.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EditCaNhanRepository {
    private final AppExecutors appExecutors;
    private final UserService userService;
    private final UserDAO userDAO;
    private final MyDatabase myDatabase;
    @Inject
    EditCaNhanRepository(AppExecutors appExecutors, MyDatabase myDatabase, UserDAO userDAO,UserService userService){
        this.myDatabase=myDatabase;
        this.userDAO=userDAO;
        this.userService=userService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<UserBTS>> getUser(String email, String token){
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
                return userDAO.loadWithEmail(email);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserBTS>> createCall() {
                return userService.getUser("bearer "+token);
            }
        }.asLiveData();
    }
    public LiveData<Resource<UserBTS>> updateUser(UserBTS userBTS,String token){
        return new NetworkBoundResource<UserBTS, Void>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Void item) {
                userDAO.save(userBTS);
            }

            @Override
            protected boolean shouldFetch(@Nullable UserBTS data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<UserBTS> loadFromDb() {
                return userDAO.load(userBTS.getIDUser());
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Void>> createCall() {
                return userService.putUser("bearer "+token,userBTS);
            }
        }.asLiveData();
    }
}
