package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;

import model.Message;
import model.ShopCar;
import utils.UILUtils;

public class MessageAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Message> mDate;
	private String type;
	private int State;
	private ViewHold viewHold;
	public MessageAdapter(Context context, ArrayList<Message> mDate) {
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
			convertView= View.inflate(context, R.layout.item_message, null);
			viewHold = new ViewHold();
			viewHold.TvTime= (TextView) convertView.findViewById(R.id.tv_time);
			viewHold.TvDel= (TextView) convertView.findViewById(R.id.tv_del);
			viewHold.TvTitle= (TextView) convertView.findViewById(R.id.tv_text);
			convertView.setTag(viewHold);
		}else {
			viewHold= (ViewHold) convertView.getTag();
		}
		   viewHold.TvTitle.setText(""+mDate.get(position).getText());
		   viewHold.TvTime.setText(""+mDate.get(position).getCreatedAt());
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
		TextView TvTitle;
		TextView TvTime;
		TextView TvDel;
	}
}
