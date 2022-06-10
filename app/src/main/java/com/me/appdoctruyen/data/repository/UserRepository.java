package com.me.appdoctruyen.data.repository;

import com.me.appdoctruyen.data.models.Book;
import com.me.appdoctruyen.data.models.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface UserRepository {
    Completable delete(User user);
    Completable insertAll(User... users);
    Completable update(User user);
    Single<User> findByUserName(String userName);
    Flowable<List<User>> findByName(String name);
    Flowable<List<User>> getAll();
    Single<User> login(String username, String password);
}
