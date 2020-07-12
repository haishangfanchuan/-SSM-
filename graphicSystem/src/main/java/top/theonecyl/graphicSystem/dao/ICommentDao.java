package top.theonecyl.graphicSystem.dao;

import org.apache.ibatis.annotations.Param;
import top.theonecyl.graphicSystem.entity.Comment;

import java.util.List;

public interface ICommentDao {

    /**
     * 查询评论列表
     * @param commentCondition
     * @return
     */
    List<Comment> queryCommentList(@Param("commentCondition") Comment commentCondition,
                                   @Param("startComment") int startComment,
                                   @Param("pageCount")     int pageCount

    );

    /**
     * 获取评论总数
     * @param commentCondition
     * @return
     */
    int getTotalCommentNum(@Param("commentCondition") Comment commentCondition);

    /**
     * 添加评论
     */
    int addComment(@Param("comment") Comment comment);

    /**
     * 删除评论
     */
    int delComment(@Param("commentId") int commentId);

   /* *//**
     * 查询某个文章的评论数
     * @param articleId
     * @return
     *//*
    int findCommentNumByArticleId(@Param("articleId") int articleId);*/
}
