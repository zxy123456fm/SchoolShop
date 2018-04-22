package com.example.agriculture.Shop;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.example.agriculture.LoginActivity;
import com.example.agriculture.ManagerOrderActivity;
import com.example.agriculture.UpdatePasswordAcitvity;
import com.example.gaoguo.R;
import utils.SetUtils;
public class ShopActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        initview();
    }
    private void initview() {
        findViewById(R.id.rtl_exist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShopActivity.this);
                builder.setMessage("确认要退出？");
                builder.setTitle("提示");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {   @Override
                public void onClick(DialogInterface dialog, int which) {
                    SetUtils.saveIsLogin(ShopActivity.this,false);
                    startActivity(new Intent(ShopActivity.this,LoginActivity.class));
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
        findViewById(R.id.rtl_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GoodManagerActivity.class)
                .putExtra("shop",true));
            }
        });findViewById(R.id.rtl_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ManagerOrderActivity.class));
            }
        });findViewById(R.id.rtl_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LeaveMessageListActivity.class));
            }
        });findViewById(R.id.rtl_update_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UpdatePasswordAcitvity.class));
            }
        });
    }
}
