package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.agriculture.GoodDetailActivity;
import com.example.gaoguo.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import adapter.GoodTypeAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import model.Product;
import utils.GlideImageLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodFragment extends Fragment {
    private Banner banner;
    private ArrayList<String> mBannerDate=new ArrayList<>();
    private ListView mList;
    private GoodTypeAdapter adapter;
    private ArrayList<Product> mDate=new ArrayList<>();
    private TextView mTvState;

    public GoodFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_food,null);
        initview(layout);
        initBaner();
        initdate(false);
        initevetn();
        return layout;
    }
    private void initdate(boolean isSearch) {
        BmobQuery<Product> query = new BmobQuery<>();
        query.addWhereEqualTo("IsHomeShow",true);
        query.findObjects(new FindListener<Product>() {
            @Override
            public void done(List<Product> list, BmobException e) {
                if(e==null) {
                    mDate.clear();
                    mDate.addAll(list);
                    adapter.notifyDataSetChanged();
                    if(list.size()==0){
                        mTvState.setVisibility(View.VISIBLE);
                        mList.setVisibility(View.GONE);
//                        Toast.makeText(getActivity(),"抱歉，暂无商品",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initevetn() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), GoodDetailActivity.class).putExtra("date",mDate.get(position)));
            }
        });

    }
    private void initBaner() {
        mBannerDate.clear();
        mBannerDate.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523723152242&di=047318db1cd57b735aaf7310ac778ab7&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0117d15963b0e3a8012193a37a5501.jpg%401280w_1l_2o_100sh.jpg");
        mBannerDate.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3801563808,2068349244&fm=27&gp=0.jpg");
        mBannerDate.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523723170140&di=43019e2d30c5f6fab809caa47ef81e59&imgtype=0&src=http%3A%2F%2Fimg5.pcpop.com%2FArticleImages%2F500x375%2F2%2F2738%2F002738405.jpg");
        mBannerDate.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524317865&di=62e9f8ebc7356444dd4ea67990a6b5e9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.cache.netease.com%2Fhouse%2F2015%2F4%2F3%2F20150403171721163d5.jpg");
        mBannerDate.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523723213848&di=b65d91b6ce9be5dbe106bf8bdaea8cb0&imgtype=0&src=http%3A%2F%2Fpic15.nipic.com%2F20110704%2F3963642_115421953000_2.jpg");
        banner.setImages(mBannerDate);
        banner.setBannerAnimation(Transformer.Default);
        banner.setImageLoader(new GlideImageLoader());
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }
    private void initview(View layout) {
        banner = (Banner) layout.findViewById(R.id.banner);
        mTvState = (TextView) layout.findViewById(R.id.tv_state);
        mList = (ListView) layout.findViewById(R.id.list);
        adapter=new GoodTypeAdapter(getActivity(),mDate);
        mList.setAdapter(adapter);
    }
}
