package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.gaoguo.R;

import java.util.ArrayList;

import model.order;
import utils.UILUtils;

public class OrderAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<order> mDate;
	private String type;
	private int State;
	private ViewHold viewHold;
	public OrderAdapter(Context context, ArrayList<order> mDate) {
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
			convertView= View.inflate(context, R.layout.item_order, null);
			viewHold = new ViewHold();
			viewHold.TvTIme= (TextView) convertView.findViewById(R.id.tv_time);
			viewHold.TvTitle= (TextView) convertView.findViewById(R.id.tv_title);
			viewHold.TvState= (TextView) convertView.findViewById(R.id.tv_state);
			viewHold.TvPrice= (TextView) convertView.findViewById(R.id.tv_price);
			viewHold.TvDel= (TextView) convertView.findViewById(R.id.tv_delete);
			viewHold.Img= (ImageView) convertView.findViewById(R.id.img);
			convertView.setTag(viewHold);
		}else {
			viewHold= (ViewHold) convertView.getTag();
		}
//		if(mDate.get(position).isShopCar()){
//
//		}else {
			viewHold.TvTitle.setText(""+mDate.get(position).getgoodName());
			viewHold.TvTIme.setText(""+mDate.get(position).getCreatedAt());
			viewHold.TvPrice.setText("订单价格:"+"￥"+mDate.get(position).getgoodTPrice());
			UILUtils.displayImageNoAnim(mDate.get(position).getImg1(),viewHold.Img);
//		}
		if(mDate.get(position).getState()==0){
			viewHold.TvState.setText("未发货");
			viewHold.TvDel.setVisibility(View.GONE);
		}else if(mDate.get(position).getState()==1){
			viewHold.TvState.setText("商家已发货，确认收货");
			viewHold.TvDel.setVisibility(View.GONE);
		} else if(mDate.get(position).getState()==2){
			viewHold.TvState.setText("已确认收货,点击评分");
			viewHold.TvDel.setVisibility(View.VISIBLE);
		}else if(mDate.get(position).getState()==3){
			viewHold.TvState.setText("已完成");
			viewHold.TvDel.setVisibility(View.VISIBLE);
		}
			viewHold.TvState.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if(onGetGoodsLitner!=null){
						onGetGoodsLitner.getGood(position);
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
		TextView TvTitle;
		TextView TvTIme;
		TextView TvState;
		TextView TvPrice;
		TextView TvDel;
	}
	public  interface onGetGoodsLitner{
		void getGood(int pos);
	}
	public onGetGoodsLitner onGetGoodsLitner;
	public void setOnGetGoodsLitner(onGetGoodsLitner on){
		onGetGoodsLitner=on;
	}
}
