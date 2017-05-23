package com.dev.ldhnam.kotlinsample;

import com.dev.ldhnam.kotlinsample.util.Event;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RxSortedDiffList<T> {

    private CompositeDisposable disposable;

    private static Executor sExecutor = Executors.newSingleThreadExecutor();

    private SnappySortedList<T> mSnappySortedList;

    public RxSortedDiffList(Class<T> clazz, RxSortedListCallback<T> callback) {
        mSnappySortedList = new SnappySortedList<>(clazz, callback);
        disposable = new CompositeDisposable();
    }

    public RxSortedDiffList(Class<T> clazz, RxSortedListCallback<T> callback, int initialCapacity) {
        mSnappySortedList = new SnappySortedList<>(clazz, callback, initialCapacity);
        disposable = new CompositeDisposable();
    }

    public void add(T item) {
        disposable.add(Observable.just(item)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> mSnappySortedList.add(item), Throwable::printStackTrace));
    }

    public void addAll(List<T> users) {
        disposable.add(Observable.just(users)
                .subscribeOn(Schedulers.from(sExecutor))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u -> mSnappySortedList.addAll(u)));
    }

    public void clearSubscribe() {
        disposable.clear();
    }

    public void clear() {
        disposable.add(Observable.create(e -> mSnappySortedList.clear())
                .subscribeOn(Schedulers.from(sExecutor))
                .observeOn(AndroidSchedulers.mainThread()).subscribe());
    }

    public T get(int index) {
        return mSnappySortedList.get(index);
    }

    public List<T> getData() {
        return Arrays.asList(mSnappySortedList.mData);
    }

    public Observable<Boolean> remove(T item) {
        return Observable.fromCallable(() -> mSnappySortedList.remove(item)).compose(onBackground());
    }

    public void removeItemAt(int index) {
        disposable.add(Observable.just(index)
                .subscribeOn(Schedulers.from(sExecutor))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> mSnappySortedList.removeItemAt(i)));
    }

    public Observable<Object> set(Collection<T> items) {
        return mSnappySortedList.set(items)
                .subscribeOn(Schedulers.from(sExecutor));
    }

    public Observable<Object> set(Collection<T> items, boolean isSorted) {
        return mSnappySortedList.set(items, isSorted)
                .subscribeOn(Schedulers.from(sExecutor));
    }

    public int size() {
        return mSnappySortedList.size();
    }

    public void updateItemAt(int index, T item) {
        disposable.add(Observable.just(item)
                .subscribeOn(Schedulers.from(sExecutor))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> mSnappySortedList.updateItemAt(index, i)));
    }

    private <T2> ObservableTransformer<T2, T2> onBackground() {
        return observable -> Observable.just(Event.INSTANCE)
                .subscribeOn(Schedulers.from(sExecutor))
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(o -> observable);
    }
}