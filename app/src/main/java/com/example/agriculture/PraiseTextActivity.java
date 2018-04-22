package com.example.agriculture;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gaoguo.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import model.order;

/**
 * Created by Administrator on 2018/4/15/015.
 */

public class PraiseTextActivity extends BaseActivity {
    private order mDate;
    private TextView mTvText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praise_order_text);
        initview();
        initdate();
        initevetn();
    }

    private void initevetn() {
    }

    private void initdate() {
//        mDate= (order) getIntent().getSerializableExtra("date");
        mTvText.setText(""+getIntent().getStringExtra("date"));
    }

    private void initview() {
        mTvText = (TextView) findViewById(R.id.tv_text);
    }
}
