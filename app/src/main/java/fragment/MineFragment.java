package fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agriculture.LoginActivity;
import com.example.agriculture.MoneyActivity;
import com.example.agriculture.MyOrderActivity;
import com.example.agriculture.MyShopCarActivity;
import com.example.agriculture.ShopWallActivity;
import com.example.agriculture.UpdatePasswordAcitvity;
import com.example.agriculture.UserInfoActivity;
import com.example.agriculture.message.MyPostLeaveMessageActivity;
import com.example.gaoguo.R;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import model.CircleImageView;
import model.User;
import utils.ImageUtils;
import utils.SetUtils;
import utils.UILUtils;
/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {
    private File tempFile;
    private Uri imageUri;
    private final int REQUSET_SELECT_PHOTOS=231;
    ArrayList<String> mPhotos=new ArrayList<String>();
    ArrayList<String> mImgUrl=new ArrayList<String>();
    protected static final int TAKE_PICTURE = 100;
    public final String takePhotoPostfix = ".jpg"; // 拍照文件后缀
    protected static final int CHOOSE_PICTURE = 101;
    private CircleImageView mImgHead;
    public MineFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_mine,null);
        initview(layout);
        initdate();
        initevent();
        return layout;
    }

    private void initevent() {
        mImgHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
                {  //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUSET_SELECT_PHOTOS);
                }else { //权限已经被授予，在这里直接写要执行的相应方法即可
                    ChangeHead();
                }
            }
        });
    }

    private void initdate() {
        UILUtils.displayImageNoAnim(SetUtils.GetImgHead(getActivity()),mImgHead);
    }

    private void initview(View layout) {
        mImgHead = (CircleImageView) layout.findViewById(R.id.img_head);
        /**
         * 安全退出
         */
        layout.findViewById(R.id.rtl_exist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("是否安全退出?");
                builder.setPositiveButton(
                        "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SetUtils.saveIsLogin(getActivity(),false);
                                Intent intent=new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        layout.findViewById(R.id.rtl_update_pas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpdatePasswordAcitvity.class));
            }
        });layout.findViewById(R.id.rtl_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
            }
        });layout.findViewById(R.id.rtl_car).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyShopCarActivity.class));
            }
        });layout.findViewById(R.id.rtl_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
            }
        });layout.findViewById(R.id.rtl_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyPostLeaveMessageActivity.class));
            }
        });layout.findViewById(R.id.rtl_money).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MoneyActivity.class));
            }
        });layout.findViewById(R.id.rtl_score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShopWallActivity.class));
            }
        });
    }
    /**
     * 更换头像
     */
    private void ChangeHead() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
        builder.setMessage("是否要更换头像?");
        builder.setPositiveButton(
                "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, CHOOSE_PICTURE);
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
//            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
//            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
//            ft.replace(R.id.main_info_fragment,homeFragment.newInstance(true));
//            ft.commit();
        }
        if (requestCode == CHOOSE_PICTURE) {
            if (data == null) {
            } else {
                Uri uri = data.getData(); // 得到Uri
                String fPath = uri2filePath(uri); // 转化为路径
                Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null,
                        null);
                cursor.moveToFirst();
                String imgPath = cursor.getString(1); // 图片文件路径
                String imgName = cursor.getString(3); // 图片文件名
                if (resultCode == -1) {
                    mPhotos.clear();
                    mPhotos.add(fPath);
//                    fileName.add(imgName);
                    ImageUtils.displayLocalImage(mPhotos.get(0), mImgHead);
                    UpdateHead();
                }
            }
        }
    }
    /** 把Uri转化成文件路径 */
    public String uri2filePath(Uri uri){
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(uri,proj,null,null,null);
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(index);
        return path;
    }

    /**
     * 更新头像
     */
    public  void  UpdateHead(){
        Log.i("上传头像的地址===",""+mPhotos.get(0));
        startProgressDialog("上传中...");
        final BmobFile bmobFile=new BmobFile(new File(mPhotos.get(0)));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                stopProgressDialog();
                if(e==null){
                    final String ImgHeadUrl = bmobFile.getFileUrl();
                    startProgressDialog("信息更新中...");
                    User myUser = new User();
                    myUser.setHeadUrl(ImgHeadUrl);
                    myUser.update(SetUtils.GetId(getActivity()),new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            stopProgressDialog();
                            if(e==null){
                                SetUtils.saveImgHead(getActivity(),ImgHeadUrl);
                                Toast("更新成功");
                            }else {
                                Toast("更新失败"+e.toString());
                            }
                        }
                    });
                } else {
                    Toast("上传失败");
                }
            }
        });
    }
}
