package top.theonecyl.graphicSystem.enums;

public enum  ImageStateEnum {
    ERROR(-1,"操作错误"),SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"系统错误"),NOT_IMAGE(-1002,"图片信息为空");
    private int state;
    private String stateInfo;

    /**
     * 根据传入的状态码返回相应的enum类型
     * @param state
     * @return
     */
    public static ImageStateEnum stateOf(int state){
        for(ImageStateEnum imageStateEnum : values()){
            if(imageStateEnum.getState()==state){
                return imageStateEnum;
            }
        }
        return null;
    }

    ImageStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
