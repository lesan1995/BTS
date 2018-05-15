package com.example.lequa.bts.ui.changepassword;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.lequa.bts.model.Login;
import com.example.lequa.bts.model.Password;
import com.example.lequa.bts.model.UserBTS;
import com.example.lequa.bts.repository.ChangePasswordRepository;
import com.example.lequa.bts.util.AbsentLiveData;
import com.example.lequa.bts.vo.Resource;

import javax.inject.Inject;

public class ChangePasswordViewModel extends ViewModel {
    final MutableLiveData<String> token = new MutableLiveData<>();
    final MutableLiveData<Password> password = new MutableLiveData<>();
    private final LiveData<Resource<Login>> result;
    @Inject
    public ChangePasswordViewModel(ChangePasswordRepository changePasswordRepository){
        result= Transformations.switchMap(password, password->{
            if(password==null){
                return AbsentLiveData.create();

            }else{
                return changePasswordRepository.changePassword(password,token.getValue());
            }
        });
    }
    public LiveData<Resource<Login>> getResult(){
        return this.result;
    }
    public void setPassword(String token,Password password){
        this.token.setValue(token);
        this.password.setValue(password);
    }
}
