package com.example.lequa.bts.di;

import com.example.lequa.bts.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract HomeActivity contributeMainActivity();
}
