package com.example.agriculture.Shop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.agriculture.BaseActivity;
import com.example.gaoguo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import adapter.GdPicAdapter;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import model.Product;
import utils.SetUtils;
import utils.UriUtils;
import utils.Utis;
import views.StationaryGridview;
public class PostGoodActivity extends BaseActivity {
    private final int REQUSET_SELECT_PHOTOS=231;
    protected static final int CHOOSE_PICTURE = 101;
    private RelativeLayout mRtlComplite;
    private ArrayList<String> mPhotos = new ArrayList<>();
    private ArrayList<File> mFilePhotos = new ArrayList<File>();
    private ArrayList<String> mFileName = new ArrayList<String>();
    private StationaryGridview mGdPic;
    private GdPicAdapter gdPicAdapter;
    private String file_path= Utis.getAppFolder();
    private String imgName;
    private EditText mEtDetail;
    private EditText mEtName;
    private EditText mEtPrice;
    private Spinner mSpType;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private String mType;
    private CheckBox mCbShow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_food);
        initview();
        initSpType();
        initUploadPic();
        initevent();
    }
    private void initSpType() {
        list.clear();
        list.add("女士鞋服专区");
        list.add("男士鞋服专区");
        list.add("化妆品专区");
        list.add("电子产品专区");
        list.add("其他专区");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mSpType.setAdapter(adapter);
        mSpType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mType=list.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initevent() {
        findViewById(R.id.rtl_comfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtDetail.getText().toString().equals("")||mEtName.getText().toString().trim().equals("")||
                        mEtPrice.getText().toString().trim().equals("")||mType.toString().trim().equals("")){
                    Toast("请填写完整");
                    return;
                }
                switch (mPhotos.size()){
                    case 0:
                        Post0Photo();
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
     * f发布不带图片
     */
    private void Post0Photo() {
        startProgressDialog("发布中...");
        Product food = new Product();
        food.setName(mEtName.getText().toString().trim());
        food.setDetail(mEtDetail.getText().toString().trim());
        food.setShopId(SetUtils.GetId(getApplicationContext()));
        food.setType(mType);
        if(mCbShow.isChecked()){
            food.setHomeShow(true);
        }else {
            food.setHomeShow(false);
        }
        food.setPrice(mEtPrice.getText().toString().trim());
        food.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    Toast("添加成功");
                    setResult(1);
                    finish();
                }
            }
        });
    }
    /**
     * 发布动态一个图片
     */
    private void PostOnePhone() {
        startProgressDialog("加载中...");
        final BmobFile bmobFile=new BmobFile(new File(mPhotos.get(0)));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                stopProgressDialog();
                if(e==null){
                    startProgressDialog("发布中...");
                    Product food = new Product();
                    food.setName(mEtName.getText().toString().trim());
                    food.setDetail(mEtDetail.getText().toString().trim());
                    food.setShopId(SetUtils.GetId(getApplicationContext()));
                    if(mCbShow.isChecked()){
                        food.setHomeShow(true);
                    }else {
                        food.setHomeShow(false);
                    }
                    food.setType(mType);
                    food.setImgUrl1(bmobFile.getFileUrl());
                    food.setPrice(mEtPrice.getText().toString().trim());
                    food.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            stopProgressDialog();
                            if(e==null){
                                Toast("添加成功");
                                setResult(1);
                                finish();
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
                                startProgressDialog("发布中...");
                                Product food = new Product();
                                food.setName(mEtName.getText().toString().trim());
                                if(mCbShow.isChecked()){
                                    food.setHomeShow(true);
                                }else {
                                    food.setHomeShow(false);
                                }
                                food.setDetail(mEtDetail.getText().toString().trim());
                                food.setType(mType);
                                food.setImgUrl1(Img1);
                                food.setShopId(SetUtils.GetId(getApplicationContext()));
                                food.setImgUrl2(Img2);
                                food.setPrice(mEtPrice.getText().toString().trim());
                                food.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        stopProgressDialog();
                                        if(e==null){
                                            Toast("添加成功");
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
                                            startProgressDialog("发布中...");
                                            Product food = new Product();
                                            if(mCbShow.isChecked()){
                                                food.setHomeShow(true);
                                            }else {
                                                food.setHomeShow(false);
                                            }
                                            food.setName(mEtName.getText().toString().trim());
                                            food.setDetail(mEtDetail.getText().toString().trim());
                                            food.setType(mType);
                                            food.setImgUrl1(Img1);
                                            food.setShopId(SetUtils.GetId(getApplicationContext()));
                                            food.setImgUrl2(Img2);
                                            food.setImgUrl3(Img3);
                                            food.setPrice(mEtPrice.getText().toString().trim());
                                            food.save(new SaveListener<String>() {
                                                @Override
                                                public void done(String s, BmobException e) {
                                                    stopProgressDialog();
                                                    if(e==null){
                                                        Toast("添加成功");
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
    private void initview() {
        mCbShow = (CheckBox) findViewById(R.id.cb_show);
        mGdPic = (StationaryGridview) findViewById(R.id.gd_pic);
        mEtDetail = (EditText) findViewById(R.id.et_detail);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPrice = (EditText) findViewById(R.id.et_price);
        mSpType = (Spinner) findViewById(R.id.sp_type);
    }
    /**
     * 初始化上传图片
     */
    private void initUploadPic() {
        gdPicAdapter = new GdPicAdapter(this, mPhotos);
        mGdPic.setAdapter(gdPicAdapter);
        gdPicAdapter.setOnSelectListner(new GdPicAdapter.OnClickSelectListner() {
            @Override
            public void onshowImgPop() {
                if (ContextCompat.checkSelfPermission(PostGoodActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
                {  //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(PostGoodActivity.this,
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