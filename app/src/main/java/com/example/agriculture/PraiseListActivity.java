package com.example.agriculture;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.PraiseAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import model.order;

/**
 * Created by Administrator on 2018/4/15/015.
 */

public class PraiseListActivity extends BaseActivity {
    private ListView mList;
    private TextView mTvState;
    private PraiseAdapter adapter;
    private ArrayList<order> mDate=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praise_list);
        initview();
        initdate();
        initevent();
    }
    private void initevent() {

    }

    private void initdate() {
        startProgressDialog("加载中...");
        BmobQuery<order> query = new BmobQuery<>();
        query.addWhereEqualTo("ShopId",getIntent().getStringExtra("id"));
        query.findObjects(new FindListener<order>() {
            @Override
            public void done(List<order> list, BmobException e) {
                    stopProgressDialog();
                    if(e==null){
                        mDate.clear();
                        for(order date:list){
                            if(date.getgoodName().equals(getIntent().getStringExtra("name"))){
                                mDate.add(date);
                            }
                        }
                        if(mDate.size()==0){
                            mTvState.setVisibility(View.VISIBLE);
                            mList.setVisibility(View.GONE);
                        }else {
                            mTvState.setVisibility(View.GONE);
                            mList.setVisibility(View.VISIBLE);
                        }
                        adapter.notifyDataSetChanged();
                    }else {
                        Toast("查询失败,"+e.getMessage());
                    }
            }
        });

    }

    private void initview() {
        mList = (ListView) findViewById(R.id.list);
        mTvState = (TextView) findViewById(R.id.tv_state);
        adapter=new PraiseAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
    }
}
