package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.agriculture.Manager.ManagerActivity;
import com.example.agriculture.Shop.ShopActivity;
import com.example.gaoguo.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import model.User;
import utils.SetUtils;
/**
 */
public class LoginActivity extends BaseActivity {
    private EditText mEtUser;
    private EditText mEtPassword;
    private TextView mTvRegist;
    private RelativeLayout mRtlComfir;
    private TextView mTvForgetPassword;
    private CheckBox mCbManager;
    private CheckBox mCbShop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        initCheckLitner();
        initevent();
        if(SetUtils.IsLogin(getApplicationContext())){
            switch (SetUtils.GetState(getApplicationContext())){
                case "0":
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                    break;
                case "1":
                    startActivity(new Intent(getApplicationContext(),ShopActivity.class));
                    finish();
                    break;
                case "2":
                    startActivity(new Intent(getApplicationContext(),ManagerActivity.class));
                    finish();
                    break;
            }
        }
    }

    /**
     * 判断checkbox的状态单选
     */
    private void initCheckLitner() {
        /**
         * 如果商家选中，那管理元就不选中
         */
        mCbShop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mCbManager.setChecked(false);
                }
            }
        });
        mCbManager.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mCbShop.setChecked(false);
                }
            }
        });
    }

    private void initevent() {
        findViewById(R.id.rtl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegistActivity.class);
                startActivity(intent);
            }
        });
        mRtlComfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtUser.getText().toString().trim().equals("")||mEtPassword.getText().toString().trim().equals("")){
                    Toast("请填写完整");
                }else {
                    Login();
                }
            }
        });
    }
    /**
     * 登录
     */
    private void Login() {
        startProgressDialog("登录中...");
        final BmobUser user = new BmobUser();
        user.setUsername(mEtUser.getText().toString().trim());
        user.setPassword(mEtPassword.getText().toString().trim());
        user.login(new SaveListener<User>() {
            @Override
            public void done(User myUser, BmobException e) {
                stopProgressDialog();
                    if(e==null){
                        if(mCbManager.isChecked()){
                            if(myUser.getState()==0 ||myUser.getState()==1){
                                Toast("抱歉，请检查您的账号和密码是否正确");
                                return;
                            }
                            if(myUser.getState()==2){
                                Toast("登录成功");
                                SetUtils.saveUser(getApplicationContext(), mEtUser.getText().toString().trim());
                                SetUtils.saveNickName(getApplicationContext(), myUser.getUsername());
                                SetUtils.savePassword(getApplicationContext(), mEtPassword.getText().toString().trim());
                                SetUtils.saveImgHead(getApplicationContext(),myUser.getHeadUrl());
                                SetUtils.saveId(getApplicationContext(), myUser.getObjectId());
                                SetUtils.saveState(getApplicationContext(),"2");
                                SetUtils.saveIsLogin(getApplicationContext(),true);
                                SetUtils.saveIsManager(getApplicationContext(),true);
                                startActivity(new Intent(LoginActivity.this, ManagerActivity.class));
                                finish();
                            }else {
                                Toast("抱歉，请检查您的账号和密码是否正确");
                            }
                        }else if(mCbShop.isChecked()){
                            if(myUser.getState()==0 ||myUser.getState()==2){
                                Toast("抱歉，请检查您的账号和密码是否正确");
                                return;
                            }
                            if(myUser.getState()==1){
                                Toast("登录成功");
                                SetUtils.saveUser(getApplicationContext(), mEtUser.getText().toString().trim());
                                SetUtils.saveNickName(getApplicationContext(), myUser.getUsername());
                                SetUtils.savePassword(getApplicationContext(), mEtPassword.getText().toString().trim());
                                SetUtils.saveImgHead(getApplicationContext(),myUser.getHeadUrl());
                                SetUtils.saveState(getApplicationContext(),"1");
                                SetUtils.saveId(getApplicationContext(), myUser.getObjectId());
                                SetUtils.saveIsLogin(getApplicationContext(),true);
                                SetUtils.saveIsManager(getApplicationContext(),true);
                                startActivity(new Intent(LoginActivity.this, ShopActivity.class));
                                finish();
                            }
                        }else {
                            if(myUser.getState()==1 ||myUser.getState()==2){
                                Toast("抱歉，请检查您的账号和密码是否正确");
                                return;
                            }
                            Toast("登录成功");
                            SetUtils.saveUser(getApplicationContext(), mEtUser.getText().toString().trim());
                            SetUtils.saveNickName(getApplicationContext(), myUser.getUsername());
                            SetUtils.savePassword(getApplicationContext(), mEtPassword.getText().toString().trim());
                            SetUtils.saveId(getApplicationContext(), myUser.getObjectId());
                            SetUtils.saveImgHead(getApplicationContext(),myUser.getHeadUrl());
                            SetUtils.saveState(getApplicationContext(),"0");
                            SetUtils.saveIsLogin(getApplicationContext(),true);
                            SetUtils.saveIsManager(getApplicationContext(),false);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }else {
                        Toast("登录失败"+e.toString());
                    }
            }
        });
    }
    private void initview() {
        mCbManager = (CheckBox) findViewById(R.id.cb_manager);
        mCbShop = (CheckBox) findViewById(R.id.cb_shop);
        mEtUser = (EditText) findViewById(R.id.et_user);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mTvRegist = (TextView) findViewById(R.id.tv_regist);
        mRtlComfir = (RelativeLayout) findViewById(R.id.rtl_comfirm);
        if(SetUtils.GetUser(getApplicationContext())!=null){
           mEtUser.setText(""+SetUtils.GetUser(getApplicationContext()));
        }
    }
}
