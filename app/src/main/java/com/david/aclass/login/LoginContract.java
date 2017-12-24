package com.david.aclass.login;

import com.david.aclass.base.BaseContract;

public interface LoginContract {

    interface View extends BaseContract.BaseView<Presenter> {

        void enterSearchActivity();

    }

    interface Presenter extends BaseContract.BasePresenter {

        void validateLogin(final String name, final String password);

    }

}
