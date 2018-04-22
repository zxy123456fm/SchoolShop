package com.example.agriculture;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MessageCommentAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import model.LeaveMessage;
import model.RecordComment;
import utils.SetUtils;
import utils.UILUtils;
import views.MyListView;

public class PlayRecordDetailActivity extends BaseActivity {
    private LeaveMessage mNews;
    private ArrayList<RecordComment> mComment=new ArrayList<>();
    private TextView mTvNickName;
    private TextView mTvContent;
    private TextView mTvTime;
    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private MyListView mList;
    private EditText mEtText;
    private TextView mTvListState;
    private RelativeLayout mRtlSend;
    private MessageCommentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate_detail);
        initview();
        initdate();
        initCommet();
        initevnet();
    }


    private void initevnet() {

    }


    private void initCommet() {
        BmobQuery<RecordComment> query = new BmobQuery<>();
        query.addWhereEqualTo("RecordId",""+ mNews.getObjectId());
        query.findObjects(new FindListener<RecordComment>() {
            @Override
            public void done(List<RecordComment> list, BmobException e) {
                if(e==null){
                    if(list.size()>0){
                        mTvListState.setVisibility(View.GONE);
                        mList.setVisibility(View.VISIBLE);
                    }
                    mComment.clear();
                    mComment.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        mRtlSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtText.getText().toString().trim().equals("")){
                    Toast("评论不能为空");
                }else {
                    AddComment();
                }
            }
        });
    }

    private void AddComment() {
        startProgressDialog("加载中...");
        RecordComment comment = new RecordComment();
        comment.setText(mEtText.getText().toString().trim());
        comment.setUserName(SetUtils.GetNickName(getApplicationContext()));
        comment.setUserId(SetUtils.GetId(getApplicationContext()));
        comment.setRecordId(mNews.getObjectId());
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    Toast("发送成功");
                    mEtText.setText("");
                    initCommet();
                }else {
                    Toast("发送失败"+e.toString());
                }
            }
        });
    }

    private void initdate() {
        mNews= (LeaveMessage) getIntent().getSerializableExtra("date");
        mTvNickName.setText(""+mNews.getNickName());
        mTvContent.setText(""+mNews.getContent());
        mTvTime.setText(""+mNews.getCreatedAt());


        if(mNews.getImg1()!=null){
            UILUtils.displayImageNoAnim(mNews.getImg1(),mImg1);
        }else {
            mImg1.setVisibility(View.GONE);
        }
        if(mNews.getImg2()!=null){
            UILUtils.displayImageNoAnim(mNews.getImg2(),mImg2);
        }else {
            mImg2.setVisibility(View.GONE);
        }
        if(mNews.getImg3()!=null){
            UILUtils.displayImageNoAnim(mNews.getImg3(),mImg3);
        }else {
            mImg3.setVisibility(View.GONE);
        }
    }
    private void initview() {
        mTvNickName = (TextView) findViewById(R.id.tv_nickname);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mImg1 = (ImageView) findViewById(R.id.img1);
        mImg2 = (ImageView) findViewById(R.id.img2);
        mImg3 = (ImageView) findViewById(R.id.img3);

        mList = (MyListView) findViewById(R.id.list);
        mEtText = (EditText) findViewById(R.id.et_text);
        mTvListState = (TextView) findViewById(R.id.tv_state);
        adapter =new MessageCommentAdapter(getApplicationContext(),mComment);
        mRtlSend = (RelativeLayout) findViewById(R.id.rtl_send);
        mList.setAdapter(adapter);
    }
}
