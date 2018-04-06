package com.my.redis.model;

import java.io.Serializable;
import java.util.Objects;

public class UserInfo implements Serializable {


    private static final long serialVersionUID = -7523824676418487564L;

    private String tel;
    private String nickname;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String tel, String nickname, String password) {
        this.tel = tel;
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "tel='" + tel + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(tel, userInfo.tel) &&
                Objects.equals(nickname, userInfo.nickname) &&
                Objects.equals(password, userInfo.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tel, nickname, password);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
