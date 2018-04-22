package com.example.agriculture.message;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.agriculture.BaseActivity;
import com.example.agriculture.PlayRecordDetailActivity;
import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MyLeaveMessageAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import model.LeaveMessage;
import utils.SetUtils;

/**
 */
public class MyPostLeaveMessageActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    private ArrayList<LeaveMessage> mDate=new ArrayList<>();
    private MyLeaveMessageAdapter adapter;
    private ListView mList;
    private SwipeRefreshLayout mRefresh;
    private RelativeLayout mRtlPost;
    private TextView mTvState;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post_message);
        initview();
        initdate(true);
        initevetn();
    }
    private void initevetn() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), PlayRecordDetailActivity.class).putExtra("date",mDate.get(position)));
            }
        });
        mRtlPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), PostLeaveMessageActivity.class),11);
            }
        });

        adapter.setonDelTimeLineLitner(new MyLeaveMessageAdapter.onDelRecordLitner() {
            @Override
            public void del(final int pos) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPostLeaveMessageActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确认要删除?");
                builder.setPositiveButton(
                        "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startProgressDialog("加载中...");
                                LeaveMessage playRecord = new LeaveMessage();
                                playRecord.delete(mDate.get(pos).getObjectId(), new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        stopProgressDialog();
                                        if(e==null){
                                            Toast("删除成功");
                                            initdate(false);
                                        }else {
                                            Toast("删除失败,"+e.getMessage());
                                        }
                                    }
                                });
                            }
                        });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }
    private void initdate(boolean a) {
        if(a){
            startProgressDialog("加载中...");
        }
        BmobQuery<LeaveMessage> query = new BmobQuery<>();
        query.addWhereEqualTo("UserId",""+ SetUtils.GetId(getApplicationContext()));
        query.order("-createdAt");
        query.findObjects(new FindListener<LeaveMessage>() {
            @Override
            public void done(List<LeaveMessage> list, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    if(list.size()==0){
                        mList.setVisibility(View.GONE);
                        mTvState.setVisibility(View.VISIBLE);
                    }else {
                        mList.setVisibility(View.VISIBLE);
                        mTvState.setVisibility(View.GONE);
                    }
                    mDate.clear();
                    mDate.addAll(list);
                    adapter.notifyDataSetChanged();
                    mRefresh.setRefreshing(false);
                }
            }
        });
    }
    @Override
    public void onRefresh() {
        initdate(true);
    }
    private void initview() {
        mTvState = (TextView) findViewById(R.id.tv_state);
        mRtlPost = (RelativeLayout) findViewById(R.id.rtl_add);
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mList = (ListView) findViewById(R.id.list);
        adapter=new MyLeaveMessageAdapter(getApplicationContext(),mDate);
        mList.setAdapter(adapter);
        mRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            initdate(false);
        }
    }
}
