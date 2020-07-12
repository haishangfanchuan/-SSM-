package top.theonecyl.graphicSystem.service;

import org.springframework.stereotype.Service;
import top.theonecyl.graphicSystem.dto.CommentExecution;
import top.theonecyl.graphicSystem.entity.Comment;

public interface ICommentService {

    //添加评论
    CommentExecution addComment(Comment comment);
    //删除评论
    int delComment(int commentId);
    //查询评论列表
    CommentExecution queryCommentList(Comment comment,int rowIndex);
    //查询评论总数
    int queryCommentCount(Comment comment);

}
