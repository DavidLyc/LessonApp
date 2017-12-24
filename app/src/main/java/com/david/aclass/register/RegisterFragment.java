package com.david.aclass.register;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterFragment extends BaseFragment<RegisterContract.Presenter> implements RegisterContract.View {

    @BindView(R.id.register_username_edit)
    EditText mRegisterUsernameEdit;
    @BindView(R.id.register_password_edit)
    EditText mRegisterPasswordEdit;
    @BindView(R.id.register_class_edit)
    EditText mRegisterClassEdit;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.register_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.register_confirm)
    public void onViewClicked() {
        String name = String.valueOf(mRegisterUsernameEdit.getText());
        String password = String.valueOf(mRegisterPasswordEdit.getText());
        String Class = String.valueOf(mRegisterClassEdit.getText());
        if (name.trim().isEmpty() || password.trim().isEmpty()) {
            Toast.makeText(getContext(), "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.registerUser(name, password, Class);
        }
    }

}
