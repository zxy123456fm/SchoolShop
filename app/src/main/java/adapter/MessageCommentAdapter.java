package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.gaoguo.R;

import java.util.ArrayList;

import model.RecordComment;


public class MessageCommentAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<RecordComment> mDate;
	private String type;
	private ViewHold viewHold;
	public MessageCommentAdapter(Context context, ArrayList<RecordComment> mDate) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView= View.inflate(context, R.layout.item_comment, null);
			viewHold = new ViewHold();
			viewHold.TvDetail= (TextView) convertView.findViewById(R.id.tv_text);
			viewHold.TvTIme= (TextView) convertView.findViewById(R.id.tv_time);
			viewHold.TvUser= (TextView) convertView.findViewById(R.id.tv_user);
			convertView.setTag(viewHold);
		}else {
			viewHold= (ViewHold) convertView.getTag();
		}
		   viewHold.TvDetail.setText(""+mDate.get(position).getText());
		   viewHold.TvTIme.setText(""+mDate.get(position).getCreatedAt());
		   viewHold.TvUser.setText(""+mDate.get(position).getUserName()+"的评论");
		return convertView;
	}
	class ViewHold{
		TextView TvDetail;
		TextView TvUser;
		TextView TvTIme;
	}
}
