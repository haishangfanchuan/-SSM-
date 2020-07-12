package top.theonecyl.graphicSystem.enums;

public enum UserStateEnum {

    ERROR(-1,"操作失败"),SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"系统错误"),NOT_USER(-1002,"用户信息为空"),UPDATE_PWD_ERROR(-1003,"修改密码信息错误");
    private int state;
    private String stateInfo;

    UserStateEnum() {}

    UserStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    //更具传入的状态码返回相应的enum类型
    public static  UserStateEnum stateOf(int state){
        for(UserStateEnum userStateEnum : UserStateEnum.values()){
            if(userStateEnum.getState()==state){
                return userStateEnum;
            }
        }
        return null;
    }
}
