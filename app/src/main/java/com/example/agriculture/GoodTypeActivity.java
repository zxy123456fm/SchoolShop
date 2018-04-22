package com.example.agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.GoodTypeAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import model.Product;

public class GoodTypeActivity extends BaseActivity {
    private GoodTypeAdapter adapter;
    private ArrayList<Product> mDate=new ArrayList<>();
    private TextView mTvTitle;
    private ListView mList;
    private String type="";
    private TextView mTvState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);
        initview();
        initdate();
        initevent();
    }
    private void initview() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvState = (TextView) findViewById(R.id.tv_state);
        mList = (ListView) findViewById(R.id.list);
        adapter=new GoodTypeAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
    }
    private void initevent() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),GoodDetailActivity.class);
                intent.putExtra("date",mDate.get(position));
                startActivity(intent);
            }
        });
    }
    private void initdate() {
        type=getIntent().getStringExtra("type");
        mTvTitle.setText(""+type);
        startProgressDialog("加载中..");
        BmobQuery<Product> query = new BmobQuery<>();
        query.addWhereEqualTo("Type",""+type);
        query.findObjects(new FindListener<Product>() {
            @Override
            public void done(List<Product> list, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    if(list.size()>0){
                        mList.setVisibility(View.VISIBLE);
                        mTvState.setVisibility(View.GONE);
                    }
                    mDate.clear();
                    mDate.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
