package com.example.lequa.bts.db;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.lequa.bts.model.Login;

import java.util.List;

@Dao
public interface LoginDAO {
    @Insert(onConflict = REPLACE)
    void save(Login login);
    @Query("SELECT * FROM Login where tokenType = :tokenType")
    LiveData<Login> load(String tokenType);
    @Query("DELETE FROM Login where tokenType = 'bearer' ")
    void deleteTable();
}
