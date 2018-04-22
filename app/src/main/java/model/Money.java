package model;

import cn.bmob.v3.BmobObject;
/**
 */
public class Money extends BmobObject {
    private String UserId;
    private double Money;
    public String getUserId() {
        return UserId;
    }
    public void setUserId(String userId) {
        UserId = userId;
    }
    public double getMoney() {
        return Money;
    }
    public void setMoney(double money) {
        Money = money;
    }
}
