package com.me.appdoctruyen.data.repository;

import com.me.appdoctruyen.data.models.Book;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface BookRepository {
    Completable delete(Book book);
    Completable insertAll(Book... books);
    Completable update(Book book);
    Flowable<List<Book>> findByAuthor(String author);
    Flowable<List<Book>> findByName(String name);
    Flowable<List<Book>> getAll();
}
