package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;

import model.ShopCarOrder;
import utils.UILUtils;

public class ShopCarOrderAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<ShopCarOrder> mDate;
	private String type;
	private int State;
	private ViewHold viewHold;
	public ShopCarOrderAdapter(Context context, ArrayList<ShopCarOrder> mDate) {
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
			convertView= View.inflate(context, R.layout.item_shop_car_order, null);
			viewHold = new ViewHold();
			viewHold.TvTIme= (TextView) convertView.findViewById(R.id.tv_time);
			viewHold.TvTitle= (TextView) convertView.findViewById(R.id.tv_title);
			viewHold.TvNum= (TextView) convertView.findViewById(R.id.tv_num);
			viewHold.TvState= (TextView) convertView.findViewById(R.id.tv_state);
			viewHold.TvPrice= (TextView) convertView.findViewById(R.id.tv_price);
			viewHold.TvDel= (TextView) convertView.findViewById(R.id.tv_delete);
			viewHold.Img= (ImageView) convertView.findViewById(R.id.img);
			convertView.setTag(viewHold);
		}else {
			viewHold= (ViewHold) convertView.getTag();
		}
			viewHold.TvTitle.setText(""+mDate.get(position).getFoodName());
			viewHold.TvNum.setText("x"+mDate.get(position).getGoodNum());
			viewHold.TvPrice.setText("订单价格:"+"￥"+mDate.get(position).getFoodTPrice());
			UILUtils.displayImageNoAnim(mDate.get(position).getImg1(),viewHold.Img);
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
		TextView TvNum;
	}
}
