package com.david.aclass.register;

import android.content.Context;
import android.widget.Toast;

import com.david.aclass.base.BasePresenter;
import com.david.aclass.util.NetworkUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    RegisterPresenter(RegisterContract.View view, Context context) {
        super(view, context);
        mView.setPresenter(this);
    }

    @Override
    public void registerUser(final String name, final String password, final String Class) {
        mView.showProgressDialog();
        addDisposable(
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext(NetworkUtils.register(name, password, Class));
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String result) throws Exception {
                                mView.cancelProgressDialog();
                                switch (result) {
                                    case "ok":
                                        Toast.makeText(mContext, "注册成功！", Toast.LENGTH_SHORT).show();
                                        mView.finishActivity();
                                        break;
                                    case "dup":
                                        Toast.makeText(mContext, "该用户名已存在！", Toast.LENGTH_SHORT).show();
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
