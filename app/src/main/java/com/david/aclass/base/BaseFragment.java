package com.david.aclass.base;

import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends Fragment {

    protected T mPresenter;
    protected MaterialDialog mProgressDialog;

    public void setPresenter(T presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    public void showProgressDialog() {
        mProgressDialog = new MaterialDialog.Builder(getContext())
                .content("加载中...")
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .show();
    }

    public void cancelProgressDialog() {
        mProgressDialog.cancel();
    }

    public void finishActivity(){
        getActivity().finish();
    }

}
