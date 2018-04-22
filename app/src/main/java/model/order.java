package model;

import cn.bmob.v3.BmobObject;

/**
 */

public class order extends BmobObject {
    private String UserId;
    private String FoodName;
    private String praise;
    private String FoodDetail;
    private String Address;
    private String FoodTPrice;
    private String ShopId;
    private String FoodTAddress;
    private boolean IsShopCar;
    private int State;
    private String Img1;
    private String Img2;
    private String Img3;
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

    public boolean isShopCar() {
        return IsShopCar;
    }

    public void setShopCar(boolean shopCar) {
        IsShopCar = shopCar;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }
}
