package top.theonecyl.graphicSystem.dto;

import top.theonecyl.graphicSystem.entity.Image;
import top.theonecyl.graphicSystem.enums.ImageStateEnum;

import java.util.List;

public class ImageExecution {
    //返回类型码
    private int state;
    //类型码说明
    private String stateInfo;
    private  List<Image> imageList;
    private Image image;

    public ImageExecution() {
    }

    public ImageExecution(ImageStateEnum imageStateEnum) {
        state = imageStateEnum.getState();
        stateInfo = imageStateEnum.getStateInfo();
    }
    public ImageExecution(ImageStateEnum imageStateEnum,List<Image> imageList) {
        state = imageStateEnum.getState();
        stateInfo = imageStateEnum.getStateInfo();
        this.imageList = imageList;
    }

    public ImageExecution(ImageStateEnum imageStateEnum,Image image) {
        state = imageStateEnum.getState();
        stateInfo = imageStateEnum.getStateInfo();
        this.image = image;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", imageList=" + imageList +
                ", image=" + image +
                '}';
    }
}
