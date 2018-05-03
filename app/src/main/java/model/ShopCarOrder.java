package model;
import cn.bmob.v3.BmobObject;
/**
 */
public class ShopCarOrder extends BmobObject {
    private int GoodNum;
    private String OrderId;
    private String goodName;
    private String goodDetail;
    private String goodTPrice;
    private String Img1;
    public String getgoodName() {
        return goodName;
    }

    public void setgoodName(String goodName) {
        goodName = goodName;
    }

    public String getgoodDetail() {
        return goodDetail;
    }

    public void setgoodDetail(String goodDetail) {
        goodDetail = goodDetail;
    }

    public String getgoodTPrice() {
        return goodTPrice;
    }

    public void setgoodTPrice(String goodTPrice) {
        goodTPrice = goodTPrice;
    }


    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }


    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public int getGoodNum() {
        return GoodNum;
    }

    public void setGoodNum(int goodNum) {
        GoodNum = goodNum;
    }
}
