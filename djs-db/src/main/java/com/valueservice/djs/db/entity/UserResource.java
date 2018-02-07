package com.valueservice.djs.db.entity;

import com.valueservice.djs.db.LoginUser;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "t_user_resource")
public class UserResource {

    @Id
    @GeneratedValue
    private Long userResourceId;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<LoginUser> loginUser;

    @OneToMany
    @JoinColumn(name = "resource_id")
    private List<UserResource> userResource;

    private Short active;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Long getUserResourceId() {
        return userResourceId;
    }

    public void setUserResourceId(Long userResourceId) {
        this.userResourceId = userResourceId;
    }

    public List<LoginUser> getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(List<LoginUser> loginUser) {
        this.loginUser = loginUser;
    }

    public List<UserResource> getUserResource() {
        return userResource;
    }

    public void setUserResource(List<UserResource> userResource) {
        this.userResource = userResource;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
