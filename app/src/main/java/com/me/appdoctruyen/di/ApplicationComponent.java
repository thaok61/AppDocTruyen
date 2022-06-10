package com.me.appdoctruyen.di;

import android.content.Context;

import com.me.appdoctruyen.DocTruyenApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, MainActivityModule.class, RepositoryModuleBuilder.class})
public interface ApplicationComponent extends AndroidInjector<DocTruyenApplication> {
    @Component.Factory
    interface Factory {
        ApplicationComponent create(@BindsInstance Context context);
    }
}
