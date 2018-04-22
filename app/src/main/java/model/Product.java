package model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 */

public class Product extends BmobObject implements Serializable{
    private String Name;
    private String ShopId;
    private String Detail;
    private boolean IsHomeShow;
    private String Price;
    private String ImgUrl1;
    private String ImgUrl2;
    private String ImgUrl3;
    private String Type;
    private String Address;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImgUrl1() {
        return ImgUrl1;
    }

    public void setImgUrl1(String imgUrl1) {
        ImgUrl1 = imgUrl1;
    }

    public String getImgUrl2() {
        return ImgUrl2;
    }

    public void setImgUrl2(String imgUrl2) {
        ImgUrl2 = imgUrl2;
    }

    public String getImgUrl3() {
        return ImgUrl3;
    }

    public void setImgUrl3(String imgUrl3) {
        ImgUrl3 = imgUrl3;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isHomeShow() {
        return IsHomeShow;
    }

    public void setHomeShow(boolean homeShow) {
        IsHomeShow = homeShow;
    }
}
