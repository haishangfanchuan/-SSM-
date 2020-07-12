package top.theonecyl.graphicSystem.entity;

import java.util.Date;

/**
 * 图片列表实体类
 */
public class ImageList {
    private Integer imageListId;
    private String imageListAddress;
    private Date createTime;
    private Integer imageId;

    public Integer getImageListId() {
        return imageListId;
    }

    public void setImageListId(Integer imageListId) {
        this.imageListId = imageListId;
    }

    public String getImageListAddress() {
        return imageListAddress;
    }

    public void setImageListAddress(String imageListAddress) {
        this.imageListAddress = imageListAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "ImageList{" +
                "imageListId=" + imageListId +
                ", imageListAddress='" + imageListAddress + '\'' +
                ", createTime=" + createTime +
                ", imageId=" + imageId +
                '}';
    }
}
