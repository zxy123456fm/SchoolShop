package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agriculture.GoodTypeActivity;
import com.example.gaoguo.R;

/**
 */
public class GoodTypeListFragment extends BaseFragment {
    public GoodTypeListFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_ganguo_list,null);
        initview(layout);
        return layout;
    }
    private void initview(View layout) {
        /**
         * 女士鞋服专区
         */
        layout.findViewById(R.id.rtl_nsxf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoodTypeActivity.class).putExtra("type","女士鞋服专区"));
            }
        });
        /**
         * 男士鞋服
         */
        layout.findViewById(R.id.rtl_nsxf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoodTypeActivity.class).putExtra("type","男士鞋服专区"));
            }
        });
        /**
         * 化妆品
         */
        layout.findViewById(R.id.rtl_hzp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoodTypeActivity.class).putExtra("type","化妆品专区"));
            }
        });
        /**
         * 电子产品专区
         */
        layout.findViewById(R.id.rtl_dzcp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoodTypeActivity.class).putExtra("type","电子产品专区"));
            }
        });
        /**
         * 其他做专区
         */
        layout.findViewById(R.id.rtl_others).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoodTypeActivity.class).putExtra("type","其他专区"));
            }
        });
    }
}
