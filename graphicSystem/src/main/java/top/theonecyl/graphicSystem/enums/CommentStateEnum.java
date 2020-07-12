package top.theonecyl.graphicSystem.enums;

import top.theonecyl.graphicSystem.entity.Comment;

import java.util.List;

public enum  CommentStateEnum {
    ERROR(-1,"操作错误"),SUCCESS(1,"操作成功"),INNER_ERRER(-1001,"非法评论"),NOT_COMMENT(-1002,"评论信息为空");
    private int state;
    private String stateInfo;


    CommentStateEnum(int state, String stateInfo) {
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
    public static  CommentStateEnum stateOf(int state){
        for(CommentStateEnum commentStateEnum :values()){
            if(commentStateEnum.getState()==state){
                return commentStateEnum;
            }
        }
        return null;
    }
}
