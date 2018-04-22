package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gaoguo.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import adapter.GoodTypeAdapter;
import adapter.ShopWallAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import model.Product;
import model.praiseScore;

/**
 */
public class ShopGoodListActivity extends BaseActivity {
    private ArrayList<String> mBannerDate=new ArrayList<>();
    private ListView mList;
    private GoodTypeAdapter adapter;
    private ArrayList<Product> mDate=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_good_list);
        initviwe();
        intidate();
        intievetn();
    }
    private void intievetn() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), GoodDetailActivity.class).putExtra("date",mDate.get(position)));
            }
        });
    }

    private void intidate() {
        startProgressDialog("加载中...");
        BmobQuery<Product> query = new BmobQuery<>();
        query.addWhereEqualTo("ShopId",getIntent().getStringExtra("id"));
        query.findObjects(new FindListener<Product>() {
            @Override
            public void done(List<Product> list, BmobException e) {
                stopProgressDialog();
                if(e==null) {
                    mDate.clear();
                    mDate.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initviwe() {
        mList = (ListView) findViewById(R.id.list);
        adapter=new GoodTypeAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
    }
}
