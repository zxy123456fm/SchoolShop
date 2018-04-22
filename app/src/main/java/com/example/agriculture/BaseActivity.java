package com.example.agriculture;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import dialog.CustomProgressDialog;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
public class BaseActivity extends FragmentActivity {
	/**
	 * 获取当前网络类型
	 * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
	 */
	public static final int NETTYPE_WIFI = 11;
	public static final int NETTYPE_CMWAP = 12;
	public static final int NETTYPE_CMNET = 13;
	public final static int RESULT_CLOSE_ACTIVITY = -9999;
	private static final int READ_PHONE_STATE = 0;
	private static ArrayList<BaseActivity> activityList = new ArrayList<BaseActivity>();
	private boolean background = false;
	private Toast tip;
	private ProgressDialog loading;
	private Map<View, Runnable> touchOutsideListenerMap = new Hashtable<>();
	private CustomProgressDialog progressDialog = null;
	private int loadingCount = 0; // loading��������ʱ�ж���
	private Map<View, Runnable> touchListenerMap = new Hashtable<>();
	private boolean containVisiableStartActivity = false;
	private boolean IsConnectNet=true;
	private FrameLayout group;
	private View statusBarView;
	private View content;
	/**
	 */
	@Override
	protected void onStart() {
		super.onStart();
	}
	public  void IsNewConnect(boolean IsConnectNet){
		Toast("连接网络网站"+IsConnectNet);
	}
	public void onDestroy() {
		background = true;
		activityList.remove(this);
		super.onDestroy();
	}
	/**
	 * 解决Subscription内存泄露问题
	 * @param s
	 */
	private CompositeSubscription mCompositeSubscription;
	protected void addSubscription(Subscription s) {
		if (this.mCompositeSubscription == null) {
			this.mCompositeSubscription = new CompositeSubscription();
		}
		this.mCompositeSubscription.add(s);
	}
	public void back(View view) {
		finish();
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public void onResume() {
		background = false;
		super.onResume();
	}
	public void Toast(String msg){
		Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
	}
	public void startProgressDialog(String msg){
		if (progressDialog == null){
			progressDialog = CustomProgressDialog.createDialog(this);
			if(!"".equals(msg)) {
				progressDialog.setMessage(msg);
			}
		}
		progressDialog.show();
	}
	public void stopProgressDialog(){
		if (progressDialog != null){
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	public boolean isTelNumber(String tel){
		String telRegex = "[1][3758]\\d{9}";
		if(!tel.matches(telRegex)){
			return false;
		}
		return true;
	}


}
