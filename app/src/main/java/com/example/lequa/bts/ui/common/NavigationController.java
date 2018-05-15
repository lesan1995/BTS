package com.example.lequa.bts.ui.common;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.lequa.bts.HomeActivity;
import com.example.lequa.bts.R;
import com.example.lequa.bts.ui.addmatdien.AddMatDienFragment;
import com.example.lequa.bts.ui.addnhatram.AddNhaTramFragment;
import com.example.lequa.bts.ui.addtaikhoan.AddTaiKhoanFragment;
import com.example.lequa.bts.ui.addtram.AddTramFragment;
import com.example.lequa.bts.ui.canhan.CaNhanFragment;
import com.example.lequa.bts.ui.changepassword.ChangePasswordFragment;
import com.example.lequa.bts.ui.chitietmatdien.ChiTietMatDienFragment;
import com.example.lequa.bts.ui.chittiettaikhoan.ChiTietTaiKhoanFragment;
import com.example.lequa.bts.ui.dsmatdien.DSMatDienFragment;
import com.example.lequa.bts.ui.dstram.DSTramFragment;
import com.example.lequa.bts.ui.editcanhan.EditCaNhanFragment;
import com.example.lequa.bts.ui.edittoado.EditToaDoFragment;
import com.example.lequa.bts.ui.hinhanh.HinhAnhFragment;
import com.example.lequa.bts.ui.login.LoginFragment;
import com.example.lequa.bts.ui.map.MainFragment;
import com.example.lequa.bts.ui.nhatram.NhaTramFragment;
import com.example.lequa.bts.ui.taikhoan.TaiKhoanFragment;
import com.example.lequa.bts.ui.toado.ToaDo;
import com.example.lequa.bts.ui.tram.TramFragment;

import javax.inject.Inject;

