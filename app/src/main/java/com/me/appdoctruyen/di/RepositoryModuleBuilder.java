package com.me.appdoctruyen.di;

import com.me.appdoctruyen.data.repository.BookRepository;
import com.me.appdoctruyen.data.repository.BookRepositoryImpl;
import com.me.appdoctruyen.data.repository.UserRepository;
import com.me.appdoctruyen.data.repository.UserRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModuleBuilder {
    @Singleton
    @Binds
    abstract UserRepository bindUserRepository(UserRepositoryImpl repo);

    @Singleton
    @Binds
    abstract BookRepository bindBookRepository(BookRepositoryImpl repo);
}
