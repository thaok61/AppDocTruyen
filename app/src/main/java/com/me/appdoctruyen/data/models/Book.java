package com.me.appdoctruyen.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
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