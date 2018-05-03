package model;

import cn.bmob.v3.BmobObject;

/**
 */

public class order extends BmobObject {
    private String UserId;
    private String goodName;
    private String praise;
    private String goodDetail;
    private String Address;
    private String goodTPrice;
    private String ShopId;
    private String goodTAddress;
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

    public String getgoodTAddress() {
        return goodTAddress;
    }

    public void setgoodTAddress(String goodTAddress) {
        goodTAddress = goodTAddress;
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