public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;
    @Inject
    public NavigationController(HomeActivity homeActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = homeActivity.getSupportFragmentManager();
    }

    public void navigateToLogin(Boolean dangXuat) {
        String tag = "login";
        Log.d("navigateToLogin",tag);
        LoginFragment loginFragment = LoginFragment.create(dangXuat);
        fragmentManager.beginTransaction()
                .replace(containerId, loginFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToMain(String email,String token,String chucVu) {
        String tag = "main" + "/" + email+"/"+token+"/"+chucVu;
        MainFragment mainFragment = MainFragment.create(email,token,chucVu);
        fragmentManager.beginTransaction()
                .replace(containerId, mainFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToTram(String id,String token) {
        String tag = "tram" + "/" + id+"/"+token;
        TramFragment tramFragment =TramFragment.create(id,token);
        fragmentManager.beginTransaction()
                .replace(containerId, tramFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToTram(String id,String token,String chucVu) {
        String tag = "tram" + "/" + id+"/"+token+"/"+chucVu;
        TramFragment tramFragment =TramFragment.create(id,token,chucVu);
        fragmentManager.beginTransaction()
                .replace(containerId, tramFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToNhaTram(String id,String token) {
        String tag = "nhatram" + "/" + id+"/"+token;
        NhaTramFragment nhaTramFragment =NhaTramFragment.create(id,token);
        fragmentManager.beginTransaction()
                .replace(containerId, nhaTramFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToNhaTram(String id,String token,String chucVu) {
        String tag = "nhatram" + "/" + id+"/"+token+"/"+chucVu;
        NhaTramFragment nhaTramFragment =NhaTramFragment.create(id,token,chucVu);
        fragmentManager.beginTransaction()
                .replace(containerId, nhaTramFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToAddNhaTram(String id,String token) {
        String tag = "addnhatram" + "/" + id+"/"+token;
        AddNhaTramFragment addNhaTramFragment =AddNhaTramFragment.create(id,token);
        fragmentManager.beginTransaction()
                .replace(containerId, addNhaTramFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToAddTram(String token) {
        String tag = "addnhatram" +"/"+token;
        AddTramFragment addTramFragment =AddTramFragment.create(token);
        fragmentManager.beginTransaction()
                .replace(containerId, addTramFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToTaiKhoan(String token) {
        String tag = "taikhoan" +"/"+token;
        TaiKhoanFragment taiKhoanFragment =TaiKhoanFragment.create(token);
        fragmentManager.beginTransaction()
                .replace(containerId, taiKhoanFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToChiTietTaiKhoan(String id,String token) {
        String tag = "chitiettaikhoan" + "/" + id+"/"+token;
        ChiTietTaiKhoanFragment chiTietTaiKhoanFragment =ChiTietTaiKhoanFragment.create(id,token);
        fragmentManager.beginTransaction()
                .replace(containerId, chiTietTaiKhoanFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToHinhAnh(String id,String token) {
        String tag = "hinhanh" + "/" + id+"/"+token;
        HinhAnhFragment hinhAnhFragment =HinhAnhFragment.create(id,token);
        fragmentManager.beginTransaction()
                .replace(containerId, hinhAnhFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToToaDo(String token) {
        String tag = "toado" +"/"+token;
        ToaDo toaDo =ToaDo.create(token);
        fragmentManager.beginTransaction()
                .replace(containerId, toaDo,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToEditToaDo(String id,String token) {
        String tag = "edittoado" + "/" + id+"/"+token;
        EditToaDoFragment editToaDoFragment =EditToaDoFragment.create(id,token);
        fragmentManager.beginTransaction()
                .replace(containerId, editToaDoFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToAddTaiKhoan(String token) {
        String tag = "addtaikhoan" +"/"+token;
        AddTaiKhoanFragment addTaiKhoanFragment =AddTaiKhoanFragment.create(token);
        fragmentManager.beginTransaction()
                .replace(containerId, addTaiKhoanFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToDSTram(String token) {
        String tag = "dstram" +"/"+token;
        DSTramFragment dsTramFragment =DSTramFragment.create(token);
        fragmentManager.beginTransaction()
                .replace(containerId, dsTramFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToCaNhan(String email,String token) {
        String tag = "canhan" + "/" + email+"/"+token;
        CaNhanFragment caNhanFragment = CaNhanFragment.create(email,token);
        fragmentManager.beginTransaction()
                .replace(containerId, caNhanFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToEditCaNhan(String email,String token) {
        String tag = "editcanhan" + "/" + email+"/"+token;
        EditCaNhanFragment editCaNhanFragment = EditCaNhanFragment.create(email,token);
        fragmentManager.beginTransaction()
                .replace(containerId, editCaNhanFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToChangePassword(String token) {
        String tag = "changepassword" +"/"+token;
        ChangePasswordFragment changePasswordFragment =ChangePasswordFragment.create(token);
        fragmentManager.beginTransaction()
                .replace(containerId, changePasswordFragment,tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToChiTietMatDien(String idMatDien,String token) {
        String tag = "chitietmatdien" + "/" + idMatDien+"/"+token;
        ChiTietMatDienFragment chiTietMatDienFragment = ChiTietMatDienFragment.create(idMatDien,token);
        fragmentManager.beginTransaction()
                .replace(containerId, chiTietMatDienFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToDSMatDien(String idTram,String cv,String token) {
        String tag = "dsmatdien" + "/" + idTram+"/"+token+"/"+cv;
        DSMatDienFragment dsMatDienFragment = DSMatDienFragment.create(idTram,cv,token);
        fragmentManager.beginTransaction()
                .replace(containerId, dsMatDienFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
    public void navigateToAddMatDien(String idTram,String token) {
        String tag = "addmatdien" + "/" + idTram+"/"+token;
        AddMatDienFragment addMatDienFragment = AddMatDienFragment.create(idTram,token);
        fragmentManager.beginTransaction()
                .replace(containerId, addMatDienFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}
