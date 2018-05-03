package com.example.agriculture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import model.Money;
import model.Product;
import model.ShopCar;
import model.order;
import utils.SetUtils;
import utils.UILUtils;
public class GoodDetailActivity extends BaseActivity {
    private ImageView mImg2;
    private ImageView mImg;
    private ImageView mImg3;
    private TextView mTvName;
    private TextView mTvDetail;
    private RelativeLayout mRtlBuy;
    private TextView mTvPrice;
    private Product mgood;
    private TextView mTvAddress;
    private TextView mTvCollect;
    private TextView mTvCollect1;
    private RelativeLayout mRtlAddCar;
    private RelativeLayout mRtlOrder;
    private RelativeLayout mRtlAddress;
    private TextView mTvAddresss;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        initview();
        initdate();
        initevent();
    }

    private void initevent() {
        findViewById(R.id.tv_praise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PraiseListActivity.class)
                .putExtra("id",mgood.getShopId())
                .putExtra("name",mgood.getName()));
            }
        });
    }

    private void initdate() {
        if(getIntent().getStringExtra("address")!=null){
            mRtlAddress.setVisibility(View.VISIBLE);
            mTvAddresss.setText(""+getIntent().getStringExtra("address"));
        }


        if(getIntent().getSerializableExtra("date")!=null){
            mgood= (Product) getIntent().getSerializableExtra("date");
            mTvDetail.setText(mgood.getDetail());
            mTvName.setText(""+mgood.getName());
            mTvAddress.setText(mgood.getAddress());
            mTvPrice.setText("￥:"+mgood.getPrice());
            UILUtils.displayImageNoAnim(mgood.getImgUrl1(),mImg);
            if(mgood.getImgUrl2()==null){
                mImg2.setVisibility(View.GONE);
            }else {
                mImg2.setVisibility(View.VISIBLE);
                UILUtils.displayImageNoAnim(mgood.getImgUrl2(),mImg2);
            }
            if(mgood.getImgUrl3()==null){
                mImg3.setVisibility(View.GONE);
            }else {
                mImg3.setVisibility(View.VISIBLE);
                UILUtils.displayImageNoAnim(mgood.getImgUrl3(),mImg3);
            }
            if(getIntent().getBooleanExtra("order",false)){
                mRtlOrder.setVisibility(View.GONE);
            }
        }
        /**
         * 下单
         */
        mRtlBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SetUtils.IsLogin(getApplicationContext())){
                    final EditText editText = new EditText(getApplicationContext());
                    editText.setHintTextColor(getResources().getColor(R.color.dark_gray));
                    editText.setHint("请输入您的地址");
                    editText.setTextColor(getResources().getColor(R.color.light_black));
                    AlertDialog.Builder builder = new AlertDialog.Builder(GoodDetailActivity.this);
                    builder.setView(editText) ;
                    builder.setTitle("提示,确认下单购买？");
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {   @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SetOrder(editText.getText().toString().trim());
                    }
                    });
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {   @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();}
                    });
                    builder.create().show();

                }else {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
            }
        });
        /**
         * 购物车
         */
        mRtlAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SetUtils.IsLogin(getApplicationContext())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(GoodDetailActivity.this);
                    builder.setMessage("购买一份，确认添加到购物车？");
                    builder.setTitle("提示");
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {   @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        AddCarr();
                    }
                    });
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {   @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();}
                    });
                    builder.create().show();

                }else {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
            }
        });
    }
    /**
     * 下单
     */
    private void SetOrder(final String address) {
        startProgressDialog("加载中...");
        BmobQuery<Money> query = new BmobQuery<>();
        query.addWhereEqualTo("UserId",""+SetUtils.GetId(getApplicationContext()));
        query.findObjects(new FindListener<Money>() {
            @Override
            public void done(final List<Money> list, BmobException e) {
                if(e==null){
                    if(list.size()==0){
                        stopProgressDialog();
                        Toast("抱歉,您的余额不足");
                    }else if(list.get(0).getMoney()<Double.parseDouble(mgood.getPrice())){
                        stopProgressDialog();
                        Toast("抱歉,您的余额不足");
                    }else {
                        startProgressDialog("加载中...");
                        order order = new order();
                        order.setgoodDetail(mgood.getDetail());
                        order.setgoodName(mgood.getName());
                        order.setAddress(address);
                        order.setShopId(mgood.getShopId());
                        order.setImg1(mgood.getImgUrl1());
                        order.setImg2(mgood.getImgUrl2());
                        order.setgoodTPrice(mgood.getPrice());
                        order.setImg3(mgood.getImgUrl3());
                        order.setUserId(SetUtils.GetId(getApplicationContext()));
                        order.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if(e==null){
                                    Money money = new Money();
                                    money.setMoney(list.get(0).getMoney()-Double.parseDouble(mgood.getPrice()));
                                    money.update(list.get(0).getObjectId(), new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            stopProgressDialog();
                                            if(e==null){
                                                Toast("下单成功");
                                                finish();
                                            }else {
                                                Toast("下单失败,"+e.getMessage());
                                            }
                                        }
                                    });
                                }else {
                                    stopProgressDialog();
                                    Toast("下单失败,"+e.getMessage());
                                }
                            }
                        });
                    }
                }else {
                    stopProgressDialog();
                    Toast("查询失败,"+e.getMessage());
                }
            }
        });

    }
    /**
     * 下单
     */
    private void AddCarr() {
        startProgressDialog("加载中...");
        ShopCar order = new ShopCar();
        order.setgoodDetail(mgood.getDetail());
        order.setgoodName(mgood.getName());
        order.setImg1(mgood.getImgUrl1());
        order.setImg2(mgood.getImgUrl2());
        order.setShopId(mgood.getShopId());
        order.setgoodTPrice(mgood.getPrice());
        order.setImg3(mgood.getImgUrl3());
        order.setUserId(SetUtils.GetId(getApplicationContext()));
        order.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    Toast("添加成功");
                    finish();
                }
            }
        });
    }
    private void initview() {
        mRtlAddress = (RelativeLayout) findViewById(R.id.rtl_address);
        mTvAddresss = (TextView) findViewById(R.id.tv_addresss);
        mRtlOrder = (RelativeLayout) findViewById(R.id.rtl_orders);
        mTvCollect = (TextView) findViewById(R.id.tv_collect);
        mTvCollect1 = (TextView) findViewById(R.id.tv_collect1);
        mRtlAddCar = (RelativeLayout) findViewById(R.id.rtl_add_car);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mTvDetail = (TextView) findViewById(R.id.tv_detail);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mRtlBuy = (RelativeLayout) findViewById(R.id.rtl_buy);
        mImg = (ImageView) findViewById(R.id.img);
        mImg2 = (ImageView) findViewById(R.id.img2);
        mImg3 = (ImageView) findViewById(R.id.img3);
    }
}
