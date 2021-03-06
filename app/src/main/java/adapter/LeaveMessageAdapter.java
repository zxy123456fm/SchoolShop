package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;

import model.LeaveMessage;
import utils.UILUtils;

public class LeaveMessageAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<LeaveMessage> mDate;
	private ViewHold viewHold;
	public LeaveMessageAdapter(Context context, ArrayList<LeaveMessage> mDate) {
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
			convertView= View.inflate(context, R.layout.item_communicate_record, null);
			viewHold = new ViewHold();
			viewHold.TvNickName= (TextView) convertView.findViewById(R.id.tv_nickname);
			viewHold.TvAddress= (TextView) convertView.findViewById(R.id.tv_address);
			viewHold.TvContent= (TextView) convertView.findViewById(R.id.tv_content);
			viewHold.TvDelete= (TextView) convertView.findViewById(R.id.tv_delete);
			viewHold.TvTime= (TextView) convertView.findViewById(R.id.tv_time);
			viewHold.Img1= (ImageView) convertView.findViewById(R.id.img1);
			viewHold.Img2= (ImageView) convertView.findViewById(R.id.img2);
			viewHold.Img3= (ImageView) convertView.findViewById(R.id.img3);
			viewHold.LlImg= (LinearLayout) convertView.findViewById(R.id.ll_img);
			convertView.setTag(viewHold);
		}else {
			viewHold= (ViewHold) convertView.getTag();
		}
		viewHold.TvNickName.setText(""+mDate.get(position).getNickName());
		viewHold.TvContent.setText(""+mDate.get(position).getTitle());
		viewHold.TvTime.setText(""+mDate.get(position).getCreatedAt());
		if(mDate.get(position).getImg1()==null){
			viewHold.LlImg.setVisibility(View.GONE);
			viewHold.Img1.setVisibility(View.INVISIBLE);
			viewHold.Img2.setVisibility(View.INVISIBLE);
			viewHold.Img3.setVisibility(View.INVISIBLE);
		}else if(mDate.get(position).getImg1()!=null &&mDate.get(position).getImg2()==null){
			viewHold.LlImg.setVisibility(View.VISIBLE);
			UILUtils.displayImageNoAnim(mDate.get(position).getImg1(),viewHold.Img1);
			viewHold.Img2.setVisibility(View.INVISIBLE);
			viewHold.Img3.setVisibility(View.INVISIBLE);
		}else if(mDate.get(position).getImg1()!=null &&mDate.get(position).getImg2()!=null &&mDate.get(position).getImg3()==null){
			viewHold.LlImg.setVisibility(View.VISIBLE);
			UILUtils.displayImageNoAnim(mDate.get(position).getImg1(),viewHold.Img1);
			UILUtils.displayImageNoAnim(mDate.get(position).getImg2(),viewHold.Img2);
			viewHold.Img3.setVisibility(View.INVISIBLE);
		}else if(mDate.get(position).getImg1()!=null &&mDate.get(position).getImg2()!=null &&mDate.get(position).getImg3()!=null){
			viewHold.LlImg.setVisibility(View.VISIBLE);
			UILUtils.displayImageNoAnim(mDate.get(position).getImg1(),viewHold.Img1);
			UILUtils.displayImageNoAnim(mDate.get(position).getImg2(),viewHold.Img2);
			UILUtils.displayImageNoAnim(mDate.get(position).getImg3(),viewHold.Img3);
		}
		return convertView;
	}
	class ViewHold{
		TextView TvNickName;
		TextView TvAddress;
		TextView TvTime;
		TextView TvDelete;
		TextView TvContent;
		ImageView Img1;
		ImageView Img2;
		ImageView Img3;
		LinearLayout LlImg;
	}
}
