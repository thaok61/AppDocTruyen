package com.me.appdoctruyen.di;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.me.appdoctruyen.data.dao.BookDao;
import com.me.appdoctruyen.data.dao.UserDao;
import com.me.appdoctruyen.data.database.AppDatabase;
import com.me.appdoctruyen.data.models.Book;
import com.me.appdoctruyen.data.models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Singleton
    @Provides
    public AppDatabase provideDatabase(Context context) {
        return AppDatabase.getDatabase(context);
    }

    @Singleton
    @Provides
    public UserDao provideUserDao(AppDatabase db) {
        return db.userDao();
    }

    @Singleton
    @Provides
    public BookDao provideBookDao(AppDatabase db) {
        return db.bookDao();
    }

}
