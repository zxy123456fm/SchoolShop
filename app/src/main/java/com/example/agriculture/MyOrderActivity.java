package com.example.agriculture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.OrderAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import model.Product;
import model.order;
import utils.SetUtils;

public class MyOrderActivity extends BaseActivity {
    private OrderAdapter adapter;
    private ArrayList<order> mDate=new ArrayList<>();
    private ListView mList;
    private TextView mTvState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        initview();
        initdate(true);
        initevent();
    }
    private void initevent() {
        /**
         * 确认收货
         */
        adapter.setOnGetGoodsLitner(new OrderAdapter.onGetGoodsLitner() {
            @Override
            public void getGood(final int pos) {
                if(mDate.get(pos).getState()==1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyOrderActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("是否确认收货?");
                    builder.setPositiveButton(
                            "确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    GetGood(pos);
                                    dialog.dismiss();
                                }
                            });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }else if(mDate.get(pos).getState()==2){
                    //评价
                    startActivityForResult(new Intent(getApplicationContext(),PraiseOrderActivity.class)
                            .putExtra("date",mDate.get(pos)),11);
                }else if(mDate.get(pos).getState()==3){
                    //评价
//                    startActivityForResult(new Intent(getApplicationContext(),PraiseTextActivity.class)
//                            .putExtra("date",mDate.get(pos).getPraise()),11);
                }
            }
        });


        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mDate.get(position).isShopCar()){
                    startActivity(new Intent(getApplicationContext(),ShopCarOrderDetailActivity.class).putExtra("Id",""+mDate.get(position).getObjectId()).putExtra("price",mDate.get(position).getFoodTPrice()));
                }else {
                    Product food = new Product();
                    food.setName(mDate.get(position).getFoodName());
                    food.setDetail(mDate.get(position).getFoodDetail());
                    food.setAddress(mDate.get(position).getFoodTAddress());
                    food.setPrice(mDate.get(position).getFoodTPrice());
                    food.setImgUrl1(mDate.get(position).getImg1());
                    food.setImgUrl2(mDate.get(position).getImg2());
                    food.setImgUrl3(mDate.get(position).getImg3());
                    startActivity(new Intent(getApplicationContext(),GoodDetailActivity.class).putExtra("order",true).putExtra("date",food));
                }
            }
        });
        adapter.setOnDelOrderLitner(new OrderAdapter.onDelOrderLitner() {
            @Override
            public void del(int pos) {
                startProgressDialog("加载中...");
                order order = new order();
                order.delete(mDate.get(pos).getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                          stopProgressDialog();
                        if(e==null){
                            Toast("取消成功");
                            initdate(true);
                        }
                    }
                });
            }
        });
    }

    /**
     * 确认收货
     * @param pos
     */
    private void GetGood(int pos) {
        startProgressDialog("加载中...");
        order order = new order();
        order.setState(2);
        order.update(mDate.get(pos).getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                 stopProgressDialog();
                if(e==null){
                      Toast("确认收货成功");
                    initdate(true);
                }
            }
        })      ;
    }

    private void initdate(boolean a) {
        if(a){
            startProgressDialog("加载中...");
        }
        BmobQuery<order> query = new BmobQuery<>();
        query.order("-createdAt");
        query.addWhereEqualTo("UserId", SetUtils.GetId(getApplicationContext()));
        query.findObjects(new FindListener<order>() {
            @Override
            public void done(List<order> list, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    mDate.clear();
                    mDate.addAll(list);
                    if(list.size()==0){
                        mTvState.setVisibility(View.VISIBLE);
                        mList.setVisibility(View.GONE);
                    }else {
                        mTvState.setVisibility(View.GONE);
                        mList.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void initview() {
        mList = (ListView) findViewById(R.id.list);
        mTvState = (TextView) findViewById(R.id.tv_state);
        adapter=new OrderAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            initdate(false);
        }
    }
}
