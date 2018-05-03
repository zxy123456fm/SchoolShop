package com.example.agriculture.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.agriculture.BaseActivity;
import com.example.agriculture.GoodDetailActivity;
import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;
import adapter.GoodManagerAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import model.Product;
import utils.SetUtils;
public class GoodManagerActivity extends BaseActivity {
    private ArrayList<Product> mDate=new ArrayList<>();
    private GoodManagerAdapter adapter;
    private ListView mList;
    private TextView mTvAdd;
    private TextView mTvState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_manager);
        initview();
        initdate(true);
        initevent();
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
        adapter.setOnDelgoodLitner(new GoodManagerAdapter.onDelgoodLitner() {
            @Override
            public void Del(int pos) {
                startProgressDialog("加载中...");
                Product good = new Product();
                good.delete(mDate.get(pos).getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        stopProgressDialog();
                        if(e==null){
                            Toast("删除成功");
                            initdate(false);
                        }
                    }
                });
            }
        });
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), PostGoodActivity.class),11);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            initdate(false);
        }
    }
    private void initdate(boolean s) {
        if(s){
            startProgressDialog("加载中...");
        }
        BmobQuery<Product> goodBmobQuery = new BmobQuery<>();
        if(getIntent().getBooleanExtra("shop",false)){
            goodBmobQuery.addWhereEqualTo("ShopId", SetUtils.GetId(getApplicationContext()));
        }
        goodBmobQuery.findObjects(new FindListener<Product>() {
            @Override
            public void done(List<Product> list, BmobException e) {
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
        adapter=new GoodManagerAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
        mTvAdd = (TextView) findViewById(R.id.tv_add);
    }
}
