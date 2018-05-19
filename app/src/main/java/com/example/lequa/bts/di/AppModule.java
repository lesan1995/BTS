package com.example.lequa.bts.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import com.example.lequa.bts.api.ApiUtils;
import com.example.lequa.bts.api.HinhAnhTramService;
import com.example.lequa.bts.api.LoginService;
import com.example.lequa.bts.api.MatDienService;
import com.example.lequa.bts.api.NhaMangService;
import com.example.lequa.bts.api.NhaTramService;
import com.example.lequa.bts.api.NhatKyService;
import com.example.lequa.bts.api.PasswordService;
import com.example.lequa.bts.api.RegisterService;
import com.example.lequa.bts.api.TramService;
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
import com.example.lequa.bts.tinh.TinhUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton @Provides
    LoginService provideLoginService(){
        return ApiUtils.getLoginService();
    }
    @Singleton @Provides
    UserService provideUserService(){
        return ApiUtils.getUserService();
    }
    @Singleton @Provides
    TramService provideTramService(){
        return ApiUtils.getTramService();
    }
    @Singleton @Provides
    HinhAnhTramService provideHinhAnhTramService(){
        return ApiUtils.getHinhAnhTramService();
    }
    @Singleton @Provides
    NhaTramService provideNhaTramService(){
        return ApiUtils.getNhaTramService();
    }
    @Singleton @Provides
    NhaMangService provideNhaMangService(){
        return ApiUtils.getNhaMangService();
    }
    @Singleton @Provides
    RegisterService provideRegisterService(){
        return ApiUtils.getRegisterService();
    }
    @Singleton @Provides
    PasswordService providePasswordService(){
        return ApiUtils.getPasswordService();
    }
    @Singleton @Provides
    MatDienService provideMatDienService(){
        return ApiUtils.getMatDienService();
    }
    @Singleton @Provides
    NhatKyService provideNhatKyService(){
        return ApiUtils.getNhatKyService();
    }
    @Singleton @Provides
    MyDatabase provideDb(Application app) {
        return Room.databaseBuilder(app, MyDatabase.class,"bts.db").build();
    }
    @Singleton @Provides
    LoginDAO provideLoginDao(MyDatabase db) {
        return db.loginDAO();
    }
    @Singleton @Provides
    UserDAO provideUserDao(MyDatabase db) {
        return db.userDAO();
    }
    @Singleton @Provides
    TramDAO provideTramDao(MyDatabase db) {
        return db.tramDAO();
    }
    @Singleton @Provides
    HinhAnhTramDAO provideHinhAnhTramDao(MyDatabase db) {
        return db.hinhAnhTramDAO();
    }
    @Singleton @Provides
    NhaTramDAO provideNhaTramDao(MyDatabase db) {
        return db.nhaTramDAO();
    }
    @Singleton @Provides
    NhaMangDAO provideNhaMangDao(MyDatabase db) {
        return db.nhaMangDAO();
    }
    @Singleton @Provides
    MatDienDAO provideMatDienDao(MyDatabase db) {
        return db.matDienDAO();
    }
    @Singleton @Provides
    NhatKyDAO provideNhatKyDao(MyDatabase db) {
        return db.nhatKyDAO();
    }
    @Singleton @Provides
    TinhUtil provideTinhUtil(Application app){
        return new TinhUtil(app);
    }


}
