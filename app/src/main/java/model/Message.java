package model;

import cn.bmob.v3.BmobObject;

/**
 */

public class Message extends BmobObject {
    private String Text;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
