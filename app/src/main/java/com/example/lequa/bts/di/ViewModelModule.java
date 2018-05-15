package com.example.lequa.bts.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.lequa.bts.ui.addmatdien.AddMatDienViewModel;
import com.example.lequa.bts.ui.addnhatram.AddNhaTramViewModel;
import com.example.lequa.bts.ui.addtaikhoan.AddTaiKhoanViewModel;
import com.example.lequa.bts.ui.addtram.AddTramViewModel;
import com.example.lequa.bts.ui.canhan.CaNhanViewModel;
import com.example.lequa.bts.ui.changepassword.ChangePasswordViewModel;
import com.example.lequa.bts.ui.chitietmatdien.ChiTietMatDienViewModel;
import com.example.lequa.bts.ui.chittiettaikhoan.ChiTietTaiKhoanViewModel;
import com.example.lequa.bts.ui.dsmatdien.DSMatDienViewModel;
import com.example.lequa.bts.ui.dstram.DSTramViewModel;
import com.example.lequa.bts.ui.editcanhan.EditCaNhanViewModel;
import com.example.lequa.bts.ui.edittoado.EditToaDoViewModel;
import com.example.lequa.bts.ui.hinhanh.HinhAnhViewModel;
import com.example.lequa.bts.ui.login.LoginViewModel;
import com.example.lequa.bts.ui.map.MainViewModel;
import com.example.lequa.bts.ui.nhatram.NhaTramViewModel;
import com.example.lequa.bts.ui.taikhoan.TaiKhoanViewModel;
import com.example.lequa.bts.ui.toado.ToaDoViewModel;
import com.example.lequa.bts.ui.tram.TramViewModel;
import com.example.lequa.bts.viewmodel.BTSViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(TramViewModel.class)
    abstract ViewModel bindTramViewModel(TramViewModel tramViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(NhaTramViewModel.class)
    abstract ViewModel bindNhaTramViewModel(NhaTramViewModel nhaTramViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(AddNhaTramViewModel.class)
    abstract ViewModel bindAddNhaTramViewModel(AddNhaTramViewModel addNhaTramViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(AddTramViewModel.class)
    abstract ViewModel bindAddTramViewModel(AddTramViewModel addTramViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(TaiKhoanViewModel.class)
    abstract ViewModel bindTaiKhoanViewModel(TaiKhoanViewModel taiKhoanViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(ChiTietTaiKhoanViewModel.class)
    abstract ViewModel bindChiTietTaiKhoanViewModel(ChiTietTaiKhoanViewModel chitietTaiKhoanViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(HinhAnhViewModel.class)
    abstract ViewModel bindHinhAnhViewModel(HinhAnhViewModel hinhAnhViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(ToaDoViewModel.class)
    abstract ViewModel bindToaDoViewModel(ToaDoViewModel toaDoViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(EditToaDoViewModel.class)
    abstract ViewModel bindEditToaDoViewModel(EditToaDoViewModel editToaDoViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(AddTaiKhoanViewModel.class)
    abstract ViewModel bindAddTaiKhoanViewModel(AddTaiKhoanViewModel addTaiKhoanViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(DSTramViewModel.class)
    abstract ViewModel bindDSTramViewModel(DSTramViewModel dsTramViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(CaNhanViewModel.class)
    abstract ViewModel bindCaNhanViewModel(CaNhanViewModel caNhanViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(EditCaNhanViewModel.class)
    abstract ViewModel bindEditCaNhanViewModel(EditCaNhanViewModel editCaNhanViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel.class)
    abstract ViewModel bindChangePasswordViewModel(ChangePasswordViewModel changePasswordViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(DSMatDienViewModel.class)
    abstract ViewModel bindDSMatDienViewModel(DSMatDienViewModel dsMatDienViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(ChiTietMatDienViewModel.class)
    abstract ViewModel bindChiTietMatDienViewModel(ChiTietMatDienViewModel chiTietMatDienViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(AddMatDienViewModel.class)
    abstract ViewModel bindAddMatDienViewModel(AddMatDienViewModel addMatDienViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BTSViewModelFactory factory);
}