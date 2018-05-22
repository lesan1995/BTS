package com.example.lequa.bts.di;

import com.example.lequa.bts.ui.addmatdien.AddMatDienFragment;
import com.example.lequa.bts.ui.addnhatky.AddNhatKyFragment;
import com.example.lequa.bts.ui.addnhatram.AddNhaTramFragment;
import com.example.lequa.bts.ui.addtaikhoan.AddTaiKhoanFragment;
import com.example.lequa.bts.ui.addtram.AddTramFragment;
import com.example.lequa.bts.ui.baocao.BaoCaoFragment;
import com.example.lequa.bts.ui.canhan.CaNhanFragment;
import com.example.lequa.bts.ui.changepassword.ChangePasswordFragment;
import com.example.lequa.bts.ui.chitietmatdien.ChiTietMatDienFragment;
import com.example.lequa.bts.ui.chittiettaikhoan.ChiTietTaiKhoanFragment;
import com.example.lequa.bts.ui.dsmang.DSMangFragment;
import com.example.lequa.bts.ui.dsmatdien.DSMatDienFragment;
import com.example.lequa.bts.ui.dsnhatky.DSNhatKyFragment;
import com.example.lequa.bts.ui.dstram.DSTramFragment;
import com.example.lequa.bts.ui.editcanhan.EditCaNhanFragment;
import com.example.lequa.bts.ui.edittoado.EditToaDoFragment;
import com.example.lequa.bts.ui.hinhanh.HinhAnhFragment;
import com.example.lequa.bts.ui.login.LoginFragment;
import com.example.lequa.bts.ui.map.MainFragment;
import com.example.lequa.bts.ui.nhatky.NhatKyFragment;
import com.example.lequa.bts.ui.nhatram.NhaTramFragment;
import com.example.lequa.bts.ui.taikhoan.TaiKhoanFragment;
import com.example.lequa.bts.ui.thongke.ThongKeFragment;
import com.example.lequa.bts.ui.thongtin.ThongTinFragment;
import com.example.lequa.bts.ui.toado.ToaDo;
import com.example.lequa.bts.ui.tram.TramFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();
    @ContributesAndroidInjector
    abstract TramFragment contributeTramFragment();
    @ContributesAndroidInjector
    abstract NhaTramFragment contributeNhaTramFragment();
    @ContributesAndroidInjector
    abstract AddNhaTramFragment contributeAddNhaTramFragment();
    @ContributesAndroidInjector
    abstract AddTramFragment contributeAddTramFragment();
    @ContributesAndroidInjector
    abstract TaiKhoanFragment contributeTaiKhoanFragment();
    @ContributesAndroidInjector
    abstract ChiTietTaiKhoanFragment contributeChiTietTaiKhoanFragment();
    @ContributesAndroidInjector
    abstract HinhAnhFragment contributeHinhAnhFragment();
    @ContributesAndroidInjector
    abstract ToaDo contributeToaDo();
    @ContributesAndroidInjector
    abstract EditToaDoFragment contributeEditToaDoFragment();
    @ContributesAndroidInjector
    abstract AddTaiKhoanFragment contributeAddTaiKhoanFragment();
    @ContributesAndroidInjector
    abstract DSTramFragment contributeDSTramFragment();
    @ContributesAndroidInjector
    abstract CaNhanFragment contributeCaNhanFragment();
    @ContributesAndroidInjector
    abstract EditCaNhanFragment contributeEditCaNhanFragment();
    @ContributesAndroidInjector
    abstract ChangePasswordFragment contributeChangePasswordFragment();
    @ContributesAndroidInjector
    abstract DSMatDienFragment contributeDSMatDienFragment();
    @ContributesAndroidInjector
    abstract ChiTietMatDienFragment contributeChiTietMatDienFragment();
    @ContributesAndroidInjector
    abstract AddMatDienFragment contributeAddMatDienFragment();
    @ContributesAndroidInjector
    abstract DSMangFragment contributeDSMangFragment();
    @ContributesAndroidInjector
    abstract DSNhatKyFragment contributeDSNhatKyFragment();
    @ContributesAndroidInjector
    abstract NhatKyFragment contributeNhatKyFragment();
    @ContributesAndroidInjector
    abstract BaoCaoFragment contributeBaoCaoFragment();
    @ContributesAndroidInjector
    abstract AddNhatKyFragment contributeAddNhatKyFragment();
    @ContributesAndroidInjector
    abstract ThongTinFragment contributeThongTinFragment();
    @ContributesAndroidInjector
    abstract ThongKeFragment contributeThongKeFragment();

}
