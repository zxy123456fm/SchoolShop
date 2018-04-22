package com.example.agriculture.Shop;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.agriculture.AddMessageActivity;
import com.example.agriculture.BaseActivity;
import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MessageAdapter;
import adapter.OrderAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import model.Message;
import model.Product;
import model.order;
import utils.SetUtils;
public class MessageActivity extends BaseActivity {
    private MessageAdapter adapter;
    private ArrayList<Message> mDate=new ArrayList<>();
    private ListView mList;
    private TextView mTvAdd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initview();
        initdate(true);
        initevent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            initdate(false);
        }
    }
    private void initevent() {
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivityForResult(new Intent(getApplicationContext(), AddMessageActivity.class),1);
            }
        });
        adapter.setOnDelOrderLitner(new MessageAdapter.onDelOrderLitner() {
            @Override
            public void del(int pos) {
                startProgressDialog("加载中...");
                Message order = new Message();
                order.delete(mDate.get(pos).getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                          stopProgressDialog();
                        if(e==null){
                            Toast("删除成功");
                            initdate(true);
                        }
                    }
                });
            }
        });
    }
    private void initdate(boolean a) {
        if(a){
            startProgressDialog("加载中...");
        }
        BmobQuery<Message> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Message>() {
            @Override
            public void done(List<Message> list, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    mDate.clear();
                    mDate.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void initview() {
        mList = (ListView) findViewById(R.id.list);
        mTvAdd = (TextView) findViewById(R.id.tv_add);
        adapter=new MessageAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
    }
}
