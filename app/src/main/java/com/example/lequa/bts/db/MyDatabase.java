package com.example.lequa.bts.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.lequa.bts.model.HinhAnhTram;
import com.example.lequa.bts.model.Login;
import com.example.lequa.bts.model.MatDien;
import com.example.lequa.bts.model.NhaMang;
import com.example.lequa.bts.model.NhaTram;
import com.example.lequa.bts.model.Tram;
import com.example.lequa.bts.model.UserBTS;

@Database(entities = {Login.class, UserBTS.class,Tram.class,HinhAnhTram.class,
        NhaTram.class, NhaMang.class,MatDien.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract LoginDAO loginDAO();
    public abstract UserDAO userDAO();
    public abstract TramDAO tramDAO();
    public abstract HinhAnhTramDAO hinhAnhTramDAO();
    public abstract NhaTramDAO nhaTramDAO();
    public abstract NhaMangDAO nhaMangDAO();
    public abstract MatDienDAO matDienDAO();
}
