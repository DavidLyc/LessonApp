package com.david.aclass.base;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter {

    protected T mView;
    private CompositeDisposable mDisposables;
    protected Context mContext;
    protected Boolean mIsFirstLoad = true;

    public BasePresenter(T view, Context context) {
        mView = view;
        mContext = context;
        mDisposables = new CompositeDisposable();
    }

    protected void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mDisposables.clear();
    }

}
