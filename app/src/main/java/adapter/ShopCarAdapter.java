package adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;

import model.GoodState;
import model.ShopCar;
import utils.UILUtils;

public class ShopCarAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<ShopCar> mDate;
	private String type;
	private int State;
	private ViewHold viewHold;
	public static ArrayList<Integer> mGoosNums=new ArrayList<>();
	public static ArrayList<Boolean> mIsSelectGoods=new ArrayList<>();
	public ShopCarAdapter(Context context, ArrayList<ShopCar> mDate) {
		this.context = context;
		this.mDate = mDate;

	}
	@Override
	public int getCount() {
		return mDate.size();
	}
	@Override
	public Object getItem(int position) {
		return null;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView= View.inflate(context, R.layout.item_shop_car, null);
			viewHold = new ViewHold();
			viewHold.TvTIme= (TextView) convertView.findViewById(R.id.tv_time);
			viewHold.Cb= (CheckBox) convertView.findViewById(R.id.cb);
			viewHold.TvTitle= (TextView) convertView.findViewById(R.id.tv_title);
			viewHold.TvState= (TextView) convertView.findViewById(R.id.tv_state);
			viewHold.TvPrice= (TextView) convertView.findViewById(R.id.tv_price);
			viewHold.TvDel= (TextView) convertView.findViewById(R.id.tv_del);
			viewHold.EtNum= (EditText) convertView.findViewById(R.id.et_num);
			viewHold.Img= (ImageView) convertView.findViewById(R.id.img);
			viewHold.ImgAdd= (ImageView) convertView.findViewById(R.id.img_add);
			viewHold.ImgRedece= (ImageView) convertView.findViewById(R.id.img_reduce);
			convertView.setTag(viewHold);
		}else {
			viewHold= (ViewHold) convertView.getTag();
		}
		setDate(viewHold,position);
		viewHold.ImgRedece.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(onReduceLitner!=null){
					onReduceLitner.Reduce(position,mGoosNums.get(position));
				}
			}
		});
		viewHold.ImgAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(onAddLitner!=null){
					onAddLitner.Add(position,mGoosNums.get(position));
				}
			}
		});
		viewHold.TvDel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(onDelOrderLitner!=null){
					onDelOrderLitner.del(position);
				}
			}
		});
//		if(viewHold.Cb.isChecked()){
//			  mIsSelectGoods.set(position,true);
//		}else {
//			  mIsSelectGoods.set(position,false);
//		}
		viewHold.Cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				Log.i("被选中状态"+position,""+isChecked);
				if(oncheckLitner!=null){
//					if(isChecked){
//						mIsSelectGoods.set(position,true);
//					}else {
//						mIsSelectGoods.set(position,false);
//					}
//					mIsSelectGoods.set(position,isChecked);
					oncheckLitner.check(position,isChecked);
				}
			}
		});
		viewHold.TvDel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(onDelOrderLitner!=null){
					onDelOrderLitner.del(position);
				}
			}
		});
		return convertView;
	}
	/**
	 * 设置数据
	 * @param position
     */
	private void setDate(ViewHold viewHold,int position) {
		viewHold.TvTitle.setText(""+mDate.get(position).getgoodName());
		viewHold.TvTIme.setText(""+mDate.get(position).getCreatedAt());
		viewHold.TvPrice.setText("订单价格:"+"￥"+mDate.get(position).getgoodTPrice());
		viewHold.EtNum.setEnabled(false);
//		viewHold.EtNum.setText("1");
		UILUtils.displayImageNoAnim(mDate.get(position).getImg1(),viewHold.Img);
	}

	public int getState() {
		return State;
	}
	public  onDelOrderLitner onDelOrderLitner;
	public void setOnDelOrderLitner(onDelOrderLitner on){
		onDelOrderLitner=on;
	}
	public interface onDelOrderLitner{
		void  del(int pos);
	}
	public void setState(int state) {
		State = state;
	}
	class ViewHold{
		ImageView Img;
		ImageView ImgAdd;
		ImageView ImgRedece;
		EditText EtNum;
		TextView TvTitle;
		TextView TvTIme;
		TextView TvState;
		TextView TvPrice;
		CheckBox Cb;
		TextView TvDel;
	}

	public onCheckLitner oncheckLitner;
	public void setCheckLitner(onCheckLitner on){
		oncheckLitner=on;
	}
	public interface onCheckLitner{
		void check(int pos,boolean IsCheck);
	}
	public onReduceLitner onReduceLitner;
	public void setOnReduceLitner(onReduceLitner on){
		onReduceLitner=on;
	}
	public interface onReduceLitner{
		void Reduce(int pos,int num);
	}
	public onAddLitner onAddLitner;
	public void setOnAddLitner(onAddLitner on){
		onAddLitner=on;
	}
	public interface onAddLitner{
		void Add(int pos,int num);
	}
	/**
	 * 第一种方法 更新对应view的内容
	 *
	 * @param position 要更新的位置
	 */
	public static void UpdateNums(ListView listView, int position,boolean IsAdd) {
		/**第一个可见的位置**/
		int firstVisiblePosition = listView.getFirstVisiblePosition();
		/**最后一个可见的位置**/
		int lastVisiblePosition = listView.getLastVisiblePosition();
		/**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
		if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
			/**获取指定位置view对象**/
			View view = listView.getChildAt(position - firstVisiblePosition);
			EditText mEtNum = (EditText) view.findViewById(R.id.et_num);
//			CheckBox Cb= (CheckBox) view.findViewById(R.id.cb);
			mEtNum.setEnabled(false);
			int num=Integer.parseInt(mEtNum.getText().toString().trim());
			if(IsAdd){
				mEtNum.setText(""+(num+1));
				mGoosNums.set(position,(num+1));
			}else {
				if(num>=1){
					mEtNum.setText(""+(num-1));
					mGoosNums.set(position,(num-1));
				}else {
					mEtNum.setText(""+(0));
					mGoosNums.set(position,(0));
				}
			}
		}
	}
	public void iniGoosNums(ArrayList<ShopCar> mDate){
		mGoosNums.clear();
		mIsSelectGoods.clear();
		if(mDate.size()>0){
			for (int i=0;i<mDate.size();i++){
				mGoosNums.add(0);
				mIsSelectGoods.add(false);
			}
		}
		Log.i("===商品的数量",""+mGoosNums.toString());
	}

	public ArrayList<GoodState> iniGoodCheckState(ArrayList<ShopCar> mDate){
		ArrayList<GoodState> mState=new ArrayList<>();
		mState.clear();
		if(mDate.size()>0){
			for (int i=0;i<mDate.size();i++){
				mState.add(new GoodState(false,i));
			}
		}
		return mState;
	}

	/**
	 * 获取商品的数量集合
	 * @return
     */
	public ArrayList<Integer> getGoodNums(){
		  return  mGoosNums;
	}
	/**
	 * 获取商品是否选择
	 * @return
	 */
	public ArrayList<Boolean> getIsGoodsSelect(){
		return  mIsSelectGoods;
	}
}

