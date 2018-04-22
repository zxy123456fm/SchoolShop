package model;

import cn.bmob.v3.BmobUser;

/**
 */

public class User extends BmobUser {
    private String Address;
    private int State;
    private String Age;
    private String HeadUrl;
    private String Phone;
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getHeadUrl() {
        return HeadUrl;
    }

    public void setHeadUrl(String headUrl) {
        HeadUrl = headUrl;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
