package com.example.agriculture;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gaoguo.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import model.Message;

public class AddMessageActivity extends BaseActivity {

    private EditText mEtText;
    private RelativeLayout mRtlAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        initview();
        initevnet();
    }

    private void initevnet() {
        mRtlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEtText.getText().toString().trim().equals("")){
                        Toast("请输入完整");
                }else {
                    Add();
                }
            }
        });
    }
    /**
     * 发布消息
     */
    private void Add() {
        startProgressDialog("加载中...");
        Message message = new Message();
        message.setText(mEtText.getText().toString().trim());
        message.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                 stopProgressDialog();
                if(e==null){
                     Toast("发布成功");
                    setResult(1);
                    finish();
                }
            }
        })      ;
    }

    private void initview() {
        mEtText = (EditText) findViewById(R.id.et_text);
        mRtlAdd = (RelativeLayout) findViewById(R.id.rtl_add);
    }
}
