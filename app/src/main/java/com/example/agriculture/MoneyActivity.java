package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import model.Money;
import utils.SetUtils;

public class MoneyActivity extends BaseActivity {
    private RelativeLayout mRtlCz;
    private TextView mTvYue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        initview();
        initdate(true);
        initevent();
    }
    private void initevent() {
        /**
         * 充值
         */
        mRtlCz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddMoneyActivity.class);
                startActivityForResult(intent,11);
            }
        });
    }
    private void initdate(boolean IsResh) {
        if(IsResh){
            startProgressDialog("加载中...");
        }
        BmobQuery<Money> query = new BmobQuery<>();
        query.addWhereEqualTo("UserId",""+ SetUtils.GetId(getApplicationContext()));
        query.findObjects(new FindListener<Money>() {
            @Override
            public void done(List<Money> list, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    if(list.size()==0){
                        mTvYue.setText("0.00元");
                    }else {
                        mTvYue.setText(""+list.get(0).getMoney()+"元");
                    }
                }
            }
        });
    }

    private void initview() {
        mRtlCz = (RelativeLayout) findViewById(R.id.rtl_cz);
        mTvYue = (TextView) findViewById(R.id.tv_yue);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            initdate(false);
        }
    }
}
