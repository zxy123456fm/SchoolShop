package adapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;
import model.Product;
import utils.UILUtils;
public class GoodManagerAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Product> mDate;
	private ViewHold viewHold;
	public GoodManagerAdapter(Context context, ArrayList<Product> mDate) {
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
			convertView= View.inflate(context, R.layout.item_food_manager, null);
			viewHold = new ViewHold();
			viewHold.TvName= (TextView) convertView.findViewById(R.id.tv_name);
			viewHold.TvDetail= (TextView) convertView.findViewById(R.id.tv_detail);
			viewHold.TvAddress= (TextView) convertView.findViewById(R.id.tv_address);
			viewHold.TvPrice= (TextView) convertView.findViewById(R.id.tv_price);
			viewHold.TvDel= (TextView) convertView.findViewById(R.id.tv_delete);
			viewHold.TvTime= (TextView) convertView.findViewById(R.id.tv_time);
			viewHold.Img= (ImageView) convertView.findViewById(R.id.img);
			convertView.setTag(viewHold);
		}else {
			viewHold= (ViewHold) convertView.getTag();
		}
		   viewHold.TvName.setText(""+mDate.get(position).getName());
		   viewHold.TvAddress.setText(""+mDate.get(position).getAddress());
		   viewHold.TvDetail.setText(""+mDate.get(position).getDetail());
		   viewHold.TvPrice.setText("￥:"+mDate.get(position).getPrice());
		   if(mDate.get(position).getImgUrl1()==null||mDate.get(position).getImgUrl1().equals("")){
		   	  viewHold.Img.setVisibility(View.GONE);
		   }else {
			   viewHold.Img.setVisibility(View.VISIBLE);
			   UILUtils.displayImageNoAnim(mDate.get(position).getImgUrl1(),viewHold.Img);
		   }
		   viewHold.TvTime.setText("添加时间:"+mDate.get(position).getCreatedAt());
		   viewHold.TvDel.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
				   if(onDelgoodLitner!=null){
						onDelgoodLitner.Del(position);
				   }
			   }
		   });
		return convertView;
	}
	public onDelgoodLitner onDelgoodLitner;
	public void setOnDelgoodLitner(onDelgoodLitner on){
		onDelgoodLitner=on;
	}
	public interface onDelgoodLitner{
		void Del(int pos);
	}
	class ViewHold{
		TextView TvDel;
		TextView TvTime;
		TextView TvName;
		TextView TvDetail;
		TextView TvPrice;
		TextView TvAddress;
		ImageView Img;
	}
}
