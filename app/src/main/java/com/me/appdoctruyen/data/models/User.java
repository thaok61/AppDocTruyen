package com.me.appdoctruyen.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"user_name", "email"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid = 0;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "password")
    public String password;
    // 0 is admin, 1 is user
    @ColumnInfo(name = "type")
    public Integer type = 1;
    @ColumnInfo(name = "is_verified")
    public Boolean isVerified;


    public User(String userName, String firstName, String lastName, String email,
                String password, Integer type, Boolean isVerified) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.isVerified = isVerified;
    }

    @Ignore
    public User(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
