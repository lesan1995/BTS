package com.example.lequa.bts.ui.login;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.lequa.bts.model.Login;
import com.example.lequa.bts.model.UserBTS;
import com.example.lequa.bts.model.UserLogin;
import com.example.lequa.bts.repository.LoginRespository;
import com.example.lequa.bts.util.AbsentLiveData;
import com.example.lequa.bts.vo.Resource;
import com.example.lequa.bts.vo.Status;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    final MutableLiveData<UserLogin> userLogin = new MutableLiveData<>();
    final MutableLiveData<Boolean> deleteAllData = new MutableLiveData<>();
    final MutableLiveData<String> token = new MutableLiveData<>();
    final MutableLiveData<String> email = new MutableLiveData<>();
    private final LiveData<Resource<Login>> login;
    private final LiveData<Resource<Login>> resultDeleteAllData;
    private final LiveData<Resource<UserBTS>> userBTS;

    @Inject
    public LoginViewModel(LoginRespository loginRespository){
        login= Transformations.switchMap(userLogin,userLogin->{
            if(userLogin==null){
                return AbsentLiveData.create();

            }else{
                return loginRespository.getToken(userLogin);
            }
        });
        resultDeleteAllData= Transformations.switchMap(deleteAllData,deleteAllData->{
            if(deleteAllData==null){
                return AbsentLiveData.create();

            }else{
                return loginRespository.deleteAll();
            }
        });
        userBTS= Transformations.switchMap(token,token->{
            if(token==null){
                return AbsentLiveData.create();

            }else{
                return loginRespository.getUser(email.getValue(),token);
            }
        });
    }
    public LiveData<Resource<Login>> getLogin(){
        return this.login;
    }
    public LiveData<Resource<Login>> getResultDeleteAllData(){
        return this.resultDeleteAllData;
    }
    public LiveData<Resource<UserBTS>> getUser(){
        return this.userBTS;
    }
    public void setLogin(UserLogin userLogin){
        this.userLogin.setValue(userLogin);
    }
    public void setDeleteAllData(Boolean deleteAllData){
        this.deleteAllData.setValue(deleteAllData);
    }
    public void setUser(String email,String token){
        this.email.setValue(email);
        this.token.setValue(token);
    }
}
