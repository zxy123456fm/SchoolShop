package model;
import cn.bmob.v3.BmobObject;
/**
 */
public class ShopCarOrder extends BmobObject {
    private int GoodNum;
    private String OrderId;
    private String FoodName;
    private String FoodDetail;
    private String FoodTPrice;
    private String Img1;
    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodDetail() {
        return FoodDetail;
    }

    public void setFoodDetail(String foodDetail) {
        FoodDetail = foodDetail;
    }

    public String getFoodTPrice() {
        return FoodTPrice;
    }

    public void setFoodTPrice(String foodTPrice) {
        FoodTPrice = foodTPrice;
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
