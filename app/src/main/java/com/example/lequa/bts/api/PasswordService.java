package com.example.lequa.bts.api;

import android.arch.lifecycle.LiveData;
import com.example.lequa.bts.model.Password;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PasswordService {
    @POST("api/Account/ChangePassword")
    LiveData<ApiResponse<Void>> changePassword(@Header("Authorization") String token, @Body Password password);
}
