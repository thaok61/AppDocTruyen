package com.me.appdoctruyen.data.repository;

import com.me.appdoctruyen.data.dao.BookDao;
import com.me.appdoctruyen.data.models.Book;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class BookRepositoryImpl implements BookRepository {
    BookDao bookDao;

    public BookRepositoryImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Completable delete(Book book) {
        return bookDao.delete(book);
    }

    @Override
    public Completable insertAll(Book... books) {
        return bookDao.insertAll(books);
    }

    @Override
    public Completable update(Book book) {
        return bookDao.update(book);
    }

    @Override
    public Flowable<List<Book>> findByAuthor(String author) {
        return bookDao.findByAuthor(author);
    }

    @Override
    public Flowable<List<Book>> findByName(String name) {
        return bookDao.findByName(name);
    }

    @Override
    public Flowable<List<Book>> getAll() {
        return bookDao.getAll();
    }
}
