package model;
import cn.bmob.v3.BmobObject;

/**
 */
public class LeaveMessage extends BmobObject {
    private String UserId;
    private String title;
    private String NickName;
    private String content;
    private String img1;
    private String img2;
    private String img3;
//    private String createdAt;
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }


//    @Override
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    @Override
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }

    @Override
    public String toString() {
        return "LeaveMessage{" +
                "UserId='" + UserId + '\'' +
                ", NickName='" + NickName + '\'' +
                ", content='" + content + '\'' +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", img3='" + img3 + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
