package com.Lee.ExchangeGo.UserData;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2015/7/26.
 */
public class Myuser extends BmobUser {
    private String nickname;
    private String address;
    private String qq;
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }




}
