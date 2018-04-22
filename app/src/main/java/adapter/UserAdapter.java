package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;

import model.Message;
import model.User;

public class UserAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<User> mDate;
	private String type;
	private int State;
	private ViewHold viewHold;
	public UserAdapter(Context context, ArrayList<User> mDate) {
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
			convertView= View.inflate(context, R.layout.item_user, null);
			viewHold = new ViewHold();
			viewHold.TvName= (TextView) convertView.findViewById(R.id.tv_name);
			viewHold.TvTime= (TextView) convertView.findViewById(R.id.tv_time);
			convertView.setTag(viewHold);
		}else {
			viewHold= (ViewHold) convertView.getTag();
		}
		   viewHold.TvName.setText("用户名:"+mDate.get(position).getUsername());
		   viewHold.TvTime.setText("注册时间:"+mDate.get(position).getCreatedAt());
		return convertView;
	}
	class ViewHold{
		TextView TvName;
		TextView TvTime;
	}
}
