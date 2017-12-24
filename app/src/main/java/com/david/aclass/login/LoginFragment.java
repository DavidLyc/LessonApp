package com.david.aclass.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.david.aclass.R;
import com.david.aclass.base.BaseFragment;
import com.david.aclass.register.RegisterActivity;
import com.david.aclass.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.login_name_edit)
    EditText loginNameEdit;
    @BindView(R.id.login_password_edit)
    EditText loginPasswordEdit;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.login_confirm, R.id.login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_confirm:
                login();
                break;
            case R.id.login_register:
                register();
                break;
        }
    }

    private void login() {
        String name = String.valueOf(loginNameEdit.getText());
        String password = String.valueOf(loginPasswordEdit.getText());
        if (name.trim().isEmpty() || password.trim().isEmpty()) {
            Toast.makeText(getContext(), "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.validateLogin(name, password);
        }
    }

    private void register() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void enterSearchActivity() {
        getActivity().finish();
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

}
