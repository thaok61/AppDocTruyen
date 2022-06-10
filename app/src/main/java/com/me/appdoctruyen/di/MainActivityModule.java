package com.me.appdoctruyen.di;

import com.me.appdoctruyen.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity injectMain();
}
