package com.example.lequa.bts.api;

import android.arch.lifecycle.LiveData;
import com.example.lequa.bts.model.NhaMang;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NhaMangService {
    @GET("api/NhaMangs")
    LiveData<ApiResponse<List<NhaMang>>> getNhaMangs(@Header("Authorization") String token);
    @GET("api/NhaMangs/{id}")
    LiveData<ApiResponse<NhaMang>> getNhaMangs(@Header("Authorization") String token, @Query("id") String id);

}
