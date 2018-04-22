package com.example.agriculture.Manager;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.agriculture.BaseActivity;
import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.UserAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import model.User;

public class KeHuInfoActivity extends BaseActivity {
    private UserAdapter adapter;
    private ArrayList<User> mDate=new ArrayList<>();
    private ListView mList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_hu_info);
        intiview();
        inidate();
    }
    private void inidate() {
        startProgressDialog("加载中...");
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("State",0);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                 stopProgressDialog();
                if(e==null){
                      mDate.clear();
                    mDate.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }
        })      ;
    }

    private void intiview() {
        mList = (ListView) findViewById(R.id.list);
        adapter=new UserAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
    }
}
