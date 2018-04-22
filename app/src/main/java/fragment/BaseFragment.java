package fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import dialog.CustomProgressDialog;

/**
 * Created by Administrator on 2017/4/10.
 */

public class BaseFragment extends Fragment {
    private CustomProgressDialog progressDialog;

    public void startProgressDialog(String msg){
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(getActivity());
            if(!"".equals(msg)) {
                progressDialog.setMessage(msg);
            }
        }
        progressDialog.show();
    }
    public void stopProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
    public void Toast(String msg){
        Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
    }
}
