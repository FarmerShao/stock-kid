package com.farmershao.www.stockmember.repository.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * UserPO 会员信息
 *
 * @author Shao Yu
 * @since 2018/9/11 11:16
 **/
@Entity
@Table(name = "stock_user")
public class UserPO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String photo;
    @Column(nullable = false, name = "registry_time")
    private Date registryTime;
    @Column(nullable = false)
    private String mobile;
    @Column(name = "id_card")
    private String idCard;
    @Column
    private String name;

    protected UserPO() {}

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getRegistryTime() {
        return registryTime;
    }

    public void setRegistryTime(Date registryTime) {
        this.registryTime = registryTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
