package com.david.aclass.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.david.aclass.base.BasePresenter;
import com.david.aclass.util.NetworkUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    LoginPresenter(@NonNull LoginContract.View view, @NonNull Context context) {
        super(view, context);
        mView.setPresenter(this);
    }

    @Override
    public void validateLogin(final String name, final String password) {
        mView.showProgressDialog();
        addDisposable(
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext(NetworkUtils.login(name, password));
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String result) throws Exception {
                                mView.cancelProgressDialog();
                                switch (result) {
                                    case "pass":
                                        mView.enterSearchActivity();
                                        Toast.makeText(mContext, "登录成功！", Toast.LENGTH_SHORT).show();
                                        break;
                                    case "deny":
                                        Toast.makeText(mContext, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                                        break;
                                    case "timeout":
                                        Toast.makeText(mContext, "网络连接出错！", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        })
        );
    }

}
