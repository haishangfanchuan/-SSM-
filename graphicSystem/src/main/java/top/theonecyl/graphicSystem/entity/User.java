package top.theonecyl.graphicSystem.entity;

import java.util.Date;

/**
 * 用户实体类
 */
public class User {
    private Integer userId;
    private String username;
    private String userLoginName;
    private String userPassword;
    private String userImgAddress;
    private String userPhoneNumber;
    private String userEmail;
    private String userAddress;
    private Date createTime;
    private Date lastTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImgAddress() {
        return userImgAddress;
    }

    public void setUserImgAddress(String userImgAddress) {
        this.userImgAddress = userImgAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userLoginName='" + userLoginName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userImgAddress='" + userImgAddress + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", createTime=" + createTime +
                ", lastTime=" + lastTime +
                '}';
    }
}
