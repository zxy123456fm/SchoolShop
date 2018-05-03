package com.example.agriculture;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.ShopCarOrderAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import model.ShopCarOrder;
public class ShopCarOrderDetailActivity extends BaseActivity {
    private ShopCarOrderAdapter adapter;
    private ArrayList<ShopCarOrder> mDate=new ArrayList<>();
    private ListView mList;
    private TextView mTvPrice;
    private TextView mTvAddress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car_order_detail);
        initviwe();
        initdate();
    }
    private float allPrice;
    private void getAllPrice() {
        for (int i=0;i<mDate.size();i++){
            allPrice=allPrice+Float.parseFloat(mDate.get(i).getgoodTPrice());
        }
        mTvPrice.setText("总价格:"+getIntent().getStringExtra("price")+"元");
    }
    private void initdate() {
        if(getIntent().getStringExtra("address")!=null){
            mTvAddress.setText("地址"+getIntent().getStringExtra("address"));
        }
        startProgressDialog("加载中...");
        BmobQuery<ShopCarOrder> query = new BmobQuery<>();
        query.addWhereEqualTo("OrderId",""+getIntent().getStringExtra("Id"));
        query.findObjects(new FindListener<ShopCarOrder>() {
            @Override
            public void done(List<ShopCarOrder> list, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    mDate.clear();
                    mDate.addAll(list);
                    adapter.notifyDataSetChanged();
                    getAllPrice();
                }
            }
        });
    }
    private void initviwe() {
        mList = (ListView) findViewById(R.id.list);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        adapter=new ShopCarOrderAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
    }
}
