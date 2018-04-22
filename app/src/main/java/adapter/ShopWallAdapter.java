package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;

import model.User;
import model.praiseScore;
import utils.UILUtils;

public class ShopWallAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<praiseScore> mDate;
	private String type;
	private int State;
	private ViewHold viewHold;
	public ShopWallAdapter(Context context, ArrayList<praiseScore> mDate) {
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
			convertView= View.inflate(context, R.layout.item_shop_wall, null);
			viewHold = new ViewHold();
			viewHold.TvScore= (TextView) convertView.findViewById(R.id.tv_score);
			viewHold.Img= (ImageView) convertView.findViewById(R.id.img);
			convertView.setTag(viewHold);
		}else {
			viewHold= (ViewHold) convertView.getTag();
		}
		   viewHold.TvScore.setText("评分:"+mDate.get(position).getScore());
		UILUtils.displayImage(mDate.get(position).getImgUrl(),viewHold.Img);
		return convertView;
	}
	class ViewHold{
		TextView TvScore;
		ImageView Img;
	}
}
