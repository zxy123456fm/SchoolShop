package model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/4/20/020.
 */

public class praiseScore extends BmobObject {
    private String ShopId;
    private String Score;
    private String ImgUrl;
    private String GoodName;

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getGoodName() {
        return GoodName;
    }

    public void setGoodName(String goodName) {
        GoodName = goodName;
    }
}
