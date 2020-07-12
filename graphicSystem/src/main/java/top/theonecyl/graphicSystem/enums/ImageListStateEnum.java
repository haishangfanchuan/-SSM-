package top.theonecyl.graphicSystem.enums;

public enum ImageListStateEnum {
    ERROR(-1,"操作失败"),SUCCESS(1,"操作成功"),INNER_EEOR(-1001,"系统错误"),NOT_IMAGE_LIST(-1002,"图片列表信息为空");
    private int state;
    private String stateInfo;

    ImageListStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    //更具传入stat返回对应的enum类型
    public static ImageListStateEnum stateOf(int state){
        for(ImageListStateEnum imageListStateEnum:values()){
            if(imageListStateEnum.getState() ==state){
                return imageListStateEnum;
            }
        }
        return null;
    }
}
