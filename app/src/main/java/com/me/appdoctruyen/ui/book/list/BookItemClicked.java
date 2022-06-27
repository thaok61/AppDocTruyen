package com.me.appdoctruyen.ui.book.list;

import androidx.annotation.NonNull;

import com.me.appdoctruyen.data.models.Book;

public interface BookItemClicked {
    public void onItemClicked(@NonNull Book book);
}
