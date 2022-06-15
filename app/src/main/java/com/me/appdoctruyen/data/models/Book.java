package com.me.appdoctruyen.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Book {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return bid == book.bid && Objects.equals(author, book.author) && Objects.equals(name, book.name) && Objects.equals(content, book.content) && Objects.equals(image, book.image) && Objects.equals(category, book.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, author, name, content, image, category);
    }

    @PrimaryKey(autoGenerate = true)
    public int bid = 0;
    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "content")
    public String content;
    @ColumnInfo(name = "image")
    public String image;
    @ColumnInfo(name = "category")
    public String category;

    public Book(String author, String name, String content, String image, String category) {
        this.author = author;
        this.name = name;
        this.content = content;
        this.image = image;
        this.category = category;
    }
}