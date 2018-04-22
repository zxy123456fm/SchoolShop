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

import adapter.ManagerOrderAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import model.Product;
import model.order;
import utils.SetUtils;

public class ManagerOrderActivity extends BaseActivity {
    private ManagerOrderAdapter adapter;
    private ArrayList<order> mDate=new ArrayList<>();
    private ListView mList;
    private TextView mTvState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_order);
        initview();
        initdate();
        initevent();
    }
    private void initevent() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mDate.get(position).isShopCar()){
                    startActivity(new Intent(getApplicationContext(),ShopCarOrderDetailActivity.class).putExtra("Id",""+mDate.get(position).getObjectId()).putExtra("address",mDate.get(position).getAddress()).putExtra("price",mDate.get(position).getFoodTPrice()));
                }else {
                    Product food = new Product();
                    food.setName(mDate.get(position).getFoodName());
                    food.setDetail(mDate.get(position).getFoodDetail());
                    food.setAddress(mDate.get(position).getFoodTAddress());
                    food.setPrice(mDate.get(position).getFoodTPrice());
                    food.setImgUrl1(mDate.get(position).getImg1());
                    food.setImgUrl2(mDate.get(position).getImg2());
                    food.setImgUrl3(mDate.get(position).getImg3());
                    startActivity(new Intent(getApplicationContext(),GoodDetailActivity.class).putExtra("order",true).putExtra("date",food).putExtra("address",mDate.get(position).getAddress()));
                }
            }
        });
        adapter.setOnDelOrderLitner(new ManagerOrderAdapter.onDelOrderLitner() {
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
                            initdate();
                        }
                    }
                });
            }
        });
        adapter.setOnSendGoodLitner(new ManagerOrderAdapter.onSendGoodLitner() {
            @Override
            public void Send(final int pos) {
                if(mDate.get(pos).getState()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ManagerOrderActivity.this);
                    builder.setMessage("确认要发货？");
                    builder.setTitle("提示");
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {   @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();}
                    });
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {   @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SendGoods(pos);
                        dialog.dismiss();
                    }
                    });
                    builder.create().show();
                }else if(mDate.get(pos).getState()==3){
//                    startActivityForResult(new Intent(getApplicationContext(),PraiseTextActivity.class)
//                            .putExtra("date",mDate.get(pos).getPraise()),11);
                }
            }
        });
    }
    private void SendGoods(int pos) {
        startProgressDialog("加载中...");
        order order = new order();
        order.setState(1);
        order.setShopCar(mDate.get(pos).isShopCar());
        order.update(mDate.get(pos).getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                    Toast("发货成功");
                 initdate();
            }
        });
    }

    private void initdate() {
        startProgressDialog("加载中...");
        BmobQuery<order> query = new BmobQuery<>();
        query.addWhereEqualTo("ShopId",""+ SetUtils.GetId(getApplicationContext()));
        query.order("-createdAt");
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
                        mList.setVisibility(View.VISIBLE);
                        mTvState.setVisibility(View.GONE);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void initview() {
        mTvState = (TextView) findViewById(R.id.tv_state);
        mList = (ListView) findViewById(R.id.list);
        adapter=new ManagerOrderAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
    }
}
