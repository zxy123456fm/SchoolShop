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
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import model.Money;
import utils.SetUtils;

public class AddMoneyActivity extends BaseActivity {

    private EditText mEtPrice;
    private RelativeLayout mRtlCz;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        initview();
        initevent();
    }

    private void initevent() {
        mRtlCz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtPrice.getText().toString().trim().equals("")){
                    Toast("请填写金额");
                }else {
                    CzPrice();
                }
            }
        });
    }

    /**
     *
     */
    private void CzPrice() {
        startProgressDialog("加载中...");
        BmobQuery<Money> query = new BmobQuery<>();
        query.addWhereEqualTo("UserId",""+ SetUtils.GetId(getApplicationContext()));
        query.findObjects(new FindListener<Money>() {
            @Override
            public void done(List<Money> list, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    if(list.size()==0){
                        AddUsrPrice();
                    }else {
                        EditUserPrice(list.get(0).getObjectId(),list.get(0).getMoney());
                    }
                }
            }
        });
    }

    /**
     * 向用户增加金额
     * @param objectId
     */
    private void EditUserPrice(String objectId,double price) {
        startProgressDialog("加载中...");
        Money money = new Money();
        money.setMoney(Double.valueOf(mEtPrice.getText().toString())+price);
        money.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                stopProgressDialog();
                if(e==null){
                    Toast("充值成功");
                    setResult(1);
                    finish();
                }
            }
        });
    }

    /**
     * 新增用的金额数据
     */
    private void AddUsrPrice() {
        startProgressDialog("加载中...");
        Money money = new Money();
        money.setUserId(SetUtils.GetId(getApplicationContext()));
        money.setMoney(Double.valueOf(mEtPrice.getText().toString().trim()));
        money.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    Toast("充值成功");
                    setResult(1);
                    finish();
                }
            }
        });
    }

    private void initview() {
        mEtPrice = (EditText) findViewById(R.id.et_price);
        mRtlCz = (RelativeLayout) findViewById(R.id.rtl_chognzhi);
    }
}
