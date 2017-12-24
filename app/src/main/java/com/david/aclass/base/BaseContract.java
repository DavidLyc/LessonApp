package com.david.aclass.base;

public interface BaseContract {

    interface BasePresenter {

        void subscribe();

        void unsubscribe();

    }

    interface BaseView<T extends BaseContract.BasePresenter> {

        void setPresenter(T presenter);

        void showProgressDialog();

        void cancelProgressDialog();

        void finishActivity();

    }

}
