package top.theonecyl.graphicSystem.entity;

import java.util.Date;
import java.util.List;

/**
 * 图片实体类
 */
public class Image {
    private Integer imageId;
    private String imageName;
    private String imageAddress;
    private Date createTime;
    private Date lastTime;
    private User user;
    private Integer userId;
    private String userName;
    private List<ImageList> imageList;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ImageList> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageList> imageList) {
        this.imageList = imageList;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", imageName='" + imageName + '\'' +
                ", imageAddress='" + imageAddress + '\'' +
                ", createTime=" + createTime +
                ", lastTime=" + lastTime +
                ", user=" + user +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", imageList=" + imageList +
                '}';
    }
}
