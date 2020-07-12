package top.theonecyl.graphicSystem.dto;

import top.theonecyl.graphicSystem.entity.ImageList;
import top.theonecyl.graphicSystem.enums.ImageListStateEnum;

import java.util.List;

public class ImageListExecution {
    //返回类型码
    private int state;
    //类型码说明
    private String stateInfo;

    private List<ImageList> imageLists;

    public ImageListExecution() {
    }
    public ImageListExecution(ImageListStateEnum imageListStateEnum) {
        state = imageListStateEnum.getState();
        stateInfo = imageListStateEnum.getStateInfo();
    }
    public ImageListExecution(ImageListStateEnum imageListStateEnum,List<ImageList> imageLists) {
        state = imageListStateEnum.getState();
        stateInfo = imageListStateEnum.getStateInfo();
        this.imageLists =imageLists;
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

    public List<ImageList> getImageLists() {
        return imageLists;
    }

    public void setImageLists(List<ImageList> imageLists) {
        this.imageLists = imageLists;
    }

    @Override
    public String toString() {
        return "ImageListExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", imageLists=" + imageLists +
                '}';
    }
}
