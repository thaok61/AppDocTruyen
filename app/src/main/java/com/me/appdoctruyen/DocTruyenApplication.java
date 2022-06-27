package com.me.appdoctruyen;

//import com.me.appdoctruyen.di.DaggerApplicationComponent;

import com.me.appdoctruyen.di.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class DocTruyenApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        return null;
        return DaggerApplicationComponent.factory().create(getApplicationContext());
    }
}
