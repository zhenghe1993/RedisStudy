package com.jmper.mybatis.entity;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2017-12-23 20:16:12)
 */
@Alias("user")
public class User {

    private Integer id;

    private String userName;
    private String password;
    private String nickName;
    private String address;
    private String telephone;
    private BigDecimal income;
    private Date createTime;


    public User() {
    }

    public User(Integer id, String userName, String password, String nickName, String address, String telephone, BigDecimal income, Date createTime) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.address = address;
        this.telephone = telephone;
        this.income = income;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", income=" + income +
                ", createTime=" + createTime +
                '}';
    }
}
