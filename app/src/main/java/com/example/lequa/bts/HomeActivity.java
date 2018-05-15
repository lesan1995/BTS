package com.example.lequa.bts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

import com.example.lequa.bts.db.LoginDAO;
import com.example.lequa.bts.model.Login;
import com.example.lequa.bts.model.UserLogin;
import com.example.lequa.bts.ui.common.NavigationController;
import com.example.lequa.bts.ui.login.LoginFragment;
import com.example.lequa.bts.ui.login.LoginViewModel;
import com.example.lequa.bts.vo.Resource;
import com.google.android.gms.maps.OnMapReadyCallback;

import javax.inject.Inject;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends AppCompatActivity implements HasSupportFragmentInjector{
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    NavigationController navigationController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            navigationController.navigateToLogin(false);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        try{
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
        catch (Exception e){
            super.onBackPressed();
        }

    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            LoginFragment loginFragment=((LoginFragment)getSupportFragmentManager().findFragmentByTag("login"));

            if(loginFragment!=null){
                if(loginFragment.isVisible()){
                    ((LoginFragment)getSupportFragmentManager().findFragmentByTag("login")).myOnKeyDown(keyCode);
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
