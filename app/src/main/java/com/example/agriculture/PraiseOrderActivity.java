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
import model.order;
import model.praiseScore;

/**
 * Created by Administrator on 2018/4/15/015.
 */

public class PraiseOrderActivity extends BaseActivity {
    private order mDate;
    private EditText mEtText;
    private RelativeLayout mRtlComfirm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praise_order);
        initview();
        initdate();
        initevetn();
    }

    private void initevetn() {
        mRtlComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtText.getText().toString().trim().equals("")){
                    Toast("请输入您的评价内容");
                }else if(Integer.parseInt(mEtText.getText().toString().trim())>10){
                    Toast("评分不能大于10分");
                }else {
                    startProgressDialog("加载中...");
                    order order = new order();
                    order.setState(3);
                    order.setShopId(mDate.getShopId());
                    order.setAddress(mDate.getAddress());
                    order.setFoodDetail(mDate.getFoodDetail());
                    order.setFoodName(mDate.getFoodName());
                    order.setPraise(mEtText.getText().toString().trim());
                    order.setFoodTPrice(mDate.getFoodTPrice());
                    order.setImg1(mDate.getImg1());
                    order.setImg2(mDate.getImg2());
                    order.setImg3(mDate.getImg3());
                    order.update(mDate.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                BmobQuery<praiseScore> query = new BmobQuery<>();
                                query.addWhereEqualTo("ShopId",""+mDate.getShopId());
                                query.findObjects(new FindListener<praiseScore>() {
                                    @Override
                                    public void done(List<praiseScore> list, BmobException e) {
                                        if(e==null) {
                                            if(list.size()==0){
                                                praiseScore score = new praiseScore();
                                                score.setScore(mEtText.getText().toString().trim());
                                                score.setImgUrl(mDate.getImg1());
                                                score.setShopId(mDate.getShopId());
                                                score.save(new SaveListener<String>() {
                                                    @Override
                                                    public void done(String s, BmobException e) {
                                                        stopProgressDialog();
                                                        Toast("评分成功");
                                                        setResult(1);
                                                        finish();
                                                    }
                                                });
                                            }else {
                                                praiseScore score = new praiseScore();
                                                float socreCu=Integer.parseInt(mEtText.getText().toString().trim());
                                                float score1 = Integer.parseInt(list.get(0).getScore());
                                                float scores=(socreCu+score1)/2;
                                                score.setScore(scores+"");
                                                score.setImgUrl(mDate.getImg1());
                                                score.setShopId(mDate.getShopId());
                                                score.update(list.get(0).getObjectId(), new UpdateListener() {
                                                    @Override
                                                    public void done(BmobException e) {
                                                        stopProgressDialog();
                                                        Toast("评分成功");
                                                        setResult(1);
                                                        finish();
                                                    }
                                                });
                                            }
                                        } else {
                                            stopProgressDialog();
                                            Toast("操作失败");
                                        }
                                    }
                                });
                            }else {
                                stopProgressDialog();
                                Toast("评价失败,"+e.getMessage());
                            }
                        }
                    });
                }
            }
        });
    }

    private void initdate() {
        mDate= (order) getIntent().getSerializableExtra("date");

    }

    private void initview() {
        mEtText = (EditText) findViewById(R.id.et_score);
        mRtlComfirm = (RelativeLayout) findViewById(R.id.rtl_comfirm);
    }
}
