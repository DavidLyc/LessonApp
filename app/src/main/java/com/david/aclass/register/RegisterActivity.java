package com.david.aclass.register;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.david.aclass.R;
import com.david.aclass.base.BaseActivity;
import com.david.aclass.util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(null);
        mToolbarTitle.setText("注册新用户");

        RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), registerFragment, R.id.fragment);
        }

        new RegisterPresenter(registerFragment, this);
    }

}
