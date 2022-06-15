package com.me.appdoctruyen.ui.book.list;

import androidx.lifecycle.ViewModel;

import com.me.appdoctruyen.data.repository.BookRepository;

import javax.inject.Inject;

public class BookViewModel extends ViewModel {

    private BookRepository bookRepository;

    @Inject
    public BookViewModel(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
