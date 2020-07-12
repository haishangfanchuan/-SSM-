package top.theonecyl.graphicSystem.dto;

import top.theonecyl.graphicSystem.entity.Comment;
import top.theonecyl.graphicSystem.enums.CommentStateEnum;

import java.util.List;

public class CommentExecution {
    //返回类型码
    private int state;
    //类型码说明
    private String stateInfo;
    //评论总数
    private int commentCount;
    private Comment comment;
    //评论列表
    private List<Comment> commentList;

    public CommentExecution() {

    }
    public CommentExecution(CommentStateEnum commentStateEnum){
        state=commentStateEnum.getState();
        stateInfo = commentStateEnum.getStateInfo();
    }

    public CommentExecution(CommentStateEnum commentStateEnum,List<Comment> commentList){
        state=commentStateEnum.getState();
        stateInfo = commentStateEnum.getStateInfo();
        this.commentList = commentList;
    }
    public CommentExecution(CommentStateEnum commentStateEnum,Comment comment){
        state=commentStateEnum.getState();
        stateInfo = commentStateEnum.getStateInfo();
        this.comment = comment;
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

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", commentCount=" + commentCount +
                ", comment=" + comment +
                ", commentList=" + commentList +
                '}';
    }
}
