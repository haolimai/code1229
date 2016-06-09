package com.shaiing.code1229.bean;

/**
 * Created by mike on 2016/3/3.
 */
public class HomePageBean {
    private String avatar;
    private String nickname;
    private int pubTime;
    private String picture;
    private String desc;

    public HomePageBean(String avatar, String nickname, int pubTime, String picture, String desc) {
        this.avatar = avatar;
        this.nickname = nickname;
        this.pubTime = pubTime;
        this.picture = picture;
        this.desc = desc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPubTime() {
        return pubTime;
    }

    public void setPubTime(int pubTime) {
        this.pubTime = pubTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
