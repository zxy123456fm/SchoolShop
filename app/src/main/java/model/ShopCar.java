package model;
import cn.bmob.v3.BmobObject;
/**
 */
public class ShopCar extends BmobObject {
    private int BuyNum;
    private String UserId;
    private String FoodName;
    private String ShopId;
    private String FoodDetail;
    private String FoodTPrice;
    private String FoodTAddress;
    private int State;
    private String Img1;
    private String Img2;
    private String Img3;
    private boolean IsSelect;

    @Override
    public String toString() {
        return "ShopCar{" +
                "BuyNum=" + BuyNum +
                ", IsSelect=" + IsSelect +
                '}';
    }

    public String getUserId() {
        return UserId;
    }
    public void setUserId(String userId) {
        UserId = userId;
    }
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

    public String getFoodTAddress() {
        return FoodTAddress;
    }

    public void setFoodTAddress(String foodTAddress) {
        FoodTAddress = foodTAddress;
    }

    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }

    public String getImg2() {
        return Img2;
    }

    public void setImg2(String img2) {
        Img2 = img2;
    }

    public String getImg3() {
        return Img3;
    }

    public void setImg3(String img3) {
        Img3 = img3;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getBuyNum() {
        return BuyNum;
    }

    public void setBuyNum(int buyNum) {
        BuyNum = buyNum;
    }


    public boolean isSelect() {
        return IsSelect;
    }

    public void setSelect(boolean select) {
        IsSelect = select;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }
}
