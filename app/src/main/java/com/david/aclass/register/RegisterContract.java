package com.david.aclass.register;

import com.david.aclass.base.BaseContract;

public interface RegisterContract {

    interface View extends BaseContract.BaseView<Presenter> {


    }

    interface Presenter extends BaseContract.BasePresenter {

        void registerUser(String name, String password, String Class);

    }

}
