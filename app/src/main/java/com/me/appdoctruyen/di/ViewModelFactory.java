package com.me.appdoctruyen.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.me.appdoctruyen.ui.book.list.BookViewModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;
import kotlin.annotation.AnnotationTarget;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {
    private Map<Class<ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactory(Map<Class<ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                modelClass.isAssignableFrom(entry.getKey());
                {
                    creator = entry.getValue();
                }
            }
        }

        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class: " + modelClass);
        }

        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

@Module
abstract class ViewModelBuilder {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(BookViewModel.class)
    abstract ViewModel bindViewModel(BookViewModel vm);
}

@Retention(RetentionPolicy.RUNTIME)
@MapKey
@interface ViewModelKey {
    Class<? extends ViewModel> value();
}

