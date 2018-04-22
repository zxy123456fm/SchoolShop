package com.example.agriculture;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.gaoguo.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/12/1.
 */
public class UpdatePasswordAcitvity extends BaseActivity {

    private RelativeLayout mRtlUpdate;
    private EditText mEtOldPassword;
    private EditText mEtNewPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        initview();
        initevent();
    }

    private void initevent() {
        mRtlUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtNewPassword.getText().toString().trim().equals("") ||mEtOldPassword.getText().toString().trim().equals("")){
                    Toast("请输入完整");
                }else {
                    UpdatePassword();
                }
            }
        });
    }
    /**
     * 更新密码
     */
    private void UpdatePassword() {
        BmobUser.updateCurrentUserPassword(mEtOldPassword.getText().toString().trim(), mEtNewPassword.getText().toString().trim(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                  if(e==null){
                     Toast("密码修改成功");
                      finish();
                  }else if(e.getErrorCode()==210){
                      Toast("旧密码错误，请重新输入");
                  }else {
                      Toast("修改密码失败，请稍后重试"+e.toString());
                  }
            }
        });
    }
    private void initview() {
        mRtlUpdate = (RelativeLayout) findViewById(R.id.rtl_update);
        mEtOldPassword = (EditText) findViewById(R.id.et_old_password);
        mEtNewPassword = (EditText) findViewById(R.id.et_new_password);
    }
}
