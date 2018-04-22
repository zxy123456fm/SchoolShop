package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.agriculture.PlayRecordDetailActivity;
import com.example.gaoguo.R;
import java.util.ArrayList;
import java.util.List;

import adapter.LeaveMessageAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import model.LeaveMessage;

/**
 */
public class CommunicateFragment extends BaseFragment {
    private ArrayList<LeaveMessage> mDate=new ArrayList<>();
    private LeaveMessageAdapter adapter;
    private ListView mList;
    private RelativeLayout mRtlPost;
    private TextView mTvState;
    public CommunicateFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_sq,null);
        initview(layout);
        initdate();
        initevetn();
        return layout;
    }
    private void initevetn() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), PlayRecordDetailActivity.class).putExtra("date",mDate.get(position)));
            }
        });
    }
    private void initdate() {
        BmobQuery<LeaveMessage> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<LeaveMessage>() {
            @Override
            public void done(List<LeaveMessage> list, BmobException e) {
                stopProgressDialog();
                if(e==null){
                    mDate.clear();
                    mDate.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void initview(View layout) {
        mTvState = (TextView) layout.findViewById(R.id.tv_state);
        mRtlPost = (RelativeLayout)layout. findViewById(R.id.rtl_add);
        mList = (ListView) layout.findViewById(R.id.list);
        adapter=new LeaveMessageAdapter(getActivity(),mDate);
        mList.setAdapter(adapter);
    }
}
