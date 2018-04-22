package com.example.agriculture;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;

import fragment.CommunicateFragment;
import fragment.GoodFragment;
import fragment.GoodTypeListFragment;
import fragment.MineFragment;
import fragment.ShopCarFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mVpTabs;
    private ArrayList<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;
      private RelativeLayout mRtlMine;
    private RelativeLayout mRtlProduct;
    private TextView mTvMine;
    private TextView mTvProduct;
    private GoodFragment goodFragment;
    private CommunicateFragment communicateFragment;
    private GoodTypeListFragment goodTypeListFragment;
    private ShopCarFragment shopCarFragment;
    private MineFragment mineFragment;
    private TextView mTvCar;
    private TextView mTvClass;
    private RelativeLayout mRtlClass;
    private RelativeLayout mRtlCar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initPage();
    }
    private void initPage() {
        mVpTabs.setOffscreenPageLimit(1);
        mFragments=new ArrayList<>();
        mFragments.clear();
        if(goodFragment==null){
            mFragments.add(new GoodFragment());
        } else {
            mFragments.add(goodFragment);
        }
        if(goodTypeListFragment==null){
            mFragments.add(new GoodTypeListFragment());
        } else {
            mFragments.add(goodTypeListFragment);
        }
        if(communicateFragment==null){
            mFragments.add(new CommunicateFragment());
        } else {
            mFragments.add(communicateFragment);
        }
        if(mineFragment==null){
            mFragments.add(new MineFragment());
        } else {
            mFragments.add(mineFragment);
        }
        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }
            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mVpTabs.setAdapter(mAdapter);
        mVpTabs.setCurrentItem(0);
        mVpTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public int mCurentPageIndex;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                ResetTab();
                switch (position) {
                    case 0:
                        ChangeType(mTvProduct, getResources().getDrawable(R.mipmap.home1), R.color.blue);
                        break;
                    case 1:
                        ChangeType(mTvClass, getResources().getDrawable(R.mipmap.list1), R.color.blue);
                        break;
                    case 2:
                        ChangeType(mTvCar, getResources().getDrawable(R.mipmap.car1), R.color.blue);
                        break;
                    case 3:
                        ChangeType(mTvMine, getResources().getDrawable(R.mipmap.mine1), R.color.blue);
                        break;
                }
                mCurentPageIndex = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    /**
     * 重置Tab
     */
    public  void  ResetTab(){
        ChangeType(mTvProduct, getResources().getDrawable(R.mipmap.home),R.color.light_black);
        ChangeType(mTvMine, getResources().getDrawable(R.mipmap.mine),R.color.light_black);
        ChangeType(mTvCar, getResources().getDrawable(R.mipmap.car),R.color.light_black);
        ChangeType(mTvClass, getResources().getDrawable(R.mipmap.list),R.color.light_black);
    }
    public  void ChangeType(TextView mTv, Drawable drawable, int color){
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTv.setCompoundDrawables(null, drawable, null, null);
        mTv.setTextColor(getResources().getColor(color));
    }
    private void initview() {
        mVpTabs = (ViewPager) findViewById(R.id.vPager);
        mRtlProduct = (RelativeLayout) findViewById(R.id.rtl_product);
        mRtlMine = (RelativeLayout) findViewById(R.id.rtl_mine);
        mRtlClass = (RelativeLayout) findViewById(R.id.rtl_class);
        mRtlCar = (RelativeLayout) findViewById(R.id.rtl_car);
        mTvProduct = (TextView) findViewById(R.id.tv_product);
        mTvCar = (TextView) findViewById(R.id.tv_car);
        mTvClass = (TextView) findViewById(R.id.tv_class);
        mTvMine = (TextView) findViewById(R.id.tv_mine);
        mRtlMine.setOnClickListener(this);
        mRtlProduct.setOnClickListener(this);
        mRtlCar.setOnClickListener(this);
        mRtlClass.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rtl_product:
                mVpTabs.setCurrentItem(0);
                break;
            case R.id.rtl_class:
                mVpTabs.setCurrentItem(1);
                break;
            case R.id.rtl_car:
                mVpTabs.setCurrentItem(2);
                break;
            case R.id.rtl_mine:
                mVpTabs.setCurrentItem(3);
                break;
        }
    }
}
