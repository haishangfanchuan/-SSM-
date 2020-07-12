package top.theonecyl.graphicSystem.enums;

import top.theonecyl.graphicSystem.entity.Article;

import java.util.List;

public enum  ArticleStateEnum {
    ERROR(-1,"操作错误"),SUCCESS(1,"操作成功"),INNER_ERRER(-1001,"非法文章"),NOT_ARTICLE(-1002,"文章信息为空");
    private int state;
    private String stateInfo;


    ArticleStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }


    public static  ArticleStateEnum stateOf(int state){
        for(ArticleStateEnum articleStateEnum : values()){
            if(articleStateEnum.getState()==state){
                return articleStateEnum;
            }
        }
        return null;
    }
}
