package com.me.appdoctruyen.data.repository;

import com.me.appdoctruyen.data.dao.UserDao;
import com.me.appdoctruyen.data.models.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class UserRepositoryImpl implements UserRepository {
    UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Completable delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public Completable insertAll(User... users) {
        return userDao.insertAll(users);
    }

    @Override
    public Completable update(User user) {
        return userDao.update(user);
    }

    @Override
    public Single<User> findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public Flowable<List<User>> findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public Flowable<List<User>> getAll() {
        return userDao.getAll();
    }

    @Override
    public Single<User> login(String username, String password) {
        return userDao.login(username, password);
    }
}
