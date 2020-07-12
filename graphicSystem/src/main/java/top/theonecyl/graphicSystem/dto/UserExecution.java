package top.theonecyl.graphicSystem.dto;

import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.enums.UserStateEnum;

/**
 * 用户操作返回结果的封装类
 */
public class UserExecution {
    //返回类型码
    private int state;
    //返回值说明
    private String stateInfo;
    private User user;

    public UserExecution(){
    }
    //操作失败时
    public UserExecution(UserStateEnum userStateEnum){
        state= userStateEnum.getState();
        stateInfo = userStateEnum.getStateInfo();
    }
    //操作成功时
    public UserExecution(UserStateEnum userStateEnum,User user){
        state= userStateEnum.getState();
        stateInfo = userStateEnum.getStateInfo();
        this.user=user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", user=" + user +
                '}';
    }
}
