package com.bwie.quarterhour.activity;

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
import com.bwie.quarterhour.presenter.Presenter_Login;
import com.bwie.quarterhour.view.IViewLogin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegActivity extends BaseActivity<IViewLogin, Presenter_Login> implements View.OnClickListener, IViewLogin {

    @BindView(R.id.reg_back)
    ImageView regBack;
    @BindView(R.id.reg_has)
    TextView regHas;
    @BindView(R.id.reg_uname)
    EditText regUname;
    @BindView(R.id.reg_pwd)
    EditText regPwd;
    @BindView(R.id.reg_but)
    Button regBut;
    @BindView(R.id.reg_yk_login)
    TextView regYkLogin;

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
        return R.layout.activity_reg;
    }

    public void click() {
        regBack.setOnClickListener(this);
        regBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_back:
                finish();
                break;
            case R.id.reg_but:
                if(TextUtils.isEmpty(regPwd.getText().toString())||TextUtils.isEmpty(regUname.getText().toString())){
                    Toast.makeText(this, "用户名、密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.postReg(regPwd.getText().toString(), regUname.getText().toString());
                }
                break;
        }
    }

    @Override
    public void error() {
        regUname.setError("注册号码已存在");
    }

    @Override
    public void loginSucc() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reg() {

    }
}
