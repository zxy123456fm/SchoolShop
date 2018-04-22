package com.example.agriculture.message;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.agriculture.BaseActivity;
import com.example.gaoguo.R;

import java.io.File;
import java.util.ArrayList;

import adapter.GdPicAdapter;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import model.LeaveMessage;
import utils.SetUtils;
import utils.UriUtils;
import views.StationaryGridview;

public class PostLeaveMessageActivity extends BaseActivity {
    private ArrayList<String> mPhotos = new ArrayList<>();
    private StationaryGridview mGdPic;
    private GdPicAdapter gdPicAdapter;
    private final int REQUSET_SELECT_PHOTOS=231;
    protected static final int CHOOSE_PICTURE = 101;
    private String imgName;
    private ArrayList<String> mFileName = new ArrayList<String>();
    private EditText mEtContent;
    private RelativeLayout mRtlAdd;
    private TextView mTvAddress;
    private EditText mEtTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_messagess);
        initview();
        initUploadPic();
        inidate();
        initevent();
    }
    private void initUploadPic() {
        gdPicAdapter = new GdPicAdapter(this, mPhotos);
        mGdPic.setAdapter(gdPicAdapter);
        gdPicAdapter.setOnSelectListner(new GdPicAdapter.OnClickSelectListner() {
            @Override
            public void onshowImgPop() {
                if (ContextCompat.checkSelfPermission(PostLeaveMessageActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
                {  //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(PostLeaveMessageActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUSET_SELECT_PHOTOS);
                }else { //权限已经被授予，在这里直接写要执行的相应方法即可
                    Log.i("点击选择", "选择");
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,CHOOSE_PICTURE);
                }
            }
        });
    }
    private int type;
    private void inidate() {
        type=getIntent().getIntExtra("type",0);
    }
    private void initevent() {
        findViewById(R.id.rtl_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtContent.getText().toString().trim().equals("")||mEtTitle.getText().toString().trim().equals("")){
                    Toast("请填写完整");
                    return;
                }
                switch (mPhotos.size()){
                    case 0:
                        Add();
                        break;
                    case 1:
                        PostOnePhone();
                        break;
                    case 2:
                        Post2ePhone();
                        break;
                    case 3:
                        Post3Phone();
                        break;
                }
            }
        });
    }
    /**
     * 发布3张图片
     */
    private void Post3Phone() {
        startProgressDialog("加载中...");
        final BmobFile bmobFile=new BmobFile(new File(mPhotos.get(0)));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                stopProgressDialog();
                if(e==null){
                    final String Img1 = bmobFile.getFileUrl();  //第一张
                    startProgressDialog("加载中...");
                    final BmobFile bmobFile=new BmobFile(new File(mPhotos.get(1)));
                    bmobFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            stopProgressDialog();
                            if(e==null){
                                final String Img2 = bmobFile.getFileUrl();  //第2张
                                startProgressDialog("加载中...");
                                final BmobFile bmobFile=new BmobFile(new File(mPhotos.get(2)));
                                bmobFile.uploadblock(new UploadFileListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        stopProgressDialog();
                                        if(e==null){
                                            final String Img3 = bmobFile.getFileUrl();  //第2张
                                            startProgressDialog("加载中...");
                                            LeaveMessage playRecord = new LeaveMessage();
                                            playRecord.setTitle(mEtTitle.getText().toString().trim());
                                            playRecord.setContent(mEtContent.getText().toString().trim());
                                            playRecord.setUserId(SetUtils.GetId(getApplicationContext()));
                                            playRecord.setNickName(SetUtils.GetNickName(getApplicationContext()));
                                            playRecord.setImg1(Img1);
                                            playRecord.setImg2(Img2);
                                            playRecord.setImg3(Img3);
                                            playRecord.save(new SaveListener<String>() {
                                                @Override
                                                public void done(String s, BmobException e) {
                                                    if(e==null){
                                                        Toast("发布成功");
                                                        setResult(1);
                                                        finish();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }
    /**
     * 发布两张图片
     */
    private void Post2ePhone() {
        startProgressDialog("加载中...");
        final BmobFile bmobFile=new BmobFile(new File(mPhotos.get(0)));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                stopProgressDialog();
                if(e==null){
                    final String Img1 = bmobFile.getFileUrl();  //第一张
                    startProgressDialog("加载中...");
                    final BmobFile bmobFile=new BmobFile(new File(mPhotos.get(1)));
                    bmobFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            stopProgressDialog();
                            if(e==null){
                                final String Img2 = bmobFile.getFileUrl();  //第2张
                                startProgressDialog("加载中...");
                                LeaveMessage playRecord = new LeaveMessage();
                                playRecord.setContent(mEtContent.getText().toString().trim());
                                playRecord.setUserId(SetUtils.GetId(getApplicationContext()));
                                playRecord.setTitle(mEtTitle.getText().toString().trim());
                                playRecord.setNickName(SetUtils.GetNickName(getApplicationContext()));
                                playRecord.setImg1(Img1);
                                playRecord.setImg2(Img2);
                                playRecord.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        stopProgressDialog();
                                        if(e==null){
                                            Toast("发布成功");
                                            setResult(1);
                                            finish();
                                        }
                                    }
                                });
                            }
                        }
                    });

                }
            }
        });
    }
    /**
     * 发布一个图片
     */
    private void PostOnePhone() {
        startProgressDialog("加载中...");
        final BmobFile bmobFile=new BmobFile(new File(mPhotos.get(0)));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                stopProgressDialog();
                if(e==null){
                    startProgressDialog("加载中...");
                    LeaveMessage playRecord = new LeaveMessage();
                    playRecord.setContent(mEtContent.getText().toString().trim());
                    playRecord.setUserId(SetUtils.GetId(getApplicationContext()));
                    playRecord.setTitle(mEtTitle.getText().toString().trim());
                    playRecord.setNickName(SetUtils.GetNickName(getApplicationContext()));
                    playRecord.setImg1(bmobFile.getFileUrl());
                    playRecord.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            stopProgressDialog();
                            if(e==null){
                                Toast("发布成功");
                                setResult(1);
                                finish();
                            }
                        }
                    });
                }else {
                    Toast(e.toString());
                }
            }
        });
    }
    /**
     * tianj ji添加
     */
    private void Add() {
        startProgressDialog("加载中...");
        LeaveMessage playRecord = new LeaveMessage();
        playRecord.setContent(mEtContent.getText().toString().trim());
        playRecord.setUserId(SetUtils.GetId(getApplicationContext()));
        playRecord.setTitle(mEtTitle.getText().toString().trim());
        playRecord.setNickName(SetUtils.GetNickName(getApplicationContext()));
        playRecord.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    Toast("发布成功");
                    setResult(1);
                    finish();
                }
            }
        });
    }
    private void initview() {
        mGdPic = (StationaryGridview) findViewById(R.id.gd_pic);
        mEtContent = (EditText) findViewById(R.id.et_content);
        mRtlAdd = (RelativeLayout) findViewById(R.id.rtl_add);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mEtTitle = (EditText) findViewById(R.id.et_title);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUSET_SELECT_PHOTOS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,CHOOSE_PICTURE);
            } else {
                Toast("权限被取消");
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_PICTURE) {
            if (data != null) {
                Uri uri = data.getData(); // 得到Uri
//                Bitmap bitmap = Utis.ImageSizeCompress(getApplicationContext(), uri);
                String fPath = UriUtils.getPath(this, uri);
                if (resultCode == -1) {
                    try {
                        imgName =getIntent().getStringExtra("appid")+"_"+ new File(fPath).getName();
                        mFileName.add(imgName);
                    } catch (Exception e) {
                    };
//                    final String compressImage = Utis.compressImage(fPath, file_path+"/"+imgName, 30);
                    Log.e("压缩前的图片路径",""+fPath);
//                    Log.e("压缩后的图片路径",""+compressImage);
//                        mPhotos.add(compressImage);
                    mPhotos.add(fPath);
                    gdPicAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
