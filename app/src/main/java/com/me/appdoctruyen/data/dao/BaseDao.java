package com.me.appdoctruyen.data.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.me.appdoctruyen.data.models.User;

import io.reactivex.rxjava3.core.Completable;

public interface BaseDao<T> {
    @Insert
    Completable insertAll(T... ts);

    @Delete
    Completable delete(T t);

    @Update
    Completable update(T t);
}
