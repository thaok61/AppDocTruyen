package com.me.appdoctruyen.ui.book.list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.me.appdoctruyen.data.models.Book;
import com.me.appdoctruyen.data.repository.BookRepository;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BookViewModel extends ViewModel {
    public final static String TAG = BookViewModel.class.getSimpleName();
    private BookRepository bookRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<List<Book>> _dataBook = new MutableLiveData<>();
    public LiveData<List<Book>> dataBook = _dataBook;
    @Inject
    public BookViewModel(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void getAllBook() {
        Disposable observable = bookRepository.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(books -> {
            _dataBook.postValue(books);
        }, error-> {
            Log.e(TAG, "getAllBook: Error", error);
            error.printStackTrace();
        });

        compositeDisposable.add(observable);
    }
}
