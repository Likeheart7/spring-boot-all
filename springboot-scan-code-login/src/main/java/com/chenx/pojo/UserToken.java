package com.chenx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.Objects;

@TableName("user_token")
public class UserToken {
    private String uuid;
    private Long userId;
    private Date LoginTime;
    private Date createTime;
    private Boolean state;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(Date loginTime) {
        LoginTime = loginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToken userToken = (UserToken) o;
        return Objects.equals(uuid, userToken.uuid) && Objects.equals(userId, userToken.userId) && Objects.equals(LoginTime, userToken.LoginTime) && Objects.equals(createTime, userToken.createTime) && Objects.equals(state, userToken.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, userId, LoginTime, createTime, state);
    }
}
