package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;


import com.example.gaoguo.R;

import java.util.ArrayList;

import utils.ImageUtils;
/**
 */

/**
 * 选择图片适配器
 */
@Deprecated
public class GdPicAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> mPhotos;
    private int num;
    private boolean isImgUrl = false;
    private OnClickSelectListner listner;

    public GdPicAdapter(Context context, ArrayList<String> mPhotos) {
        this.context = context;
        this.mPhotos = mPhotos;
    }
    public GdPicAdapter(Context context, ArrayList<String> mPhotos, int Num) {
        this.context = context;
        this.mPhotos = mPhotos;
        this.num = Num;
    }

    public GdPicAdapter(Context context, ArrayList<String> mPhotos, boolean isImgUrl){
        this.context = context;
        this.mPhotos = mPhotos;
        this.isImgUrl = isImgUrl;
    }
    @Override
    public int getCount() {
        if(num==1){
            return 1;
        } else {
            if (mPhotos.size() >= 3) {
                return 3;
            }
            return (mPhotos.size() + 1);
        }
    }

    @Override
    public String getItem(int position) {
        return mPhotos.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_image, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
            viewHolder.bt = (Button) convertView.findViewById(R.id.item_grida_bt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPhotos.size() == position) {
                    if (listner != null) {
                        listner.onshowImgPop();
                    }
                } else {
//                    Intent intent = new Intent(context, ImageShowActivity.class);
//                    intent.putExtra("ImgPath", mPhotos.get(position));
//                    context.startActivity(intent);
                }
            }
        });
        if (position == mPhotos.size()) {
            ImageUtils.displayResourece(viewHolder.image, R.mipmap.addpic);
            viewHolder.bt.setVisibility(View.GONE);
            if (position == 3) {
                viewHolder.image.setVisibility(View.GONE);
            }
        } else {
            if(isImgUrl){
                ImageUtils.displayImage(mPhotos.get(position), viewHolder.image);
            } else {
                ImageUtils.displayLocalImage(mPhotos.get(position), viewHolder.image);
            }
            viewHolder.bt.setVisibility(View.VISIBLE);
        }
        viewHolder.bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPhotos.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public class ViewHolder {
        public ImageView image;
        public Button bt;
    }
    public interface OnClickSelectListner {
        public void onshowImgPop();
    }
    public void setOnSelectListner(OnClickSelectListner listner) {
        this.listner = listner;
    }
}