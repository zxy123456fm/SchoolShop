package fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gaoguo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.ShopCarAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import model.GoodState;
import model.Money;
import model.ShopCar;
import model.ShopCarOrder;
import model.order;
import utils.SetUtils;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**Fragment其实可以理解为一个具有自己生命周期的控件，只不过这个控件又有点特殊，
 * 它有自己的处理输入事件的能力，有自己的生命周期，又必须依赖于Activity，能互相通信和托管。
 */
public class ShopCarFragment extends BaseFragment {
    private ShopCarAdapter adapter;
    private ArrayList<ShopCar> mDate = new ArrayList<>();
    private ArrayList<ShopCar> mShopOrder = new ArrayList<>();
    private ListView mList;
    private TextView mTvBuy;
    private order mShopCarOrder = new order();
    private float Price = 0;
    private TextView mTvAllPrice;
    private ArrayList<GoodState> mGoodCheckState = new ArrayList<>();

    public ShopCarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_shop_car, null);
        initview(layout);
        initdate();
        initevnet();
        return layout;
    }

    private void initevnet() {
        /**
         * 增加商品
         */
        adapter.setOnAddLitner(new ShopCarAdapter.onAddLitner() {
            @Override
            public void Add(int pos, int num) {
                adapter.UpdateNums(mList, pos, true);
//                Toast(""+adapter.getGoodNums().get(pos));
                ArrayList<Integer> goodNums = adapter.getGoodNums();
                Price = Price + Float.parseFloat(mDate.get(pos).getgoodTPrice());
                Float allPrice = getAllPrice();
                mTvAllPrice.setText("总价:" + allPrice + "元");
                ShopCar shopCar = new ShopCar();
                shopCar.setImg1(mDate.get(pos).getImg1());
                shopCar.setgoodName(mDate.get(pos).getgoodName());
                shopCar.setgoodTPrice("" + Float.parseFloat(mDate.get(pos).getgoodTPrice()));
                shopCar.setBuyNum(goodNums.get(pos));
                if (mGoodCheckState.get(pos).isSelect()) {
                    shopCar.setSelect(true);
                } else {
                    shopCar.setSelect(false);
                }
                mShopOrder.set(pos, shopCar);
                Log.i("订单详情的变化情况===-----", "" + mShopOrder.toString());
            }
        });
        /**
         * 减少商品
         */
        adapter.setOnReduceLitner(new ShopCarAdapter.onReduceLitner() {
            @Override
            public void Reduce(int pos, int num) {
                adapter.UpdateNums(mList, pos, false);
                ArrayList<Integer> goodNums = adapter.getGoodNums();
                if (adapter.getGoodNums().get(pos) >= 0) {
                    Price = Price - Float.parseFloat(mDate.get(pos).getgoodTPrice());
                }
                Float allPrice = getAllPrice();
                mTvAllPrice.setText("总价:" + allPrice + "元");
                ShopCar shopCar = new ShopCar();
                shopCar.setImg1(mDate.get(pos).getImg1());
                shopCar.setgoodName(mDate.get(pos).getgoodName());
                shopCar.setgoodTPrice("" + Float.parseFloat(mDate.get(pos).getgoodTPrice()));
                shopCar.setBuyNum(goodNums.get(pos));
                if (mGoodCheckState.get(pos).isSelect()) {
                    shopCar.setSelect(true);
                } else {
                    shopCar.setSelect(false);
                }
                mShopOrder.set(pos, shopCar);
                Log.i("订单详情的变化情况===-----", "" + mShopOrder.toString());
            }
        });

        /**
         * 选择 dd
         */
        adapter.setCheckLitner(new ShopCarAdapter.onCheckLitner() {
            @Override
            public void check(int pos, boolean IsChecked) {
                ArrayList<Integer> goodNums = adapter.getGoodNums();
                if (IsChecked) {
                    mGoodCheckState.set(pos, new GoodState(true, pos));
                    Float allPrice = getAllPrice();
                    mTvAllPrice.setText("总价:" + allPrice + "元");
                    ShopCar shopCar = new ShopCar();
                    shopCar.setImg1(mDate.get(pos).getImg1());
                    shopCar.setgoodName(mDate.get(pos).getgoodName());
                    shopCar.setgoodTPrice("" + Float.parseFloat(mDate.get(pos).getgoodTPrice()));
                    shopCar.setBuyNum(goodNums.get(pos));
                    shopCar.setSelect(true);
                    mShopOrder.set(pos, shopCar);
//                   if(!mShopOrder.contains(mDate.get(pos))){
//                       mShopOrder.add(mDate.get(pos));
//                       for (int i=0;i<mShopOrder.size();i++){
//                            if(mShopOrder.get(i)==mDate.get(pos)){
//                                ShopCar car = new ShopCar();
//                                car.setUserId(mDate.get(pos).getUserId());
//                                car.setBuyNum(goodNums.get(pos));
//                                car.setgoodDetail(mDate.get(pos).getgoodDetail());
//                                car.setgoodName(mDate.get(pos).getgoodName());
//                                car.setgoodTAddress(mDate.get(pos).getgoodTAddress());
//                                car.setgoodTPrice(mDate.get(pos).getgoodTPrice());
//                                car.setImg1(mDate.get(pos).getImg1());
//                                car.setImg2(mDate.get(pos).getImg2());
//                                car.setImg3(mDate.get(pos).getImg3());
//                                car.setState(mDate.get(pos).getState());
//                                mShopOrder.set(i,car);
//                            }
//                       }
//
//                   }
                    Log.i("选择的多少", "" + mShopOrder.size());
                } else {
                    mGoodCheckState.set(pos, new GoodState(false, pos));
                    Float allPrice = getAllPrice();
//                   Price=Price-(Float.parseFloat(mDate.get(pos).getgoodTPrice())*num);
                    mTvAllPrice.setText("总价:" + (allPrice) + "元");
                    ShopCar car = new ShopCar();
                    ShopCar shopCar = new ShopCar();
                    shopCar.setImg1(mDate.get(pos).getImg1());
                    shopCar.setgoodName(mDate.get(pos).getgoodName());
                    shopCar.setgoodTPrice("" + Float.parseFloat(mDate.get(pos).getgoodTPrice()));
                    shopCar.setBuyNum(goodNums.get(pos));
                    shopCar.setSelect(false);
                    mShopOrder.set(pos, shopCar);
//                       if(mShopOrder.contains(mDate.get(pos))){
//                           mShopOrder.remove(mDate.get(pos));
//                           mShopOrders.remove(mDate.get(pos));
//                           Log.i("不选择的多少",""+mShopOrder.size());
//                       }
                }
//               Log.i("选择商品的情况",""+mGoodCheckState.toString());
                Log.i("订单详情的变化情况===-----", "" + mShopOrder.toString());
            }

        });
        /**
         * 删除购物车里面的东西
         */
        adapter.setOnDelOrderLitner(new ShopCarAdapter.onDelOrderLitner() {
            @Override
            public void del(final int pos) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确认要删除？");
                builder.setTitle("提示");
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DelGoods(pos);
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        /**
         * 确认下单
         */
        mTvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mGoodCheckState.size()==0){
                    Toast("请选择商品下单");
                    return;
                }
                if (Price == 0) {
                    Toast("请选择下单");
                    return;
                }
                final EditText editText = new EditText(getApplicationContext());
                editText.setHintTextColor(getResources().getColor(R.color.dark_gray));
                editText.setTextColor(Color.BLACK);
                editText.setHint("请输入您的地址");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示,确认下单购买？");
                builder.setView(editText);
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /**
                         * 判断钱是否够
                         */
                        startProgressDialog("加载中...");
                        BmobQuery<Money> query = new BmobQuery<>();
                        query.addWhereEqualTo("UserId",""+SetUtils.GetId(getApplicationContext()));
                        query.findObjects(new FindListener<Money>() {
                            @Override
                            public void done(List<Money> list, BmobException e) {
                                stopProgressDialog();
                                 if(e==null){
                                     if(list.size()==0){
                                        Toast("抱歉,您的余额不足.");
                                     }else if(list.get(0).getMoney()<getAllPrice()){
                                         Toast("抱歉,您的余额不足");
                                     }else if(list.get(0).getMoney()>getAllPrice()){
                                         startProgressDialog("加载中...");
                                         Money money = new Money();
                                         money.setMoney((list.get(0).getMoney()-getAllPrice()));
                                         money.update(list.get(0).getObjectId(), new UpdateListener() {
                                             @Override
                                             public void done(BmobException e) {
                                                  stopProgressDialog();
                                                  if(e==null){
                                                      MakeOrder(editText.getText().toString().trim());
                                                  }else {
                                                      Toast(e.toString());
                                                  }
                                             }
                                         })      ;
                                     }
                                 } else {
                                     Toast(e.toString());
                                 }
                            }
                        })      ;
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    /**
     * 下单
     */
    private ArrayList<ShopCar> mShopOrderss = new ArrayList<>();

    private void MakeOrder(String address) {
        mShopOrderss.clear();
        for (int i = 0; i < mShopOrder.size(); i++) {
            if (mShopOrder.get(i).isSelect()) {
                mShopOrderss.add(mShopOrder.get(i));
            }
        }
        startProgressDialog("加载中...");
        order order = new order();
        order.setUserId(SetUtils.GetId(getApplicationContext()));
        order.setAddress(address);
        order.setShopCar(true);
        order.setgoodTPrice(getAllPrice() + "");
        order.setgoodName(mShopOrderss.get(0).getgoodName() + "..." + "等" + mShopOrderss.size() + "个商品");
        order.setImg1(mShopOrderss.get(0).getImg1());
        order.save(new SaveListener<String>() {
            @Override
            public void done(String order, BmobException e) {
                stopProgressDialog();
                for (int i = 0; i < mShopOrderss.size(); i++) {
                    AddShopCarOrder(order, mShopOrderss.get(i), i, false);
                }
//                BmobQuery<model.order> query = new BmobQuery<>();
//                query.addWhereEqualTo("goodName",mShopOrderss.get(0).getgoodName()+"..."+"等"+mShopOrderss.size()+"个商品");
//                query.addWhereEqualTo("UserId", SetUtils.GetId(getApplicationContext()));
//                query.addWhereEqualTo("goodTPrice",""+getAllPrice());
//                query.addWhereEqualTo("IsShopCar",true);
//                query.addWhereEqualTo("Img1",mShopOrderss.get(0).getImg1());
//                query.findObjects(new FindListener<model.order>() {
//                    @Override
//                    public void done(List<order> list, BmobException e) {
//                        if(e==null){
//                            for (int i=0;i<mShopOrderss.size();i++){
////                                if(mShopOrder.get(i).isSelect()){
////                                    if(i==mShopOrder.size()-1){
////                                        AddShopCarOrder(list.get(0).getObjectId(),mShopOrder.get(i),i,true);
////                                    } else {
//                                        AddShopCarOrder(list.get(0).getObjectId(),mShopOrderss.get(i),i,false);
////                                    }
////                                }
//                            }
//                        }
//                    }
//                });
            }
        });
    }

    /**
     * 删除购物车商品
     *
     * @param pos
     */
    private void DelGoods(int pos) {
        startProgressDialog("加载中...");
        ShopCar car = new ShopCar();
        car.delete(mDate.get(pos).getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                stopProgressDialog();
                if (e == null) {
                    Toast("删除成功");
                    initdate();
                }
            }
        });
    }

    private void initdate() {
        startProgressDialog("加载中...");
        BmobQuery<ShopCar> query = new BmobQuery<>();
        query.addWhereEqualTo("UserId", "" + SetUtils.GetId(getApplicationContext()));
        query.findObjects(new FindListener<ShopCar>() {
            @Override
            public void done(List<ShopCar> list, BmobException e) {
                stopProgressDialog();
                if (e == null) {
                    mDate.clear();
                    mShopOrder.clear();
                    mDate.addAll(list);
//                    adapter=new ShopCarAdapter(getApplicationContext(),mDate);
//                    mList.setAdapter(adapter);
                    adapter.iniGoosNums(mDate);
                    mGoodCheckState = adapter.iniGoodCheckState(mDate);
                    adapter.notifyDataSetChanged();
                    mShopOrder.clear();
                    if (mDate.size() > 0) {
                        for (int i = 0; i < mDate.size(); i++) {
                            ShopCar shopCar = new ShopCar();
                            shopCar.setImg1(mDate.get(i).getImg1());
                            shopCar.setgoodName(mDate.get(i).getgoodName());
                            shopCar.setgoodTPrice(mDate.get(i).getgoodTPrice());
                            shopCar.setSelect(false);
                            shopCar.setBuyNum(0);
                            mShopOrder.add(shopCar);
                        }
                        Log.i("订单详情的初始化-----", "" + mShopOrder.toString());
                    }
                }
            }
        });
    }

    private void initview(View layout) {
        mTvBuy = (TextView) layout.findViewById(R.id.tv_buy);
        mTvAllPrice = (TextView) layout.findViewById(R.id.tv_all_price);
        mList = (ListView) layout.findViewById(R.id.list);
        adapter = new ShopCarAdapter(getApplicationContext(), mDate);
        mList.setAdapter(adapter);
    }

    /**
     * 添加到购物车订单详情
     *
     * @param orderId
     * @param mShopCar
     * @param pos
     */
    public void AddShopCarOrder(String orderId, final ShopCar mShopCar, final int pos, final boolean IsLast) {
        ShopCarOrder order = new ShopCarOrder();
        order.setImg1(mShopCar.getImg1());
//        order.setgoodDetail(mShopCar.getgoodDetail());
        order.setgoodName(mShopCar.getgoodName());
        order.setgoodTPrice("" + Float.parseFloat(mShopCar.getgoodTPrice()));
        order.setGoodNum(mShopCar.getBuyNum());
        order.setOrderId(orderId);
        order.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    if (pos == mShopOrderss.size() - 1) {
//                       for (int i=0;i<mDate.size();i++){
//                           DelShopCarGoods(mDate.get(i),mDate.size());
//                       }
                        Toast("下单成功");
                    }

                }
            }
        });
    }

    public void DelShopCarGoods(ShopCar cars, final int pos) {
        ShopCar car = new ShopCar();
        car.delete(cars.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    if (pos == mDate.size() - 1) {
                        Log.i("删除的第几个", "" + pos);
                        Toast("下单成功");
                    } else {
                        Log.i("删除情况", "删除失败原因" + pos + e.toString());
                    }
                }
            }
        });
    }

    /**
     * 获取总金额
     *
     * @return
     */
    public Float getAllPrice() {
        Float allPrice = 0f;
        ArrayList<Integer> goodNums = adapter.getGoodNums();
        for (int i = 0; i < mDate.size(); i++) {
            if (mGoodCheckState.get(i).isSelect()) {//选择了
                allPrice = allPrice + Float.parseFloat(mDate.get(i).getgoodTPrice()) * (goodNums.get(i));
            } else {

            }
        }
        return allPrice;
    }
}