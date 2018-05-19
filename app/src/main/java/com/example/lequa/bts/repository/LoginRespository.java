package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.ApiUtils;
import com.example.lequa.bts.api.LoginService;
import com.example.lequa.bts.api.UserService;
import com.example.lequa.bts.db.HinhAnhTramDAO;
import com.example.lequa.bts.db.LoginDAO;
import com.example.lequa.bts.db.MatDienDAO;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.NhaMangDAO;
import com.example.lequa.bts.db.NhaTramDAO;
import com.example.lequa.bts.db.NhatKyDAO;
import com.example.lequa.bts.db.TramDAO;
import com.example.lequa.bts.db.UserDAO;
import com.example.lequa.bts.model.Login;
import com.example.lequa.bts.model.UserBTS;
import com.example.lequa.bts.model.UserLogin;
import com.example.lequa.bts.ui.login.LoginViewModel;
import com.example.lequa.bts.util.Objects;
import com.example.lequa.bts.vo.Resource;
import com.example.lequa.bts.vo.Status;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Singleton
public class LoginRespository {
    private final AppExecutors appExecutors;
    private final LoginService loginService;
    private final UserService userService;
    private final LoginDAO loginDAO;
    private final HinhAnhTramDAO hinhAnhTramDAO;
    private final NhaMangDAO nhaMangDAO;
    private final NhaTramDAO nhaTramDAO;
    private final TramDAO tramDAO;
    private final UserDAO userDAO;
    private final MatDienDAO matDienDAO;
    private final NhatKyDAO nhatKyDAO;
    private final MyDatabase myDatabase;

    @Inject
    LoginRespository(AppExecutors appExecutors,MyDatabase myDatabase, LoginDAO loginDAO,LoginService loginService,UserService userService,
                     HinhAnhTramDAO hinhAnhTramDAO,NhaMangDAO nhaMangDAO,NhaTramDAO nhaTramDAO,
                     TramDAO tramDAO,UserDAO userDAO,MatDienDAO matDienDAO,NhatKyDAO nhatKyDAO){
        this.myDatabase=myDatabase;
        this.loginDAO=loginDAO;
        this.hinhAnhTramDAO=hinhAnhTramDAO;
        this.nhaMangDAO=nhaMangDAO;
        this.nhaTramDAO=nhaTramDAO;
        this.tramDAO=tramDAO;
        this.userDAO=userDAO;
        this.matDienDAO=matDienDAO;
        this.nhatKyDAO=nhatKyDAO;
        this.loginService=loginService;
        this.userService=userService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<Login>> getToken(UserLogin userLogin){
        return new NetworkBoundResource<Login, Login>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Login item) {
                loginDAO.save(item);
            }
            @Override
            protected boolean shouldFetch(@Nullable Login data) {
                return data==null;
            }
            @NonNull
            @Override
            protected LiveData<Login> loadFromDb() {
                return loginDAO.load("bearer");
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Login>> createCall() {
                return loginService.postLogin("password",userLogin.getEmail(),userLogin.getPassword());
            }
        }.asLiveData();
    }
    public LiveData<Resource<Login>> deleteAll(){
        return new NetworkBoundResource<Login, Void>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Void item) {
                loginDAO.deleteTable();
                hinhAnhTramDAO.deleteTable();
                nhaMangDAO.deleteTable();
                nhaTramDAO.deleteTable();
                tramDAO.deleteTable();
                userDAO.deleteTable();
                matDienDAO.deleteTable();
                nhatKyDAO.deleteTable();
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
                 MutableLiveData<ApiResponse<Void>> r=new MutableLiveData<>();
                 r.postValue(new ApiResponse<Void>(Response.success(null)));
                 return r;
            }
        }.asLiveData();
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
}
