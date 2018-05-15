package com.example.lequa.bts.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.lequa.bts.AppExecutors;
import com.example.lequa.bts.api.ApiResponse;
import com.example.lequa.bts.api.TramService;
import com.example.lequa.bts.api.UserService;
import com.example.lequa.bts.db.MyDatabase;
import com.example.lequa.bts.db.TramDAO;
import com.example.lequa.bts.db.UserDAO;
import com.example.lequa.bts.model.Tram;
import com.example.lequa.bts.model.UserBTS;
import com.example.lequa.bts.vo.Resource;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddTramRepository {
    private final AppExecutors appExecutors;
    private final UserService userService;
    private final TramService tramService;
    private final UserDAO userDAO;
    private final TramDAO tramDAO;
    private final MyDatabase myDatabase;
    @Inject
    AddTramRepository(AppExecutors appExecutors, MyDatabase myDatabase, UserDAO userDAO,TramDAO tramDAO,
                    UserService userService,TramService tramService){
        this.myDatabase=myDatabase;
        this.userDAO=userDAO;
        this.tramDAO=tramDAO;
        this.userService=userService;
        this.tramService=tramService;
        this.appExecutors=appExecutors;
    }
    public LiveData<Resource<List<UserBTS>>> getAllUser(String token){
        return new NetworkBoundResource<List<UserBTS>,List<UserBTS>>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull List<UserBTS> item) {
                userDAO.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<UserBTS> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<UserBTS>> loadFromDb() {
                return userDAO.loadAllQuanLy();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<UserBTS>>> createCall() {
                return userService.getAllUser("bearer "+token);
            }
        }.asLiveData();
    }
    public LiveData<Resource<Tram>> addTram(Tram tram, String token){
        return new NetworkBoundResource<Tram,Tram>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Tram item) {
                tramDAO.save(item);
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
            protected LiveData<ApiResponse<Tram>> createCall() {
                return tramService.postTram("bearer "+token,tram);
            }
        }.asLiveData();
    }
}
