package com.me.appdoctruyen.di;

import androidx.lifecycle.ViewModel;

import com.me.appdoctruyen.MainActivity;
import com.me.appdoctruyen.ui.book.list.BookViewModel;
import com.me.appdoctruyen.ui.book.list.FirstFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity injectMain();
    
    @ContributesAndroidInjector
    abstract FirstFragment injectFirstFragment();

    @Binds
    @IntoMap
    @ViewModelKey(BookViewModel.class)
    abstract ViewModel bindViewModel(BookViewModel vm);
}
