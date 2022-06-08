package com.me.appdoctruyen.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.me.appdoctruyen.data.models.Book;
import com.me.appdoctruyen.data.models.User;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface BookDao extends BaseDao<Book> {
    @Query("SELECT * FROM book")
    Flowable<List<Book>> getAll();

    @Query("SELECT * FROM book WHERE category LIKE :category")
    Flowable<List<Book>> loadAllByCategory(String category);

    @Query("SELECT * FROM book WHERE name LIKE '%'|| :name || '%' ")
    Flowable<Book> findByName(String name);

    @Query("SELECT * FROM book WHERE author LIKE '%'|| :author || '%' ")
    Flowable<Book> findByAuthor(String author);
}
