package com.example.agriculture;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.gaoguo.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import model.User;

public class RegistActivity extends BaseActivity {
    private EditText mEtPassword;
    private EditText mEtUser;
    private RelativeLayout mRtlComfirm;
    private EditText mEtPassword1;
    private EditText mEtPhone;
    private CheckBox mCbShop;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initview();
        initevent();
    }
    private void initview() {
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mCbShop = (CheckBox) findViewById(R.id.cb_shop);
        mEtUser = (EditText) findViewById(R.id.et_user);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mEtPassword1 = (EditText) findViewById(R.id.et_password1);
        mRtlComfirm = (RelativeLayout) findViewById(R.id.rtl_comfirm);
    }
    private void initevent() {
        findViewById(R.id.rtl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRtlComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtUser.getText().toString().trim().equals("") || mEtPassword.getText().toString().trim().equals("")|| mEtPassword1.getText().toString().trim().equals("")|| mEtPhone.getText().toString().trim().equals("")) {
                    Toast("请填写完整");
                } else if (!mEtPassword.getText().toString().trim().equals(mEtPassword1.getText().toString().trim())) {
                    Toast("抱歉，两次输入的密码不一致");
                }else {
                    Regist();
                }
            }

        });
    }
    private void Regist() {
        startProgressDialog("注册中...");
        final User myUser = new User();
        myUser.setUsername(mEtUser.getText().toString().trim());
        myUser.setPassword(mEtPassword.getText().toString().trim());
        myUser.setHeadUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491821862688&di=b41c4655f02381f4694cfa35605386c3&imgtype=0&src=http%3A%2F%2Fwww.lgmjg.com%2Fimg%2F275946.jpg");
        if(mCbShop.isChecked()){
            myUser.setState(1);
        }     else {
            myUser.setState(0);
        }
        myUser.setPhone(mEtPhone.getText().toString().trim());
        myUser.signUp(new SaveListener<User>() {
            @Override
            public void done(User myUser, BmobException e) {
                stopProgressDialog();
                if (e == null) {
                    finish();
                    Toast("注册成功");
                } else {
                    Toast(""+e.toString());
                    Log.e("", "");
                }
            }
        });
    }
}

            ;
