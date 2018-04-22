package com.example.agriculture;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.gaoguo.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import model.User;
import utils.SetUtils;

public class UserInfoActivity extends BaseActivity {
    private RelativeLayout mRtlComfirm;
    private EditText mEtAddress;
    private EditText mEtPhone;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        initview();
        initdate();
        initevent();
    }
    private void initdate() {
        startProgressDialog("加载中...");
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId",""+SetUtils.GetId(getApplicationContext()));
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    if(list.get(0).getAddress()==null){
                          mEtAddress.setText("暂未设置");
                    }else {
                        mEtAddress.setText(""+list.get(0).getAddress());
                    }
                    if(list.get(0).getPhone()==null){
                        mEtPhone.setText("暂未设置");
                    }else {
                        mEtPhone.setText(""+list.get(0).getPhone());
                    }
                }
            }
        });
    }
    private void initview() {
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtAddress = (EditText) findViewById(R.id.et_address);
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
                if (mEtAddress.getText().toString().trim().equals("")|| mEtPhone.getText().toString().trim().equals("")) {
                    Toast("请填写完整");
                }else {
                    Regist();
                }
            }

        });
    }
    private void Regist() {
        startProgressDialog("加载中...");
        final User myUser = new User();
        myUser.setPhone(mEtPhone.getText().toString().trim());
        myUser.setAddress(mEtAddress.getText().toString().trim());
        myUser.update(SetUtils.GetId(getApplicationContext()), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                stopProgressDialog();
                if(e==null){
                    Toast("修改成功");
                    finish();
                }
            }
        });
    }
}

            ;
