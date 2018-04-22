package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.ShopWallAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import model.praiseScore;

/**
 * Created by Administrator on 2018/4/20/020.
 */

public class ShopWallActivity extends BaseActivity {

    private ListView mList;
    private ShopWallAdapter adapter;
    private ArrayList<praiseScore> mDate=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_wall);
        initviwe();
        intidate();
        intievetn();
    }
    private void intievetn() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),ShopGoodListActivity.class)
                .putExtra("id",mDate.get(position).getShopId()));
            }
        });
    }

    private void intidate() {
        startProgressDialog("加载中...");
        BmobQuery<praiseScore> query = new BmobQuery<>();
        query.order("Score");
        query.findObjects(new FindListener<praiseScore>() {
            @Override
            public void done(List<praiseScore> list, BmobException e) {
                stopProgressDialog();
                mDate.clear();
                mDate.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initviwe() {
        mList = (ListView) findViewById(R.id.list);
        adapter=new ShopWallAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
    }
}
