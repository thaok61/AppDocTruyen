package com.me.appdoctruyen.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.me.appdoctruyen.data.models.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDao extends BaseDao<User> {
    @Query("SELECT * FROM user")
    Flowable<List<User>> getAll();

    @Query("SELECT * FROM user WHERE user_name LIKE :userName")
    Single<User> findByUserName(String userName);

    @Query("SELECT * FROM user WHERE first_name LIKE '%' || :name || '%' OR " +
            "last_name LIKE '%' || :name || '%'")
    Flowable<List<User>> findByName(String name);

    @Query("SELECT * FROM user WHERE user_name LIKE :userName AND " +
            "password LIKE :password")
    Single<User> login(String userName, String password);
}
