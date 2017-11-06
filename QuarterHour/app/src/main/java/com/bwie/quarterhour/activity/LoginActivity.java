package com.bwie.quarterhour.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.quarterhour.R;
import com.bwie.quarterhour.base.BaseActivity;
import com.bwie.quarterhour.base.BasePresenter;
import com.bwie.quarterhour.presenter.Presenter_Login;
import com.bwie.quarterhour.view.IViewLogin;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<IViewLogin, Presenter_Login> implements View.OnClickListener, IViewLogin {


    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(R.id.reg)
    TextView reg;
    @BindView(R.id.login_uname)
    EditText loginUname;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.forget_pwd)
    TextView forgetPwd;
    @BindView(R.id.yk_login)
    TextView ykLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        click();
    }

    @Override
    protected Presenter_Login getPresenter() {
        return new Presenter_Login();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    public void click() {
        reg.setOnClickListener(this);
        loginBack.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login:
                if (TextUtils.isEmpty(loginPwd.getText().toString()) || TextUtils.isEmpty(loginUname.getText().toString())) {
                    Toast.makeText(this, "用户名、密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.postLogin(loginPwd.getText().toString(), loginUname.getText().toString(),this);
                }
                break;
            case R.id.reg:
                Intent intent = new Intent(this, RegActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void error() {
        loginUname.setError("用户名或密码错误");
    }

    @Override
    public void loginSucc() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reg() {
    }
}
