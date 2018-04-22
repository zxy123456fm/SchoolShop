package com.example.agriculture.Manager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.agriculture.BaseActivity;
import com.example.agriculture.LoginActivity;
import com.example.agriculture.Shop.GoodManagerActivity;
import com.example.agriculture.Shop.LeaveMessageListActivity;
import com.example.agriculture.Shop.ShopActivity;
import com.example.gaoguo.R;

import utils.SetUtils;

public class ManagerActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager2);
        inievent();
    }
    private void inievent() {
        findViewById(R.id.rtl_exist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManagerActivity.this);
                builder.setMessage("确认要退出？");
                builder.setTitle("提示");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {   @Override
                public void onClick(DialogInterface dialog, int which) {
                    SetUtils.saveIsLogin(ManagerActivity.this,false);
                    startActivity(new Intent(ManagerActivity.this,LoginActivity.class));
                    finish();
                    dialog.dismiss();
                }
                });
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {   @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();}
                });
                builder.create().show();
            }
        });
        findViewById(R.id.rtl_kehu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KeHuInfoActivity.class));
            }
        });
        findViewById(R.id.rtl_shanghu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShopInfoActivity.class));
            }
        });findViewById(R.id.rtl_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GoodManagerActivity.class));
            }
        });findViewById(R.id.rtl_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LeaveMessageListActivity.class));
            }
        });
    }
}
